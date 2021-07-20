package org.whistle.easywechat.service;

import org.springframework.stereotype.Service;
import org.whistle.easywechat.consts.AppConst;
import org.whistle.easywechat.consts.UploadType;

import javax.annotation.Resource;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 临时素材管理服务
 *
 * @author Gentvel
 * @version 1.0.0
 */
@Service
public class TemporaryMaterialService {
    @Resource
    private EasyWeChatRequest easyWeChatRequest;

    /**
     * 上传图片
     * @param accessToken accessToken
     * @param file 文件
     * @return json
     */
    public String uploadImage(String accessToken, File file){
        return upload(accessToken,file,UploadType.image);
    }

    /**
     * 上传音频
     * @param accessToken accessToken
     * @param file 文件
     * @return json
     */
    public String uploadVoice(String accessToken, File file){
        return upload(accessToken,file,UploadType.voice);
    }

    /**
     * 上传文件接口
     * @param accessToken token
     * @param file 文件
     * @param type 类型
     * @return json
     */
    private String upload(String accessToken, File file, UploadType type){
        return easyWeChatRequest.file(AppConst.ADD_TEMPORARY,accessToken,type.name(),file);
    }

    /**
     * 上传图文消息素材接口
     * @param accessToken token
     */
    public String uploadNews(String accessToken, String news){
        return easyWeChatRequest.post(AppConst.ADD_TEMPORARY_NEWS,accessToken,news);
    }

    /**
     * 获取临时素材
     * @param accessToken token
     * @param mediaId mediaId
     * @return TODO 将返回值不修改成String
     */
    public String get(String accessToken,String mediaId){
        Map<String, String> params = new HashMap<>(2);
        params.put("access_token", accessToken);
        params.put("media_id", mediaId);
        return easyWeChatRequest.get(AppConst.GET_TEMPORARY,params);
    }


}
