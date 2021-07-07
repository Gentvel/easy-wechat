package org.whistle.easywechat.receive.ordinary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whistle.easywechat.consts.ReceiveMessageType;
import org.whistle.easywechat.bean.FromMessage;
import org.whistle.easywechat.receive.WeChatMessageHandler;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 接收消息处理器
 * @author Gentvel
 * @version 1.0.0
 */
@Component
public class WeChatOrdinaryDispatcher {
    private static final Map<String, WeChatMessageHandler> MSG_MAP = new HashMap<>(10);


    @Autowired
    public WeChatOrdinaryDispatcher(List<WeChatMessageHandler> weChatMessageHandlerList) {
        weChatMessageHandlerList.forEach(dispatcher->MSG_MAP.put(dispatcher.getType(),dispatcher));
    }


    public String dispatch(FromMessage fromMessage){
        if(MSG_MAP.get(fromMessage.getMsgType())==null){
            fromMessage.setMsgType(ReceiveMessageType.fallback.name());
        }
        return MSG_MAP.get(fromMessage.getMsgType()).dispatch(fromMessage);
    }
}
