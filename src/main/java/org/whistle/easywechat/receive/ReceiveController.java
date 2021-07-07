package org.whistle.easywechat.receive;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Gentvel
 * @version 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("receive")
public class ReceiveController {
    @Resource
    private ReceiveService receiveService;

    @GetMapping
    public String accessProcess(String signature, String timestamp, String nonce, String echostr, @Nullable String encrypt_type,@Nullable String msg_signature){
        return  receiveService.accessProcess(signature, timestamp, nonce, echostr);
    }

    @PostMapping
    public String messageProcess(HttpServletRequest httpServletRequest){
        return receiveService.messageProcess(httpServletRequest);
    }
}
