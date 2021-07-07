package org.whistle.easywechat.pipeline;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.whistle.easywechat.bean.FromMessage;
import org.whistle.easywechat.receive.event.WeChatEventDispatcher;
import org.whistle.easywechat.receive.ordinary.WeChatOrdinaryDispatcher;

import javax.annotation.Resource;

/**
 * 消息类型处理器
 * @author Gentvel
 * @version 1.0.0
 */
@Slf4j
@Order(Integer.MIN_VALUE+2)
@Component
public class MsgTypeHandler implements ContextHandler{


    @Resource
    private WeChatOrdinaryDispatcher weChatOrdinaryDispatcher;

    @Resource
    private WeChatEventDispatcher weChatEventDispatcher;

    @Override
    public boolean handle(MsgContext context) {
        FromMessage fromMessage = context.getFromMessage();
        String response;
        //事件推送
        if(StringUtils.hasText(fromMessage.getEvent())){
             response = weChatEventDispatcher.dispatch(fromMessage);
             //普通消息
        }else{
            response = weChatOrdinaryDispatcher.dispatch(fromMessage);
        }
        log.debug(response);
        context.setResponseString(response);
        /*if(StringUtils.hasText(response)){
            return true;
        }*/

        return true;
    }
}
