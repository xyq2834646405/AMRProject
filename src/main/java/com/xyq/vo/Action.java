package com.xyq.vo;

import java.io.Serializable;

/**
 * @Author xyq
 * @create 2019-09-09 21:13
 */
public class Action implements Serializable {
    private int actid;
    private Groups groups;
    private String title;
    private String url;
    private int sflag;

    public int getActid() {
        return actid;
    }

    public void setActid(int actid) {
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

    public int getSflag() {
        return sflag;
    }

    public void setSflag(int sflag) {
        this.sflag = sflag;
    }

    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }
}
