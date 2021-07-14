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
    private String accessToken="47_32hcdTK7irhUX54kaZvfWS5-rxFkGEyKE1i_ed4D-NsXw8BmFxs8rWy-hKU4YXF1P3v1A28noSgosMpVgieBSm6qkeiUj0GiOEuQp9Avk0hGApHx8yn9pAaVjfYGxSYeU5UFVgdBrr8Di8IdUKNiAJAYOR";

    private LocalDateTime expires;

    @Autowired
    private AccessTokenService accessTokenService;

    @Autowired
    private TemporaryMaterialService temporaryMaterialService;
    @Test
    @DisplayName("获取accessToken")
    public void testGetAccessToken(){
        if(!StringUtils.hasText(accessToken)){
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

}
