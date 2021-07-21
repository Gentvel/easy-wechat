package org.whistle.easywechat.service;

import com.google.gson.JsonObject;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.whistle.easywechat.bean.News;
import org.whistle.easywechat.consts.AppConst;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

/**
 * 永久素材接口请求
 * @author Gentvel
 * @version 1.0.0
 */
public interface Permanent {


    /**
     * 新增永久图文素材
     * @param accessToken Access Token
     * @param news {@link News}
     * @return {@link JsonObject}
     */
    @POST(AppConst.ADD_PERMANENT_NEWS)
    Call<JsonObject> uploadNews(@Query("access_token")String accessToken, @Body News news);


    /**
     * 上传图文消息内的图片获取URL
     * @param accessToken Access Token
     * @param file 图片文件
     * @return {@link JsonObject}
     */
    @Multipart
    @POST(AppConst.ADD_PERMANENT_NEWS_MATERIAL)
    Call<JsonObject> uploadNewsImage(@Query("access_token")String accessToken,  @Part MultipartBody.Part file);


    /**
     * 新增其他类型永久素材
     * @param accessToken accessToken
     * @param type 媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
     * @param file form-data中媒体文件标识，有filename、filelength、content-type等信息
     * @param description 在上传视频素材时需要POST另一个表单，id为description，包含素材的描述信息，内容格式为JSON，格式如下：
     *                    {
     *     "title":VIDEO_TITLE,
     *     "introduction":INTRODUCTION
     * }
     * @return {@link JsonObject}
     */
    @Multipart
    @POST(AppConst.ADD_PERMANENT)
    Call<JsonObject> uploadMaterial(@Query("access_token")String accessToken, @Query("type")String type, @Part("description") RequestBody description, @Part MultipartBody.Part file);

    /**
     * 获取永久素材
     * @param accessToken 接口调用凭证
     * @param mediaId media
     * @return {@link ResponseBody}
     */
    @POST(AppConst.GET_PERMANENT)
    Call<ResponseBody> getMaterial(@Query("access_token")String accessToken,@Body Map<String, String> mediaId);

}
