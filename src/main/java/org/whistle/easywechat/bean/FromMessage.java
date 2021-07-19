package org.whistle.easywechat.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 *
 * 接收消息Bean
 * @author Gentvel
 * @version 1.0.0
 */
@Data
@XStreamAlias("xml")
public class FromMessage {

    @XStreamAlias("ToUserName")
    private String toUserName;
    @XStreamAlias("FromUserName")
    private String fromUserName;
    @XStreamAlias("CreateTime")
    private String createTime;
    @XStreamAlias("MsgType")
    private String msgType;
    @XStreamAlias("MsgId")
    private String msgId;
    /**
     * 文本
     */
    @XStreamAlias("Content")
    private String content;
    /**
     * 图像
     */
    @XStreamAlias("PicUrl")
    private String picUrl;
    @XStreamAlias("MediaId")
    private String mediaId;
    /**
     * 语音
     */
    @XStreamAlias("Format")
    private String format;
    @XStreamAlias("Recognition")
    private String recognition;
    /**
     * 视频
     */
    @XStreamAlias("ThumbMediaId")
    private String thumbMediaId;
    /**
     * 地理位置
     */
    @XStreamAlias("Location_X")
    private String location_X;
    @XStreamAlias("Location_Y")
    private String location_Y;
    @XStreamAlias("Scale")
    private String scale;
    @XStreamAlias("Label")
    private String label;
    /**
     * 链接
     */
    @XStreamAlias("Title")
    private String title;
    @XStreamAlias("Description")
    private String description;
    @XStreamAlias("Url")
    private String url;

    /**
     * 事件
     */
    @XStreamAlias("Event")
    private String event;
    /**
     * 扫描带参数二维码事件/自定义菜单事件/点击菜单跳转链接时的事件推送
     * 如果用户还未关注公众号，则用户可以关注公众号，关注后微信会将带场景值关注事件推送给开发者。
     * 如果用户已经关注公众号，则微信会将带场景值扫描事件推送给开发者。
     */
    @XStreamAlias("EventKey")
    private String eventKey;
    @XStreamAlias("Ticket")
    private String ticket;
    /**
     * 上报地理位置事件
     */
    @XStreamAlias("Latitude")
    private String latitude;
    @XStreamAlias("Longitude")
    private String longitude;
    @XStreamAlias("Precision")
    private String precision;

}
