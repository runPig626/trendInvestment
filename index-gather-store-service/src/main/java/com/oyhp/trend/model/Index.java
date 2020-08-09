package com.oyhp.trend.model;

import java.io.Serializable;

/**
 * 指数代码类，用于指数里的名称和代码
 * @author OuYangHaiPing<ouyanghaiping @ pvc123.com>
 * @date 2020-08-08
 */
public class Index implements Serializable{

    /**指数代码*/
    private String code;

    /**指数名称*/
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
