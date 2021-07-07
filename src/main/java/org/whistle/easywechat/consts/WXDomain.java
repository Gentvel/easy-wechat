package org.whistle.easywechat.consts;

/**
 * 微信公众号接口域名
 * <ol>
 *   <li>通用域名(api.weixin.qq.com)，使用该域名将访问官方指定就近的接入点；</li>
 *   <li>通用异地容灾域名(api2.weixin.qq.com)，当上述域名不可访问时可改访问此域名；</li>
 *   <li>上海域名(sh.api.weixin.qq.com)，使用该域名将访问上海的接入点；</li>
 *   <li>深圳域名(sz.api.weixin.qq.com)，使用该域名将访问深圳的接入点；</li>
 *   <li>香港域名(hk.api.weixin.qq.com)，使用该域名将访问香港的接入点。</li>
 * </ol>
 * @author Gentvel
 * @version 1.0.0
 */
public enum WXDomain {
    COMMON("api.weixin.qq.com"),
    COMMON2("api2.weixin.qq.com"),
    SHANGHAI("sh.api.weixin.qq.com"),
    SHENZHEN("sz.api.weixin.qq.com"),
    HONGKONG("hk.api.weixin.qq.com");
    private String domain;

    WXDomain(String domain){
        this.domain = domain;
    }

    public String getDomain() {
        return domain;
    }
}
