package org.whistle.easywechat.receive.ordinary;

import org.whistle.easywechat.consts.ReceiveMessageType;
import org.whistle.easywechat.receive.AbstractWeChatMessageHandler;

/**
 *
 * 文本消息处理器
 * @author Gentvel
 * @version 1.0.0
 */
public abstract class TextOrdinaryMessageHandler extends AbstractWeChatMessageHandler {

    /*@Override
    public ToMessage process(FromMessage fromMessage) {
        log.debug("接收到的文本内容为：{}",fromMessage.getContent());
        ToMessage toMessage = new ToMessage(fromMessage);
        toMessage.setMsgType(ReceiveMessageType.text.name());
        toMessage.setContent("123");


        return toMessage;
    }*/
    @Override
    public String getType() {
        return ReceiveMessageType.text.name();
    }

}
