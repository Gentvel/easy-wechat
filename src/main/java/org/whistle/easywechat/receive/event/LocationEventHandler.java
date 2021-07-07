package org.whistle.easywechat.receive.event;

import org.whistle.easywechat.consts.ReceiveMessageType;
import org.whistle.easywechat.receive.WeChatMessageHandler;

/**
 * 上报地理位置事件
 * @author Gentvel
 * @version 1.0.0
 */
public abstract class LocationEventHandler implements WeChatMessageHandler {

    @Override
    public String getType() {
        return ReceiveMessageType.EventType.LOCATION.name();
    }
}
