package org.whistle.easywechat;

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
import org.whistle.easywechat.consts.SendType;
import org.whistle.easywechat.service.EasyWeChat;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.time.LocalDateTime;

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
    private TemplateEngine templateEngine;




    @Resource
    private EasyWeChat easyWeChat;


    @Test
    @DisplayName("上传永久素材")
    public  void testUploadPermanent(){
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
        //{"media_id":"EQtep6xL-T4MXrKTXCSKhdYwPwo22i3I1KyMr1sh5N8","item":[]}
        //{"media_id":"EQtep6xL-T4MXrKTXCSKhSWqQ8r4fsgmfImxTJqMAvA","item":[]}
        //{"media_id":"EQtep6xL-T4MXrKTXCSKhS9ujwX2QRLcaMUdW7aZUvg","item":[]}
        //{"media_id":"EQtep6xL-T4MXrKTXCSKhaZ0D_bxBUPtFcuB4EsGICw","item":[]}
        //{"media_id":"EQtep6xL-T4MXrKTXCSKhYDOEoI9FsyHiuUBADpLeBk","item":[]}
        //{"media_id":"EQtep6xL-T4MXrKTXCSKhUrqeNfdhJD42rGWKgNDI5I","item":[]}
        //{"media_id":"EQtep6xL-T4MXrKTXCSKhaeTldlEqIndnYt9q4Vgxto","item":[]}
        //{"media_id":"EQtep6xL-T4MXrKTXCSKhTtqEiBo3Xop5omxdJxBDyk","item":[]}
    }



    private ResourceLoader resourceLoader;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
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
