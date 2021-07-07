package org.whistle.easywechat.pipeline;

/**
 * 回复消息处理器
 * @author Gentvel
 * @version 1.0.0
 */
public class ResponseHandler implements ContextHandler{
    @Override
    public boolean handle(MsgContext context) {
        return false;
    }
}
