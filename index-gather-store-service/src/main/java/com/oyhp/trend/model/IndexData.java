package com.oyhp.trend.model;

/**
 * 指数代码数据点，日期，收盘点
 * @author OuYangHaiPing<ouyanghaiping @ pvc123.com>
 * @date 2020-08-09
 */
public class IndexData {

    /** 交易日期 */
    private String date;

    /** 收盘点 */
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
