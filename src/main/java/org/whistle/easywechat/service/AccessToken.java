package org.whistle.easywechat.service;

import com.google.gson.JsonObject;
import org.whistle.easywechat.consts.AppConst;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * AccessToken 获取服务
 * @author Gentvel
 * @version 1.0.0
 */
public interface AccessToken {
    /**
     * getAccessToken
     */
    @GET(AppConst.ACCESS_TOKEN)
    Call<JsonObject> getAccessToken(@Query("appid")String appid, @Query("secret") String secret,@Query("grant_type") String grantType);
}
