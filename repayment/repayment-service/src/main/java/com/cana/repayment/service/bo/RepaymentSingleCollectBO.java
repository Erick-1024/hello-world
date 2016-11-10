package com.cana.repayment.service.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.cana.repayment.dao.mapper.gen.RepaymentSingleCollectMapper;
import com.cana.repayment.dao.po.RepaymentSingleCollect;
import com.cana.repayment.dao.po.RepaymentSingleDistributeDetail;
import com.cana.repayment.service.IRepositoryService;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;

public class RepaymentSingleCollectBO extends RepaymentSingleCollect{
	
	private static final long serialVersionUID = 6565606644319525904L;
	
	private IRepositoryService repositoryService = SpringApplicationContext.getApplicationContext().getBean(IRepositoryService.class);
	
	private List<RepaymentSingleDistributeDetailBO> items;
	
	
	public RepaymentSingleCollectBO(String id) {
		this(SpringApplicationContext.getApplicationContext().getBean(RepaymentSingleCollectMapper.class).selectByPrimaryKey(id));
	}

	public RepaymentSingleCollectBO(RepaymentSingleCollect po){
		BeanUtils.copyProperties(po, this);
	}

	/**
	 * 根据还款明细计算总的还款额
	 * @return
	 */
	public long calcTotal() {
		long total = 0L;
		for(RepaymentSingleDistributeDetailBO item : lazyLoadItemBOs())
			total += item.total();
		return total;
	}
	
	/**
	 * 懒加载还款明细
	 * @return
	 */
	public List<RepaymentSingleDistributeDetailBO> lazyLoadItemBOs(){
		if(items != null)
			return items;
		items = new ArrayList<>();
		for(RepaymentSingleDistributeDetail item : repositoryService.getRepaymentItemsByRepaymentId(getId()))
			items.add(new RepaymentSingleDistributeDetailBO(item));
		return items;
	}

	/**
	 * 根据还款明细计算总的本金还款金额
	 * @return
	 */
	public long calcTotalPrincipal() {
		long total = 0L;
		for(RepaymentSingleDistributeDetailBO item : lazyLoadItemBOs())
			total += item.totalPrincipal();
		return total;
	}

}
