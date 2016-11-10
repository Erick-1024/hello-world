package com.cana.yundaex.service.impl;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.cana.member.api.IUserApi;
import com.cana.vbam.common.yundaex.dto.apply.YdCustomerApplyDetailDTO;
import com.cana.vbam.common.yundaex.dto.apply.YdCustomerApplyListQueryDTO;
import com.cana.vbam.common.yundaex.dto.apply.YdCustomerApplyMinDTO;
import com.cana.yundaex.common.dto.apply.YdCustomerApplyAddRequestDTO;
import com.cana.yundaex.common.enums.TimeUnit;
import com.cana.yundaex.common.enums.YundaexAuditState;
import com.cana.yundaex.dao.mapper.gen.YundaexCustomerApplyMapper;
import com.cana.yundaex.dao.mapper.gen.YundaexOutCustomerMapper;
import com.cana.yundaex.dao.po.YundaexCustomerApply;
import com.cana.yundaex.dao.po.YundaexCustomerApplyExample;
import com.cana.yundaex.dao.po.YundaexCustomerApplyExample.Criteria;
import com.cana.yundaex.dao.po.YundaexOutCustomer;
import com.cana.yundaex.dao.po.YundaexOutCustomerExample;
import com.cana.yundaex.service.IYundaexAuditService;
import com.cana.yundaex.service.convertors.YundaexCustomerApplyConvert;
import com.cana.yundaex.service.utils.ValidatorUtils;
import com.travelzen.framework.common.PageList;
import com.travelzen.framework.core.exception.WebException;

/**
 * 韵达项目-审核服务接口 实现类
 * 
 * @author xiaoyu
 *
 */
@Service
public class YundaexAuditServiceImpl implements IYundaexAuditService {

	@Resource
	private YundaexCustomerApplyMapper  yundaexCustomerApplyMapper;
	
	@Resource
	private IUserApi userApiImpl;
	
	@Resource
	private YundaexOutCustomerMapper ydOutCustomerMapper;
	
	/**
	 * 保存补充资料到韵达客户申请表中
	 */
	@Override
	public void saveAdditionInfo(YdCustomerApplyAddRequestDTO ydCustomerApplyAddRequestDTO) {
		
		ValidatorUtils.checkAdditionInfo(ydCustomerApplyAddRequestDTO);
		String stationNo = ydCustomerApplyAddRequestDTO.getStationNo(); 
		YundaexCustomerApplyExample example = new YundaexCustomerApplyExample();
		example.createCriteria().andStationNoEqualTo(stationNo);
		
		YundaexCustomerApply yundaexCustomerApply = new YundaexCustomerApply();
		BeanUtils.copyProperties(ydCustomerApplyAddRequestDTO, yundaexCustomerApply);
		yundaexCustomerApply = YundaexCustomerApplyConvert.convertApplyAddDTO2CustomerApply(ydCustomerApplyAddRequestDTO,yundaexCustomerApply);
		// 首次申请 默认借款期限为3个月
		yundaexCustomerApply.setShortLoanLimit(3);
		yundaexCustomerApply.setLimitUnit(TimeUnit.MONTH.name());
		yundaexCustomerApplyMapper.updateByExampleSelective(yundaexCustomerApply,example);
		
	}

	
	/**
	 * 获取客户审核列表信息
	 */
	@Override
	public PageList<YdCustomerApplyMinDTO> getYundaexAuditList(YdCustomerApplyListQueryDTO ydCustomerApplyListQueryDTO) {
		PageList<YdCustomerApplyMinDTO> result = new PageList<YdCustomerApplyMinDTO>();
		YundaexCustomerApplyExample ydCustomerApplyExample = new YundaexCustomerApplyExample();
		//搜索条件
		getYundaexCriteria(ydCustomerApplyExample,ydCustomerApplyListQueryDTO);
		int pageSize = ydCustomerApplyListQueryDTO.getPageSize();
		ydCustomerApplyExample.setOrderByClause("apply_date desc");
		ydCustomerApplyExample.setLimitStart((ydCustomerApplyListQueryDTO.getPage() -1) * pageSize);
		ydCustomerApplyExample.setLimitEnd(pageSize);
		List<YundaexCustomerApply> ydCustomerApplys = yundaexCustomerApplyMapper.selectByExample(ydCustomerApplyExample);
		if(ydCustomerApplys.size() == 0)
			return result;
		//封装出页面所需要MinDTO的数据
		List<YdCustomerApplyMinDTO> ydCustomerApplyMinDTOs = YundaexCustomerApplyConvert.convertCustomerApply2CustomerApplyMinDTO(ydCustomerApplys);
		for(int i = 0; i < ydCustomerApplyMinDTOs.size(); i++) {
			YdCustomerApplyMinDTO customerApplyMinDTO = ydCustomerApplyMinDTOs.get(i);
			YundaexCustomerApply ydcustomerApply = ydCustomerApplys.get(i);
			String auditStateStr = customerApplyMinDTO.getAuditState();
			//人工审核为等待的时候是不显示审核人的
			if(YundaexAuditState.ACCESS.name().equals(auditStateStr) || YundaexAuditState.NOTACCESS.name().equals(auditStateStr)) {
				try {
					customerApplyMinDTO.setAuditor(userApiImpl.queryEmployeeDetail(ydcustomerApply.getAuditorId()).getUsername());
				} catch (Exception e) {
					throw WebException.instance("无法获取审核人信息");
				}
			}
		}
		result.setRecords(ydCustomerApplyMinDTOs);
		result.setTotalRecords(yundaexCustomerApplyMapper.countByExample(ydCustomerApplyExample));
		return result;
	}

	/**
	 * 封装搜索条件
	 * @param ydCustomerApplyExample
	 * @param ydCustomerApplyListQueryDTO
	 */
	private void getYundaexCriteria(YundaexCustomerApplyExample ydCustomerApplyExample,
			YdCustomerApplyListQueryDTO ydCustomerApplyListQueryDTO) {
		String startDate = ydCustomerApplyListQueryDTO.getStartDate();
		String endDate = ydCustomerApplyListQueryDTO.getEndDate();
		String customerName = ydCustomerApplyListQueryDTO.getCustomerName();
		String auditState = ydCustomerApplyListQueryDTO.getAuditState();
		Criteria ydCriteria = ydCustomerApplyExample.createCriteria();
		try {
			if(StringUtils.isNotBlank(startDate))
			    ydCriteria.andApplyDateGreaterThanOrEqualTo(DateUtils.parseDate(startDate.trim(), "yyyy-MM-dd"));
			if(StringUtils.isNotBlank(endDate))
			    ydCriteria.andApplyDateLessThanOrEqualTo(DateUtils.parseDate(endDate.trim(), "yyyy-MM-dd"));
		} catch (ParseException e) {
			throw WebException.instance("查询时间格式错误");
		}
		if(StringUtils.isNotBlank(customerName)){
			ydCriteria.andStationNameLike("%"+customerName.trim()+"%");
		}
		if(StringUtils.isNotBlank(auditState)){
			ydCriteria.andAccessManualStateEqualTo(auditState);
		}
		ydCriteria.andAccessAutomaticStateEqualTo(YundaexAuditState.ACCESS.name());
	}

	/**
	 * 获取审核详情
	 */
	@Override
	public YdCustomerApplyDetailDTO getCustomerApplyInfo(String id) {
		YundaexCustomerApply yundaexCustomerApply = yundaexCustomerApplyMapper.selectByPrimaryKey(id);
		YdCustomerApplyDetailDTO customerApplyDetailDTO = YundaexCustomerApplyConvert.convertCustomerApply2CustomerApplyDetailDTO(yundaexCustomerApply);
		// 把账号信息  以每四位加一个空格显示
		if(StringUtils.isNotBlank(customerApplyDetailDTO.getPayAccount())){
			customerApplyDetailDTO.setPayAccount(customerApplyDetailDTO.getPayAccount().replaceAll("\\d{4}", "$0 "));
		}
		return customerApplyDetailDTO;
	}

	@Override
	public YdCustomerApplyDetailDTO getUserBaseInfo(String userId) {

		YundaexOutCustomerExample example = new YundaexOutCustomerExample();
		example.createCriteria().andMemberIdEqualTo(userId);
		List<YundaexOutCustomer> ydOutCustomers = ydOutCustomerMapper.selectByExample(example);
		if(CollectionUtils.isEmpty(ydOutCustomers))
			return null;
		YundaexCustomerApplyExample example2 = new YundaexCustomerApplyExample();
		example2.createCriteria().andStationNoEqualTo(ydOutCustomers.get(0).getStationNo()); 
		List<YundaexCustomerApply> ydCustomerApplys = yundaexCustomerApplyMapper.selectByExample(example2);
		if(CollectionUtils.isEmpty(ydCustomerApplys))
			return null;
		//BeanUtils.copyProperties(ydCustomerApplys.get(0), ydCustomerApplyDetailDTO);
		
		YdCustomerApplyDetailDTO ydCustomerApplyDetailDTO = YundaexCustomerApplyConvert.convertCustomerApply2CustomerApplyDetailDTO(ydCustomerApplys.get(0));
		return ydCustomerApplyDetailDTO;
	}

	@Override
	public void insertYundaexCustomerApply(YundaexCustomerApply yundaexCustomerApply) {
		yundaexCustomerApplyMapper.insert(yundaexCustomerApply);
	}

	@Override
	public void insertYundaexCustomerApplys(List<YundaexCustomerApply> yundaexCustomerApplys) {
		for(YundaexCustomerApply yundaexCustomerApply : yundaexCustomerApplys){
			yundaexCustomerApplyMapper.insert(yundaexCustomerApply);
		}
	}

}
