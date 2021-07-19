package org.whistle.easywechat.service;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.whistle.easywechat.consts.AppConst;
import org.whistle.easywechat.consts.MassMailingType;
import org.whistle.easywechat.exception.MassMailingFormatException;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 群发消息接口
 *
 * @author Gentvel
 * @version 1.0.0
 */
@Service
public class MassMailingService implements EnvironmentAware {
    @Resource
    private EasyWeChatRequest easyWeChatRequest;

    private Environment environment;
    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    /**
     * 群发图文消息预览接口
     * @param accessToken token
     * @param mediaId mediaId
     * @param touser 发送的用户
     */
    public String preview(String accessToken, String mediaId, String touser){
        PreView preView = new PreView(MassMailingType.mpnews.name(), mediaId,null);
        preView.setTouser(touser);
        String s = JSONObject.toJSONString(preView);
        return easyWeChatRequest.post(AppConst.MASS_MAILING_PREVIEW,accessToken,s);
    }

    /**
     * 群发音频消息预览接口
     * @param accessToken token
     * @param mediaId mediaId
     * @param touser 发送的用户
     */
    public String previewVoice(String accessToken, String mediaId, String touser){
        PreView preView = new PreView(MassMailingType.voice.name(), mediaId,null);
        preView.setTouser(touser);
        String s = JSONObject.toJSONString(preView);
        return easyWeChatRequest.post(AppConst.MASS_MAILING_PREVIEW,accessToken,s);
    }

    /**
     * 群发图片消息预览接口
     * @param accessToken token
     * @param mediaId mediaId
     * @param touser 发送的用户
     */
    public String previewImage(String accessToken, String mediaId, String touser){
        PreView preView = new PreView(MassMailingType.image.name(), mediaId,null);
        preView.setTouser(touser);
        String s = JSONObject.toJSONString(preView);
        return easyWeChatRequest.post(AppConst.MASS_MAILING_PREVIEW,accessToken,s);
    }

    /**
     * 群发视频消息预览接口
     * @param accessToken token
     * @param mediaId mediaId
     * @param touser 发送的用户
     */
    public String previewVideo(String accessToken, String mediaId, String touser){
        PreView preView = new PreView(MassMailingType.mpvideo.name(), mediaId,null);
        preView.setTouser(touser);
        String s = JSONObject.toJSONString(preView);
        return easyWeChatRequest.post(AppConst.MASS_MAILING_PREVIEW,accessToken,s);
    }

    /**
     * 群发文本消息预览接口
     * @param accessToken token
     * @param content content
     * @param touser 发送的用户
     */
    public String previewText(String accessToken, String content, String touser){
        PreView preView = new PreView(MassMailingType.text.name(), null,content);
        preView.setTouser(touser);
        String s = JSONObject.toJSONString(preView);
        return easyWeChatRequest.post(AppConst.MASS_MAILING_PREVIEW,accessToken,s);
    }

    /**
     * 查询群发消息发送状态
     * @param accessToken accesstoken
     * @param msgId 群发消息后返回的消息id
     */
    public String getStatus(String accessToken,String msgId){
        Map<String, String> msgIdMap = new HashMap<>(1);
        msgIdMap.put("msg_id",msgId);
        return easyWeChatRequest.post(AppConst.GET_MASS_MAILING_STATUS,accessToken,JSONObject.toJSONString(msgIdMap));
    }


    /**
     * 删除群发
     * @param accessToken accesstoken
     * @param msgId 群发消息后返回的消息id
     */
    public String delete(String accessToken,String msgId,Integer articleIndex){
        Map<String, Object> msgMap = new HashMap<>(2);
        msgMap.put("msg_id",msgId);
        msgMap.put("article_idx",articleIndex);
        return easyWeChatRequest.post(AppConst.DEL_MASS_MAILING,accessToken,JSONObject.toJSONString(msgMap));
    }


    /**
     * 发送图文消息接口
     * @param accessToken accessToken
     * @param tagId 当tagId为空时，说明是向全部用户发送，否则发送特定标签用户
     * @param sendIgnoreReprint 图文消息被判定为转载时，是否继续群发。 1为继续群发（转载），0为停止群发。
     */
    public String send(String accessToken,String mediaId,@Nullable Integer tagId,@Nullable Integer sendIgnoreReprint){
        String s = buildMassMail(MassMailingType.mpnews, mediaId, tagId, sendIgnoreReprint, null, null, null);
        return sendMessage(accessToken,s);
    }

    /**
     * 发送文本消息接口
     * @param accessToken accessToken
     * @param tagId 当tagId为空时，说明是向全部用户发送，否则发送特定标签用户
     */
    public String sendText(String accessToken,String content,@Nullable Integer tagId){
        String s = buildMassMail(MassMailingType.text, content, tagId, null, null, null, null);
        return sendMessage(accessToken,s);
    }


    /**
     * 发送语音消息接口
     * @param accessToken accessToken
     * @param tagId 当tagId为空时，说明是向全部用户发送，否则发送特定标签用户
     */
    public String sendVoice(String accessToken,String mediaId,@Nullable Integer tagId){
        String s = buildMassMail(MassMailingType.voice, mediaId, tagId, null, null, null, null);
        return sendMessage(accessToken,s);
    }

    /**
     * 发送图片消息接口
     * @param accessToken accessToken
     * @param tagId 当tagId为空时，说明是向全部用户发送，否则发送特定标签用户
     */
    public String sendImages(String accessToken,@Nullable Integer tagId,@Nullable Integer needOpenComment,@Nullable Integer onlyFansCanComment,@Nullable String[] mediaIds){
        String s = buildMassMail(MassMailingType.image, null, tagId, null, needOpenComment, onlyFansCanComment, mediaIds);
        return sendMessage(accessToken,s);
    }

    /**
     * 发送视频消息接口
     * @param accessToken accessToken
     * @param tagId 当tagId为空时，说明是向全部用户发送，否则发送特定标签用户
     */
    public String sendVideo(String accessToken,String mediaId,@Nullable Integer tagId){
        String s = buildMassMail(MassMailingType.voice, mediaId, tagId, null, null, null, null);
        return sendMessage(accessToken,s);
    }

    private String sendMessage(String accessToken,String massMailing){
        return easyWeChatRequest.post(AppConst.MASS_MAILING_SEND,accessToken,massMailing);
    }


    /**
     * 创建MassMailing对象
     * @param type 类型
     * @param mediaId mediaId,当类型为text时，其内容为content
     * @param tagId tagId
     * @param sendIgnoreReprint mpnews类型 图文消息被判定为转载时，是否继续群发。 1为继续群发（转载），0为停止群发。 该参数默认为0。
     * @param needOpenComment images类型 是否打开评论，0不打开，1打开
     * @param onlyFansCanComment images类型 是否粉丝才可评论，0所有人可评论，1粉丝才可评论
     * @param mediaIds images类型
     */
    private String buildMassMail(MassMailingType type, String mediaId, @Nullable Integer tagId,@Nullable Integer sendIgnoreReprint,@Nullable Integer needOpenComment,@Nullable Integer onlyFansCanComment,@Nullable String[] mediaIds){
        MassMail massMail = new MassMail(type.name(),mediaId);
        MassMail.Filter filter = massMail.getFilter();

        if(ObjectUtils.isEmpty(tagId)){
            filter.set_to_all(true);
        }else{
            filter.setTag_id(tagId);
        }

        if("image".equals(type.name())){
            Images images = massMail.getImages();
            if(ObjectUtils.isEmpty(mediaIds)){
                throw new MassMailingFormatException("当消息类型为images时，mediaIds不能为空！");
            }
            images.setMedia_ids(mediaIds);
            if(ObjectUtils.isEmpty(needOpenComment)){
                String property = environment.getProperty("whistle.wechat.needOpenComment");
                if(StringUtils.hasText(property)){
                    images.setNeed_open_comment(Integer.parseInt(property));
                }
            }else{
                images.setNeed_open_comment(needOpenComment);
            }
            if(ObjectUtils.isEmpty(onlyFansCanComment)){
                String property = environment.getProperty("whistle.wechat.onlyFansCanComment");
                if(StringUtils.hasText(property)){
                    images.setOnly_fans_can_comment(Integer.parseInt(property));
                }
            }else{
                images.setOnly_fans_can_comment(onlyFansCanComment);
            }
        }

        if("mpnews".equals(type.name())){
            if(ObjectUtils.isEmpty(sendIgnoreReprint)){
                String property = environment.getProperty("whistle.wechat.sendIgnoreReprint");
                if(StringUtils.hasText(property)){
                    massMail.setSend_ignore_reprint(Integer.parseInt(property));
                }
            }else{
                massMail.setSend_ignore_reprint(sendIgnoreReprint);
            }
        }

        return JSONObject.toJSONString(massMail);
    }




    @Data
    class PreView{
        private String touser;
        private String msgtype;
        private MpNews npnews;
        private Text text;
        private Voice voice;
        private Image image;
        private MpVideo mpvideo;


        public PreView(String msgtype,String mediaId,String content){
            this.msgtype = msgtype;
            switch (msgtype){
                case "mpnews": npnews = new MpNews();npnews.setMedia_id(mediaId);break;
                case "text": text = new Text();text.setContent(content);break;
                case "voice": voice = new Voice();voice.setMedia_id(mediaId);break;
                case "image": image = new Image();image.setMedia_id(mediaId);break;
                case "mpvideo": mpvideo = new MpVideo();mpvideo.setMedia_id(mediaId);break;
                default: throw new IllegalArgumentException("消息类型["+msgtype+"]不存在，创建错误");
            }
        }
    }

    @Data
    public class MassMail {
        private Filter filter;

        private String[] touser;
        private MpNews npnews;
        private Text text;
        private Voice voice;
        private Images images;
        private MpVideo mpvideo;


        private String msgtype;
        /**
         * 图文消息被判定为转载时，是否继续群发。 1为继续群发（转载），0为停止群发。 该参数默认为0。
         */
        private Integer send_ignore_reprint;

        private MassMail(String msgtype,String mediaId){
            this.msgtype = msgtype;
            switch (msgtype){
                case "mpnews": npnews = new MpNews();npnews.setMedia_id(mediaId);break;
                case "text": text = new Text();text.setContent(mediaId);break;
                case "voice": voice = new Voice();voice.setMedia_id(mediaId);break;
                case "image": images = new Images();break;
                case "mpvideo": mpvideo = new MpVideo();mpvideo.setMedia_id(mediaId);break;
                default: throw new IllegalArgumentException("消息类型["+msgtype+"]不存在，创建错误");
            }

            this.filter = new Filter();
            filter.set_to_all(true);
        }


        @Data
        class Filter{
            private boolean is_to_all;
            private Integer tag_id;
        }

    }

    @Data
    class MpNews{
        private String media_id;
    }

    @Data
    class Text{
        private String content;
    }
    @Data
    class Voice{
        private String media_id;
    }
    @Data
    class Image{
        private String media_id;
    }
    @Data
    class MpVideo{
        private String media_id;
    }
    @Data
    class Images{
        private String[] media_ids;
        private String recommend;
        private Integer need_open_comment;
        private Integer only_fans_can_comment;

    }
}
