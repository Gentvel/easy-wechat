package org.whistle.easywechat.receive.event;

import org.whistle.easywechat.consts.ReceiveMessageType;
import org.whistle.easywechat.receive.WeChatMessageHandler;

/**
 * 关注事件
 * @author Gentvel
 * @version 1.0.0
 */
public abstract class SubscribeEventHandler implements WeChatMessageHandler {

    @Override
    public String getType() {
        return ReceiveMessageType.EventType.subscribe.name();
    }
}
