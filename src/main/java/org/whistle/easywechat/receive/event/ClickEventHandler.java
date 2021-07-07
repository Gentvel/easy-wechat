package org.whistle.easywechat.receive.event;

import org.whistle.easywechat.consts.ReceiveMessageType;
import org.whistle.easywechat.receive.WeChatMessageHandler;

/**
 * 自定义菜单事件
 * @author Gentvel
 * @version 1.0.0
 */
public abstract class ClickEventHandler implements WeChatMessageHandler {

    @Override
    public String getType() {
        return ReceiveMessageType.EventType.CLICK.name();
    }
}
