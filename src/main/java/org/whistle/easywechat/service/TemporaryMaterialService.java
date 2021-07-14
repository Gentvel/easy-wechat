package org.whistle.easywechat.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
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
     * 上传文件接口
     * @param accessToken token
     * @param file 文件
     * @param type 类型
     * @return json
     */
    private String upload(String accessToken, File file, UploadType type){
        return easyWeChatRequest.file(AppConst.ADD_TEMPORARY,accessToken,type.name(),file);
    }


    public String get(String accessToken,String mediaId){
        Map<String, String> params = new HashMap<>(2);
        params.put("access_token", accessToken);
        params.put("media_id", mediaId);
        return easyWeChatRequest.get(AppConst.GET_TEMPORARY,params);
    }


}