package org.whistle.easywechat.service;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.whistle.easywechat.boot.EasyWeChatProperties;
import org.whistle.easywechat.consts.AppConst;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取AccessToken服务
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
 * </ul>
 *
 * @author Gentvel
 * @version 1.0.0
 */
@Service
public class AccessTokenService implements EnvironmentAware {
    private Environment environment;
    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Resource
    private EasyWeChatRequest easyWeChatRequest;

    public String getAccessToken(){
        return getAccessToken(null,null);
    }

    public String getAccessToken(@Nullable String appid, @Nullable String secret){
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
        Map<String, String> params = new HashMap<>(3);
        params.put("grant_type", "client_credential");
        params.put("appid", appid);
        params.put("secret", secret);
        return easyWeChatRequest.get(AppConst.ACCESS_TOKEN,params,null);

    }


}
