package com.oyhp.trend.model;

import java.io.Serializable;

/**
 * 指数数据模型
 * @author OYHP
 */
public class IndexData implements Serializable{

    private static final long serialVersionUID = 11153566813408046L;
    /** 指数数据日期 */
    private String date;
    /** 指数闭合点 */
    private float closePoint;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getClosePoint() {
        return closePoint;
    }

    public void setClosePoint(float closePoint) {
        this.closePoint = closePoint;
    }
     
}