package org.whistle.easywechat.pipeline;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.whistle.easywechat.consts.EncryptionType;
import org.whistle.easywechat.bean.FromMessage;
import org.whistle.easywechat.bean.ToMessage;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * 消息接收上下文
 *
 * @author Gentvel
 * @version 1.0.0
 */
@Getter
@Setter
public class MsgContext{

    /**
     * 开始处理时间
     */
    private LocalDateTime startTime;
    /**
     * 结束处理时间
     */
    private LocalDateTime endTime;

    /**
     * 模型出错时的消息
     */
    private String errMsg;
    /**
     * 加解密类型
     */
    private EncryptionType encryptionType;
    /**
     * 接收消息
     */
    private FromMessage fromMessage;

    /**
     * Request
     */
    private HttpServletRequest httpServletRequest;


    /**
     * 回复Xml
     */
    private String responseString;
}
