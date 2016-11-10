/**
 * 
 */
package com.cana.yundaex.service.transaction.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.yundaex.common.dto.YundaexCustomerApplyDTO;
import com.cana.yundaex.common.dto.YundaexCustomerApplyQueryDTO;
import com.cana.yundaex.common.dto.YundaexTstationInfoDTO;
import com.cana.yundaex.common.dto.YundaexTstationInfoQueryDTO;
import com.cana.yundaex.common.dto.YundaexTstationInfoResponse;
import com.cana.yundaex.common.enums.StationInfoType;
import com.cana.yundaex.common.enums.YundaexPullStationType;
import com.cana.yundaex.common.enums.YundaexStationDataStatus;
import com.cana.yundaex.dao.mapper.gen.YundaexTstationInfoMapper;
import com.cana.yundaex.dao.mapper.gen.YundaexTstationInfoRecordMapper;
import com.cana.yundaex.dao.po.YundaexCustomerApply;
import com.cana.yundaex.dao.po.YundaexTstationInfo;
import com.cana.yundaex.dao.po.YundaexTstationInfoRecord;
import com.cana.yundaex.dao.po.YundaexTstationInfoRecordExample;
import com.cana.yundaex.response.api.IYundaexPullTstationInfoApi;
import com.cana.yundaex.service.IYundaexCustomerApplyService;
import com.cana.yundaex.service.IYundaexRetryTaskService;
import com.cana.yundaex.service.transaction.IYundaexTstationInfoTransactionService;
import com.dianping.cat.Cat;
import com.travelzen.framework.core.common.ReturnClass;
import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.util.DateUtils;

/**
 * 操作 韵达信息网点信息
 * @author guguanggong
 *
 */
@Service
public class YundaexTstationInfoTransactionServiceImpl implements IYundaexTstationInfoTransactionService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private YundaexTstationInfoMapper yundaexTstationInfoMapper;
	
	@Resource
	private YundaexTstationInfoRecordMapper yundaexTstationInfoRecordMapper;
	
	@Resource
	private IYundaexPullTstationInfoApi yundaexPullTstationInfoApi;
	
	@Resource
	private IYundaexCustomerApplyService yundaexCustomerApplyService;
	
	@Resource
	private IYundaexRetryTaskService yundaexRetryTaskService;
	
	@Resource
	private IVbamCommonService commonService;
	
	@Override
	public ReturnClass saveTstationInfo(YundaexTstationInfoQueryDTO infoQueryDTO, YundaexTstationInfoResponse returnData) {
		String validMsg = StringUtils.EMPTY;
		StationInfoType stationInfoType = StationInfoType.N;
		try {
			validMsg = validResponse(returnData); // 检验数据
			logger.info("开始保存网点数据，本次拉取{}条", returnData.getCount());
			saveYundaexTstationInfo(returnData.getOperataDatas());
			logger.info("本次处理{}条", returnData.getCount());
			stationInfoType = StationInfoType.Y; // 如保存成功，修改客户申请表为 Y;
			Cat.logMetricForSum("网点定时任务-拉取网点信息数据总计", returnData.getCount());
			return new ReturnClass(ReturnCode.SUCCESS);
		} catch(Exception e) {
			validMsg = e.getMessage();
			return new ReturnClass(ReturnCode.FAIL, e.getMessage());
		} finally {
			// 修改客户申请表中的 是否拉取网点数据 标记
			logger.info("更新客户申请表拉取网点数据标记");
			updateCustomerApplyByStationNo(infoQueryDTO.getStationNo(), stationInfoType, validMsg);
			logger.info("处理网点数据完成");
		}
	}
	
	/**
	 * 修改客户申请表中的数据状态和网点数据记录表中的数据状态
	 * 
	 * @param batchDate 拉取数据时间 YYYY-MM
	 * @param stationNo 站点编号
	 * @param stationDataStatus 拉取数据状态
	 * @param extraData 扩展数据 json串
	 * @param stationInfoType 客户申请表 拉取数据状态
	 * @param  returnMsg 客户申请表 返回信息
	 */
	@Override
	public void updateCustomerApplyAndStationRecord(String batchDate, String stationNo, YundaexStationDataStatus stationDataStatus, String extraData, StationInfoType stationInfoType, String returnMsg) {
		// 修改客户申请表中数据状态
		updateCustomerApplyByStationNo(stationNo, stationInfoType, returnMsg);
		// 更新网点数据状态
		updateStationRecord(batchDate, stationNo, stationDataStatus, extraData);
	}
	
	/**
	 * 根据网点编号更新站点记录标记
	 * 
	 * @param stationNo
	 * @param returnMap
	 */
	@Override
	public void updateCustomerApplyByStationNo(String stationNo, StationInfoType stationInfoType, String returnMsg) {
		YundaexCustomerApplyQueryDTO applyQueryDTO = new YundaexCustomerApplyQueryDTO();
		applyQueryDTO.setStationNo(stationNo);
		List<YundaexCustomerApplyDTO> applyDTOs = yundaexCustomerApplyService.getYdCustApplyByParam(applyQueryDTO);
		if (CollectionUtils.isEmpty(applyDTOs)) {
			throw WebException.instance("客户申请系统不存在");
		}
		YundaexCustomerApplyDTO applyDTO = applyDTOs.get(0);
		YundaexCustomerApplyDTO dto = new YundaexCustomerApplyDTO();
		dto.setId(applyDTO.getId());
		dto.setWhetherStationInfo(stationInfoType.name());
		if (StringUtils.isNotBlank(returnMsg)) {
			dto.setReasonW(returnMsg);
		}
		yundaexCustomerApplyService.updateCustomerApplyById(dto);
	}

	/**
	 * 保存拉取的数据
	 * @param operataDatas
	 */
	private void saveYundaexTstationInfo(List<YundaexTstationInfoDTO> operataDatas) {
		if (CollectionUtils.isEmpty(operataDatas)) {
			return;
		}
		long time = System.currentTimeMillis();
		String currentDate = getCurrentDate();
		String endDate = DateUtils.getFirstDayOfMonth(DateUtils.getJustDate(currentDate));
		for (YundaexTstationInfoDTO dto : operataDatas) {
			YundaexTstationInfo info = new YundaexTstationInfo();
			BeanUtils.copyProperties(dto, info);
			info.setCreateTime(new Date());
			info.setUpdateTime(new Date());
			yundaexTstationInfoMapper.insertSelective(info);
			
			// 更新网点数据记录状态
			updateStationRecord(endDate, info.getStationNo(), YundaexStationDataStatus.SUCCESS, null);
		}
		logger.info("本次执行时间:{}ms", System.currentTimeMillis() - time);
	}

	/**
	 * 检验返回数据正确性
	 * 
	 * @param result
	 * @return
	 */
	private String validResponse(YundaexTstationInfoResponse result) {
		String returnMsg = "拉取数据成功";
		if (result == null) {
			logger.error("网点数据转换错误或是没拉取到网点数据");
			throw WebException.instance("网点数据转换错误或是没拉取到网点数据");
		}

		List<YundaexTstationInfoDTO> infoDTOs = result.getOperataDatas();
		if (CollectionUtils.isEmpty(infoDTOs)) {
			logger.error("请求网点信息为空");
			throw WebException.instance("请求网点信息为空");
		}

		int resCount = infoDTOs.size(); // 返回网点条数
		int count = result.getCount(); // 网点记录数
		if (count != resCount) {
			logger.error("请求网点信息条数，与返回记录数不符");
			throw WebException.instance("请求网点信息条数，与返回记录数不符");
		}

		logger.info("拉取数据成功"); // 拉取数据成功
		return returnMsg;
	}

	@Override
	public ReturnClass synTstationInfo(YundaexTstationInfoQueryDTO infoQueryDTO, YundaexTstationInfoResponse returnData) {
		try {
			validResponse(returnData); // 检验数据
			logger.info("开始保存网点数据，本次拉取{}条", returnData.getCount());
			saveYundaexTstationInfo(returnData.getOperataDatas());
			logger.info("本次处理{}条", returnData.getCount());
			Cat.logMetricForSum("网点定时任务-拉取网点信息数据总计", returnData.getCount());
			return new ReturnClass(ReturnCode.SUCCESS);
		} catch(Exception e) {
			return new ReturnClass(ReturnCode.FAIL, e.getMessage());
		}
	}
	
	/**
	 * 拉取数据网点经营数据任务
	 */
	@Override
	public void stationPullTask(YundaexCustomerApply yundaexCustomerApply) {
		YundaexTstationInfoQueryDTO tstationInfo = getYundaexTstationInfoDTO(yundaexCustomerApply);
		List<YundaexTstationInfoQueryDTO> queryDTOs = new ArrayList<>();
		queryDTOs.add(tstationInfo);
		
		// 创建重度任务、拉取记录
		createStationPull(queryDTOs, YundaexPullStationType.PULL);
	}
	
	
	/**
	 * 1.创建重试任务（第一次拉取数据、同步数据）
	 * 2.创建拉取数据记录
	 */
	@Override
	public void createStationPull(List<YundaexTstationInfoQueryDTO> queryDTOs, YundaexPullStationType type) {
		if (CollectionUtils.isEmpty(queryDTOs)) {
			return;
		}
		
		switch (type) {
			case PULL:
				// 1.创建重试任务（第一次拉取数据、同步数据）
				yundaexRetryTaskService.createStationPullTask(queryDTOs);
				// 2.创建拉取数据记录
				createStationRecord(queryDTOs);
				break;
			case SYN:
				// 1.创建重试任务（第一次拉取数据、同步数据）
				yundaexRetryTaskService.createStationSynTask(queryDTOs);
				// 2.创建拉取数据记录
				createStationRecord(queryDTOs);
				break;
			default:
				break;
		}
	}
	
	/**
	 * 更新网点记录数据状态
	 * @param batchDate 拉取数据时间 YYYY-MM
	 * @param stationNo 站点编号
	 * @param stationDataStatus 拉取数据状态
	 * @param extraData 扩展数据 json串
	 */
	@Override
	public void updateStationRecord(String batchDate, String stationNo, YundaexStationDataStatus stationDataStatus, String extraData) {
		validDate(batchDate, stationNo, stationDataStatus);
		
		YundaexTstationInfoRecordExample example = new YundaexTstationInfoRecordExample();
		example.createCriteria().andBatchDateEqualTo(batchDate).andStationNoEqualTo(stationNo);
		
		List<YundaexTstationInfoRecord> infoRecords = yundaexTstationInfoRecordMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(infoRecords)) {
			logger.error("网点数据记录中");
			throw WebException.instance("拉取数据时间为空");
		}
		
		YundaexTstationInfoRecord record = new YundaexTstationInfoRecord();
		record.setExtraData(extraData);
		record.setStationDataStatus(stationDataStatus.name());
		yundaexTstationInfoRecordMapper.updateByExampleSelective(record, example);
	}
	
	/**
	 * 新建拉取数据记录 状态 处理中
	 * @param queryDTOs
	 */
	private void createStationRecord(List<YundaexTstationInfoQueryDTO> queryDTOs) {
		String currentDate = getCurrentDate();
		String endDate = DateUtils.getFirstDayOfMonth(DateUtils.getJustDate(currentDate));
		for (YundaexTstationInfoQueryDTO queryDTO : queryDTOs) {
			saveStation(endDate, queryDTO.getStationNo(), YundaexStationDataStatus.HANDLING, null);
		}
	}
	
	/**
	 * 保存记录信息
	 * @param batchDate 拉取数据时间 YYYY-MM
	 * @param stationNo 站点编号 
	 * @param stationDataStatus 拉取数据状态
	 * @param extraData 扩展数据 json串
	 */
	public void saveStation(String batchDate, String stationNo, YundaexStationDataStatus stationDataStatus, String extraData) {
		validDate(batchDate, stationNo, stationDataStatus);
		
		YundaexTstationInfoRecord record = new YundaexTstationInfoRecord();
		record.setBatchDate(batchDate);
		record.setStationNo(stationNo);
		record.setStationDataStatus(stationDataStatus.name());
		record.setExtraData(extraData);
		yundaexTstationInfoRecordMapper.insertSelective(record);
	}
	
	/**
	 * 客户的 网点信息
	 * 
	 * @return
	 */
	private YundaexTstationInfoQueryDTO getYundaexTstationInfoDTO(YundaexCustomerApply yundaexCustomerApply) {
		String currentDate = getCurrentDate();
		Date createTime = yundaexCustomerApply.getCreateTime();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(createTime);
		calendar.add(Calendar.YEAR, -2);
		Date startTime = calendar.getTime();
		String startDate = DateUtils.getFirstDayOfMonth(startTime);
		String endDate = DateUtils.getFirstDayOfMonth(DateUtils.getJustDate(currentDate));

		YundaexTstationInfoQueryDTO tstationInfoQueryDTO = new YundaexTstationInfoQueryDTO();
		tstationInfoQueryDTO.setStationNo(yundaexCustomerApply.getStationNo());
		tstationInfoQueryDTO.setEndDate(endDate);
		tstationInfoQueryDTO.setStartDate(startDate);
		return tstationInfoQueryDTO;
	}
	
	/**
	 * 系统虚拟时间
	 * @return
	 */
	private String getCurrentDate() {
		String currentDate = commonService.getCurrentDate();
		return currentDate;
	}
	
	/**
	 * 检验数据
	 * @param batchDate
	 * @param stationNo
	 * @param stationDataStatus
	 */
	private void validDate(String batchDate, String stationNo, YundaexStationDataStatus stationDataStatus) {
		if (StringUtils.isBlank(batchDate) ) {
			logger.error("拉取数据时间为空");
			throw WebException.instance("拉取数据时间为空");
		}
		
		if (StringUtils.isBlank(stationNo) ) {
			logger.error("站点编号为空");
			throw WebException.instance("站点编号为空");
		}

		if (stationDataStatus == null) {
			logger.error("拉取网点数据状态为空");
			throw WebException.instance("拉取网点数据状态为空");
		}
	}

}
