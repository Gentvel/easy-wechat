package org.whistle.easywechat.service;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.whistle.easywechat.bean.News;
import org.whistle.easywechat.consts.SendType;
import org.whistle.easywechat.consts.UploadType;
import org.whistle.easywechat.consts.WXDomain;
import org.whistle.easywechat.exception.EasyWeChatRequestException;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * EasyWeChat 统一接口
 * @author Gentvel
 * @version 1.0.0
 */
@Slf4j
@Component
public class EasyWeChat {
    private final Environment environment;

    private static final String HTTP = "http://";
    private static final String HTTPS = "https://";

    private final AccessToken accessToken;

    private final Permanent permanent;

    private final MassMailing massMailing;

    private static final Map<String, String> media = new HashMap<>(1){
        {put("media_id",null);}
    };

    public EasyWeChat(Environment environment){
        this.environment = environment;

        String domain = environment.getProperty("whistle.wechat.domain");

        if(StringUtils.hasText(domain)){
            domain = WXDomain.COMMON.getDomain();
        }
        Retrofit retrofit = new Retrofit.Builder()
                // 设置OKHttpClient,如果不设置会提供一个默认的
                .client(new OkHttpClient())
                //设置baseUrl
                .baseUrl(HTTPS + domain)
                //添加Gson转换器
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        accessToken = retrofit.create(AccessToken.class);
        permanent = retrofit.create(Permanent.class);
        massMailing = retrofit.create(MassMailing.class);
    }

    /**
     *<ul>
     *   <li>-1	系统繁忙，此时请开发者稍候再试</li>
     *   <li>0	请求成功</li>
     *   <li>40001	AppSecret错误或者AppSecret不属于这个公众号，请开发者确认AppSecret的正确性</li>
     *   <li>40002	请确保grant_type字段值为client_credential</li>
     *   <li>40164	调用接口的IP地址不在白名单中，请在接口IP白名单中进行设置。（小程序及小游戏调用不要求IP地址在白名单内。）</li>
     *   <li>89503	此IP调用需要管理员确认,请联系管理员</li>
     *   <li>89501	此IP正在等待管理员确认,请联系管理员</li>
     *   <li>89506	24小时内该IP被管理员拒绝调用两次，24小时内不可再使用该IP调用</li>
     *   <li>89507	1小时内该IP被管理员拒绝调用一次，1小时内不可再使用该IP调用</li>
     *</ul>
     */
    public JsonObject getAccessToken(){
        return getAccessToken(null,null);
    }

    /**
     *<ul>
     *   <li>-1	系统繁忙，此时请开发者稍候再试</li>
     *   <li>0	请求成功</li>
     *   <li>40001	AppSecret错误或者AppSecret不属于这个公众号，请开发者确认AppSecret的正确性</li>
     *   <li>40002	请确保grant_type字段值为client_credential</li>
     *   <li>40164	调用接口的IP地址不在白名单中，请在接口IP白名单中进行设置。（小程序及小游戏调用不要求IP地址在白名单内。）</li>
     *   <li>89503	此IP调用需要管理员确认,请联系管理员</li>
     *   <li>89501	此IP正在等待管理员确认,请联系管理员</li>
     *   <li>89506	24小时内该IP被管理员拒绝调用两次，24小时内不可再使用该IP调用</li>
     *   <li>89507	1小时内该IP被管理员拒绝调用一次，1小时内不可再使用该IP调用</li>
     *</ul>
     * @param appid appid
     * @param secret secret
     */
    public JsonObject getAccessToken(String appid,String secret){
        if(!StringUtils.hasText(appid)){
            appid = environment.getProperty("whistle.wechat.appId");
            if(!StringUtils.hasText(appid)){
                throw new IllegalArgumentException("参数[appId]不能为空，请在SpringBoot配置文件中配置或者方法传入");
            }
        }
        if(!StringUtils.hasText(secret)){
            secret = environment.getProperty("whistle.wechat.secret");
            if(!StringUtils.hasText(secret)){
                throw new IllegalArgumentException("参数[secret]不能为空，请在SpringBoot配置文件中配置或者方法传入");
            }
        }
        Call<JsonObject> request = accessToken.getAccessToken(appid, secret, "client_credential");
        return queueJson(request);
    }

    /**
     * 新增永久图文素材-单篇文章
     * @param accessToken accessToken
     * @param title 标题
     * @param thumbMediaId 图文消息的封面图片素材id（必须是永久mediaID）
     * @param showCover 是否显示封面，0为false，即不显示，1为true，即显示
     * @param content 图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS,涉及图片url必须来源 "上传图文消息内的图片获取URL"接口获取。外部图片url将被过滤。
     * @param contentSourceUrl 图文消息的原文地址，即点击“阅读原文”后的URL
     * @return {@link JsonObject}
     */
    public JsonObject uploadNews(String accessToken,String title,String thumbMediaId,int showCover,String content,String contentSourceUrl){
        String author = environment.getProperty("whistle.wechat.author");
        String digest = environment.getProperty("whistle.wechat.digest");
        Integer onlyFansCanComment = environment.getProperty("whistle.wechat.onlyFansCanComment",Integer.class);
        if(ObjectUtils.isEmpty(onlyFansCanComment)){
            onlyFansCanComment = 1;
        }
        Integer needOpenComment = environment.getProperty("whistle.wechat.needOpenComment",Integer.class);
        if(ObjectUtils.isEmpty(needOpenComment)){
            needOpenComment = 1;
        }
        News news = News.start().content(content).title(title).thumbMediaId(thumbMediaId)
                .showCover(showCover).contentSourceUrl(contentSourceUrl)
                .author(author).digest(digest).fansCanComment(onlyFansCanComment).openComment(needOpenComment)
                .end().build();
        return uploadNews(accessToken,news);
    }

    /**
     * 新增永久图文素材
     * @param accessToken Access Token
     * @param news {@link News}
     * @return {@link JsonObject}
     */
    public JsonObject uploadNews(String accessToken,News news){
        Call<JsonObject> newsRequest = permanent.uploadNews(accessToken, news);
        return queueJson(newsRequest);
    }

    /**
     * 上传图文消息内的图片获取URL
     * @param accessToken accessToken
     * @param image imageFile
     */
    public JsonObject uploadNewsImage(String accessToken, File image){
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), image);
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("media", image.getName(), requestFile);
        Call<JsonObject> newsImage = permanent.uploadNewsImage(accessToken, body);
        return queueJson(newsImage);
    }

    /**
     * 上传永久图片素材，注意与
     * {@link EasyWeChat#uploadNewsImage(java.lang.String, java.io.File)}不同，这个方法上传图文内的图片素材
     * 而此方法单纯上传素材，返回值有media_id
     * @param accessToken Acc
     * @param image {@link File} 图片文件
     * @return {@link JsonObject}
     */
    public JsonObject uploadImage(String accessToken,File image){
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), image);
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("media", image.getName(), requestFile);
        // add another part within the multipart request
        //String descriptionString = "hello, this is description speaking";
        //RequestBody description =
                //RequestBody.create(
                        //MediaType.parse("multipart/form-data"), descriptionString);
        Call<JsonObject> newsImage = permanent.uploadMaterial(accessToken, UploadType.image.name(), null,body);
        return queueJson(newsImage);
    }


    /**
     * 上传永久音频素材
     * @param accessToken Acc
     * @param voice {@link File} voice文件
     * @return {@link JsonObject}
     */
    public JsonObject uploadVoice(String accessToken,File voice){
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), voice);
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("media", voice.getName(), requestFile);
        Call<JsonObject> newsImage = permanent.uploadMaterial(accessToken, UploadType.voice.name(), null,body);
        return queueJson(newsImage);
    }

    /**
     * 获取永久素材
     * @param accessToken 调用接口凭证
     * @param mediaId 要获取的素材的media_id
     * @return {@link ResponseBody}
     */
    public ResponseBody getMaterial(String accessToken,String mediaId){
        Call<ResponseBody> material = permanent.getMaterial(accessToken, setMediaId(mediaId));
        return queueBody(material);
    }


    /**
     * 发送预览消息
     * @param accessToken 接口调用凭证
     * @param sendType {@link SendType} 推文类型
     * @param mediaId mediaId 特别的，当SendType为text时，那么该内容为content
     * @param toUser 发送的用户openId
     * @return {@link JsonObject}
     */
    public JsonObject preview(String accessToken, SendType sendType,String mediaId, String toUser){
        HashMap<String, Object> to = new HashMap<>(3);
        HashMap<String, String> object = new HashMap<>(1);
        switch (sendType.name()){
            case "voice":
            case "image":
            case "mpvideo":
            case "mpnews":to.put("msgtype",sendType.name());
                            object.put("media_id",mediaId);
                            to.put(sendType.name(),object);
                            to.put("touser",toUser);break;
            //当类型为text时，mediaId为content
            case "text": to.put("msgtype",sendType.name());
                object.put("content",mediaId);
                to.put(sendType.name(),object);
                to.put("touser",toUser);break;
            default: throw new IllegalArgumentException("消息类型["+sendType.name()+"]不存在，创建错误");
        }
        Call<JsonObject> preview = massMailing.preview(accessToken, to);
        return queueJson(preview);
    }

    /**
     * 根据标签进行群发【订阅号与服务号认证后均可用】
     * @param accessToken 接口调用凭证
     * @param sendType {@link SendType} 发送消息类型
     * @param mediaId mediaId
     * @param tagId tagId
     * @return {@link JsonObject}
     */
    public JsonObject send(String accessToken, SendType sendType,String mediaId,Integer tagId){
        HashMap<String, Object> to = new HashMap<>(4);
        HashMap<String, String> object = new HashMap<>(1);
        HashMap<String, Object> filter = new HashMap<>(2);
        //设置filter
        if(ObjectUtils.isEmpty(tagId)){
            filter.put("is_to_all",true);
        }else{
            filter.put("is_to_all",false);
            filter.put("tag_id",tagId);
        }

        switch (sendType.name()){
            case "voice":
            case "image"://TODO 当推文为图片时怎么封装数据？
            case "mpvideo":
            case "mpnews":to.put("msgtype",sendType.name());
                object.put("media_id",mediaId);
                to.put(sendType.name(),object);
                to.put("filter",filter);
                //图文消息被判定为转载时，是否继续群发。 1为继续群发（转载），0为停止群发。 该参数默认为0。
                String sendIgnoreReprint = environment.getProperty("whistle.wechat.sendIgnoreReprint");
                if(StringUtils.hasText(sendIgnoreReprint)){
                    to.put("send_ignore_reprint",Integer.parseInt(sendIgnoreReprint));
                }
                break;
            //当类型为text时，mediaId为content
            case "text": to.put("msgtype",sendType.name());
                object.put("content",mediaId);
                to.put(sendType.name(),object);
                to.put("filter",filter);
                break;
            default: throw new IllegalArgumentException("消息类型["+sendType.name()+"]不存在，创建错误");
        }

        log.info(JSONObject.toJSONString(to));
        Call<JsonObject> send = massMailing.send(accessToken, to);
        return queueJson(send);
    }


    private Map<String, String> setMediaId(String mediaId){
        media.put("media_id",mediaId);
        return media;
    }

    private ResponseBody queueBody(Call<ResponseBody> call){
        try {
            Response<ResponseBody> execute = call.execute();
            if(execute.isSuccessful()){
                ResponseBody body = execute.body();
                assert body != null;
                return  body;
            }else{
                log.error("请求出错！请求码为：[{}],错误信息为：[{}]",execute.code(),execute.message());
                throw new EasyWeChatRequestException(execute.message());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new EasyWeChatRequestException("请求出错！");
    }

    private JsonObject queueJson(Call<JsonObject> call){
        try {
            Response<JsonObject> execute = call.execute();
            if(execute.isSuccessful()){
                JsonObject body = execute.body();
                assert body != null;
                return  body;
            }else{
                log.error("请求出错！请求码为：[{}],错误信息为：[{}]",execute.code(),execute.message());
                throw new EasyWeChatRequestException(execute.message());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new EasyWeChatRequestException("请求出错！");
    }
}
