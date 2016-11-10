package com.cana.asset.dao.po;

import java.io.Serializable;

public class ProjectBlobTextWithBLOBs extends ProjectBlobText implements Serializable {
    /**
     *产品描述
     */
    private String productIntroduction;

    /**
     *产品类型描述
     */
    private String productTypeDesc;

    private static final long serialVersionUID = 1L;

    /**
     *产品描述
     */
    public String getProductIntroduction() {
        return productIntroduction;
    }

    /**
     *产品描述
     */
    public void setProductIntroduction(String productIntroduction) {
        this.productIntroduction = productIntroduction == null ? null : productIntroduction.trim();
    }

    /**
     *产品类型描述
     */
    public String getProductTypeDesc() {
        return productTypeDesc;
    }

    /**
     *产品类型描述
     */
    public void setProductTypeDesc(String productTypeDesc) {
        this.productTypeDesc = productTypeDesc == null ? null : productTypeDesc.trim();
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
        ProjectBlobTextWithBLOBs other = (ProjectBlobTextWithBLOBs) that;
        return (this.getProjectId() == null ? other.getProjectId() == null : this.getProjectId().equals(other.getProjectId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getProductIntroduction() == null ? other.getProductIntroduction() == null : this.getProductIntroduction().equals(other.getProductIntroduction()))
            && (this.getProductTypeDesc() == null ? other.getProductTypeDesc() == null : this.getProductTypeDesc().equals(other.getProductTypeDesc()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getProjectId() == null) ? 0 : getProjectId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getProductIntroduction() == null) ? 0 : getProductIntroduction().hashCode());
        result = prime * result + ((getProductTypeDesc() == null) ? 0 : getProductTypeDesc().hashCode());
        return result;
    }
}