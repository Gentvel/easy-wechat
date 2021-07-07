package org.whistle.easywechat.receive.event;

import org.whistle.easywechat.consts.ReceiveMessageType;
import org.whistle.easywechat.receive.WeChatMessageHandler;

/**
 * 扫描带参数二维码，已关注时的事件推送
 * @author Gentvel
 * @version 1.0.0
 */
public abstract class ScanEventHandler implements WeChatMessageHandler {

    @Override
    public String getType() {
        return ReceiveMessageType.EventType.SCAN.name();
    }
}
