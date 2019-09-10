package com.xyq.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @Author xyq
 * @create 2019-09-09 21:14
 */
public class Groups implements Serializable {
    private int gid;
    private String title;
    private String type;
    private List<Dept> allDepts;
    private List<Action> allActions;

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Dept> getAllDepts() {
        return allDepts;
    }

    public void setAllDepts(List<Dept> allDepts) {
        this.allDepts = allDepts;
    }

    public List<Action> getAllActions() {
        return allActions;
    }

    public void setAllActions(List<Action> allActions) {
        this.allActions = allActions;
    }
}
