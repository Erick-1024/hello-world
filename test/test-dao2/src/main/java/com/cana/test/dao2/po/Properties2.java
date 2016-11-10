/**
* Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
*/
package com.cana.test.dao2.po;

import java.io.Serializable;

public class Properties2 implements Serializable {
    /**
    *主键
    */
    private String id;

    /**
    *版本号
    */
    private String name;

    /**
    *对应key的值
    */
    private String value;

    private static final long serialVersionUID = 1L;

    /**
    *主键
    */
    public String getId() {
        return id;
    }

    /**
    *主键
    */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
    *版本号
    */
    public String getName() {
        return name;
    }

    /**
    *版本号
    */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
    *对应key的值
    */
    public String getValue() {
        return value;
    }

    /**
    *对应key的值
    */
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Properties2 other = (Properties2) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getValue() == null ? other.getValue() == null : this.getValue().equals(other.getValue()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getValue() == null) ? 0 : getValue().hashCode());
        return result;
    }
}