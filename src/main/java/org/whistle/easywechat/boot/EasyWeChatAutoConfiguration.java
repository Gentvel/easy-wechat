package org.whistle.easywechat.boot;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.whistle.easywechat.consts.EncryptionType;


/**
 * @author Gentvel
 * @version 1.0.0
 */
@Import(EasyWeChatImportSelector.class)
@ComponentScan({"org.whistle.easywechat.pipeline","org.whistle.easywechat.receive","org.whistle.easywechat.service"})
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({EasyWeChatProperties.class})
public class EasyWeChatAutoConfiguration {
    public EasyWeChatAutoConfiguration(EasyWeChatProperties easyWeChatProperties){
        EncryptionType type = easyWeChatProperties.getEncryptionType();
        if(!type.equals(EncryptionType.plaintextMode)){
            String encodingAESKey = easyWeChatProperties.getEncodingAESKey();
            if(encodingAESKey.length()!=43){
                throw new IllegalArgumentException("参数[encodingAESKey]配置错误！长度不等于43，请检查");
            }
        }
    }

}
