package com.rdc.sumiy.swiftgankio.model;
/**
 * Created by sumiy on 2016/8/14.
 */
import com.rdc.sumiy.swiftgankio.utils.factory.ArticleTitle;

/**
 * Created by sumiy on 2016/8/14.
 */
public class QianduanModel  extends ArticleTitle{
    private String desc;
    private String url;
    private String createdAt;
    private String who;
    public QianduanModel(String url, String desc, String createdAt, String who) {
        this.url = url;
        this.desc = desc;
        this.createdAt = createdAt;
        this.who = who;
    }
    public QianduanModel() {
    }
    @Override
    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public void setWho(String who) {
        this.who = who;
    }
    public String getDesc() {
        return desc;
    }
    public String getUrl() {
        return url;
    }
    public String getCreatedAt() {
        return createdAt;
    }
    public String getWho() {
        return who;
    }
}