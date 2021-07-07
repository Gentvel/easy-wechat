package org.whistle.easywechat.pipeline;

import com.thoughtworks.xstream.XStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.whistle.easywechat.bean.FromMessage;
import org.whistle.easywechat.consts.EncryptionType;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 加解密类型处理器
 *
 * @author Gentvel
 * @version 1.0.0
 */
@Slf4j
@Order(Integer.MIN_VALUE+1)
@Component
public class EncryptionHandler implements ContextHandler{
    private static final XStream XSTREAM;

    static {
        XSTREAM=new XStream();
        XSTREAM.processAnnotations(new Class[]{FromMessage.class});
        //xStream对象设置默认安全防护，同时设置允许的类
        XStream.setupDefaultSecurity(XSTREAM);
        XSTREAM.allowTypes(new Class[]{FromMessage.class});
    }
    @Override
    public boolean handle(MsgContext context) {
        EncryptionType encryptionType = context.getEncryptionType();
        log.debug("当前加解密方式为: [{}]",context.getEncryptionType());
        //明文模式
        if(encryptionType.equals(EncryptionType.plaintextMode)){
            try {
                HttpServletRequest httpServletRequest = context.getHttpServletRequest();

                FromMessage fromMessage = (FromMessage)XSTREAM.fromXML(httpServletRequest.getInputStream());
                context.setFromMessage(fromMessage);
            } catch (IOException e) {
                context.setErrMsg("请求I/O错误！");
                return false;
            }
            return true;
        //兼容模式
        }else if(encryptionType.equals(EncryptionType.compatibilityMode)){

            return true;
        //安全模式
        }else{

            return true;
        }
    }
}
