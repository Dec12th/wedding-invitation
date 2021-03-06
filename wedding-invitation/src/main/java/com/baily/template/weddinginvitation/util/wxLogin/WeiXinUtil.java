package com.baily.template.weddinginvitation.util.wxLogin;

import java.util.Map;

public class WeiXinUtil {

	public final static String access_token_url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	public final static String oauth2_1_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	public final static String oauth2_2_url = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	public final static String get_userInfo_url="https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
	public final static String get_hangye_url = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=ACCESS_TOKEN";
	//上面那五个，不用改。把下边的appid和appsecret改了就行
	public final static String appid="wxc028256ea6d51af1";//wx58612798d159188
	public final static String appSecret="3a409b892ebe6fe6ae4d56c81768f8dd";//3e62412621a12621aa061b53437e89664
	
	
	
	public static AccessToken getAccessToken(String appid, String appSecret) {
		//替换真实appid和appsecret
		String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appSecret);
		AccessToken accesstoken=new AccessToken();
		//得到json对象
		Map<String,Object> jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
		
		//将得到的json对象的属性值，存到accesstoken中
		accesstoken.setToken((String) jsonObject.get("access_token"));
		accesstoken.setExpiresIn((Integer) jsonObject.get("expires_in"));
		return accesstoken;
	} 
	
/**
 * 网页授权认证	
 * @param appId
 * @param appSecret
 * @param code
 * @return
 */
	public static WeixinOauth2Token getOauth2AccessToken(String appId,String appSecret,String code) {
		
		String  requestUrl=oauth2_1_url.replace("APPID", appId).replace("SECRET", appSecret).replace("CODE", code);
		//发送请求获取网页授权凭证
		Map<String,Object> jsonObject = CommonUtil.httpsRequest(requestUrl, EnumMethod.GET.name(), null);
		WeixinOauth2Token   wxo=new WeixinOauth2Token();
		wxo.setAccessToken((String) jsonObject.get("access_token"));
		wxo.setExpiresIn((Integer) jsonObject.get("expires_in"));
		wxo.setRefreshToken((String) jsonObject.get("refresh_token"));
		wxo.setOpenId((String) jsonObject.get("openid"));
		wxo.setScope((String) jsonObject.get("scope"));
		
		return wxo;
		
	}
	/**
	 * 获取用户的基本信息
	 * @param accessToken
	 * @param openId
	 * @return
	 */
	public static SNSUserInfo getSNSUserInfo(String accessToken,String openId) {
		String requestUrl=oauth2_2_url.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
		SNSUserInfo snsuserinfo=new SNSUserInfo();
		//通过网页授权获取用户信息
		Map<String,Object> jsonObject=CommonUtil.httpsRequest(requestUrl, EnumMethod.GET.name(), null);
		
		snsuserinfo.setOpenId((String) jsonObject.get("openid"));
		snsuserinfo.setNickname((String) jsonObject.get("nickname"));
		snsuserinfo.setSex((Integer) jsonObject.get("sex"));
		snsuserinfo.setCountry((String) jsonObject.get("country"));
		snsuserinfo.setProvince((String) jsonObject.get("province"));
		snsuserinfo.setCity((String) jsonObject.get("city"));
		snsuserinfo.setHeadImgUrl((String) jsonObject.get("headimgurl"));
		//snsuserinfo.setPrivilegeList(JSONArray._fromArray(jsonObject.getString("privilege"),String.class));
		return snsuserinfo; 
	}
	
	/**
	 * 创建网页授权的url
	 * @param redirectUri
	 * @return
	 */
	public static String createUrl(String redirectUri) {
		
		String url =get_userInfo_url.replace("APPID", appid).replace("REDIRECT_URI", CommonUtil.urlEncodeUTF8(redirectUri)).replace("SCOPE", "snsapi_userinfo");
		System.out.println(url);
		return url;
	}	
	
	/**
	 * 长连接转化成短链接，提高扫码速度跟成功率
	 * @param args
	 */
	
	public static String  shortURL(String longURL, String wxAppId, String secret) {
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/shorturl?access_token=ACCESS_TOKEN";
        try {
        	//将更新后的access_token,替换上去
			requestUrl = requestUrl.replace("ACCESS_TOKEN",getAccessToken(wxAppId, secret).getToken());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String jsonMsg = "{\"action\":\"long2short\",\"long_url\":\"%s\"}";
        //格式化url
        String.format(jsonMsg, longURL);
		Map<String,Object> jsonobject = CommonUtil.httpsRequest(requestUrl, "POST",String.format(jsonMsg, longURL));
        //转换成短连接成功
        return (String) jsonobject.get("short_url");
    }

	
	public static void main(String[] args) {
		AccessToken a =getAccessToken(appid, appSecret);
		//String s= shortURL(sss, appid, appSecret);
		//System.out.println(s);
		System.out.println(a.getToken());
		System.out.println(a.getExpiresIn());
	}
	
}
