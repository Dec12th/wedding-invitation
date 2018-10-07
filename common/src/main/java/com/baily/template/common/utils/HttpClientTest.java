package com.baily.template.common.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.ServiceUnavailableRetryStrategy;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.RequestWrapper;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * 类: HttpClient <br>
 * 描述: httpclient工具类 <br>
 * 作者: song<br>
 * 时间: 2017年7月21日 下午3:15:27
 */
public class HttpClientTest {

    static CloseableHttpClient client;

    public static final int TIME_OUT = 10;


    static {

        client = HttpClients.custom().setRetryHandler(new CustomHttpRequestRetryHandler()).setServiceUnavailableRetryStrategy(new CustomServiceUnavailableRetryStrategy(3, 1000)).setDefaultRequestConfig(RequestConfig.custom().setConnectionRequestTimeout(TIME_OUT)
                .setConnectTimeout(TIME_OUT)
                .setSocketTimeout(TIME_OUT)
                .build()).build();
    }

    /**
     * 自定义根据请求异常进行重试处理器
     */
    static class CustomHttpRequestRetryHandler implements HttpRequestRetryHandler {

        /**
         * the number of times a method will be retried
         */
        private final int retryCount;

        public CustomHttpRequestRetryHandler(int retryCount) {
            this.retryCount = retryCount;
        }

        public CustomHttpRequestRetryHandler() {
            this(3);
        }

        /**
         * Determines if a method should be retried after an IOException
         * occurs during execution.
         *
         * @param exception      the exception that occurred
         * @param executionCount the number of times this method has been
         *                       unsuccessfully executed
         * @param context        the context for the request execution
         * @return {@code true} if the method should be retried, {@code false}
         * otherwise
         */
        @Override
        public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
            Args.notNull(exception, "Exception parameter");
            Args.notNull(context, "HTTP context");
            if (executionCount > this.retryCount) {
                // Do not retry if over max retry count
                return false;
            }
            final HttpClientContext clientContext = HttpClientContext.adapt(context);
            final HttpRequest request = clientContext.getRequest();

            if (requestIsAborted(request)) {
                return false;
            }

            if (handleAsIdempotent(request)) {
                // Retry if the request is considered idempotent
                return true;
            }

            if (!clientContext.isRequestSent()) {
                // Retry if the request has not been sent fully or
                // if it's OK to retry methods that have been sent
                return true;
            }
            // otherwise do not retry
            return false;
        }

        protected boolean requestIsAborted(final HttpRequest request) {
            HttpRequest req = request;
            if (request instanceof RequestWrapper) { // does not forward request to original
                req = ((RequestWrapper) request).getOriginal();
            }
            return (req instanceof HttpUriRequest && ((HttpUriRequest) req).isAborted());
        }

        protected boolean handleAsIdempotent(final HttpRequest request) {
            return !(request instanceof HttpEntityEnclosingRequest);
        }

    }


    /**
     * 自定义根据返回码结果重试策略
     */
    static class CustomServiceUnavailableRetryStrategy implements ServiceUnavailableRetryStrategy {

        private final int maxRetries;

        private final long retryInterval;

        public CustomServiceUnavailableRetryStrategy(int maxRetries, int retryInterval) {
            super();
            Args.positive(maxRetries, "Max retries");
            Args.positive(retryInterval, "Retry interval");
            this.maxRetries = maxRetries;
            this.retryInterval = retryInterval;
        }

        public CustomServiceUnavailableRetryStrategy() {
            this(1, 1000);
        }

        /**
         * Determines if a method should be retried given the response from the target server.
         *
         * @param response       the response from the target server
         * @param executionCount the number of times this method has been
         *                       unsuccessfully executed
         * @param context        the context for the request execution
         * @return {@code true} if the method should be retried, {@code false}
         * otherwise
         */
        @Override
        public boolean retryRequest(org.apache.http.HttpResponse response, int executionCount, HttpContext context) {
            return executionCount <= this.maxRetries &&
                    response.getStatusLine().getStatusCode() != HttpStatus.OK.value();
        }

        /**
         * @return The interval between the subsequent auto-retries.
         */
        @Override
        public long getRetryInterval() {
            return retryInterval;
        }
    }

    /**
     * 方法: get <br>
     * 描述: get请求 <br>
     * 作者: Teacher song<br>
     * 时间: 2017年7月21日 下午3:15:25
     *
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public static String get(String url, HashMap<String, Object> params) {
        try {
            HttpGet httpGet = new HttpGet();
            Set<String> keySet = params.keySet();
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(url).append("?t=").append(System.currentTimeMillis());
            for (String key : keySet) {
                stringBuffer.append("&").append(key).append("=").append(params.get(key));
            }
            httpGet.setURI(new URI(stringBuffer.toString()));
            CloseableHttpResponse execute = client.execute(httpGet);
            int statusCode = execute.getStatusLine().getStatusCode();
            if (200 != statusCode) {
                return "";
            }
            return EntityUtils.toString(execute.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 方法: post <br>
     * 描述: post请求 <br>
     * 作者: Teacher song<br>
     * 时间: 2017年7月21日 下午3:20:31
     *
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public static String post(String url, HashMap<String, Object> params) {
        try {
            HttpPost httpPost = new HttpPost();
            httpPost.setURI(new URI(url));
            List<NameValuePair> parameters = new ArrayList<>();
            Set<String> keySet = params.keySet();
            for (String key : keySet) {
                NameValuePair e = new BasicNameValuePair(key, params.get(key).toString());
                parameters.add(e);
            }
            HttpEntity entity = new UrlEncodedFormEntity(parameters, "utf-8");
            httpPost.setEntity(entity);
            CloseableHttpResponse execute = client.execute(httpPost);
            int statusCode = execute.getStatusLine().getStatusCode();
            if (200 != statusCode) {
                return "";
            }
            return EntityUtils.toString(execute.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {


        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("city", "北京");
        String string = HttpClientTest.get("https://www.baidu.com", params);
        System.out.println(string);
    }
}
