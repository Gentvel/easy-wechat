package org.whistle.easywechat.consts;

/**
 * 接收消息类型常数
 * @author Gentvel
 * @version 1.0.0
 */
public enum ReceiveMessageType {
    /**
     * 文本
     */
    text,
    /**
     * 图像
     */
    image,
    /**
     * 链接
     */
    link,
    /**
     * 地理位置
     */
    location,
    /**
     * 音频
     */
    voice,
    /**
     * 视频
     */
    video,
    /**
     * 小视频
     */
    shortvideo,
    /**
     * 推送
     */
    event,
    /**
     * fallback
     */
    fallback;

    public enum EventType{
        /**
         * 订阅
         */
        subscribe,
        /**
         * 取消订阅
         */
        unsubscribe,

        /**
         *带参数二维码事件
         */
        SCAN,
        /**
         * 上报地理位置事件
         */
        LOCATION,
        /**
         *自定义菜单事件
         */
        CLICK,
        /**
         *点击菜单跳转链接时的事件推送
         */
        VIEW
    }
}
