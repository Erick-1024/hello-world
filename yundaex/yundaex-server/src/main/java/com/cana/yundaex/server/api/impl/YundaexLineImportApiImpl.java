package com.cana.yundaex.server.api.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.cana.vbam.common.utils.Constants;
import com.cana.yundaex.api.IYundaexLineImportApi;
import com.cana.yundaex.common.dto.YundaexLineImportDTO;
import com.cana.yundaex.common.dto.YundaexLineImportRedisDTO;
import com.cana.yundaex.dao.mapper.gen.YundaexCustomerApplyMapper;
import com.cana.yundaex.dao.po.YundaexCustomerApply;
import com.cana.yundaex.dao.po.YundaexCustomerApplyExample;
import com.cana.yundaex.service.transaction.IYundaexLineImportService;
import com.travelzen.framework.common.PageList;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.core.util.ValidationUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;
import com.travelzen.framework.redis.client.SpringRedisClient;

public class YundaexLineImportApiImpl implements IYundaexLineImportApi {

	@Resource
	private SequenceGenerator seqGen;

	@Resource
	private IYundaexLineImportService yundaexLineImportService;
	
	@Resource
	private YundaexCustomerApplyMapper ydCustomerApplyMapper;

	private final SpringRedisClient redisCache = SpringRedisClient.getInstance();

	@Override
	public String generateRediskey() {
		return DateTimeUtil.datetime12() + seqGen.getNextSeq(Constants.SEQUENCE_NAME_REDIS_LINE_IMPORT_INFO_ID, 4);
	}

	@Override
	public void batchSaveToRedis(String customerId, String rediskey, List<YundaexLineImportDTO> list) {
		List<YundaexLineImportDTO> passYundaexLineImprotDTOs = new ArrayList<>();
		List<YundaexLineImportDTO> notPassYundaexLineImprotDTOs = new ArrayList<>();
		for(YundaexLineImportDTO yundaexLineImprotDTO : list){
			Boolean flag = checkYundaexLineImportDTO(yundaexLineImprotDTO);
			if(flag)
				passYundaexLineImprotDTOs.add(yundaexLineImprotDTO);
			else
				notPassYundaexLineImprotDTOs.add(yundaexLineImprotDTO);
		}
		//排除自身相同站点数据
		List<String> sameStationNoList = getSameStationNoList(passYundaexLineImprotDTOs);
		//删除
		Iterator<YundaexLineImportDTO> iterator = passYundaexLineImprotDTOs.iterator(); 
		while(iterator.hasNext()){
			YundaexLineImportDTO next = iterator.next(); 
			if(sameStationNoList.contains(next.getStationNo())){
				iterator.remove();
				notPassYundaexLineImprotDTOs.add(next);
			}
		}
		YundaexLineImportRedisDTO redisDTO = new YundaexLineImportRedisDTO();
		redisDTO.setPassYundaexLineImportDTOs(passYundaexLineImprotDTOs);
		redisDTO.setNotPassYundaexLineImportDTOs(notPassYundaexLineImprotDTOs);
		redisCache.save(rediskey, redisDTO);
	}

	private List<String> getSameStationNoList(List<YundaexLineImportDTO> passYundaexLineImprotDTOs) {
		List<String> list = new ArrayList<>();
		for(int i=0;i<passYundaexLineImprotDTOs.size()-1;i++){
			for(int j=i+1;j<passYundaexLineImprotDTOs.size();j++){
				YundaexLineImportDTO now = passYundaexLineImprotDTOs.get(i);
				YundaexLineImportDTO next = passYundaexLineImprotDTOs.get(j);
				if(now.getStationNo().equals(next.getStationNo())){
					list.add(now.getStationNo());
				}
			}
		}
		return list;
	}

	private Boolean checkYundaexLineImportDTO(YundaexLineImportDTO yundaexLineImprotDTO) {
		// 站点编号
		String stationNo = yundaexLineImprotDTO.getStationNo(); 
		if(StringUtils.isBlank(stationNo))
			return false;
		YundaexCustomerApplyExample example = new YundaexCustomerApplyExample();
		example.createCriteria().andStationNoEqualTo(stationNo);
		List<YundaexCustomerApply> ydCustomerApplys = ydCustomerApplyMapper.selectByExample(example); 
		if(CollectionUtils.isNotEmpty(ydCustomerApplys))
			return false;
		if(StringUtils.isBlank(yundaexLineImprotDTO.getStationMgr()))
			return false;
		if(StringUtils.isBlank(yundaexLineImprotDTO.getStationName()))
			return false;
		if(StringUtils.isBlank(yundaexLineImprotDTO.getCustName()))
			return false;
		if(StringUtils.isBlank(yundaexLineImprotDTO.getProvince()))
			return false;
		if(StringUtils.isBlank(yundaexLineImprotDTO.getCity()))
			return false;
		if(StringUtils.isBlank(yundaexLineImprotDTO.getAddress()))
			return false;
		if(yundaexLineImprotDTO.getBusiLimit() == null)
			return false;
		if(StringUtils.isBlank(yundaexLineImprotDTO.getPayAccount()))
			return false;
		//身份证号码
		String custIdno = yundaexLineImprotDTO.getCustIdno(); 
		if(StringUtils.isBlank(custIdno))
			return false;
		if(!ValidationUtil.isValidatePattern("^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$", custIdno))
			return false;
		//联系人手机号码
		String custPhone = yundaexLineImprotDTO.getCustPhone(); 
		if(StringUtils.isBlank(custPhone))
			return false;
		if(!ValidationUtil.isValidatePattern("^(0|86|17951)?(13[0-9]|15[012356789]|17[0678]|18[0-9]|14[57])[0-9]{8}$", custPhone))
			return false;
		return true;
	}

	@Override
	public PageList<YundaexLineImportDTO> queryLineRedis(String rediskey,String status, int page, int pageSize) {
		PageList<YundaexLineImportDTO> result = new PageList<YundaexLineImportDTO>();
		YundaexLineImportRedisDTO yundaexLineImprotRedisDTO = (YundaexLineImportRedisDTO) redisCache.read(rediskey);
		if("success".equals(status)){
			result.setRecords(yundaexLineImprotRedisDTO.getPassYundaexLineImportDTOs().subList(getStartIndex(page, pageSize),
					getEndIndex(page, pageSize, yundaexLineImprotRedisDTO.getPassYundaexLineImportDTOs())));
			result.setTotalRecords(yundaexLineImprotRedisDTO.getPassYundaexLineImportDTOs().size());
			return result;
		}else{
			result.setRecords(yundaexLineImprotRedisDTO.getNotPassYundaexLineImportDTOs().subList(getStartIndex(page, pageSize),
					getEndIndex(page, pageSize, yundaexLineImprotRedisDTO.getNotPassYundaexLineImportDTOs())));
			result.setTotalRecords(yundaexLineImprotRedisDTO.getNotPassYundaexLineImportDTOs().size());
			return result;
		}
	}

	private int getStartIndex(int page, int pageSize) {
		return (page - 1) * pageSize;
	}

	private int getEndIndex(int page, int pageSize, List<?> curList) {
		return page * pageSize < curList.size() ? page * pageSize : curList.size();
	}

	@Override
	public void importExcelList(String redisKey, String customerId, String operatorId) {
		yundaexLineImportService.importExcelList(redisKey);
	}

}
