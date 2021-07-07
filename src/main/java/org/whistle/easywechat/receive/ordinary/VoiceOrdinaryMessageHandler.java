package org.whistle.easywechat.receive.ordinary;

import org.whistle.easywechat.consts.ReceiveMessageType;
import org.whistle.easywechat.receive.AbstractWeChatMessageHandler;

/**
 *
 * 语音消息处理器
 * @author Gentvel
 * @version 1.0.0
 */
public abstract class VoiceOrdinaryMessageHandler extends AbstractWeChatMessageHandler {


    @Override
    public String getType() {
        return ReceiveMessageType.voice.name();
    }

}
