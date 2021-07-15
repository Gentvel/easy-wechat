package org.whistle.easywechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;
import org.whistle.easywechat.service.AccessTokenService;
import org.whistle.easywechat.service.PermanentMaterialService;
import org.whistle.easywechat.service.TemporaryMaterialService;

import java.io.File;
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
public class ServicesTest {
    private String appid = "wx32c2aac964d2e892";
    private String secret = "6fccb15a4b1f3687674711186e644d68";
    //临时素材
    private String accessToken="47_9DBUACB1xRzL3-GaT6pLWKxqzjWDKaJX7WGkOHn_SQ0ZwfXFqeoIJAANolmi99uQNZTctpm6SkeiveQ8YkP0yaX-1yVvaqnhyPTao2beTQHPHb9vMdSXZDo_zT-x6o1km3i3tQm3B7_ZDH6OWTDdABADAK";

    private LocalDateTime expires;

    @Autowired
    private AccessTokenService accessTokenService;

    @Autowired
    private TemporaryMaterialService temporaryMaterialService;

    @Autowired
    private PermanentMaterialService permanentMaterialService;
    @Test
    @DisplayName("获取accessToken")
    public void testGetAccessToken(){

        //String resAccessToken = accessTokenService.getAccessToken(appid, secret);
        //{"access_token":"47_32hcdTK7irhUX54kaZvfWS5-rxFkGEyKE1i_ed4D-NsXw8BmFxs8rWy-hKU4YXF1P3v1A28noSgosMpVgieBSm6qkeiUj0GiOEuQp9Avk0hGApHx8yn9pAaVjfYGxSYeU5UFVgdBrr8Di8IdUKNiAJAYOR","expires_in":7200}
        String resAccessToken = accessTokenService.getAccessToken();
        log.info(resAccessToken);
        JSONObject jsonObject = JSON.parseObject(resAccessToken);
        long expires_in = jsonObject.getLong("expires_in");
        if(expires_in>0){
            accessToken=jsonObject.getString("access_token");
            expires = LocalDateTime.now().plusHours(2);
            log.info("当前access_token:{}",accessToken);
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
        String s = permanentMaterialService.uploadImage(accessToken, new File("E:\\12\\bg5.png"));
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
    }


    @Test
    @DisplayName("测试上传图文")
    public void testUploadNews(){
        String s = permanentMaterialService.uploadNews(accessToken, "");
        log.info(s);
    }
}
