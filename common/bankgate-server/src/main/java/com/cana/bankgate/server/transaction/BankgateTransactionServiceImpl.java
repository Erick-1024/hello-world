/**
 * Copyright © 2015 Cana. All rights reserved.
 */
package com.cana.bankgate.server.transaction;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.cana.bankgate.server.constants.BankgateConstant;
import com.cana.bankgate.server.mapper.BankgateExtraMapper;
import com.cana.bankgate.server.mapper.gen.BankgateTransMapper;
import com.cana.bankgate.server.po.BankgateTrans;
import com.cana.bankgate.server.po.BankgateTransExample;
import com.cana.bankgate.server.po.BankgateTransExample.Criteria;
import com.cana.bankgate.server.request.account.BankAccountGroup;
import com.cana.bankgate.server.utils.LogExceptionUtil;
import com.cana.vbam.common.bankgate.dto.request.BankAccountBaseDTO;
import com.cana.vbam.common.bankgate.dto.request.BankAccountCreateDTO;
import com.cana.vbam.common.bankgate.dto.request.FreezeFundDTO;
import com.cana.vbam.common.bankgate.dto.request.PlatformWithdrawFundDTO;
import com.cana.vbam.common.bankgate.dto.request.TransferFundDTO;
import com.cana.vbam.common.bankgate.dto.request.UnfreezeFundDTO;
import com.cana.vbam.common.bankgate.dto.response.BankAccountDTO;
import com.cana.vbam.common.bankgate.dto.response.BankgateTransDTO;
import com.cana.vbam.common.bankgate.enums.BankBizType;
import com.cana.vbam.common.bankgate.enums.BankTranStatus;
import com.cana.vbam.common.bankgate.enums.FundBizType;
import com.cana.vbam.common.utils.Constants;
import com.google.common.collect.Lists;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;

/**
 * 数据库事务性的业务流程
 * 
 * @author ducer
 *
 */
@Service("bankgateTransactionService")
@Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ)
public class BankgateTransactionServiceImpl implements IBankgateTransactionService {
  @Resource
  private BankgateTransMapper bankgateTransMapper;
  @Resource
  private BankgateExtraMapper bankgateExtraMapper;
  @Resource
  private SequenceGenerator generator;

  @Override
  public BankgateTrans saveCreateBefore(BankBizType type, BankAccountCreateDTO createDTO) {
    validateDuplicateBiz(type, createDTO.getBusinessSeq());
    
    BankgateTrans trans = wrapperBaseBankgateTransInfo(type);
    trans.setTransDate(createDTO.getTransDate());
    trans.setBusinessSeq(createDTO.getBusinessSeq());
    if(StringUtils.isNoneBlank(createDTO.getBankUserName(),createDTO.getMainAccountNo())) {
      trans.setBankUserName(createDTO.getBankUserName());
      trans.setMainAccountNo(createDTO.getMainAccountNo());
    }else{
      trans.setBankUserName(BankgateConstant.config.getBankUserName());
      trans.setMainAccountNo(BankgateConstant.config.getMainAccountNo());
    }
    
    bankgateTransMapper.insertSelective(trans);
    return trans;
  }

  @Override
  public <T extends BankAccountBaseDTO> BankgateTrans saveTradeBefore(BankBizType type,
      T tradeDTO) {
    validateDuplicateBiz(type, tradeDTO.getBusinessSeq());
    
    BankgateTrans trans = wrapperBaseBankgateTransInfo(type);
    BankAccountGroup group = extractBankAccountGroup(tradeDTO.getAccountNo());
    trans.setBankUserName(group.getBankUserName());
    trans.setMainAccountNo(group.getMainAccountNo());
    trans.setBusinessSeq(tradeDTO.getBusinessSeq());
    trans.setAccountNo(tradeDTO.getAccountNo());
    trans.setTransDate(tradeDTO.getTransDate());
    
    if(type.oneOf(BankBizType.platform_withdraw_fund)) {
      PlatformWithdrawFundDTO withdraw = (PlatformWithdrawFundDTO)tradeDTO;
      trans.setReceiveAccountNo(withdraw.getReceiveAccountNo());
      trans.setAmount(withdraw.getAmount());
    }
    else if(type.oneOf(BankBizType.transfer_fund,BankBizType.adjust_fund,BankBizType.freeze_pay)) {
      TransferFundDTO transfer = (TransferFundDTO)tradeDTO;
      trans.setReceiveAccountNo(transfer.getReceiveAccountNo());
      trans.setAmount(transfer.getAmount());
    }
    else if(type.oneOf(BankBizType.unfreeze_fund,BankBizType.unfreeze_pay)) {
      UnfreezeFundDTO unfreeze = (UnfreezeFundDTO)tradeDTO;
      String originId = extractOriginId(type,unfreeze);
      trans.setReceiveAccountNo(unfreeze.getReceiveAccountNo());
      trans.setAmount(unfreeze.getAmount());
      trans.setOriginId(originId);
    }
    else if(type.oneOf(BankBizType.freeze_fund)) {
      FreezeFundDTO freeze = (FreezeFundDTO)tradeDTO;
      trans.setAmount(freeze.getAmount());
    }
    
    bankgateTransMapper.insertSelective(trans);
    return trans;
  }
  
  private BankgateTrans wrapperBaseBankgateTransInfo(BankBizType type) {
    BankgateTrans trans = new BankgateTrans();
    String gateSeq = generateGateSeq();
    trans.setId(gateSeq);
    trans.setGateSeq(gateSeq);
    trans.setBusinessSeq(type.toString());
    trans.setBusinessType(type.toString());
    trans.setTransType(type.toString());
    trans.setCreateTime(new Date());
    trans.setUpdateTime(new Date());
    trans.setStatus(BankTranStatus.handling.toString());
    return trans;
  }
  /**
   * 验证重复业务流水，重复则抛出异常
   */
  private void validateDuplicateBiz(BankBizType type, String bizSeq) {
    BankgateTransExample ex = new BankgateTransExample();
    Criteria criteria = ex.createCriteria().andBusinessSeqEqualTo(bizSeq);
    criteria.andBusinessTypeEqualTo(type.name());
    List<BankgateTrans> exists = bankgateTransMapper.selectByExample(ex);
    if (!CollectionUtils.isEmpty(exists)) {
      LogExceptionUtil.validateFail("存在相同的业务类型和业务流水号重复请求.");
    }
  }

  /**
   * 根据附属账号提取附属账号所属的主账号和主账号用户名.若附属账号为空，则默认取平台主账号和用户名
   */
  @Override
  public BankAccountGroup extractBankAccountGroup(String accountNo) {
    BankAccountGroup group = new BankAccountGroup();
    if (StringUtils.isBlank(accountNo)) {
      group.setBankUserName(BankgateConstant.config.getBankUserName());
      group.setMainAccountNo(BankgateConstant.config.getMainAccountNo());
      return group;
    }
    BankgateTransExample ex = new BankgateTransExample();
    Criteria criteria = ex.createCriteria().andAccountNoEqualTo(accountNo);
    criteria.andTransTypeEqualTo(BankBizType.create_bank_account.name());
    List<BankgateTrans> transes = bankgateTransMapper.selectByExample(ex);
    if (!CollectionUtils.isEmpty(transes)) {
      group.setBankUserName(transes.get(0).getBankUserName());
      group.setMainAccountNo(transes.get(0).getMainAccountNo());
      return group;
    }
//    group.setBankUserName("knzcgl");
//    group.setMainAccountNo("8110201013300209680");
//    return group;
    String msg = "查询不到附属账号所属的主账号和主账号用户名，该附属账号不在主账号体系类-网关抛出,附属账号：" + accountNo;
    LogExceptionUtil.log(msg);
    throw new RuntimeException(msg);
  }

  /**
   * 解冻和解冻支付的时候获取原网关流水ID,不存在则返回null
   */
  @Override
  public String extractOriginId(BankBizType type, UnfreezeFundDTO unfreeze) {
    if (type.oneOf(BankBizType.unfreeze_fund,BankBizType.unfreeze_pay)) {
      BankgateTransExample ex = new BankgateTransExample();
      Criteria criteria = ex.createCriteria().andAccountNoEqualTo(unfreeze.getAccountNo());
      criteria.andBusinessSeqEqualTo(unfreeze.getOriginBusinessSeq());
      if (BankBizType.unfreeze_fund.equals(type)) {
        criteria.andBusinessTypeEqualTo(BankBizType.freeze_pay.name());
      } else if (BankBizType.unfreeze_pay.equals(type)) {
        criteria.andBusinessTypeEqualTo(BankBizType.freeze_fund.name());
      }
      List<BankgateTrans> transes = bankgateTransMapper.selectByExample(ex);
      if (CollectionUtils.isEmpty(transes)) {
        LogExceptionUtil.logAndthrow("解冻或解冻支付操作：不存在对应的冻结支付或冻结流水");
      } else {
        if (transes.size() > 1) {
          LogExceptionUtil.logAndthrow("解冻或解冻支付操作：存在两条以上相同的流水记录，无法确定原网关流水ID");
        }
        return transes.get(0).getId();
      }
    }
    return null;
  }

  @Override
  public void updateAfterCreateAccount(String id, BankAccountDTO dto) {
    BankgateTrans trans = bankgateExtraMapper.lockById(id);
    validateStatus(trans);
    trans.setStatus(dto.getStatus().name());
    trans.setAccountNo(dto.getAccountNo());
    trans.setUpdateTime(new Date());
    bankgateTransMapper.updateByPrimaryKeySelective(trans);
  }

  @Override
  public void updateStatusById(String id, BankTranStatus status) {
    BankgateTrans trans = bankgateExtraMapper.lockById(id);
    validateStatus(trans);
    trans.setStatus(status.name());
    trans.setUpdateTime(new Date());
    bankgateTransMapper.updateByPrimaryKeySelective(trans);
  }


  @Override
  public List<BankgateTransDTO> query(List<String> bizSeqs) {
    BankgateTransExample ex = new BankgateTransExample();
    ex.createCriteria().andBusinessSeqIn(bizSeqs);
    ex.setOrderByClause("create_time asc");
    List<BankgateTrans> transes = bankgateTransMapper.selectByExample(ex);
    List<BankgateTransDTO> dtos = Lists.newArrayList();
    if (CollectionUtils.isEmpty(transes)) {
      return dtos;
    }
    for (BankgateTrans trans : transes) {
      BankgateTransDTO dto = new BankgateTransDTO();
      BeanUtils.copyProperties(trans, dto);
      dtos.add(dto);
    }
    return dtos;
  }

  @Override
  public BankgateTrans queryGateTransByBizTypeAndSeq(FundBizType type, String bizSeq) {
    BankgateTransExample ex = new BankgateTransExample();
    Criteria criteria = ex.createCriteria().andBusinessSeqEqualTo(bizSeq);
    if (type != null) {
      criteria.andBusinessTypeEqualTo(type.toString());
    }
    List<BankgateTrans> transes = bankgateTransMapper.selectByExample(ex);
    if (CollectionUtils.isEmpty(transes)) {
      // LogExceptionUtil.logAndthrow("该业务类型和业务流水不存在于网关端.");
      return null;
    }
    if (transes.size() != 1) {
      LogExceptionUtil.logAndthrow("该流水号不唯一，请附加交易类型FundBizType进行查询.");
    }
    return transes.get(0);
  }

  /**
   * 验证状态是否符合要求
   */
  private void validateStatus(BankgateTrans trans) {
    if (trans == null || !(BankTranStatus.handling.name().equals(trans.getStatus()) || BankTranStatus.timeout.name().equals(trans.getStatus()))) {
      LogExceptionUtil.logAndthrow("网关流水不存在或流水状态关联异常，只能更新状态为银行处理中的流水记录的状态。");
    }
  }

  /**
   * 生成新的流水号
   */
  private String generateGateSeq() {
    return DateTimeUtil.datetime12()
        + generator.getNextSeq(Constants.SEQUENCE_NAME_BANKGATE_TRANS_ID, 5);
  }
}
