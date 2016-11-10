package com.cana.account.service.converter;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.cana.account.dao.mapper.gen.AccountMapper;
import com.cana.account.dao.po.Account;
import com.cana.account.dao.po.AccountExample;
import com.cana.account.dao.po.AccountTradeRecord;
import com.cana.account.dao.po.AccountTradeRecordPO;
import com.cana.member.dao.po.User;
import com.cana.vbam.common.account.dto.AccountDTO;
import com.cana.vbam.common.account.enums.AccountAccumulationStatus;
import com.cana.vbam.common.account.enums.AccountStatus;
import com.cana.vbam.common.account.enums.AccountSupervisionStatus;
import com.cana.vbam.common.account.dto.AccountTradeRecordDTO;
import com.cana.vbam.common.account.enums.AccountTradeStatus;
import com.cana.vbam.common.account.enums.AccountTradeType;
import com.cana.vbam.common.account.enums.AccountType;
import com.cana.vbam.common.member.enums.user.UserType;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.travelzen.framework.core.util.MoneyUtil;

@Component
public class AccountConverter {

    @Resource
    private AccountMapper accountMapper;

    public AccountDTO convertForDetail(User customer, Account account) {
        if (account == null)
            return null;

        List<AccountDTO> dtos = convertForList(customer, Lists.newArrayList(account));
        AccountDTO dto = dtos.get(0);
        if (AccountAccumulationStatus.HAVE_ACCUMULATION.name().equals(account.getAccumulationStatus())) {
            List<Account> specialAccounts = getSpecialAccounts(account.getId());
            if (CollectionUtils.isNotEmpty(specialAccounts)) {
                List<AccountDTO> specialAccountDTOs = Lists.newArrayList();
                for (Account specialAccount : specialAccounts) {
                    AccountDTO specialDTO = new AccountDTO();
                    specialDTO.setAccountId(specialAccount.getId());
                    specialDTO.setAccountName(specialAccount.getCompanyName());
                    specialDTO.setAccountNo(specialAccount.getAccountNo());
                    specialDTO.setCompanyId(specialAccount.getCompanyId());
                    specialDTO.setBuyerName(specialAccount.getBuyerName());
                    AccountStatus accountStatus = AccountStatus.valueOf(specialAccount.getAccountStatus());
                    specialDTO.setAccountStatusDesc(accountStatus.desc());
                    AccountType accountType = AccountType.valueOf(specialAccount.getAccountType());
                    specialDTO.setAccountTypeDesc(accountType.desc());
                    specialAccountDTOs.add(specialDTO);
                }
                dto.setSpecialAccounts(specialAccountDTOs);
            }
        }
        return dto;
    }

    public List<AccountDTO> convertForList(User customer, List<Account> accounts) {
        if (accounts == null || accounts.isEmpty())
            return Lists.newArrayList();

        List<AccountDTO> accountDTOs = Lists.newArrayList();
        for (Account account : accounts) {
            AccountDTO dto = new AccountDTO();
            dto.setAccountId(account.getId());
            dto.setAccountNo(account.getAccountNo());
            dto.setAccountName(account.getCompanyName());
            AccountType type = AccountType.valueOf(account.getAccountType());
            dto.setAccountType(type);
            dto.setAccountTypeDesc(type.getDesc());
            dto.setCompanyId(account.getCompanyId());
            UserType userType = UserType.valueOf(account.getUserType());
            dto.setUserType(userType);
            if (UserType.FACTOR.equals(userType)) {
            	dto.setFactorId(account.getCompanyId());
                dto.setFactorName(account.getCompanyName());
                dto.setFinaceId(account.getSupervisorId());
                dto.setFinaceName(account.getSupervisorName());
            } else if (UserType.FINACE.equals(userType)) {
            	dto.setFactorId(account.getSupervisorId());
                dto.setFactorName(account.getSupervisorName());
                dto.setFinaceId(account.getCompanyId());
                dto.setFinaceName(account.getCompanyName());
            }else if(UserType.CORECOMPANY.equals(userType))
            	dto.setCoreCompanyName(account.getCompanyName());
            AccountStatus status = AccountStatus.valueOf(account.getAccountStatus());
            dto.setAccountStatus(status);
            dto.setAccountStatusDesc(status.desc());
            dto.setBuyerName(account.getBuyerName());
            AccountSupervisionStatus supervisionStatus = AccountSupervisionStatus.valueOf(account.getSupervisionStatus());
            dto.setSupervisionStatus(supervisionStatus);
            dto.setSupervisionStatusDesc(supervisionStatus.desc());
            AccountAccumulationStatus accumulationStatus = AccountAccumulationStatus.valueOf(account.getAccumulationStatus());
            dto.setAccumulationStatus(accumulationStatus);
            dto.setAccumulationStatusDesc(accumulationStatus.desc());
            if (StringUtils.isNotBlank(account.getAccumulationId())) {
                dto.setAccumulationAccountId(account.getAccumulationId());
            }
            dto.setDefaultRepayment(BooleanUtils.isTrue(account.getIsDefaultRepayment()));
            dto.setTransferInAccount(BooleanUtils.isTrue(account.getIsTransferInAccount()));
            dto.setAccountSupervisionId(account.getAccountSupervisionId());
            if (customer != null) {
                fillAllowOperations(customer, dto, account);
            }
            accountDTOs.add(dto);
        }
        fillAccumulationAccountNo(accountDTOs);
        return accountDTOs;
    }

    /**
     * 转换 收支明细
     * @param accountTradeRecords
     * @return
     */
	public List<AccountTradeRecordDTO> convertAccountTradeRecordList(List<AccountTradeRecord> accountTradeRecords) {
		if (accountTradeRecords == null || accountTradeRecords.isEmpty())
			return Lists.newArrayList();

		List<AccountTradeRecordDTO> accountTradeRecordDTOs = Lists.newArrayList();
		for (AccountTradeRecord accountTradeRecord : accountTradeRecords) {
			AccountTradeRecordDTO dto = new AccountTradeRecordDTO();
			dto.setId(accountTradeRecord.getId());
			dto.setAccountId(accountTradeRecord.getAccountId());
			dto.setBusinessSeq(accountTradeRecord.getBusinessSeq());
			AccountTradeType tradeType = AccountTradeType.valueOf(accountTradeRecord.getTradeType());
			dto.setTradeType(tradeType);
			dto.setOperateType(accountTradeRecord.getOperateType());
			dto.setRemark(accountTradeRecord.getRemark());
			dto.setOppositeAccountName(accountTradeRecord.getOppositeAccountName());
			dto.setOppositeAccountNo(accountTradeRecord.getOppositeAccountNo());
			dto.setAmount(MoneyUtil.cent2Yuan(accountTradeRecord.getAmount() == null ? 0 : accountTradeRecord.getAmount()));
			dto.setTradeStartTime(accountTradeRecord.getTradeStartTime());
			dto.setTradeEndTime(accountTradeRecord.getTradeEndTime());

			AccountTradeStatus tradeStatus = AccountTradeStatus.TRADE_HANDLING;
			if (StringUtils.isNotBlank(accountTradeRecord.getStatus())) {
				tradeStatus = AccountTradeStatus.valueOf(accountTradeRecord.getStatus());
			}
			if (StringUtils.isNotBlank(accountTradeRecord.getAccountSupervisionId())) {
				dto.setAccountSupervisionStatus(AccountSupervisionStatus.HAVE_SUPERVISION);
			}
			dto.setStatus(tradeStatus);
			dto.setTradeTypeDesc(tradeType.getDesc());
			dto.setStatusDesc(tradeStatus.getDesc());
			accountTradeRecordDTOs.add(dto);
		}
		return accountTradeRecordDTOs;
	}
    
    /**
     * 转换 流水明细
     * @param accountTradeRecordDTOs
     * @return
     */
    public List<AccountTradeRecordDTO> convertAccountTradeRecordDTOs(List<AccountTradeRecordPO> accountTradeRecordPOs) {
    	if (accountTradeRecordPOs == null || accountTradeRecordPOs.isEmpty())
    	    return Lists.newArrayList();
    	
    	List<AccountTradeRecordDTO> dtos = Lists.newArrayList();
    	for(AccountTradeRecordPO accountTradeRecordPO : accountTradeRecordPOs){
    		AccountTradeRecordDTO dto = new AccountTradeRecordDTO();
    		dto.setId(accountTradeRecordPO.getId());
    		dto.setBusinessSeq(accountTradeRecordPO.getBusinessSeq());
    		dto.setAccountName(accountTradeRecordPO.getAccountName());
    		dto.setAccountNo(accountTradeRecordPO.getAccountNo());
    		if(StringUtils.isNotBlank(accountTradeRecordPO.getTradeType())){
	    		AccountTradeType tradeType = AccountTradeType.valueOf(accountTradeRecordPO.getTradeType());
	    		dto.setTradeType(tradeType);
	    		dto.setTradeTypeDesc(tradeType.getDesc());
    		}
    		dto.setRemark(accountTradeRecordPO.getRemark());
    		dto.setOperateType(accountTradeRecordPO.getOperateType());
    		dto.setOppositeAccountName(accountTradeRecordPO.getOppositeAccountName());
    		if(StringUtils.isNotBlank(accountTradeRecordPO.getOppositeAccountNo())){
    			dto.setOppositeAccountNo(accountTradeRecordPO.getOppositeAccountNo());
    		}
    		dto.setAmount(MoneyUtil.cent2Yuan(accountTradeRecordPO.getAmount() == null ? 0 : accountTradeRecordPO.getAmount()));
    		dto.setTradeStartTime(accountTradeRecordPO.getTradeStartTime());
    		dto.setTradeEndTime(accountTradeRecordPO.getTradeEndTime());
    		if(StringUtils.isNoneBlank(accountTradeRecordPO.getAccountSupervisionId())){
    			dto.setAccountSupervisionStatusDesc(AccountSupervisionStatus.HAVE_SUPERVISION.getDesc());
	    		UserType userType = UserType.valueOf(accountTradeRecordPO.getUserType());
	    		if(UserType.FACTOR.equals(userType)){
	    			dto.setFactorName(accountTradeRecordPO.getCompanyName());
	    			dto.setFinaceName(accountTradeRecordPO.getSupervisorName());
	    		}
	    		if(UserType.FINACE.equals(userType)){
	    			dto.setFactorName(accountTradeRecordPO.getSupervisorName());
	    			dto.setFinaceName(accountTradeRecordPO.getCompanyName());
	    		}
    		}
    		else
    			dto.setAccountSupervisionStatusDesc(AccountSupervisionStatus.NO_SUPERVISION.getDesc());
    		
    		if(StringUtils.isNotBlank(accountTradeRecordPO.getAccountType())){
	    		AccountType accountType = AccountType.valueOf(accountTradeRecordPO.getAccountType());
	    		dto.setAccountTypeDesc(accountType.getDesc());
    		}
    		if(StringUtils.isNotBlank(accountTradeRecordPO.getStatus())){
	    		AccountTradeStatus status = AccountTradeStatus.valueOf(accountTradeRecordPO.getStatus());
	    		dto.setStatus(status);
	    		dto.setStatusDesc(status.getDesc());
    		}
    		if(StringUtils.isNotBlank(accountTradeRecordPO.getUserType())){
    			UserType userType = UserType.valueOf(accountTradeRecordPO.getUserType());
    			dto.setUserType(userType);
    			dto.setUserTypeDesc(userType.desc());
    		}
    		dtos.add(dto);
    	}
    	return dtos;
    }

    //账户可以进行的操作
    private void fillAllowOperations(User customer, AccountDTO dto, Account account) {
        if (AccountStatus.HANDLING.name().equals(account.getAccountStatus())) {
            // 账户正在处理其他操作中
        } else if (AccountStatus.FROZEN.name().equals(account.getAccountStatus())) {
            // 冻结中的账户
            if (customer.getId().equals(account.getOperateCompanyId())) {
                dto.setAllowUnfreeze(true);
            }
        } else {    //正常状态的账户
            if (checkFreezeAuthority(customer, account)) {
                dto.setAllowFreeze(true);
            }
            // 设为默认账户
            if (UserType.FACTOR.name().equals(customer.getUserType())
                    && AccountType.GENERAL.name().equals(account.getAccountType())
                    && AccountSupervisionStatus.HAVE_SUPERVISION.name().equals(account.getSupervisionStatus())
                    && BooleanUtils.isFalse(account.getIsDefaultRepayment())) {
                dto.setAllowSetDefault(true);
            }
            //解除监管
            if ((customer.getId().equals(account.getCompanyId())
                    || customer.getId().equals(account.getSupervisorId()))
                    && AccountSupervisionStatus.HAVE_SUPERVISION.name().equals(account.getSupervisionStatus())) {
                dto.setAllowRemoveSupervision(true);
            }
        }
    }


    private boolean checkFreezeAuthority(User customer, Account account) {
        if (UserType.CANA.name().equals(customer.getUserType())) {
            return true;
        } else if (UserType.FACTOR.name().equals(customer.getUserType())) {
            if ((customer.getId().equals(account.getCompanyId())
                    || customer.getId().equals(account.getSupervisorId()))
                    && AccountSupervisionStatus.HAVE_SUPERVISION.name().equals(account.getSupervisionStatus())) {
                return true;
            }
        }
        return false;
    }
    
    private void fillAccumulationAccountNo(List<AccountDTO> accountDTOs) {
        Set<String> accumulationIds = Sets.newHashSet();

        for (AccountDTO dto : accountDTOs) {
            if (StringUtils.isNotBlank(dto.getAccumulationAccountId()))
                accumulationIds.add(dto.getAccumulationAccountId());
        }
        if (CollectionUtils.isEmpty(accumulationIds)) return;
        List<Account> accumulationAccounts = getAccountByIds(Lists.newArrayList(accumulationIds));
        if (CollectionUtils.isEmpty(accumulationAccounts)) return;

        Map<String, Account> accumulationAccountMap = Maps.newHashMap();
        for (Account account : accumulationAccounts) {
            accumulationAccountMap.put(account.getId(), account);
        }
        for (AccountDTO dto : accountDTOs) {
            if (StringUtils.isNotBlank(dto.getAccumulationAccountId())) {
                dto.setAccumulationAccountNo(accumulationAccountMap.get(dto.getAccumulationAccountId()).getAccountNo());
                dto.setAccumulationAccountName(accumulationAccountMap.get(dto.getAccumulationAccountId()).getCompanyName());
            }
        }
    }

    private List<Account> getAccountByIds(List<String> accountIds) {
        AccountExample example = new AccountExample();
        example.createCriteria().andIdIn(accountIds);
        List<Account> accounts = accountMapper.selectByExample(example);
        return accounts;
    }

    private List<Account> getSpecialAccounts(String accumulationAccountId) {
        AccountExample example = new AccountExample();
        example.createCriteria().andAccumulationIdEqualTo(accumulationAccountId);
        List<Account> accounts = accountMapper.selectByExample(example);
        return accounts;
    }

}
