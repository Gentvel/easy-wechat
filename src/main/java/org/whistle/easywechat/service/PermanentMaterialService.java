package org.whistle.easywechat.service;

import org.springframework.stereotype.Service;
import org.whistle.easywechat.consts.AppConst;
import org.whistle.easywechat.consts.UploadType;

import javax.annotation.Resource;
import java.io.File;

/**
 * 永久素材
 * @author Gentvel
 * @version 1.0.0
 */
@Service
public class PermanentMaterialService {
    @Resource
    private EasyWeChatRequest easyWeChatRequest;

    /**
     * 上传图文消息内的图片获取URL
     * @param accessToken accessToken
     * @param file 文件
     * @return json
     */
    public String uploadNewsImage(String accessToken, File file){
        return easyWeChatRequest.file(AppConst.ADD_PERMANENT_NEWS_MATERIAL,accessToken,null,file);
    }

    /**
     * 上传图片
     * @param accessToken accessToken
     * @param file file
     * @return json
     */
    public String uploadImage(String accessToken,File file){
        return  upload(accessToken,file,UploadType.image);
    }

    /**
     * 上传文件接口
     * @param accessToken token
     * @param file 文件
     * @param type 类型
     * @return json
     */
    private String upload(String accessToken, File file, UploadType type){
        return easyWeChatRequest.file(AppConst.ADD_PERMANENT,accessToken,type.name(),file);
    }

    /**
     * 上传永久图文
     * @param accessToken token
     * @param body 消息内容
     * @return json String
     */
    public String uploadNews(String accessToken,String body){
        return easyWeChatRequest.post(AppConst.ADD_PERMANENT_NEWS,accessToken,body);
    }

    /**
     * 获取永久素材
     * @param accessToken token
     * @param mediaId JsonObject {
     *   "media_id":MEDIA_ID
     * }
     * @return 素材
     */
    public String get(String accessToken,String mediaId){
        return easyWeChatRequest.post(AppConst.GET_PERMANENT,accessToken,mediaId);
    }


    /**
     * 删除永久素材
     * @param accessToken token
     * @param mediaId JsonObject {
     *   "media_id":MEDIA_ID
     * }
     * @return 素材
     */
    public String delete(String accessToken,String mediaId){
        return easyWeChatRequest.post(AppConst.DELETE_PERMANENT,accessToken,mediaId);
    }

}
