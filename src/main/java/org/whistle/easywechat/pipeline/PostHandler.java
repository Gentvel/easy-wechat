package org.whistle.easywechat.pipeline;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;

/**
 * 后置处理器
 *
 * @author Gentvel
 * @version 1.0.0
 */
@Slf4j
@Order
@Component
public class PostHandler implements ContextHandler{
    @Override
    public boolean handle(MsgContext context) {
        context.setEndTime(LocalDateTime.now());
        Duration duration = Duration.between(context.getStartTime(), context.getEndTime());
        log.debug("消息耗时:[{}]ms",duration.toMillis());
        //context.setResponseString("success");
        return true;
    }
}
