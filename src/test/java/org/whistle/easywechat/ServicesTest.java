package org.whistle.easywechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.whistle.easywechat.bean.News;
import org.whistle.easywechat.consts.SendType;
import org.whistle.easywechat.service.*;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * test get accesstoken
 *
 * @author Gentvel
 * @version 1.0.0
 */
@Slf4j
@SpringBootTest(classes = EasyWechatApplicationTest.class)
@DisplayName("测试素材服务")
public class ServicesTest implements ResourceLoaderAware {
    private String appid = "wx32c2aac964d2e892-1";
    private String secret = "6fccb15a4b1f3687674711186e644d68";
    //临时素材
    private String accessToken="47_G94wIjZ7C8JhyWu6ZsPLe77B6Do5zXh3UR9d-53aeQmqUsku7vYfDPnvmcWIu2Dk_10o6Dpk9LYJEZke_fPY43i2Tm0VB0ieTg3aNY0pIIqbijXUFjmfYx4yBiDFuvZOfcEOxYmZ92WjNlERDXReACAHBV";

    private LocalDateTime expires;

    @Autowired
    private AccessTokenService accessTokenService;

    @Autowired
    private TemporaryMaterialService temporaryMaterialService;

    @Autowired
    private PermanentMaterialService permanentMaterialService;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private MassMailingService massMailingService;


    @Resource
    private EasyWeChat easyWeChat;

    @Test
    @DisplayName("获取accessToken")
    public void testGetAccessToken(){
        //String accessToken = easyWeChat.getAccessToken();
        //String resAccessToken = accessTokenService.getAccessToken(appid, secret);
        //{"access_token":"47_32hcdTK7irhUX54kaZvfWS5-rxFkGEyKE1i_ed4D-NsXw8BmFxs8rWy-hKU4YXF1P3v1A28noSgosMpVgieBSm6qkeiUj0GiOEuQp9Avk0hGApHx8yn9pAaVjfYGxSYeU5UFVgdBrr8Di8IdUKNiAJAYOR","expires_in":7200}
        String resAccessToken = accessTokenService.getAccessToken();
        log.info(resAccessToken);
        JSONObject jsonObject = JSON.parseObject(resAccessToken);
        long expires_in = jsonObject.getLong("expires_in");
        if(expires_in>0){
            this.accessToken =jsonObject.getString("access_token");
            expires = LocalDateTime.now().plusHours(2);
            log.info("当前access_token:{}", this.accessToken);
        }

    }
    @Test
    @DisplayName("上传临时图片")
    public void testUploadTempImage(){
        String s = temporaryMaterialService.uploadImage(accessToken, new File("D:\\left.png"));
        log.info(s);
        //{"type":"image","media_id":"YhbCXiK9RnwiyLpV_6Zbz48gwbDndwhC5SRAYN_NYkI_x3Wwe5eOdf-mN07ME7AN","created_at":1626271092,"item":[]}
    }


    @Test
    @DisplayName("获取临时素材")
    public void testGetUploadTemp(){
        String s = temporaryMaterialService.get(accessToken, "YhbCXiK9RnwiyLpV_6Zbz48gwbDndwhC5SRAYN_NYkI_x3Wwe5eOdf-mN07ME7AN");
        log.info(s);
        //{"type":"image","media_id":"YhbCXiK9RnwiyLpV_6Zbz48gwbDndwhC5SRAYN_NYkI_x3Wwe5eOdf-mN07ME7AN","created_at":1626271092,"item":[]}
    }


    @Test
    @DisplayName("上传永久素材")
    public  void testUploadPermanent(){
        //String s = permanentMaterialService.uploadImage(accessToken, new File("D:\\reactor1.png"));
        String s = permanentMaterialService.uploadImage(accessToken, new File("D:\\reactor1.png"));

        log.info(s);
        //bg1
        //http://mmbiz.qpic.cn/mmbiz_png/K8qOZbu34F9wBkfv4vzFo13qaia4EzuricW0bvAnn19W4CDLpGPIrwvxqXakEPtdwdIE37r7wjECkTbuWA0nIr3w/0
        //bg2
        //http://mmbiz.qpic.cn/mmbiz_png/K8qOZbu34F9wBkfv4vzFo13qaia4EzuricDngLmqbOeKf7SLiaAOTBvuCWVnVulGUBMjD2jtKhq9V9OrHLSUYB88g/0
        //bg3
        //http://mmbiz.qpic.cn/mmbiz_png/K8qOZbu34F9wBkfv4vzFo13qaia4EzuricMwkh3C7UbcRflB7MIK8LN1RLfYmf5STUzAzicfTqdXoicc7Y4KVWGsNQ/0
        //bg4
        //http://mmbiz.qpic.cn/mmbiz_png/K8qOZbu34F9wBkfv4vzFo13qaia4EzuricVML9CNhibZ7Libhqiar4qUgtWSNUELcnGObmQKNPyQDpIPicp3LOodxN5g/0
        //bg5
        //http://mmbiz.qpic.cn/mmbiz_png/K8qOZbu34F9wBkfv4vzFo13qaia4EzuricD3aYic9fRcEsvfaedLYR4dhyCHL95Kr9RBxjybqTlI22tnr2c3HWTjQ/0
        //image
        //{"media_id":"EQtep6xL-T4MXrKTXCSKhfJSflmXe3vQHAfCcImm1CM","url":"http:\/\/mmbiz.qpic.cn\/mmbiz_png\/K8qOZbu34FibOZM8ibOkFdHOtHdqNtENatyLMA6WzeQj6ibHtibMLaEQxN4vKgQ5ABlU7dSG6vvPCicjroXbyEmhiaGw\/0?wx_fmt=png","item":[]}
        //image
        //{"media_id":"EQtep6xL-T4MXrKTXCSKhRW5KJbVT0xUdAIEh3Wk3Uw","url":"http:\/\/mmbiz.qpic.cn\/mmbiz_png\/K8qOZbu34F9Clae8c82bibrZhZ9msqVrsO5uonxtqLV63yibuXhjB1Ks40ndmzqAujBIGttlByMaGFYCovX0L1gg\/0?wx_fmt=png","item":[]}
    }


    @Test
    @DisplayName("测试上传图文")
    public void testUploadNews(){
        Context context = new Context();
        ArrayList<PartTime> partTimes = new ArrayList<>();
        PartTime partTime = new PartTime();
        partTime.setTxt("充场单\n" +
                "微信：898615371（发名字➕电话➕个人照片➕时间）\n" +
                "日期：6月5日星期六\n" +
                "时间：\n" +
                "上午11点：5个人\n" +
                "下午2点：5个人\n" +
                "下午3点：5个人\n" +
                "下午4点：5个人\n" +
                "地址：广州北京路店\n" +
                "需求：在门口拍3张照片，停留15分钟即可离开（内部使用，不对外传播）\n" +
                "男女不限，要年轻人，活泼开朗的。价30元/人。");
        partTime.setNum("20210708");
        partTimes.add(partTime);
        PartTime partTime1 = new PartTime();
        partTime1.setTxt("番禺新造邮政企业兼职120～130元/天：\n" +
                "6月5.6号周六日 2天 女生优先！\n" +
                "连续2天.中途不可换人，不可请假 ！不能做一天。否则没有工时，结不了工资。\n" +
                "番禺4号线新造地铁站，包装信封，贴邮票，粘胶水，不是苦力活！超级轻松，工资120-130元左右，12元/小时，包工作餐。\n" +
                "时间：早上8:30-20:30，中间有吃饭休息时间，周日基本是17:30下班。\n" +
                "工资：下班后现金结算，包工作餐。\n" +
                "工作地点在新造地铁站附近，每天8:00统一在新造地铁站出口免费专车来回接送，5分钟车程。\n" +
                "报名：发送“姓名+性别+电话+年龄+邮政包装”到微信号\n" +
                "注意：看三遍再报名，飞机、迟到别报名！风雨无阻，欢迎组队报名。\n");
        partTime1.setNum("20210709");
        PartTime p = new PartTime();
        p.setNum("060902");
        p.setTxt("充场单\n" +
                "微信：898615371（发名字➕电话➕个人照片➕时间）\n" +
                "日期：6月5日星期六\n" +
                "时间：\n" +
                "上午11点：5个人\n" +
                "下午2点：5个人\n" +
                "下午3点：5个人\n" +
                "下午4点：5个人\n" +
                "地址：广州北京路店\n" +
                "需求：在门口拍3张照片，停留15分钟即可离开（内部使用，不对外传播）\n" +
                "男女不限，要年轻人，活泼开朗的。价30元/人。");
        partTimes.add(p);
        partTimes.add(partTime1);
        context.setVariable("parttimes", partTimes);
        context.setVariable("head", "");
        String result = templateEngine.process("template.html", context);

        log.info(result);


        News te = News.start().content(result).title("测试1").thumbMediaId("EQtep6xL-T4MXrKTXCSKheCHH1Zf4e3fZkCgAkhrtRc").end().build();
        String s = permanentMaterialService.uploadNews(accessToken, JSONObject.toJSONString(te));
        log.info(s);
        //{"media_id":"EQtep6xL-T4MXrKTXCSKhdYwPwo22i3I1KyMr1sh5N8","item":[]}
        //{"media_id":"EQtep6xL-T4MXrKTXCSKhSWqQ8r4fsgmfImxTJqMAvA","item":[]}
        //{"media_id":"EQtep6xL-T4MXrKTXCSKhS9ujwX2QRLcaMUdW7aZUvg","item":[]}
        //{"media_id":"EQtep6xL-T4MXrKTXCSKhaZ0D_bxBUPtFcuB4EsGICw","item":[]}
        //{"media_id":"EQtep6xL-T4MXrKTXCSKhYDOEoI9FsyHiuUBADpLeBk","item":[]}
        //{"media_id":"EQtep6xL-T4MXrKTXCSKhUrqeNfdhJD42rGWKgNDI5I","item":[]}
        //{"media_id":"EQtep6xL-T4MXrKTXCSKhaeTldlEqIndnYt9q4Vgxto","item":[]}
        //{"media_id":"EQtep6xL-T4MXrKTXCSKhTtqEiBo3Xop5omxdJxBDyk","item":[]}
    }


    @Test
    @DisplayName("群发预览接口")
    public void testPreviewNews(){
        //String preview = massMailingService.preview("47_i8DuR7IePdm2gUiwafblSkXcb37AWnKhEYnB3ahb1hPGo9jVtlbJlKmYPZ1t2mLpbXHf-FyyfKCN1hc_QFn-bCLFQ9S6amkQPEtG4rlEijMuHgXjPSD1UUBxHKwFTyurms4oDnbMyi-DZ33VAVTfABAPKI", "EQtep6xL-T4MXrKTXCSKhUrqeNfdhJD42rGWKgNDI5I","o8IHE5tYEg5C5X3IHugKQJi0ENtk");
        String preview = massMailingService.preview("47_i8DuR7IePdm2gUiwafblSkXcb37AWnKhEYnB3ahb1hPGo9jVtlbJlKmYPZ1t2mLpbXHf-FyyfKCN1hc_QFn-bCLFQ9S6amkQPEtG4rlEijMuHgXjPSD1UUBxHKwFTyurms4oDnbMyi-DZ33VAVTfABAPKI","EQtep6xL-T4MXrKTXCSKhaeTldlEqIndnYt9q4Vgxto" ,"o8IHE5tYEg5C5X3IHugKQJi0ENtk");

        log.info(preview);
        //{"media_id":"EQtep6xL-T4MXrKTXCSKhdYwPwo22i3I1KyMr1sh5N8","item":[]}
        //群发
    }


    private ResourceLoader resourceLoader;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Test
    @DisplayName("测试获取素材")
    public void testGetPermanent(){
        String s = permanentMaterialService.get(accessToken, "EQtep6xL-T4MXrKTXCSKhRW5KJbVT0xUdAIEh3Wk3Uw");
        log.info(s);
    }


    @Test
    @DisplayName("测试retrofit获取accesstoken")
    public void getAccessTokenTest(){
        JsonObject accessToken = easyWeChat.getAccessToken(null, secret);
        log.info(accessToken.toString());
        String access_token = accessToken.get("access_token").getAsString();
        log.info(access_token);
        String expire_in = accessToken.get("expires_in").getAsString();
        log.info(expire_in);
    }



    @Test
    @DisplayName("测试retrofit上传素材图片")
    public void uploadNewsImageTest(){
        JsonObject jsonObject = easyWeChat.uploadNewsImage(accessToken, new File("D:\\reactor1.png"));
        log.info(jsonObject.toString());
    }

    @Test
    @DisplayName("测试retrofit上传图文")
    public void uploadNewsTest(){
        //JsonObject jsonObject = easyWeChat.uploadNews(accessToken);
        //log.info(jsonObject.toString());
    }

    //EQtep6xL-T4MXrKTXCSKheCHH1Zf4e3fZkCgAkhrtRc
    @Test
    @DisplayName("测试retrofit上传图片素材")
    public void uploadMaterialTest(){
        JsonObject jsonObject = easyWeChat.uploadImage(accessToken,new File("D:\\reactor1.png"));
        log.info(jsonObject.toString());
    }

    //EQtep6xL-T4MXrKTXCSKhQMn482y-jq4S4g9h-zWpnE
    @Test
    @DisplayName("测试retrofit获取素材")
    public void getTest(){
        ResponseBody material = easyWeChat.getMaterial(accessToken, "EQtep6xL-T4MXrKTXCSKheCHH1Zf4e3fZkCgAkhrtRc");
        try {
            byte[] bytes = material.bytes();
            File file = new File("E:\\rector4.png");
            if(!file.exists()){
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            FileChannel channel = fileOutputStream.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
            byteBuffer.put(bytes);
            byteBuffer.flip();
            channel.write(byteBuffer);
            channel.close();
            byteBuffer.clear();
            fileOutputStream.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    @DisplayName("测试retrofit预览")
    public void preview(){
        JsonObject jsonObject = easyWeChat.preview(accessToken, SendType.text,"xixi","o8IHE5tYEg5C5X3IHugKQJi0ENtk");
        log.info(jsonObject.toString());
    }


    @Test
    @DisplayName("测试retrofit推送")
    public void send(){
        JsonObject jsonObject = easyWeChat.send(accessToken, SendType.mpnews,"EQtep6xL-T4MXrKTXCSKhTtqEiBo3Xop5omxdJxBDyk",null);
        log.info(jsonObject.toString());
    }


}
