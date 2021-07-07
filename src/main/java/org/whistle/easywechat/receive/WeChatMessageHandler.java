package org.whistle.easywechat.receive;


import org.whistle.easywechat.bean.FromMessage;

/**
 * 微信消息分发器
 * @author Gentvel
 * @version 1.0.0
 */
public interface WeChatMessageHandler {
    /**
     * 调度方法
     *
     * @param fromMessage 接收消息
     */
    String dispatch(FromMessage fromMessage);

    /**
     * 获取消息类型
     */
    String getType();
}
