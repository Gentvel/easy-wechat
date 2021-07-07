package org.whistle.easywechat.receive.ordinary;

import org.whistle.easywechat.consts.ReceiveMessageType;
import org.whistle.easywechat.receive.AbstractWeChatMessageHandler;

/**
 *
 * 小视频消息处理器
 * @author Gentvel
 * @version 1.0.0
 */
public abstract class ShortVideoOrdinaryMessageHandler extends AbstractWeChatMessageHandler {


    @Override
    public String getType() {
        return ReceiveMessageType.shortvideo.name();
    }

}
