/**
 * 
 */
package com.cana.yundaex.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.cana.yundaex.dao.mapper.gen.YundaexLoanInfoRecordMapper;
import com.cana.yundaex.dao.po.YundaexLoanInfoRecord;
import com.cana.yundaex.dao.po.YundaexLoanInfoRecordExample;
import com.cana.yundaex.service.IYundaexLoanInfoRecordService;
import com.travelzen.framework.core.exception.WebException;

/**
 * 韵达项目- 放款记录服务实现类
 * 
 * @author guguanggong
 *
 */
@Service
public class YundaexLoanInfoRecordServiceImpl implements IYundaexLoanInfoRecordService {

	@Resource
	private YundaexLoanInfoRecordMapper yundaexLoanInfoRecordMapper;

	/**
	 * 根据流水号查放款记录
	 * @return
	 */
	@Override
	public YundaexLoanInfoRecord getYundaexLoanInfoRecord(String businessSeq) {
		YundaexLoanInfoRecordExample example = new YundaexLoanInfoRecordExample();
		YundaexLoanInfoRecordExample.Criteria criteria = example.createCriteria();
		criteria.andBusinessSeqEqualTo(businessSeq);
		List<YundaexLoanInfoRecord> yundaexLoanInfoRecords = yundaexLoanInfoRecordMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(yundaexLoanInfoRecords)) {
			throw WebException.instance("没有放款记录");
		}
		YundaexLoanInfoRecord yundaexLoanInfoRecord = yundaexLoanInfoRecords.get(0);
		return yundaexLoanInfoRecord;
	}

	/**
	 * 根据融资客户ID查询放款记录条数
	 * @param financeId
	 * @return
	 */
	@Override
	public int countYundaexLoanInfoRecord(String financeId) {
		YundaexLoanInfoRecordExample example = new YundaexLoanInfoRecordExample();
		YundaexLoanInfoRecordExample.Criteria criteria = example.createCriteria();
		criteria.andFinanceIdEqualTo(financeId);
		return yundaexLoanInfoRecordMapper.countByExample(example);
	}
}
