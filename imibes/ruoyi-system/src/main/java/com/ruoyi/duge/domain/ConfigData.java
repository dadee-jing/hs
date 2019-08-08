package com.ruoyi.duge.domain;

import com.ruoyi.common.base.BaseEntity;

public class ConfigData extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String key;
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
