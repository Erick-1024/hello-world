package com.cana.vbam.common.account.dto;

import com.cana.vbam.common.account.enums.AccountApplyStatus;
import com.cana.vbam.common.dto.CompanyInfoAuditDetail;

/**
 * 账户申请审核详细信息
 * @author XuMeng
 *
 */
public class AccountApplyAuditDetail extends CompanyInfoAuditDetail {
    private static final long serialVersionUID = 8046034661083442454L;

    private String accountApplyId;      // 开户申请ID
    private boolean checkAuthorizationLetter;   //授权书是否有效
    private boolean checkUserType;              //客户类型审核
    private String roleId;      //审核分配角色Id
    private String roleName;    //审核分配角色名称

    public AccountApplyStatus getEnumStatus() {
        if (this.isAccountAuditPassed()) {
            return AccountApplyStatus.ACCEPTED;
        } else {
            return AccountApplyStatus.REJECTED;
        }
    }
    public void setIntAccountAuditStatus(int status) {
        super.setIntUserAuditStatus(status);
        this.checkAuthorizationLetter   = (binary11 & status) > 0;
        this.checkUserType              = (binary12 & status) > 0;
    }
    public int getIntAccountAuditStatus() {
        int status = super.getIntUserAuditStatus();
        status += this.checkAuthorizationLetter     ? binary11 : 0;
        status += this.checkUserType                ? binary12 : 0;
        return status;
    }
    public boolean isAccountAuditPassed() {
        return super.isUserAuditPassed()
                && this.checkAuthorizationLetter
                && this.checkUserType;
    }

    public String getAccountApplyId() {
        return accountApplyId;
    }

    public void setAccountApplyId(String accountApplyId) {
        this.accountApplyId = accountApplyId;
    }

    public boolean isCheckAuthorizationLetter() {
        return checkAuthorizationLetter;
    }

    public void setCheckAuthorizationLetter(boolean checkAuthorizationLetter) {
        this.checkAuthorizationLetter = checkAuthorizationLetter;
    }

    public boolean isCheckUserType() {
        return checkUserType;
    }

    public void setCheckUserType(boolean checkUserType) {
        this.checkUserType = checkUserType;
    }
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
