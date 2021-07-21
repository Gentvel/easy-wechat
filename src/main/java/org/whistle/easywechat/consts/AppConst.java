package org.whistle.easywechat.consts;

/**
 * 应用常量参数
 *
 * @author Gentvel
 * @version 1.0.0
 */
public class AppConst {
    /**
     * 应用参数名
     */
    public static final String APP_PREFIX = "whistle";

    /**
     * access token
     */
    public static final String ACCESS_TOKEN="/cgi-bin/token";
    /**
     * 新增临时素材
     */
    public static final String ADD_TEMPORARY="/cgi-bin/media/upload";

    /**
     * 获取临时素材
     */
    public static final String GET_TEMPORARY="/cgi-bin/media/get";
    /**
     * 新增临时图文素材
     */
    public static final String ADD_TEMPORARY_NEWS="/cgi-bin/media/uploadnews";
    /**
     * 新增永久图文素材
     */
    public static final String ADD_PERMANENT_NEWS = "/cgi-bin/material/add_news";

    /**
     * 上传图文内素材
     */
    public static final String ADD_PERMANENT_NEWS_MATERIAL ="/cgi-bin/media/uploadimg";
    /**
     * 新增其他类型素材
     */
    public static final String ADD_PERMANENT="/cgi-bin/material/add_material";

    /**
     * 获取永久素材
     */
    public static final String GET_PERMANENT = "/cgi-bin/material/get_material";

    /**
     * 删除永久素材
     */
    public static final String DELETE_PERMANENT = "/cgi-bin/material/del_material";

    /**
     * 修改永久图文素材
     */
    public static final String UPDATE_PERMANENT_NEWS = "/cgi-bin/material/update_news";
    /**
     * 获取素材总数
     */
    public static final String GET_PERMANENT_COUNT = "/cgi-bin/material/get_materialcount";

    /**
     * 获取素材列表
     */
    public static final String GET_MATERIAL_LIST = "/cgi-bin/material/batchget_material";

    /**
     * 根据标签进行群发【订阅号与服务号认证后均可用】
     */
    public static final String MASS_MAILING_SEND_TAG = "/cgi-bin/message/mass/sendall";
    /**
     * 根据OpenID列表群发【订阅号不可用，服务号认证后可用】
     */
    public static final String MASS_MAILING_SEND_USER = "/cgi-bin/message/mass/send";
    /**
     * 群发接口预览
     */
    public static final String MASS_MAILING_PREVIEW ="/cgi-bin/message/mass/preview";

    /**
     * 查询群发消息发送状态
     */
    public static final String GET_MASS_MAILING_STATUS = "/cgi-bin/message/mass/get";

    /**
     * 删除群发
     */
    public static final String DEL_MASS_MAILING = "/cgi-bin/message/mass/delete";
}
