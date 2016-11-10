package com.cana.vbam.front.biz.vo.account;

public class CreateSupervisionResult {

    private boolean result; //创建监管是否成功
    private String reason;  //失败时的原因
    private String applyId;   //成功时的申请Id

    public boolean isResult() {
        return result;
    }
    public void setResult(boolean result) {
        this.result = result;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public String getApplyId() {
        return applyId;
    }
    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }
    
}
