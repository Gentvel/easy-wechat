package org.whistle.easywechat.boot;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.whistle.easywechat.consts.AppConst;
import org.whistle.easywechat.consts.EncryptionType;
import org.whistle.easywechat.consts.WXDomain;

/**
 * 配置文件
 *
 * @author Gentvel
 * @version 1.0.0
 */
@Setter
@Getter
@ConfigurationProperties(prefix = EasyWeChatProperties.WECHAT_PREFIX)
public class EasyWeChatProperties {
    protected static final String WECHAT_PREFIX = AppConst.APP_PREFIX+"wechat";
    /**
     * 微信公众号接口域名
     * <ul>
     *   <li>通用域名(api.weixin.qq.com)，使用该域名将访问官方指定就近的接入点；</li>
     *   <li>通用异地容灾域名(api2.weixin.qq.com)，当上述域名不可访问时可改访问此域名；</li>
     *   <li>上海域名(sh.api.weixin.qq.com)，使用该域名将访问上海的接入点；</li>
     *   <li>深圳域名(sz.api.weixin.qq.com)，使用该域名将访问深圳的接入点；</li>
     *   <li>香港域名(hk.api.weixin.qq.com)，使用该域名将访问香港的接入点。</li>
     * </ul>
     */
    private String domain = WXDomain.COMMON.getDomain();

    /**
     * 第三方用户唯一凭证
     */
    private String appId;

    /**
     * 第三方用户唯一凭证密钥，即appsecret
     */
    private String secret;
    /**
     * 该Token会和接口URL中包含的Token进行比对，从而验证安全性
     */
    private String token;

    /**
     * EncodingAESKey由开发者手动填写或随机生成，将用作消息体加解密密钥。
     */
    private String encodingAESKey;

    /**
     * 明文模式、兼容模式和安全模式(默认为明文模式)
     */
    private EncryptionType encryptionType = EncryptionType.plaintextMode;



}
