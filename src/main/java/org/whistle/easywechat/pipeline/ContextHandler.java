package org.whistle.easywechat.pipeline;

/**
 * 管道处理器
 * @author Gentvel
 * @version 1.0.0
 */
public interface ContextHandler {
    /**
     * 处理输入的上下文数据
     *
     * @param context 处理时的上下文数据
     * @return 返回 true 则表示由下一个 ContextHandler 继续处理，返回 false 则表示处理结束
     */
    boolean handle(MsgContext context);
}
