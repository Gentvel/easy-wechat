package org.whistle.easywechat.service;

import com.google.gson.JsonObject;
import org.whistle.easywechat.consts.AppConst;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.Map;

/**
 * 消息群发接口
 *
 * @author Gentvel
 * @version 1.0.0
 */
public interface MassMailing {
    /**
     * 根据标签进行群发【订阅号与服务号认证后均可用】
     * @param accessToken 用户调用凭证
     * @param to {@link java.util.HashMap}
     * @return {@link JsonObject}
     */
    @POST(AppConst.MASS_MAILING_SEND_TAG)
    Call<JsonObject> send(@Query("access_token")String accessToken, @Body Map<String,Object> to);



    /**
     * 根据OpenID列表群发【订阅号不可用，服务号认证后可用】
     * @param accessToken 用户调用凭证
     * @param to {@link java.util.HashMap}
     * @return {@link JsonObject}
     */
    @POST(AppConst.MASS_MAILING_SEND_USER)
    Call<JsonObject> sendUsers(@Query("access_token")String accessToken, @Body Map<String,Object> to);


    /**
     * 删除群发【订阅号与服务号认证后均可用】
     * @param accessToken 用户调用凭证
     * @param to {@link java.util.HashMap}
     * @return {@link JsonObject}
     */
    @POST(AppConst.DEL_MASS_MAILING)
    Call<JsonObject> delete(@Query("access_token")String accessToken, @Body Map<String,Object> to);



    /**
     * 预览接口【订阅号与服务号认证后均可用】
     * @param accessToken 用户调用凭证
     * @param to {@link java.util.HashMap}
     * @return {@link JsonObject}
     */
    @POST(AppConst.MASS_MAILING_PREVIEW)
    Call<JsonObject> preview(@Query("access_token")String accessToken, @Body Map<String,Object> to);



    /**
     * 查询群发消息发送状态【订阅号与服务号认证后均可用】
     * @param accessToken 用户调用凭证
     * @param to {@link java.util.HashMap}
     * @return {@link JsonObject}
     */
    @POST(AppConst.GET_MASS_MAILING_STATUS)
    Call<JsonObject> get(@Query("access_token")String accessToken, @Body Map<String,Object> to);
}
