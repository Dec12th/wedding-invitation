//package com.baily.template.weddinginvitation.mobile.ctrl;
//
//import com.baily.template.weddinginvitation.mobile.model.BlessComment;
//import com.baily.template.weddinginvitation.mobile.model.BlessUser;
//import com.baily.template.weddinginvitation.mobile.model.HttpHeaderInfoBean;
//import com.baily.template.weddinginvitation.mobile.model.ImageList;
//import com.baily.template.weddinginvitation.mobile.service.MobileService;
//import com.baily.template.weddinginvitation.mobile.util.HttpHeaderUtils;
//import com.baily.template.weddinginvitation.user.model.User;
//import com.baily.template.weddinginvitation.user.model.UserInfo;
//import com.baily.template.weddinginvitation.user.model.UserRecord;
//import com.baily.template.weddinginvitation.user.service.UserService;
//import com.baily.template.weddinginvitation.util.GsonUtil;
//import com.baily.template.weddinginvitation.util.HttpRequestor;
//import com.google.gson.Gson;
//import com.google.gson.JsonArray;
//import org.springframework.stereotype.Controller;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.annotation.Resource;
//import javax.imageio.ImageIO;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
///**
// * 中文参数接收方式:URLDecoder.decode(request.getParameter("body"), "UTF-8");
// * <p>
// * 微信小程序对接
// */
//
//@Controller
//@RequestMapping("/mobile")
//public class MobileController {
//
//    private final String SAVE_USER = "SAVE_USER";// 用户注册
//    private final String GET_OPENID = "GET_OPENID";// 支付申请订单
//    private final String LOGIN_IN = "LOGIN_IN";// 登录
//    private final String GET_IMAGE = "GET_IMAGE";// 获取图片
//    private final String GET_PRAISE = "GET_PRAISE";// 获取赞列表
//    private final String SAVE_PRAISE = "SAVE_PRAISE";// 保存赞
//    private final String GET_COMMENT = "GET_COMMENT";// 获取评论列表
//    private final String SAVE_COMMENT = "SAVE_COMMENT";// 保存评论
//
//
//    private JsonArray jsonArray = new JsonArray();
//    @Resource
//    private MobileService mobileService;
////    @Resource
////    private UserService userService;
//
//
//    public MobileService getMobileService() {
//        return mobileService;
//    }
//
//    public void setMobileService(MobileService mobileService) {
//        this.mobileService = mobileService;
//    }
//
//    public JsonArray getJsonArray() {
//        return jsonArray;
//    }
//
//    public void setJsonArray(JsonArray jsonArray) {
//        this.jsonArray = jsonArray;
//    }
//
////    public UserService getUserService() {
////        return userService;
////    }
////
////    public void setUserService(UserService userService) {
////        this.userService = userService;
////    }
//
//    /**
//     * 接口方法总入口
//     *
//     * @return
//     * @throws Exception
//     * @author wangsong
//     */
//    //http://localhost:8080/party//mobile/mobileIn.do
//    //http://localhost:8080/party/wechat/image/arrow_chart.png
//    @RequestMapping("mobileIn")
//    public String mobileIn(HttpServletRequest request,
//                           HttpServletResponse response) throws Exception {
//        response.setContentType("application/json; charset=UTF-8");
////		request.setCharacterEncoding("application/json; charset=UTF-8");
//        HttpHeaderInfoBean headerInfoBean = HttpHeaderUtils
//                .getHeaderInfos(request);
//        if (StringUtils.isEmpty(headerInfoBean.getMethod())) {
//            headerInfoBean = HttpHeaderUtils.getHeaderInfosTest(request);
//        }
//        String method = headerInfoBean.getMethod();
//        String json = "";
//        try {
//            request.setCharacterEncoding("UTF-8");
//            if (SAVE_USER.equals(method)) {
//                // 用户注册接口
//                json = saveUser(request, response);
//            } else if (GET_OPENID.equals(method)) {
//                // 获取openiD接口
//                json = getOpenID(request, response);
//            } else if (LOGIN_IN.equals(method)) {
//                // 用户登录方法
//                json = loginin(request, response);
//            } else if (GET_IMAGE.equals(method)) {
//                //  获取图片
//                json = getImages(request, response);
//            } else if (GET_PRAISE.equals(method)) {
//                //  获取赞列表
//                json = getPraiseList(request, response);
//            } else if (SAVE_PRAISE.equals(method)) {
//                // 点赞
//                json = savePraise(request, response);
//            } else if (SAVE_COMMENT.equals(method)) {
//                // 保存评论
//                json = saveComment(request, response);
//            } else if (GET_COMMENT.equals(method)) {
//                // 获取评论列表
//                json = getCommentList(request, response);
//            } else {
//                json = "测试";
//            }
//
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        try {
//            response.getWriter().write(json);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//
//    /**
//     * 保存赞
//     *
//     * @param request
//     * @param response
//     * @return
//     */
//    private String savePraise(HttpServletRequest request,
//                              HttpServletResponse response) {
//        String nickName = request.getParameter("nickName");
//        String nickImage = request.getParameter("nickImage");
//        String openId = request.getParameter("openId");
//        try {
//            List obj = null;
//            if (null != openId && !"".equals(openId)) {
////                obj = mobileService.getBlessUserByOpenId(openId);
//            } else {
////                obj = mobileService.getBlessUserByNickImage(nickImage);
//            }
//            if (null != obj && obj.size() > 0) {
//                return "你已经点过赞了";
//            }
//            BlessUser blessUser = new BlessUser();
//            blessUser.setNick_image(nickImage + "");
//            blessUser.setNick_name(nickName + "");
//            blessUser.setCreate_time(System.currentTimeMillis() + "");
//            blessUser.setId(System.currentTimeMillis() + "");
//            blessUser.setOpen_id(openId);
////            mobileService.save(blessUser);
//            return "点赞成功";
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "点赞失败";
//    }
//
//    /**
//     * 保存评论
//     *
//     * @param request
//     * @param response
//     * @return
//     */
//    private String saveComment(HttpServletRequest request,
//                               HttpServletResponse response) {
//
//        String nickName = request.getParameter("nickName");
//        String nickImage = request.getParameter("nickImage");
//        String comment = request.getParameter("comment");
//        String time = request.getParameter("time");
//        String openId = request.getParameter("openId");
//
//        try {
//            BlessComment blessComment = new BlessComment();
//            blessComment.setNick_image(nickImage + "");
//            blessComment.setNick_name(nickName + "");
//            blessComment.setComment(comment + "");
//            SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH点mm分");
//            blessComment.setCreate_time(df.format(new Date()));
//            blessComment.setId(System.currentTimeMillis() + "");
////            mobileService.save(blessComment);
//            return "评论成功";
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "评论失败";
//    }
//
//    /**
//     * 获取赞列表
//     *
//     * @param request
//     * @param response
//     * @return
//     */
//    private String getPraiseList(HttpServletRequest request,
//                                 HttpServletResponse response) {
//        List object;
//        try {
////            object = mobileService.getAllBlessUser();
//            return new Gson().toJson(object);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "";
//
//    }
//
//    /**
//     * 获取评论列表
//     *
//     * @param request
//     * @param response
//     * @return
//     */
//    private String getCommentList(HttpServletRequest request,
//                                  HttpServletResponse response) {
//        List object;
//        try {
////            object = mobileService.getAllBlessComment();
//            return new Gson().toJson(object);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "";
//    }
//
//    /**
//     * 获取图片
//     *
//     * @param request
//     * @param response
//     * @return
//     */
//    public String getImages(HttpServletRequest request,
//                            HttpServletResponse response) {
//
//        String type = request.getParameter("homeType");//banner detail
//        String moduleId = request.getParameter("moduleId");
//
//        List<ImageList> imageList = new ArrayList<ImageList>();
//        String baseUrl = "";
//        try {
//
//            String path = "";
//            if ("banner".equals(type)) {
//                path = request.getSession().getServletContext().getRealPath("/wechat") + "/marry/banner/";
//                baseUrl = "https://pengmaster.com/party/wechat/marry/banner/";
//            } else {
//                path = request.getSession().getServletContext().getRealPath("/wechat") + "/marry/" + moduleId + "/";
//                baseUrl = "https://pengmaster.com/party/wechat" + "/marry/" + moduleId + "/";
//            }
//            System.out.println("path:" + path);
//            File file = new File(path);
//            File[] tempList = file.listFiles();
//            if (null == tempList) {
//                return "数据为空";
//            }
//            for (int i = 0; i < tempList.length; i++) {
//                String name = tempList[i].getName();
//                String orientation = "";
//                File picture = new File(path + name);
//                BufferedImage sourceImg = ImageIO.read(new FileInputStream(picture));
//                if (sourceImg.getWidth() > sourceImg.getHeight()) {
//                    orientation = "horizontal";
//                } else {
//                    orientation = "vertical";
//                }
//                String nameType = "";
//                if (null != name) {
//                    nameType = name.substring(0, name.indexOf("."));
//                }
//                imageList.add(new ImageList(com.baily.template.weddinginvitation.util.StringUtils.generateRefID()
//                        , baseUrl + name, nameType, orientation));
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return new Gson().toJson(imageList);
//    }
//
//
//    /**
//     * 用户注册
//     *
//     * @return
//     * @author wp
//     */
//    public String saveUser(HttpServletRequest request,
//                           HttpServletResponse response) {
//
//        String openId = request.getParameter("openId");
//        String userInfos = request.getParameter("userInfo");
//        Gson gson = new Gson();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化到秒
//        try {
//            //用户操作记录
//            UserRecord userRecord = new UserRecord();
//            userRecord.setId(getId());
//            userRecord.setOpenId(openId);
//            UserInfo userInfo = gson.fromJson(userInfos, UserInfo.class);
//            userRecord.setAvatarUrl(userInfo.getAvatarUrl());
//            userRecord.setCity(userInfo.getCity());
//            userRecord.setNickName(userInfo.getNickName());
//            userRecord.setProvince(userInfo.getProvince());
//            userRecord.setCreateTime(formatter.format(new Date()));
////            userService.saveUserRecord(userRecord);
//
//            if (null != openId && !"".equals(openId)) {
////                Object userOlderList = userService.getUserById(openId);
//                if (null != userOlderList) {
//                    List<Object> list = (List<Object>) userOlderList;
//                    if (list.size() > 0 && null != list.get(0) && list.get(0) instanceof Object[]) {
//                        Object[] listResult = (Object[]) list.get(0);
//                        User user = new User();
//                        user.setId(listResult[0] + "");
//                        user.setOpenId(listResult[1] + "");
//                        user.setAvatarUrl(listResult[2] + "");
//                        user.setCity(listResult[3] + "");
//                        user.setNickName(listResult[4] + "");
//                        user.setProvince(listResult[5] + "");
//                        user.setCreateTime(listResult[6] + "");
//                        user.setUpdateTime(formatter.format(new Date()));
////                        userService.updateUser(user);
//                        System.out.println("--------------update_user_success------------");
//                        return "更新成功";
//                    }
//                }
//            }
//            User user = new User();
//            user.setId(getId());
//            user.setOpenId(openId);
//            user.setAvatarUrl(userInfo.getAvatarUrl());
//            user.setCity(userInfo.getCity());
//            user.setNickName(userInfo.getNickName());
//            user.setProvince(userInfo.getProvince());
//            user.setCreateTime(formatter.format(new Date()));
////            userService.saveUser(user);
//            System.out.println("--------------save_user_success------------");
//            return "保存成功";
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "保存失败";
//        }
//    }
//
//    /**
//     * 用户登录
//     *
//     * @param request
//     * @param response
//     * @return
//     */
//    public String loginin(HttpServletRequest request,
//                          HttpServletResponse response) {
//        String openId = request.getParameter("openId");
//        User user = userService.getUserByOpenId(openId);
//        Map<String, Object> dataMap = new HashMap<>();
//        if (!StringUtils.isEmpty(user)) {
//            dataMap.put("result", "该用户已关联！");
//            dataMap.put("success", "200");
//            dataMap.put("user", user);
//        } else {
//            dataMap.put("result", "请关联用户！");
//            dataMap.put("success", "202");
//        }
//        return new Gson().toJson(dataMap);
//    }
//
//    /**
//     * 解绑微信
//     *
//     * @param request
//     * @param response
//     * @return
//     */
//    public String unbundling(HttpServletRequest request,
//                             HttpServletResponse response) {
//        String idCard = request.getParameter("idCard");
//        User user = userService.getUserByuserIdCard(idCard);
//        Map<String, Object> dataMap = new HashMap<>();
//        if (user != null) {
//            if (!StringUtils.isEmpty(user.getOpenId())) {
//                user.setOpenId("");
//                userService.updateUser(user);
//                dataMap.put("result", "解绑成功！");
//                dataMap.put("success", "300");
//            } else {
//                dataMap.put("result", "请先关联用户！");
//                dataMap.put("success", "301");
//            }
//        } else {
//            dataMap.put("result", "身份证号码输入错误！");
//            dataMap.put("success", "302");
//        }
//        return new Gson().toJson(dataMap);
//    }
//
//
//    /**
//     * 获取openid
//     *
//     * @param request
//     * @param response
//     * @return
//     */
//    protected String getOpenID(HttpServletRequest request,
//                               HttpServletResponse response) {
//        Map<String, Object> dataMap = new HashMap<>();
//        try {
//            String code = request.getParameter("code");
//            String appid = request.getParameter("appid");
//            String secret = request.getParameter("secret");
//            String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
//                    + appid
//                    + "&secret="
//                    + secret
//                    + "&code="
//                    + code
//                    + "&grant_type=authorization_code";
//            // 第一次请求 获取access_token 和 openid
//            String oppid;
//            oppid = new HttpRequestor().doGet(requestUrl);
//            Gson gson = new Gson();
//            // String access_token = (String) oppidObj.get("access_token");
//            Map<String, Object> openidMap = GsonUtil.fromJson(oppid, Map.class);
//            String openid = (String) openidMap.get("openid");
//            if (openid != null && !"".equals(openid)) {
////				User user = userService.getUserByOpenId(openid);
////				if (user == null) {
////					JsonObject.put("flag", false);
////					JsonObject.put("openid", openid);
////				} else {
////					JsonObject.put("flag", true);
////					JsonObject.put("openid", openid);
////					JsonObject.put("user", user);
////				}
//                dataMap.put("flag", true);
//                dataMap.put("openid", openid);
//            } else {
//                dataMap.put("flag", false);
//                dataMap.put("openid", "");
//                dataMap.put("message", "获取openID失败！请重试！");
//            }
//            return GsonUtil.toJson(dataMap);
//        } catch (Exception e) {
//            dataMap.put("flag", false);
//            dataMap.put("message", "服务器异常");
//            e.printStackTrace();//TODO log
//            return GsonUtil.toJson(dataMap);
//        }
//    }
//
//    /**
//     * @描述 java生成流水号
//     * 14位时间戳 + 6位随机数
//     * @作者 shaomy
//     * @时间:2017-1-12 上午10:10:41
//     * @参数:@return
//     * @返回值：String
//     */
//    public static String getId() {
//        String id = "";
//        //获取当前时间戳
//        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
//        String temp = sf.format(new Date());
//        //获取6位随机数
//        int random = (int) ((Math.random() + 1) * 100000);
//        id = temp + random;
//        return id;
//    }
//}
//
//
//
//
//
//
//
//
