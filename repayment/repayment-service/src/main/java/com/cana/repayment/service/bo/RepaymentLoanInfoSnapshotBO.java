package com.cana.repayment.service.bo;

import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.cana.repayment.dao.mapper.IRepaymentLoanInfoSnapshotMapper;
import com.cana.repayment.dao.mapper.gen.RepaymentLoanInfoSnapshotMapper;
import com.cana.repayment.dao.po.RepaymentLoanInfoSnapshot;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;

public class RepaymentLoanInfoSnapshotBO extends RepaymentLoanInfoSnapshot{
	
	private static final long serialVersionUID = -2646656108445061245L;
	
	@Resource
	private IRepaymentLoanInfoSnapshotMapper loanInfoSnapshotMapper = SpringApplicationContext.getApplicationContext().getBean(IRepaymentLoanInfoSnapshotMapper.class);
	
	private RepaymentLoanInfoSnapshotMapper loanInfoSnapshotBaseMapper = SpringApplicationContext.getApplicationContext().getBean(RepaymentLoanInfoSnapshotMapper.class);

	private RepaymentLoanInfoSnapshotBO lastSnapshot;
	
	public RepaymentLoanInfoSnapshotBO(RepaymentLoanInfoSnapshot po){
		BeanUtils.copyProperties(po, this);
	}

	/**
	 * 获取上一个版本的快照
	 * @return
	 */
	public RepaymentLoanInfoSnapshotBO lazyLoadLastSnapshot()
	{
		if(lastSnapshot != null)
			return lastSnapshot;
		
		if(StringUtils.isEmpty(getLastVersion()))
			return null;
		lastSnapshot = new RepaymentLoanInfoSnapshotBO(loanInfoSnapshotMapper.getByLoanInfoIdAndCurrentVersion(getLoanInfoId(), getLastVersion()));
		return lastSnapshot;
	}
	
	/**
	 * 更新自定义数据
	 * @param string
	 * @param businessSeq
	 */
	public void updateExtraData(String key, String value) {
		HashMap<String, String> extraData = new HashMap<>();
		if(StringUtils.isNotBlank(getExtraData()))
			extraData = new Gson().fromJson(getExtraData(), new TypeToken<HashMap<String, String>>(){}.getType());
		extraData.put(key, value);
		setExtraData(new Gson().toJson(extraData));
		loanInfoSnapshotBaseMapper.updateByPrimaryKey(this);
	}

	/**
	 * 获取指定key值的自定义数据
	 * @param key
	 * @return
	 */
	public String extraData(String key) {
		HashMap<String, String> extraData = new HashMap<>();
		if(StringUtils.isNotBlank(getExtraData()))
			extraData = new Gson().fromJson(getExtraData(), new TypeToken<HashMap<String, String>>(){}.getType());
		return extraData.get(key);
	}
}
