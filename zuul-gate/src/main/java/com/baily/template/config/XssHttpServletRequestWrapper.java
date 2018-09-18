package com.baily.template.config;

import org.apache.commons.lang.StringUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/** 
 * <code>{@link XssHttpServletRequestWrapper}</code> 
 */  
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
    HttpServletRequest orgRequest = null;
    private boolean isIncludeRichText = false;
  
    public XssHttpServletRequestWrapper(HttpServletRequest request, boolean isIncludeRichText) {
        super(request);  
        orgRequest = request;
        this.isIncludeRichText = isIncludeRichText;
    }  
  
    /** 
    * 覆盖getParameter方法，将参数名和参数值都做xss过滤。<br/> 
    * 如果需要获得原始的值，则通过super.getParameterValues(name)来获取<br/> 
    * getParameterNames,getParameterValues和getParameterMap也可能需要覆盖 
    */  
    @Override  
    public String getParameter(String name) {  
	    	if(("content".equals(name) || name.endsWith("WithHtml")) && !isIncludeRichText){
	    		return super.getParameter(name);
	    	}
	    	name = JsoupUtil.clean(name);
        String value = super.getParameter(name);  
        if (StringUtils.isNotBlank(value)) {
            value = JsoupUtil.clean(value);  
        }
        return value;
    }  
    
    @Override
    public String[] getParameterValues(String name) {
    	String[] arr = super.getParameterValues(name);
    	if(arr != null){
    		for (int i=0;i<arr.length;i++) {
    			arr[i] = JsoupUtil.clean(arr[i]);
    		}
    	}
    	return arr;
    }
    
  
    /** 
    * 覆盖getHeader方法，将参数名和参数值都做xss过滤。<br/> 
    * 如果需要获得原始的值，则通过super.getHeaders(name)来获取<br/> 
    * getHeaderNames 也可能需要覆盖 
    */  
    @Override  
    public String getHeader(String name) {  
    		name = JsoupUtil.clean(name);
        String value = super.getHeader(name);  
        if (StringUtils.isNotBlank(value)) {  
            value = JsoupUtil.clean(value); 
        }  
        return value;  
    }  
  
    /** 
    * 获取最原始的request 
    * 
    * @return 
    */  
    public HttpServletRequest getOrgRequest() {
        return orgRequest;  
    }  
  
    /** 
    * 获取最原始的request的静态方法 
    * 
    * @return 
    */  
    public static HttpServletRequest getOrgRequest(HttpServletRequest req) {
        if (req instanceof XssHttpServletRequestWrapper) {  
            return ((XssHttpServletRequestWrapper) req).getOrgRequest();  
        }  
  
        return req;  
    }

    @Override
    public BufferedReader getReader() throws IOException {
        System.out.println("测试测试123456：getReader");
        BufferedReader result = super.getReader();
        StringBuffer buffer = new StringBuffer();
        String line = " ";
        while ((line = result.readLine()) != null){
            buffer.append(line);
        }
        String resultStr = buffer.toString();
        resultStr = JsoupUtil.clean(resultStr);
        result = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(resultStr.getBytes())));
        return result;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        System.out.println("测试测试123456：getInputStream");
        ServletInputStream result = super.getInputStream();
        StringBuilder content = new StringBuilder();
        byte[] b = new byte[1024];
        int lens = -1;
        while ((lens = result.read(b)) > 0) {
            content.append(new String(b, 0, lens));
        }
        //原始请求内容
        String resultStr = content.toString();
        //处理后内容
        resultStr = JsoupUtil.clean(resultStr);
        final byte[] myBytes = resultStr.getBytes("UTF-8");
        ServletInputStream newResult = new ServletInputStream() {
            private int lastIndexRetrieved = -1;
            private ReadListener readListener = null;

            @Override
            public int read() throws IOException {
                int i;
                if (!isFinished()) {
                    i = myBytes[lastIndexRetrieved+1];
                    lastIndexRetrieved++;
                    if (isFinished() && (readListener != null)) {
                        try {
                            readListener.onAllDataRead();
                        } catch (IOException ex) {
                            readListener.onError(ex);
                            throw ex;
                        }
                    }
                    return i;
                } else {
                    return -1;
                }
            }

            @Override
            public boolean isFinished() {
                return (lastIndexRetrieved == myBytes.length-1);
            }

            @Override
            public boolean isReady() {
                return isFinished();
            }

            @Override
            public void setReadListener(ReadListener readListener) {
                this.readListener = readListener;
                if (!isFinished()) {
                    try {
                        readListener.onDataAvailable();
                    } catch (IOException e) {
                        readListener.onError(e);
                    }
                } else {
                    try {
                        readListener.onAllDataRead();
                    } catch (IOException e) {
                        readListener.onError(e);
                    }
                }
            }
        };
        return newResult;
    }
}
