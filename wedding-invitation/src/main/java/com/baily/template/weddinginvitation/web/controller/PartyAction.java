package com.baily.template.weddinginvitation.web.controller;

import com.baily.template.weddinginvitation.domain.IUserService;
import com.baily.template.weddinginvitation.domain.MobileService;
import com.baily.template.weddinginvitation.mobile.model.ResponseBean;
import com.baily.template.weddinginvitation.mobile.model.UserImageBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 中文参数接收方式:URLDecoder.decode(request.getParameter("body"), "UTF-8");
 * <p>
 * 后台管理对接
 */

@Controller
@RequestMapping("/")
public class PartyAction {

    @Autowired
   private MobileService mobileService;

    @Autowired
    private IUserService userService;



    // http://localhost:8080/party//marry
    @RequestMapping(value = "/marry", method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response) {
        List list = (List) userService.getUsers();
        request.setAttribute("list", list);
        System.out.println(list.toString());
        return "statistic/statisticIndex";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String saveBanner(HttpServletRequest request,
                             HttpServletResponse response) throws IOException {
        ResponseBean resBean = new ResponseBean();
        Gson gson = new Gson();
        String data = request.getParameter("data");
        if (null == data) {
            resBean.setMessage("mehod_error");
            return gson.toJson(resBean);
        }

        List<UserImageBean> lists = new Gson().fromJson(data,
                new TypeToken<List<UserImageBean>>() {
                }.getType());
        String path = System.getProperty("catalina.home") + "/wechat/"
                + "/userImage/";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        for (int i = 0; i < lists.size(); i++) {
            UserImageBean imgBean = lists.get(i);
            String name = imgBean.getName();
            String base64 = imgBean.getBase64();
            Base64ToImage(base64, path + name);
        }
        resBean.setMessage("save_success");
        return gson.toJson(resBean);
    }

    /**
     * @描述 java生成流水号 14位时间戳 + 6位随机数
     * @作者 shaomy
     * @时间:2017-1-12 上午10:10:41
     * @参数:@return
     * @返回值：String
     */
    public static String getId() {
        String id = "";
        // 获取当前时间戳
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        String temp = sf.format(new Date());
        // 获取6位随机数
        int random = (int) ((Math.random() + 1) * 100000);
        id = temp + random;
        return id;
    }

    /**
     * base64字符串转换成图片
     *
     * @param imgStr      base64字符串
     * @param imgFilePath 图片存放路径
     * @return
     * @author ZHANGJL
     * @dateTime 2018-02-23 14:42:17
     */
    public static boolean Base64ToImage(String imgStr, String imgFilePath) { // 对字节数组字符串进行Base64解码并生成图片

        if (null == imgStr || "".equals(imgStr)) // 图像数据为空
            return false;

        String[] imaStrs = imgStr.split("base64,");

        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(imaStrs[1]);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }

            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();

            return true;
        } catch (Exception e) {
            return false;
        }

    }

}
