package com.xyq.vo;

import java.io.Serializable;

/**
 * @Author xyq
 * @create 2019-09-16 10:43
 */
public class Type implements Serializable {
    private Integer tid;
    private String title;

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
