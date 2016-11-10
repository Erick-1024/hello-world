/**
 * Copyright (c) 2016-2100 Cana, Inc. All rights reserved.
 */
package com.cana.bankgate.server.utils;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;

import com.cana.bankgate.server.response.BankBaseResult;
import com.cana.bankgate.server.response.account.BankAccountBalanceData;
import com.cana.bankgate.server.response.account.BankAccountBalanceResult;
import com.cana.bankgate.server.response.account.BankAccountBaseResult;
import com.cana.bankgate.server.response.account.BankAccountSignUpStateData;
import com.cana.bankgate.server.response.account.BankAccountSignUpStateResult;
import com.cana.bankgate.server.response.account.BankInfoData;
import com.cana.bankgate.server.response.account.BankInfoResult;
import com.cana.bankgate.server.response.account.BankMainAccountBalanceData;
import com.cana.bankgate.server.response.account.BankMainAccountBalanceResult;
import com.cana.bankgate.server.response.flow.BankAccountTradeFlowData;
import com.cana.bankgate.server.response.flow.BankAccountTradeFlowResult;
import com.cana.bankgate.server.response.flow.BankMainAccountTradeFlowData;
import com.cana.bankgate.server.response.flow.BankMainAccountTradeFlowResult;
import com.cana.bankgate.server.response.fund.FreezeInfoData;
import com.cana.bankgate.server.response.fund.FreezeInfoResult;
import com.cana.bankgate.server.response.fund.TradeStatusData;
import com.cana.bankgate.server.response.fund.TradeStatusResult;
import com.cana.bankgate.server.response.trade.BankTradeDetailData;
import com.cana.bankgate.server.response.trade.BankTradeDetailResult;
import com.cana.vbam.common.bankgate.dto.response.BankAccountBalanceDataDTO;
import com.cana.vbam.common.bankgate.dto.response.BankAccountBalanceResultDTO;
import com.cana.vbam.common.bankgate.dto.response.BankAccountDTO;
import com.cana.vbam.common.bankgate.dto.response.BankAccountSignUpStateDataDTO;
import com.cana.vbam.common.bankgate.dto.response.BankAccountSignUpStateResultDTO;
import com.cana.vbam.common.bankgate.dto.response.BankAccountTradeFlowDataDTO;
import com.cana.vbam.common.bankgate.dto.response.BankAccountTradeFlowResultDTO;
import com.cana.vbam.common.bankgate.dto.response.BankBaseResultDTO;
import com.cana.vbam.common.bankgate.dto.response.BankInfoDataDTO;
import com.cana.vbam.common.bankgate.dto.response.BankInfoResultDTO;
import com.cana.vbam.common.bankgate.dto.response.BankMainAccountBalanceDataDTO;
import com.cana.vbam.common.bankgate.dto.response.BankMainAccountBalanceResultDTO;
import com.cana.vbam.common.bankgate.dto.response.BankMainAccountTradeFlowDataDTO;
import com.cana.vbam.common.bankgate.dto.response.BankMainAccountTradeFlowResultDTO;
import com.cana.vbam.common.bankgate.dto.response.BankTradeDetailDataDTO;
import com.cana.vbam.common.bankgate.dto.response.BankTradeDetailResultDTO;
import com.cana.vbam.common.bankgate.dto.response.FreezeInfoDataDTO;
import com.cana.vbam.common.bankgate.dto.response.FreezeInfoResultDTO;
import com.cana.vbam.common.bankgate.dto.response.TradeStatusDataDTO;
import com.cana.vbam.common.bankgate.dto.response.TradeStatusResultDTO;
import com.google.common.collect.Lists;
import com.travelzen.framework.core.time.DateTimeUtil;

/**
 * @author ducer
 *
 */
public class ResultConvertUtil {

  public static BankMainAccountBalanceResultDTO convertResult(BankMainAccountBalanceResult result) {
    BankMainAccountBalanceResultDTO resultDTO = new BankMainAccountBalanceResultDTO();
    resultDTO.setStatus(result.getStatus().toBankTranStatus());
    resultDTO.setStatusText(result.getStatusText());
    List<BankMainAccountBalanceData> datas = result.getBankMainAccountBalanceDatas();
    List<BankMainAccountBalanceDataDTO> dataDTOs = Lists.newArrayList();
    if (CollectionUtils.isNotEmpty(datas)) {
      for (BankMainAccountBalanceData data : datas) {
        BankMainAccountBalanceDataDTO dataDTO = new BankMainAccountBalanceDataDTO();
        BeanUtils.copyProperties(data, dataDTO);
        dataDTOs.add(dataDTO);
      }
    }
    resultDTO.setBankMainAccountBalanceDatas(dataDTOs);
    return resultDTO;
  }
  
  public static BankTradeDetailResultDTO convertResult(BankTradeDetailResult result) {
    BankTradeDetailResultDTO resultDTO = new BankTradeDetailResultDTO();
    resultDTO.setStatus(result.getStatus().toBankTranStatus());
    resultDTO.setStatusText(result.getStatusText());
    List<BankTradeDetailData> datas = result.getBankTradeDetailDatas();
    List<BankTradeDetailDataDTO> dataDTOs = Lists.newArrayList();
    if (CollectionUtils.isNotEmpty(datas)) {
      for (BankTradeDetailData data : datas) {
    	BankTradeDetailDataDTO dataDTO = new BankTradeDetailDataDTO();
        BeanUtils.copyProperties(data, dataDTO);
        String date = DateTimeUtil.getDateForm(data.getTradeDate()) + " " + DateTimeUtil.getTimeForm(data.getTradeTime());
        dataDTO.setTranDate(date);
        dataDTOs.add(dataDTO);
      }
    }
    resultDTO.setBankTradeDetailDatas(dataDTOs);
    return resultDTO;
  }

  public static BankMainAccountTradeFlowResultDTO convertResult(BankMainAccountTradeFlowResult result) {
    BankMainAccountTradeFlowResultDTO resultDTO = new BankMainAccountTradeFlowResultDTO();
    BeanUtils.copyProperties(result, resultDTO);
    List<BankMainAccountTradeFlowData> datas = result.getBankMainAccountTradeFlowDatas();
    List<BankMainAccountTradeFlowDataDTO> dataDTOs = Lists.newArrayList();
    if (CollectionUtils.isNotEmpty(datas)) {
      for (BankMainAccountTradeFlowData data : datas) {
        BankMainAccountTradeFlowDataDTO dataDTO = new BankMainAccountTradeFlowDataDTO();
        BeanUtils.copyProperties(data, dataDTO);
        dataDTOs.add(dataDTO);
      }
    }
    resultDTO.setBankMainAccountTradeFlowDatas(dataDTOs);
    resultDTO.setStatus(result.getStatus().toBankTranStatus());
    return resultDTO;
  }
  
  public static BankAccountTradeFlowResultDTO convertResult(BankAccountTradeFlowResult result) {
    BankAccountTradeFlowResultDTO resultDTO = new BankAccountTradeFlowResultDTO();
    resultDTO.setStatus(result.getStatus().toBankTranStatus());
    resultDTO.setStatusText(result.getStatusText());
    resultDTO.setSize(result.getSize());
    List<BankAccountTradeFlowData> datas = result.getBankAccountTradeFlowDatas();
    List<BankAccountTradeFlowDataDTO> dataDTOs = Lists.newArrayList();
    if (CollectionUtils.isNotEmpty(datas)) {
      for (BankAccountTradeFlowData data : datas) {
        BankAccountTradeFlowDataDTO dataDTO = new BankAccountTradeFlowDataDTO();
        BeanUtils.copyProperties(data, dataDTO);
        dataDTOs.add(dataDTO);
      }
    }
    resultDTO.setBankAccountTradeFlowDatas(dataDTOs);
    return resultDTO;
  }
  
  public static BankInfoResultDTO convertResult(BankInfoResult result) {
    BankInfoResultDTO infoDTO = new BankInfoResultDTO();
    infoDTO.setStatus(result.getStatus().toBankTranStatus());
    infoDTO.setStatusText(result.getStatusText());
    List<BankInfoData> datas = result.getBankInfoDatas();
    List<BankInfoDataDTO> dataDTOs = Lists.newArrayList();
    if (CollectionUtils.isNotEmpty(datas)) {
      for (BankInfoData data : datas) {
        BankInfoDataDTO dataDTO = new BankInfoDataDTO();
        BeanUtils.copyProperties(data, dataDTO);
        dataDTOs.add(dataDTO);
      }
    }
    infoDTO.setBankInfoDatas(dataDTOs);
    return infoDTO;
  }
  
  public static BankAccountDTO convertResult(BankAccountBaseResult result) {
    BankAccountDTO resultDTO = new BankAccountDTO();
    resultDTO.setStatus(result.getStatus().toBankTranStatus());
    resultDTO.setStatusText(result.getStatusText());
    resultDTO.setAccountName(result.getAccountName());
    resultDTO.setAccountNo(result.getAccountNo());
    return resultDTO;
  }
  
  public static BankBaseResultDTO convertResult(BankBaseResult result) {
    BankBaseResultDTO resultDTO = new BankBaseResultDTO();
    resultDTO.setStatus(result.getStatus().toBankTranStatus());
    resultDTO.setStatusText(result.getStatusText());
    return resultDTO;
  }
  
  public static FreezeInfoResultDTO convertResult(FreezeInfoResult result) {
    FreezeInfoResultDTO resultDTO = new FreezeInfoResultDTO();
    resultDTO.setStatus(result.getStatus().toBankTranStatus());
    resultDTO.setStatusText(result.getStatusText());
    List<FreezeInfoData> datas = result.getFreezeInfoDatas();
    List<FreezeInfoDataDTO> dataDTOs = Lists.newArrayList();
    if (CollectionUtils.isNotEmpty(datas)) {
      for (FreezeInfoData data : datas) {
        FreezeInfoDataDTO dataDTO = new FreezeInfoDataDTO();
        BeanUtils.copyProperties(data, dataDTO);
        dataDTOs.add(dataDTO);
      }
    }
    resultDTO.setFreezeInfoDatas(dataDTOs);
    return resultDTO;
  }
  
  public static TradeStatusResultDTO convertResult(TradeStatusResult result) {
    TradeStatusResultDTO resultDTO = new TradeStatusResultDTO();
    resultDTO.setStatus(result.getStatus().toBankTranStatus());
    resultDTO.setStatusText(result.getStatusText());
    List<TradeStatusData> datas = result.getTradeStatusDatas();
    List<TradeStatusDataDTO> dataDTOs = Lists.newArrayList();
    if (CollectionUtils.isNotEmpty(datas)) {
      for (TradeStatusData data : datas) {
        TradeStatusDataDTO dataDTO = new TradeStatusDataDTO();
        dataDTO.setStatus(data.getStatus().toBankTranStatus());
        dataDTO.setStatusText(data.getStatusText());
        dataDTO.setTradeStatusFlag(data.getTradeStatusFlag());
        dataDTOs.add(dataDTO);
      }
    }
    resultDTO.setTradeStatusDatas(dataDTOs);
    return resultDTO;
  }
  
  public static BankAccountBalanceResultDTO convertResult(BankAccountBalanceResult result) {
    BankAccountBalanceResultDTO resultDTO = new BankAccountBalanceResultDTO();
    resultDTO.setStatus(result.getStatus().toBankTranStatus());
    resultDTO.setStatusText(result.getStatusText());
    List<BankAccountBalanceData> datas = result.getAccountBalanceDatas();
    List<BankAccountBalanceDataDTO> dataDTOs = Lists.newArrayList();
    if (CollectionUtils.isNotEmpty(datas)) {
      for (BankAccountBalanceData data : datas) {
        BankAccountBalanceDataDTO dataDTO = new BankAccountBalanceDataDTO();
        BeanUtils.copyProperties(data, dataDTO);
        dataDTOs.add(dataDTO);
      }
    }
    resultDTO.setBankAccountBalanceDatas(dataDTOs);
    return resultDTO;
  }
  
  public static BankAccountSignUpStateResultDTO convertResult(BankAccountSignUpStateResult result){
	  BankAccountSignUpStateResultDTO resultDTO = new BankAccountSignUpStateResultDTO();
	  resultDTO.setStatus(result.getStatus().toBankTranStatus());
	  resultDTO.setStatusText(result.getStatusText());
	  List<BankAccountSignUpStateData> datas = result.getBankAccountSingUpStateDatas();
	  List<BankAccountSignUpStateDataDTO> dataDTOs = Lists.newArrayList();
	  if (CollectionUtils.isNotEmpty(datas)) {
	      for (BankAccountSignUpStateData data : datas) {
	    	  BankAccountSignUpStateDataDTO dataDTO = new BankAccountSignUpStateDataDTO();
	    	  BeanUtils.copyProperties(data, dataDTO);
	    	  dataDTOs.add(dataDTO);
	      	}
	    }
	  return resultDTO;
  }
}
