package org.whistle.easywechat.receive.ordinary;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.whistle.easywechat.consts.PassiveResponseType;
import org.whistle.easywechat.consts.ReceiveMessageType;
import org.whistle.easywechat.bean.ToMessage;
import org.whistle.easywechat.bean.FromMessage;
import org.whistle.easywechat.receive.AbstractWeChatMessageHandler;


/**
 * 熔断消息处理器
 * @author Gentvel
 * @version 1.0.0
 */
@Slf4j
@Component
public class FallbackOrdinaryMessageHandler extends AbstractWeChatMessageHandler {
    @Override
    public ToMessage process(FromMessage fromMessage) {
        log.info(fromMessage.toString());
        return ToMessage.builder(fromMessage).type(PassiveResponseType.text).content("抱歉，公众号目前未提供接收此类型消息的服务。");
    }

    @Override
    public String getType() {
        return ReceiveMessageType.fallback.name();
    }
}
