package org.whistle.easywechat.service;

import org.springframework.stereotype.Service;
import org.whistle.easywechat.consts.AppConst;

import javax.annotation.Resource;

/**
 * 群发消息接口
 *
 * @author Gentvel
 * @version 1.0.0
 */
@Service
public class MassMailingService {
    @Resource
    private EasyWeChatRequest easyWeChatRequest;
    /**
     * 预览接口
     */
    public String preview(String accessToken,String mediaId){
        String json = "{\"touser\":\"o8IHE5tYEg5C5X3IHugKQJi0ENtk\",\"mpnews\":{\"media_id\":\""+mediaId + "\"}, \"msgtype\":\"mpnews\"}";
        return easyWeChatRequest.post(AppConst.MASS_MAILING_PREVIEW,accessToken,json);
    }
}
