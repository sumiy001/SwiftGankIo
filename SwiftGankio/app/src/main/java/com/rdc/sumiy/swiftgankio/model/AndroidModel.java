package com.rdc.sumiy.swiftgankio.model;

import com.rdc.sumiy.swiftgankio.utils.factory.ArticleTitle;

/**
 * Created by sumiy on 2016/8/12.
 */
public class AndroidModel extends ArticleTitle {
    private String desc;
    private String url;
    private String createdAt;
    private String who;

    public AndroidModel(String url, String desc, String createdAt, String who) {
        this.url = url;
        this.desc = desc;
        this.createdAt = createdAt;
        this.who = who;
    }
    public AndroidModel() {
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
}
