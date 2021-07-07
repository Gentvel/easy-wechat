package org.whistle.easywechat.receive.ordinary;

import org.whistle.easywechat.consts.ReceiveMessageType;
import org.whistle.easywechat.receive.AbstractWeChatMessageHandler;

/**
 *
 * 地理位置消息处理器
 * @author Gentvel
 * @version 1.0.0
 */
public abstract class LocationOrdinaryMessageHandler extends AbstractWeChatMessageHandler {


    @Override
    public String getType() {
        return ReceiveMessageType.location.name();
    }

}
