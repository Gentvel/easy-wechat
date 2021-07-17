package org.whistle.easywechat.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.Getter;

/**
 * 群发对象
 * @author Gentvel
 * @version 1.0.0
 */
@Data
public class MassMail {
    private Filter filter;
    private NpNews npnews;

    private String msgtype;

    private Integer send_ignore_reprint;

    private MassMail(){

    }


    @Data
    class Filter{
        private boolean is_to_all = true;
        private Integer tag_id;
    }
    @Data
    class NpNews{
        private String media_id;
    }
}
