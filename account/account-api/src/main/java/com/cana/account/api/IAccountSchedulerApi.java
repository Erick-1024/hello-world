package com.cana.account.api;

public interface IAccountSchedulerApi {

    /**
     * 定时更新交易记录接口，由账户定时器调用，返回此次任务的执行时间明细
     */
    public String autoUpdateTradeRecordStatus();
    
    /**
     * 定时更新提现手续费
     * @return
     */
    public String autoUpdateWithdrawFee();
}
