package com.xyq.vo;

import java.io.Serializable;

/**
 * @Author xyq
 * @create 2019-09-09 21:13
 */
public class Action implements Serializable {
    private Integer actid;
    private Groups groups;
    private String title;
    private String url;
    private Integer sflag;

    public Integer getActid() {
        return actid;
    }

    public void setActid(Integer actid) {
        this.actid = actid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSflag() {
        return sflag;
    }

    public void setSflag(Integer sflag) {
        this.sflag = sflag;
    }

    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }
}
