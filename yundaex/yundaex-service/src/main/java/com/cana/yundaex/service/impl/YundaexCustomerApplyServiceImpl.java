/**
 * 
 */
package com.cana.yundaex.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cana.yundaex.common.dto.YundaexCustomerApplyDTO;
import com.cana.yundaex.common.dto.YundaexCustomerApplyQueryDTO;
import com.cana.yundaex.dao.mapper.gen.YundaexCustomerApplyMapper;
import com.cana.yundaex.dao.po.YundaexCustomerApply;
import com.cana.yundaex.dao.po.YundaexCustomerApplyExample;
import com.cana.yundaex.service.IYundaexCustomerApplyService;

/**
 * 韵达项目-韵达 客户申请资料 操作服务
 * 
 * @author guguanggong
 *
 */
@Service
public class YundaexCustomerApplyServiceImpl implements IYundaexCustomerApplyService {

	@Resource
	private YundaexCustomerApplyMapper yundaexCustomerApplyMapper;

	/*
	 * 根据参数查询韵达客户申请资料
	 */
	@Override
	public List<YundaexCustomerApplyDTO> getYdCustApplyByParam(YundaexCustomerApplyQueryDTO applyQueryDTO) {
		YundaexCustomerApplyExample example = new YundaexCustomerApplyExample();
		YundaexCustomerApplyExample.Criteria criteria = example.createCriteria();
		if (applyQueryDTO == null) {
			return null;
		}
		String stationNo = applyQueryDTO.getStationNo(); // 网点编号
		String whetherStationInfo = applyQueryDTO.getWhetherStationInfo();
		if (StringUtils.isNotBlank(stationNo)) {
			criteria.andStationNoEqualTo(stationNo);
		}
		if (StringUtils.isNotBlank(whetherStationInfo)) {
			criteria.andWhetherStationInfoEqualTo(whetherStationInfo);
		}

		List<YundaexCustomerApply> yundaexCustomerApplys = yundaexCustomerApplyMapper.selectByExample(example);

		List<YundaexCustomerApplyDTO> customerApplyDTOs = new ArrayList<YundaexCustomerApplyDTO>();
		if (CollectionUtils.isEmpty(yundaexCustomerApplys)) {
			return customerApplyDTOs;
		}

		for (YundaexCustomerApply customerApply : yundaexCustomerApplys) {
			YundaexCustomerApplyDTO applyDTO = new YundaexCustomerApplyDTO();
			BeanUtils.copyProperties(customerApply, applyDTO);
			customerApplyDTOs.add(applyDTO);
		}

		return customerApplyDTOs;
	}

	@Override
	public void updateCustomerApplyById(YundaexCustomerApplyDTO dto) {
		YundaexCustomerApply yundaexCustomerApply = new YundaexCustomerApply();
		BeanUtils.copyProperties(dto, yundaexCustomerApply);
		yundaexCustomerApplyMapper.updateByPrimaryKeySelective(yundaexCustomerApply);
	}

}
