package com.cana.vbam.common.account.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 新建监管关系DTO
 * @author XuMeng
 *
 */
public class AccountSupervisionCreateDTO implements Serializable {
    private static final long serialVersionUID = 3995080766027261789L;

    private String supervisionCompanyName;  //监管客户企业名称
    private String supervisionAccountId;    //使用一个已有账户ID作为监管账户
    private Boolean isDefaultRepayment;     //是否设置成默认还款账户
    private List<String> specialAccountIds; //关联的专用账户ID列表

    public String getSupervisionCompanyName() {
        return supervisionCompanyName;
    }
    public void setSupervisionCompanyName(String supervisionCompanyName) {
        this.supervisionCompanyName = supervisionCompanyName;
    }
    public String getSupervisionAccountId() {
        return supervisionAccountId;
    }
    public void setSupervisionAccountId(String supervisionAccountId) {
        this.supervisionAccountId = supervisionAccountId;
    }
    public Boolean getIsDefaultRepayment() {
        return isDefaultRepayment;
    }
    public void setIsDefaultRepayment(Boolean isDefaultRepayment) {
        this.isDefaultRepayment = isDefaultRepayment;
    }
    public List<String> getSpecialAccountIds() {
        return specialAccountIds;
    }
    public void setSpecialAccountIds(List<String> specialAccountIds) {
        this.specialAccountIds = specialAccountIds;
    }
}
