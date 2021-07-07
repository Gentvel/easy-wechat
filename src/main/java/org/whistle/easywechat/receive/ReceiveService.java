package org.whistle.easywechat.receive;

import com.thoughtworks.xstream.XStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.whistle.easywechat.bean.FromMessage;
import org.whistle.easywechat.pipeline.EasyWeChatExecutor;
import org.whistle.easywechat.receive.ordinary.WeChatOrdinaryDispatcher;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @author Gentvel
 * @version 1.0.0
 */
@Slf4j
@Service
public class ReceiveService {
    /**
     * token，需和公众号内配置的token一致
     */
    private static final String TOKEN = "omJNpZEhZeHj1ZxFECKkP48B5VFbk1HP";
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    @Resource
    private EasyWeChatExecutor easyWeChatExecutor;



    public String accessProcess(String signature,String timestamp,String nonce,String echostr){
        log.info("Wechat start verification ...signature:{},timestamp:{},nonce:{},echostr:{}",signature,timestamp,nonce,echostr);
        if(checkSignature(signature, timestamp, nonce)){
            log.info("Wechat verification success");
            return echostr;
        }
        log.info("Wechat verification failure");
        return null;
    }


    public String messageProcess(HttpServletRequest httpServletRequest) {
        //FromMessage fromMessage = (FromMessage) XSTREAM.fromXML(httpServletRequest.getInputStream());
        return easyWeChatExecutor.execute(httpServletRequest);
    }

    private boolean checkSignature(String signature,String timestamp,String nonce){
        String[] arr = new String[]{TOKEN,timestamp,nonce};
        Arrays.sort(arr);
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : arr) {
            stringBuilder.append(s);
        }
        MessageDigest messageDigest;
        String tmpStr = null;
        try{
            messageDigest = MessageDigest.getInstance("SHA-1");
            byte[] digest = messageDigest.digest(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
            tmpStr = byteToString(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return StringUtils.hasText(tmpStr) && tmpStr.equalsIgnoreCase(signature);
    }

    /**
     * 将密文字节数组转换成String
     */
    private String byteToString(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        // 把密文转换成十六进制的字符串形式
        for (byte aByte : bytes) {
            buf.append(HEX_DIGITS[(aByte >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[aByte & 0x0f]);
        }
        return buf.toString();
    }
}
