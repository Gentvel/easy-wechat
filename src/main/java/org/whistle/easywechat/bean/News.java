package org.whistle.easywechat.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 图文消息对象
 * @author Gentvel
 * @version 1.0.0
 */
public class News {

    private List<Article> articles = new ArrayList<>(4);

    private Article article;

    private News(){
        this.article = new Article();
    }

    public static News start(){
        return new News();
    }

    public News add(){
        this.article = new Article();
        return this;
    }

    public News thumbMediaId(String thumbMediaId){
        this.article.setThumb_media_id(thumbMediaId);
        return this;
    }

    public News author(String author){
        this.article.setAuthor(author);
        return this;
    }

    public News title(String title){
        this.article.setTitle(title);
        return this;
    }

    public News contentSourceUrl(String contentSourceUrl){
        this.article.setContent_source_url(contentSourceUrl);
        return this;
    }

    public News content(String content){
        this.article.setContent(content);
        return this;
    }

    public News digest(String digest){
        this.article.setDigest(digest);
        return this;
    }

    /**
     * 是否显示封面，1为显示，0为不显示
     */
    public News showCover(int showCover){
        this.article.setShow_cover_pic(showCover);
        return this;
    }

    /**
     * Uint32 是否打开评论，0不打开，1打开
     */
    public News openComment(int openComment){
        this.article.setNeed_open_comment(openComment);
        return this;
    }

    /**
     * Uint32 是否粉丝才可评论，0所有人可评论，1粉丝才可评论
     */
    public News fansCanComment(int fanCanComment){
        this.article.setOnly_fans_can_comment(fanCanComment);
        return this;
    }

    public News end(){
        this.articles.add(this.article);
        return this;
    }

    public News build(){
        return this;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public Article getArticleNum(int index){
        return articles.get(index);
    }
    /**
     * 参数	                是否必须	说明
     * Articles	            是	    图文消息，一个图文消息支持1到8条图文
     * thumb_media_id	    是	    图文消息缩略图的media_id，可以在素材管理-新增素材中获得
     * author	            否	    图文消息的作者
     * title	            是	    图文消息的标题
     * content_source_url	否	    在图文消息页面点击“阅读原文”后的页面，受安全限制，如需跳转Appstore，可以使用itun.es或appsto.re的短链服务，并在短链后增加 #wechat_redirect 后缀。
     * content	            是	    图文消息页面的内容，支持HTML标签。具备微信支付权限的公众号，可以使用a标签，其他公众号不能使用，如需插入小程序卡片，可参考下文。
     * digest	            否	    图文消息的描述，如本字段为空，则默认抓取正文前64个字
     * show_cover_pic	    否	    是否显示封面，1为显示，0为不显示
     * need_open_comment	否	    Uint32 是否打开评论，0不打开，1打开
     * only_fans_can_comment	否	Uint32 是否粉丝才可评论，0所有人可评论，1粉丝才可评论
     */
    @Data
    class Article{
        private String thumb_media_id;
        private String author;
        private String title;
        private String content_source_url;
        private String content;
        private String digest;
        private int show_cover_pic;
        private int need_open_comment;
        private int only_fans_can_comment;
    }

}
