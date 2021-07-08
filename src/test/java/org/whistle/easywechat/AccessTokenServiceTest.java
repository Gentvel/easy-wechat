package org.whistle.easywechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;
import org.whistle.easywechat.service.AccessTokenService;

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
public class AccessTokenServiceTest {
    private String appid = "wx32c2aac964d2e892";
    private String secret = "6fccb15a4b1f3687674711186e644d68";
    //临时素材
    private String accessToken;

    private LocalDateTime expires;

    @Autowired
    private AccessTokenService accessTokenService;
    @Test
    @DisplayName("获取accessToken")
    public void testGetAccessToken(){
        if(!StringUtils.hasText(accessToken)){
            String resAccessToken = accessTokenService.getAccessToken(appid, secret);
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

}
