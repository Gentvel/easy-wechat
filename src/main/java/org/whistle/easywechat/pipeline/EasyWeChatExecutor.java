package org.whistle.easywechat.pipeline;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.whistle.easywechat.bean.ToMessage;
import org.whistle.easywechat.boot.EasyWeChatProperties;
import org.whistle.easywechat.consts.EncryptionType;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;


/**
 * 管道执行器
 *
 * @author Gentvel
 * @version 1.0.0
 */
@Slf4j
@Component
public class EasyWeChatExecutor {

    @Resource
    private List<ContextHandler> contextHandlers;

    private EncryptionType type;

    public EasyWeChatExecutor(EasyWeChatProperties easyWeChatProperties){
        type=easyWeChatProperties.getEncryptionType();
    }

    public String execute(HttpServletRequest httpServletRequest){
        MsgContext context = new MsgContext();
        context.setHttpServletRequest(httpServletRequest);
        context.setEncryptionType(type);
        for (ContextHandler handler:contextHandlers){
           log.debug("当前Handler:{}",handler.getClass().getSimpleName());
           boolean returnValue = handler.handle(context);
           if(!returnValue){
               log.error("Handler:[{}]执行错误! 信息: [{}]",handler.getClass().getSimpleName(),context.getErrMsg());
               break;
           }
       }
        if (!ObjectUtils.isEmpty(context.getResponseString())){
            return context.getResponseString();
        }
        return "";
    }

}
