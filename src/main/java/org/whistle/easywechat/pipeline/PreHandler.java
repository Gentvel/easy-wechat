package org.whistle.easywechat.pipeline;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 前置处理器
 * @author Gentvel
 * @version 1.0.0
 */
@Slf4j
@Order(Integer.MIN_VALUE)
@Component
public class PreHandler implements ContextHandler{
    @Override
    public boolean handle(MsgContext context) {
        context.setStartTime(LocalDateTime.now());
        return true;
    }
}
