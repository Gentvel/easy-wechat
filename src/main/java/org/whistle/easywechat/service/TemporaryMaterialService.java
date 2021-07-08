package org.whistle.easywechat.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.whistle.easywechat.boot.EasyWeChatProperties;
import org.whistle.easywechat.consts.AppConst;
import org.whistle.easywechat.consts.UploadType;
import org.whistle.easywechat.utils.OkHttpUtils;

import javax.annotation.Resource;

/**
 * 临时素材管理服务
 *
 * @author Gentvel
 * @version 1.0.0
 */
@Service
public class TemporaryMaterialService {

    @Resource
    private EasyWeChatProperties easyWeChatProperties;

    public String addImage(String accessToken, MultipartFile file){
        return add(accessToken,file,UploadType.image);
    }


    private String add(String accessToken, MultipartFile file, UploadType type){
        return null;
    }


    public String get(String accessToken,String mediaId){
        OkHttpUtils.builder().url(easyWeChatProperties.getDomain()+ AppConst.GET_TEMPORARY);
        return null;
    }


}
