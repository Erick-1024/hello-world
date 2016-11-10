/**
* Copyright (c) 2016, Cana and/or its affiliates. All rights reserved.
*/
package com.cana.common.dao.po;

import java.io.Serializable;

public class CanaRSAKey implements Serializable {
    /**
    *机构ID
    */
    private String institutionId;

    /**
    *公钥
    */
    private String publicKey;

    /**
    *私钥
    */
    private String privateKey;

    private static final long serialVersionUID = 1L;

    /**
    *机构ID
    */
    public String getInstitutionId() {
        return institutionId;
    }

    /**
    *机构ID
    */
    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId == null ? null : institutionId.trim();
    }

    /**
    *公钥
    */
    public String getPublicKey() {
        return publicKey;
    }

    /**
    *公钥
    */
    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey == null ? null : publicKey.trim();
    }

    /**
    *私钥
    */
    public String getPrivateKey() {
        return privateKey;
    }

    /**
    *私钥
    */
    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey == null ? null : privateKey.trim();
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
        CanaRSAKey other = (CanaRSAKey) that;
        return (this.getInstitutionId() == null ? other.getInstitutionId() == null : this.getInstitutionId().equals(other.getInstitutionId()))
            && (this.getPublicKey() == null ? other.getPublicKey() == null : this.getPublicKey().equals(other.getPublicKey()))
            && (this.getPrivateKey() == null ? other.getPrivateKey() == null : this.getPrivateKey().equals(other.getPrivateKey()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getInstitutionId() == null) ? 0 : getInstitutionId().hashCode());
        result = prime * result + ((getPublicKey() == null) ? 0 : getPublicKey().hashCode());
        result = prime * result + ((getPrivateKey() == null) ? 0 : getPrivateKey().hashCode());
        return result;
    }
}