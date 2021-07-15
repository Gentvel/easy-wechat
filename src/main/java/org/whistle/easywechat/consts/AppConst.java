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

    public static final String HTTP_HEADER = "https://";
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
}
