package com.cana.yundaex.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cana.member.api.IUserApi;
import com.cana.vbam.common.credit.enums.Institution;
import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.yundaex.common.dto.apply.YdCustomerApplyRequestDTO;
import com.cana.yundaex.common.enums.StationInfoType;
import com.cana.yundaex.common.enums.YundaexApplyType;
import com.cana.yundaex.common.enums.YundaexAuditState;
import com.cana.yundaex.dao.mapper.gen.CommonAreaCodeMapper;
import com.cana.yundaex.dao.mapper.gen.YundaexCustomerApplyMapper;
import com.cana.yundaex.dao.po.CommonAreaCode;
import com.cana.yundaex.dao.po.CommonAreaCodeExample;
import com.cana.yundaex.dao.po.YundaexCustomerApply;
import com.cana.yundaex.dao.po.YundaexCustomerApplyExample;
import com.cana.yundaex.dao.utils.IDGenerator;
import com.cana.yundaex.service.IYundaexAuditResultService;
import com.cana.yundaex.service.convertors.YundaexCustomerApplyConvert;
import com.cana.yundaex.service.transaction.IYundaexTstationInfoTransactionService;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;

@Service
public class YundaexAuditResultServiceImpl implements IYundaexAuditResultService {

	@Resource
	private YundaexCustomerApplyMapper ydCustomerApplyMapper;
	
	@Resource
	private CommonAreaCodeMapper commonAreaCodeMapper;
	
	@Resource
	private IVbamCommonService vbamCommonService;
	
	@Resource
	private SequenceGenerator seqg;
	
	@Resource
	private IUserApi userApi;
	
	@Resource
	private IYundaexTstationInfoTransactionService yundaexTstationInfoTransactionService;
	
	/**
	 * 保存韵达客户申请资料
	 */
	@Override
	public void saveYundaexAuditResult(YdCustomerApplyRequestDTO ydCustomerApplyDTO) throws Exception {
		
		YundaexCustomerApply ydCustomerApply = new YundaexCustomerApply();
		YundaexCustomerApplyConvert.convertCustomerApplyDTO2DAO(ydCustomerApplyDTO, ydCustomerApply);
		
		//申请客户公司名称，在member_user表中不能存在
		String userId = userApi.queryUserIdByCompanyName(ydCustomerApply.getStationName()); 
		if(StringUtils.isNotBlank(userId))
			throw WebException.instance(ReturnCode.YP5132);
		// 对接口数据进入验签
		signVerify(ydCustomerApplyDTO);
		YundaexCustomerApply ydCurApply = getNewestCustomerApply(ydCustomerApplyDTO.getStationNo(),ydCustomerApplyDTO.getStationName());
		if (ydCurApply != null)
			throw WebException.instance(ReturnCode.YP5130);
		ydCustomerApply.setId(IDGenerator.generateCustomerApplyId());
		ydCustomerApply.setCustPhone(ydCustomerApplyDTO.getTelephone());
		//将韵达传来的code码转为具体的地点
		String province = getAreaFromCode(ydCustomerApply.getProvince()).getProvince();
		String city = getAreaFromCode(ydCustomerApply.getCity()).getCity();
		ydCustomerApply.setProvince(province);
		ydCustomerApply.setCity(city);
		ydCustomerApply.setAccessAutomaticState(YundaexAuditState.WAIT.name());
		//每次插入默认站点信息为"N"
		ydCustomerApply.setWhetherStationInfo(StationInfoType.N.name());
		ydCustomerApply.setNotifyFlag(true);
		ydCustomerApply.setApplyType(YundaexApplyType.INTERFACE_APPLY.name());
		ydCustomerApplyMapper.insertSelective(ydCustomerApply);
		
		// 增加拉取数据网点经营数据任务
		yundaexTstationInfoTransactionService.stationPullTask(ydCustomerApply);
	}

	private void signVerify(YdCustomerApplyRequestDTO ydCustomerApplyDTO) {
		String sign = ydCustomerApplyDTO.getSign(); 
		if(StringUtils.isBlank(sign))
			throw WebException.instance(ReturnCode.YP5131);
		StringBuffer str = new StringBuffer();
		str.append(ydCustomerApplyDTO.getStationNo()).append(ydCustomerApplyDTO.getStationName()).append(ydCustomerApplyDTO.getStationMgr())
		    .append(ydCustomerApplyDTO.getCustName()).append(ydCustomerApplyDTO.getCustIdno()).append(ydCustomerApplyDTO.getTelephone());
		vbamCommonService.rsaVerify(str.toString().getBytes(), Institution.cana.name(), sign.getBytes(), true);
	}

	private CommonAreaCode getAreaFromCode(String param) {
		CommonAreaCodeExample example = new CommonAreaCodeExample();
		example.createCriteria().andAreaCodeEqualTo(param);
		List<CommonAreaCode> areaCodes = commonAreaCodeMapper.selectByExample(example);
		if(CollectionUtils.isNotEmpty(areaCodes))
			return areaCodes.get(0);
		else
			throw WebException.instance(ReturnCode.YP5116);
	}
	
	/**
	 * 获取最近更新的客户资料
	 */
	private YundaexCustomerApply getNewestCustomerApply(String stationNo,String stationName) {
		YundaexCustomerApply ydCustomerApply = null;
		YundaexCustomerApplyExample example = new YundaexCustomerApplyExample();
		example.createCriteria().andStationNoEqualTo(stationNo);
		example.or().andStationNameEqualTo(stationName);
		example.setOrderByClause("update_time desc");
		List<YundaexCustomerApply> customerApplies = ydCustomerApplyMapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(customerApplies)) {
			ydCustomerApply = customerApplies.get(0);
		}
		return ydCustomerApply;
	}

}
