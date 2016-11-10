package com.cana.asset.service.transaction.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.cana.asset.dao.mapper.gen.ExpenseMapper;
import com.cana.asset.dao.po.Expense;
import com.cana.asset.dao.po.ExpenseExample;
import com.cana.asset.service.transaction.IAssetExpenseTransactionService;
import com.cana.vbam.common.asset.dto.ExpenseDTO;
import com.cana.vbam.common.asset.enums.ExpenseType;
import com.cana.vbam.common.utils.Constants;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.google.common.collect.Lists;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;

/**
 * @author hu
 *
 */
@Service
public class AssetExpenseTransactionServiceImpl implements IAssetExpenseTransactionService{

	@Resource
	private ExpenseMapper expenseMapper;
	
	@Resource
	private SequenceGenerator seqGen;
	
	@Override
	public void savaExpense(Expense expense) {
		
		expense.setId(generateExpenseId());
		expenseMapper.insertSelective(expense);
	}

	@Override
	public void savaExpenseList(List<Expense> expenseList) {
		for(Expense expense : expenseList){
			expense.setId(generateExpenseId());
			expenseMapper.insertSelective(expense);
		}
	}
	
	@Override
	public void deleteExpense(String id) {
		if(StringUtils.isBlank(id))
			throw WebException.instance("参数不能为空");
		expenseMapper.deleteByPrimaryKey(id);
		
	}
	
	@Override
	public void deleteExpenseByRef(String refid, ExpenseType reftype){
		if(StringUtils.isBlank(refid) || null == reftype)
			throw WebException.instance("参数不能为空");
		ExpenseExample example = new ExpenseExample();
		example.createCriteria().andRefidEqualTo(refid).andReftypeEqualTo(reftype.name());
		expenseMapper.deleteByExample(example);
	}

	@Override
	public List<ExpenseDTO> getExpenseByRefid(String refid, ExpenseType type) {
		if(StringUtils.isBlank(refid) || null == type)
			throw WebException.instance("参数不能为空");
		ExpenseExample example = new ExpenseExample();
		example.createCriteria().andRefidEqualTo(refid).andReftypeEqualTo(type.name());
		List<Expense> expenseList = expenseMapper.selectByExample(example);
		List<ExpenseDTO> expenseDTOList = Lists.newArrayList();
		for(Expense expense : expenseList){
			ExpenseDTO expenseDTO = new ExpenseDTO();
			BeanUtils.copyProperties(expense, expenseDTO);
			expenseDTO.setAmountStr(MoneyArithUtil.convertMoneyToString(expense.getAmount()));
			expenseDTOList.add(expenseDTO);
		}
			
		return expenseDTOList;
	}
	
	private String generateExpenseId(){
		return DateTimeUtil.datetime12()
				+ seqGen.getNextSeq(Constants.SEQUENCE_NAME_ASSET_EXPENSE_ID, 4);
	}

}
