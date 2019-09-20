package com.rkhd.eclient.entity;

import java.util.Map;

/**
 * Created by JYB on 2018/9/24.
 */
public class ParamEntity {
    private String id;
    private String name;
    private String value;
    private Map<Integer,Object> params;

    public ParamEntity() {
    }

    public ParamEntity(String id, String name, String value, Map<Integer, Object> params) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.params = params;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Map<Integer, Object> getParams() {
        return params;
    }

    public void setParams(Map<Integer, Object> params) {
        this.params = params;
    }
}
