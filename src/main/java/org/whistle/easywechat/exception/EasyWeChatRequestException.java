package org.whistle.easywechat.exception;

/**
 * @author Gentvel
 * @version 1.0.0
 */
public class EasyWeChatRequestException extends RuntimeException{
    public EasyWeChatRequestException(){
        super();
    }
    public EasyWeChatRequestException(String message){
        super("EasyWeChat请求微信接口出错:"+message);
    }
}
