package org.whistle.easywechat.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.whistle.easywechat.utils.OkHttpUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Gentvel
 * @version 1.0.0
 */
@Slf4j
@Component
class EasyWeChatRequest implements EnvironmentAware {
    private Environment environment;
    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    String get(String uri,Map<String,String> params){
        return request(uri,params,null,true,null);
    }

    String get(String uri,Map<String,String> params,Map<String,String> headers){
        return request(uri,params,headers,true,null);
    }

    String post(String uri,Map<String,String> params,String body){
        return request(uri,params,null,false,body);
    }

    String post(String uri,String accessToken,String body){
        Map<String, String> map = new HashMap<>(2);
        map.put("access_token", accessToken);
        return request(uri,map,null,false,body);
    }
    String request(String uri, Map<String,String> params,Map<String,String> headers,boolean isGet,@Nullable String body){
        String response;
        String domain = environment.getProperty("whistle.wechat.domain");
        OkHttpUtils okHttpUtils = OkHttpUtils.builder().url(domain + uri).addParam(params).addHeader(headers);
        if(isGet){
            response = okHttpUtils.get().sync();
        }else{
            response = okHttpUtils.post(body).sync();
        }
        return response;
    }

    String file(String uri, String accessToken, @Nullable String type, File file){
        String domain = environment.getProperty("whistle.wechat.domain");
        OkHttpUtils okHttp = OkHttpUtils.builder().url(domain + uri).addParam("access_token", accessToken);
        if(StringUtils.hasText(type)){
            okHttp = okHttp.addParam("type", type);
        }
        return okHttp.file(file).sync();
    }


}
