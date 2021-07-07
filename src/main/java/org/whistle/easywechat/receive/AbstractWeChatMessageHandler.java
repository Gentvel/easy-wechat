package org.whistle.easywechat.receive;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.whistle.easywechat.bean.FromMessage;
import org.whistle.easywechat.bean.ToMessage;

import java.io.Writer;

/**
 * 接收消息分发器
 * @author Gentvel
 * @version 1.0.0
 */
@Slf4j
public abstract class AbstractWeChatMessageHandler implements WeChatMessageHandler {
    private static final XStream stream;


    static {
        stream = new XStream(new XppDriver() {
            @Override
            public HierarchicalStreamWriter createWriter(Writer out) {
                return new PrettyPrintWriter(out) {
                    boolean cdata = true;

                    @Override
                    public void startNode(String name, Class clazz) {
                        super.startNode(name, clazz);
                    }

                    @Override
                    protected void writeText(QuickWriter writer, String text) {
                        if (cdata) {
                            writer.write("<![CDATA[");
                            writer.write(text);
                            writer.write("]]>");
                        } else {
                            writer.write(text);
                        }
                    }
                };
            }
        });
        stream.processAnnotations(new Class[]{ToMessage.class});
        //xStream对象设置默认安全防护，同时设置允许的类
        XStream.setupDefaultSecurity(stream);
        stream.allowTypes(new Class[]{ToMessage.class});
    }

    @Override
    public String dispatch(FromMessage fromMessage) {
        log.debug(fromMessage.toString());
        beforeProcess(fromMessage);
        ToMessage toMessage = process(fromMessage);
        afterProcess(fromMessage,toMessage);
        return ObjectUtils.isEmpty(toMessage) ?"success":stream.toXML(toMessage);
    }

    public abstract ToMessage process(FromMessage fromMessage);


    protected void beforeProcess(FromMessage fromMessage){};

    protected void afterProcess(FromMessage fromMessage, ToMessage response){};

}
