package org.whistle.easywechat.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.ToString;
import org.springframework.lang.Nullable;
import org.whistle.easywechat.consts.PassiveResponseType;
import org.whistle.easywechat.exception.MessageFormatException;

/**
 * 被动回复消息Bean
 * @author Gentvel
 * @version 1.0.0
 */
@ToString
@XStreamAlias("xml")
public class ToMessage {
    @XStreamAlias("ToUserName")
    private String toUserName;
    @XStreamAlias("FromUserName")
    private String fromUserName;
    @XStreamAlias("CreateTime")
    private String createTime=Long.toString(System.currentTimeMillis());
    @XStreamAlias("MsgType")
    private String msgType;
    /**
     * 文本
     */
    @XStreamAlias("Content")
    private String content;

    /**
     * 图像
     */
    @XStreamAlias("Image")
    private Image image;

    /**
     * 语音
     */
    @XStreamAlias("Voice")
    private Voice voice;

    /**
     * 视频
     */
    @XStreamAlias("Video")
    private Video video;

    /**
     * 音乐
     */
    @XStreamAlias("Music")
    private Music music;

    /**
     * 图文
     */
    @XStreamAlias("Articles")
    private Articles articles;
    @XStreamAlias("ArticleCount")
    private Articles articleCount;
    public ToMessage(){

    }

    public ToMessage(FromMessage fromMessage){
        this.toUserName = fromMessage.getFromUserName();
        this.fromUserName = fromMessage.getToUserName();
    }

    public static ToMessage builder(FromMessage fromMessage){
        return new ToMessage(fromMessage);
    }

    public ToMessage type(PassiveResponseType responseType){
        this.msgType=responseType.name();
        return this;
    }

    /**
     * 回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）
     */
    public ToMessage content(Object content){
        if(this.msgType.equals(PassiveResponseType.text.name())){
            this.content = content.toString();
        }
        else {
            throw new MessageFormatException("文本消息类型错误");
        }
        return this;
    }


    /**
     * 回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）
     */
    public ToMessage content(String content,Object... params){
        if(this.msgType.equals(PassiveResponseType.text.name())){
            this.content = String.format(content,params);
        }
        else {
            throw new MessageFormatException("文本消息类型错误");
        }
        return this;
    }

    /**
     * 通过素材管理中的接口上传多媒体文件，得到的id
     */
    public ToMessage mediaId(String mediaId){
        if(this.msgType.equals(PassiveResponseType.image.name())){
            Image image = new Image();
            image.setMediaId(mediaId);
            this.image = image;
        }else if(this.msgType.equals(PassiveResponseType.voice.name())){
            Voice voice = new Voice();
            voice.setMediaId(mediaId);
            this.voice = voice;
        }else{
            throw new MessageFormatException("图像/音频消息类型格式错误");
        }
        return this;
    }

    /**
     * 回复视频消息
     * @param mediaId 通过素材管理中的接口上传多媒体文件，得到的id
     * @param title 视频消息的标题
     * @param description 视频消息的描述
     */
    public ToMessage video(String mediaId,String title,@Nullable String description){
        if(this.msgType.equals(PassiveResponseType.video.name())){
            Video video = new Video();
            video.setMediaId(mediaId);
            video.setTitle(title);
            video.setDescription(description);
            this.video = video;
        }else {
            throw new MessageFormatException("视频消息类型格式错误");
        }
        return this;
    }


    /**
     * 音乐消息
     * @param title 音乐标题
     * @param description 音乐描述
     * @param musicUrl 音乐链接
     * @param HQMusicUrl 高质量音乐链接，WIFI环境优先使用该链接播放音乐
     * @param thumbMediaUrl 缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id
     */
    public ToMessage music(String title,String description,String musicUrl,String HQMusicUrl,String thumbMediaUrl){
        if(this.msgType.equals(PassiveResponseType.music.name())){
            Music music = new Music();
            music.setTitle(title);
            music.setDescription(description);
            music.setMusicUrl(musicUrl);
            music.setHQMusicUrl(HQMusicUrl);
            this.music = music;
        }else {
            throw new MessageFormatException("音乐消息类型格式错误");
        }
        return this;
    }


    @Data
    class Image{
        @XStreamAlias("MediaId")
        private String mediaId;
    }


    @Data
    class Voice{
        @XStreamAlias("MediaId")
        private String mediaId;
    }

    @Data
    class Video{
        @XStreamAlias("MediaId")
        private String mediaId;

        /**
         * 标题
         */
        @XStreamAlias("Title")
        private String title;
        /**
         * 描述
         */
        @XStreamAlias("Description")
        private String description;

    }

    @Data
    class Music{
        /**
         * 音乐标题
         */
        @XStreamAlias("Title")
        private String title;
        /**
         * 音乐描述
         */
        @XStreamAlias("Description")
        private String description;
        /**
         * 音乐链接
         */
        @XStreamAlias("MusicUrl")
        private String musicUrl;
        /**
         * 高质量音乐链接，WIFI环境优先使用该链接播放音乐
         */
        @XStreamAlias("HQMusicUrl")
        private String HQMusicUrl;
        /**
         * 缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id
         */
        @XStreamAlias("ThumbMediaId")
        private String thumbMediaId;
    }

    @Data
    class Articles{
        @XStreamAlias("item")
        private Article[] item;
    }


    @Data
    class Article{
        /**
         * 图文消息标题
         */
        @XStreamAlias("Title")
        private String title;

        /**
         * 图文消息描述
         */
        @XStreamAlias("Description")
        private String description;


        /**
         * 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
         */
        @XStreamAlias("PicUrl")
        private String picUrl;


        /**
         * 点击图文消息跳转链接
         */
        @XStreamAlias("Url")
        private String url;

    }
}
