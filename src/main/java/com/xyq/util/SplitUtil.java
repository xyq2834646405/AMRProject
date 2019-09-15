package com.xyq.util;

import com.xyq.action.abs.AbstractAction;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author xyq
 * @create 2019-09-15 00:37
 */
public class SplitUtil {
    private int cp = 1;
    private int ls = 5;
    private String col;
    private String kw;
    private AbstractAction action;

    public SplitUtil(AbstractAction action){
        this.action=action;
    }

    public void setCp(String cp) {
        try {
            this.cp = Integer.parseInt(cp);
        }catch (Exception e){}
    }

    public void setLs(String ls) {
        try {
            this.ls = Integer.parseInt(ls);
        }catch (Exception e){}
    }

    public void setCol(String col) {
        if(col==null||"".equals(col)){
            this.col = action.getDefaultColumn();
        }else{
            this.col = col;
        }
    }

    public void setKw(String kw){
        if (kw==null||"".equals(kw)){
            this.kw="";
        }else {
            this.kw=kw;
        }
    }

    public int getCurrentPage() {
        return cp;
    }

    public int getLineSize() {
        return ls;
    }

    public String getColumn() {
        return col;
    }

    public String getKeyWord() {
        return kw;
    }

    public void setAttribute(HttpServletRequest request,Object allRecorders,String url){
        request.setAttribute("allRecorders",allRecorders);
        request.setAttribute("url",action.getMsg(url));
    }
}
