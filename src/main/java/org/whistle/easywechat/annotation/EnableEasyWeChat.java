package org.whistle.easywechat.annotation;

import org.springframework.context.annotation.Import;
import org.whistle.easywechat.boot.EasyWeChatAutoConfiguration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Gentvel
 * @version 1.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(EasyWeChatAutoConfiguration.class)
public @interface EnableEasyWeChat {
}
