package com.cana.asset.service.transaction;

import java.util.List;

import com.cana.asset.dao.po.Expense;
import com.cana.vbam.common.asset.dto.ExpenseDTO;
import com.cana.vbam.common.asset.enums.ExpenseType;

/**
 * @author hu
 *
 */
public interface IAssetExpenseTransactionService {

	/**
	 * 保存费用
	 * @param expense
	 */
	public void savaExpense(Expense expense);
	
	/**
	 * 批量保存费用
	 * @param expense
	 */
	public void savaExpenseList(List<Expense> expense);
	
	/**
	 * 删除费用
	 * @param id
	 */
	public void deleteExpense(String id);
	
	/**
	 * 根据关联id和类型
	 * @param refId
	 * @param refType
	 */
	public void deleteExpenseByRef(String refid, ExpenseType reftype);
	
	/**
	 * 获取费用
	 * @param refid
	 * @param type
	 * @return
	 */
	public List<ExpenseDTO> getExpenseByRefid(String refid, ExpenseType type);
}
