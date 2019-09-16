package com.xyq.vo;

import java.io.Serializable;

/**
 * @Author xyq
 * @create 2019-09-16 12:13
 */
public class Subtype implements Serializable {
    private Integer stid;
    private Type type;
    private String title;

    public Integer getStid() {
        return stid;
    }

    public void setStid(Integer stid) {
        this.stid = stid;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
