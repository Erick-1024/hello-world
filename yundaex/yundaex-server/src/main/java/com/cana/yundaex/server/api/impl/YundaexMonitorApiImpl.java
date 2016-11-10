package com.cana.yundaex.server.api.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cana.vbam.common.service.IVbamCommonService;
import com.cana.vbam.common.utils.Constants;
import com.cana.vbam.common.utils.RedisUtils;
import com.cana.yundaex.api.IYundaexMonitorApi;
import com.cana.yundaex.common.dto.monitor.YundaexMonitorImportDTO;
import com.cana.yundaex.common.dto.monitor.YundaexMonitorImportRedisDTO;
import com.cana.yundaex.common.dto.monitor.YundaexmonitorGradeInfoDTO;
import com.cana.yundaex.common.enums.TimeUnit;
import com.cana.yundaex.common.enums.YundaexCreditLimitGenerateState;
import com.cana.yundaex.common.enums.YundaexJudge;
import com.cana.yundaex.common.enums.YundaexLoanType;
import com.cana.yundaex.dao.mapper.gen.YundaexCustomerApplyMapper;
import com.cana.yundaex.dao.mapper.gen.YundaexCustomerApplyMonitorMapper;
import com.cana.yundaex.dao.po.YundaexCustomerApply;
import com.cana.yundaex.dao.po.YundaexCustomerApplyExample;
import com.cana.yundaex.dao.po.YundaexCustomerApplyMonitor;
import com.cana.yundaex.dao.po.YundaexCustomerApplyMonitorExample;
import com.cana.yundaex.dao.po.YundaexGradeInfo;
import com.cana.yundaex.service.transaction.IYundaexAutomaticRulesTransactionService;
import com.cana.yundaex.service.transaction.IYundaexMonitorTransactionService;
import com.travelzen.framework.common.PageList;
import com.travelzen.framework.config.tops.TopsConfReader;
import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;
import com.travelzen.framework.redis.client.SpringRedisClient;
import com.travelzen.framework.util.DateUtils;

public class YundaexMonitorApiImpl implements IYundaexMonitorApi {

	@Resource
	private SequenceGenerator seqGen;
	
	private static final Logger LGR = LoggerFactory.getLogger(YundaexMonitorApiImpl.class);
	
	private final SpringRedisClient redisCache = SpringRedisClient.getInstance();
	
	@Resource
	private YundaexCustomerApplyMapper yundaexCustomerApplyMapper;
	
	@Resource
	private YundaexCustomerApplyMonitorMapper yundaexCustomerApplyMonitorMapper;
	
	@Resource
	private IYundaexMonitorTransactionService yundaexMonitorTransactionService;
	
	@Resource
	private IYundaexAutomaticRulesTransactionService yundaexAutomaticRulesTransactionService;
	
	@Resource
	private IVbamCommonService vbamCommonService;
	
	@Override
	public String generateRediskey() {
		return generateRedisId();
	}

	private String generateRedisId() {
		String key = DateTimeUtil.datetime12()+ seqGen.getNextSeq(Constants.SEQUENCE_NAME_REDIS_YUNDAEX_MONITOR_ID, 4);
		return key;
	}

	/**
	 * 获取redis失效时间  86400秒
	 * @return
	 */
	private int getRedisExpireTime(){
		return Integer.parseInt(TopsConfReader.getConfContent("properties/yundaex.properties", "redis.temp.timeout", ConfScope.R));
	}
	
	@Override
	public void batchSaveToRedis(String customerId, String key, List<YundaexMonitorImportDTO> list) {
		String rediskey = RedisUtils.generateMonitorRedisKeyByCustomerId(key, customerId);
		List<YundaexMonitorImportDTO> passMonitorRedis = new ArrayList<>();
		List<YundaexMonitorImportDTO> NotPassMonitorRedis = new ArrayList<>();
		Object excelRedis = redisCache.read(rediskey); 
        if(excelRedis!=null){
        	YundaexMonitorImportRedisDTO yundaexMonitorImportRedisDTO = (YundaexMonitorImportRedisDTO)redisCache.read(rediskey);
        	passMonitorRedis = yundaexMonitorImportRedisDTO.getPassYundaexMonitorImportDTOs();
        	NotPassMonitorRedis = yundaexMonitorImportRedisDTO.getNotPassYundaexMonitorImportDTOs();
		}
        for(YundaexMonitorImportDTO yundaexMonitorImportDTO : list){
        	try {
        		//站点编号是否存在
				checkIsExistStationNo(yundaexMonitorImportDTO);
				passMonitorRedis.add(yundaexMonitorImportDTO);
			} catch (Exception e) {
				yundaexMonitorImportDTO.setNotPassReason(e.getMessage());
				NotPassMonitorRedis.add(yundaexMonitorImportDTO);
			}
        }
        //检查excel文件中站点编号是否重复
        List<String> stationNoList = getSameStationNo(passMonitorRedis);
        if(CollectionUtils.isNotEmpty(stationNoList)){
        	Iterator<YundaexMonitorImportDTO> iterator = passMonitorRedis.iterator();
        	while(iterator.hasNext()){
    			YundaexMonitorImportDTO passMonitorDTO = iterator.next();
    			if(stationNoList.contains(passMonitorDTO.getStationNo())){
    				iterator.remove();
    				passMonitorDTO.setNotPassReason("站点编号重复");
    				NotPassMonitorRedis.add(passMonitorDTO);
    			}
    		}
        }
        
        YundaexMonitorImportRedisDTO yundaexMonitorImportRedisDTO = new YundaexMonitorImportRedisDTO();
        yundaexMonitorImportRedisDTO.setPassYundaexMonitorImportDTOs(passMonitorRedis);
        yundaexMonitorImportRedisDTO.setNotPassYundaexMonitorImportDTOs(NotPassMonitorRedis);
        redisCache.save(rediskey, yundaexMonitorImportRedisDTO,getRedisExpireTime());
        
	}

	private List<String> getSameStationNo(List<YundaexMonitorImportDTO> passMonitorRedis) {
		List<String> stationNoList = new ArrayList<>();
		if(CollectionUtils.isEmpty(passMonitorRedis))
			return stationNoList;
		for(int i=0;i<passMonitorRedis.size()-1;i++){
			for(int j=i+1;j<passMonitorRedis.size();j++){
				YundaexMonitorImportDTO now = passMonitorRedis.get(i);
				YundaexMonitorImportDTO next = passMonitorRedis.get(j);
				if(now.getStationNo().equals(next.getStationNo())){
					stationNoList.add(now.getStationNo());
				}
			}
		}
		return stationNoList;
	}

	/**
	 * 监控信息检查 、判重
	 * @param yundaexMonitorImportDTO
	 */
	private void checkIsExistStationNo(YundaexMonitorImportDTO yundaexMonitorImportDTO) {
		String stationNo = yundaexMonitorImportDTO.getStationNo(); 
		if(StringUtils.isBlank(stationNo))
			throw WebException.instance("公司编码为空");
		if(StringUtils.isBlank(yundaexMonitorImportDTO.getBusiLimit()))
			throw WebException.instance("加盟年限为空");
		if(new BigDecimal(yundaexMonitorImportDTO.getBusiLimit()).intValue() < 2)
			throw WebException.instance("加盟年限低于2年");
		if(StringUtils.isBlank(yundaexMonitorImportDTO.getBailBalance()))
			throw WebException.instance("保证金为空");
		if(StringUtils.isBlank(yundaexMonitorImportDTO.getYundaexJudge()))
			throw WebException.instance("韵达评价为空");
		if(YundaexJudge.getEnum(yundaexMonitorImportDTO.getYundaexJudge()) == null)
			throw WebException.instance("韵达评价类型不正确");
		if(StringUtils.isBlank(yundaexMonitorImportDTO.getStationAmount()))
			throw WebException.instance("网点数量为空");
		if(new BigDecimal(yundaexMonitorImportDTO.getStationAmount()).intValue() < 0)
			throw WebException.instance("网点数量为负值");
		if(StringUtils.isNotBlank(yundaexMonitorImportDTO.getShortLoan()) && new BigDecimal(yundaexMonitorImportDTO.getShortLoan()).intValue()!=0){
			if(StringUtils.isBlank(yundaexMonitorImportDTO.getLoanLimit()) || new BigDecimal(yundaexMonitorImportDTO.getLoanLimit()).intValue()<=0)
				throw WebException.instance("借款期限不正确");
		}
		if(StringUtils.isBlank(yundaexMonitorImportDTO.getLoanType()))
			throw WebException.instance("借款类型为空");
		if(YundaexLoanType.getEnum(yundaexMonitorImportDTO.getLoanType()) == null)
			throw WebException.instance("借款类型不正确");
		if(StringUtils.isBlank(yundaexMonitorImportDTO.getLimitUnit()))
			throw WebException.instance("期限单位为空");
		if(TimeUnit.getEnum(yundaexMonitorImportDTO.getLimitUnit()) == null)
			throw WebException.instance("期限单位类型不正确");
		
		YundaexCustomerApplyExample yundaexCustomerApplyExample = new YundaexCustomerApplyExample();
		yundaexCustomerApplyExample.createCriteria().andStationNoEqualTo(stationNo);
		List<YundaexCustomerApply> customerApplys = yundaexCustomerApplyMapper.selectByExample(yundaexCustomerApplyExample);
		if(CollectionUtils.isEmpty(customerApplys))
			throw WebException.instance("公司编码不匹配");
		if(!YundaexCreditLimitGenerateState.FINISH.name().equals(customerApplys.get(0).getCreditLimitGenerateState()))
			throw WebException.instance("未通过授信的申请");
		
		YundaexCustomerApplyMonitorExample applyMonitorExample = new YundaexCustomerApplyMonitorExample();
		String currentDate = vbamCommonService.getCurrentDate();
		String virtualDate = DateUtils.format(DateUtils.getDate(currentDate, "yyyy-MM-dd"), 23);
		applyMonitorExample.createCriteria().andStationNoEqualTo(stationNo).andMonthEqualTo(virtualDate);
		List<YundaexCustomerApplyMonitor> applyMonitors = yundaexCustomerApplyMonitorMapper.selectByExample(applyMonitorExample); 
		if(CollectionUtils.isNotEmpty(applyMonitors))
			throw WebException.instance("监控信息已存在");
	}

	@Override
	public void importExcelToDB(String key, String customerId) {
		String rediskey = RedisUtils.generateMonitorRedisKeyByCustomerId(key, customerId);
		if(redisCache.read(rediskey) != null){
			YundaexMonitorImportRedisDTO yundaexMonitorImportRedisDTO = (YundaexMonitorImportRedisDTO)redisCache.read(rediskey);
			yundaexMonitorTransactionService.importExcelToDB(yundaexMonitorImportRedisDTO.getPassYundaexMonitorImportDTOs());
		}else{
			LGR.info("key="+key+",redis中没有该key值的数据。");
			throw WebException.instance("数据不存在");
		}
	}

	@Override
	public PageList<YundaexMonitorImportDTO> queryMonitorInfoFromRedis(String key, String customerId, String status,
			int page, int pageSize) {
		PageList<YundaexMonitorImportDTO> result = new PageList<YundaexMonitorImportDTO>();
        if(redisCache.read(RedisUtils.generateMonitorRedisKeyByCustomerId(key, customerId)) == null)
        	return result;
        YundaexMonitorImportRedisDTO yundaexMonitorImportRedisDTO = (YundaexMonitorImportRedisDTO)redisCache.read(RedisUtils.generateMonitorRedisKeyByCustomerId(key, customerId));
		if("success".equals(status)){
			try {
				List<YundaexMonitorImportDTO> list = new ArrayList<>();
				if(CollectionUtils.isNotEmpty(yundaexMonitorImportRedisDTO.getPassYundaexMonitorImportDTOs())){
				    list = yundaexMonitorImportRedisDTO.getPassYundaexMonitorImportDTOs().subList(getStartIndex(page, pageSize),getEndIndex(page, pageSize, yundaexMonitorImportRedisDTO.getPassYundaexMonitorImportDTOs()));
				}
				result.setRecords(list);
				result.setTotalRecords(yundaexMonitorImportRedisDTO.getPassYundaexMonitorImportDTOs().size());
				LGR.info("查询通过的韵达监控数据redis成功");
			} catch (Exception e) {
				LGR.error("查询通过的韵达监控数据redis失败", e);
			}
		}else{
			try {
				List<YundaexMonitorImportDTO> list = new ArrayList<>();
				if(CollectionUtils.isNotEmpty(yundaexMonitorImportRedisDTO.getNotPassYundaexMonitorImportDTOs())){
				    list = yundaexMonitorImportRedisDTO.getNotPassYundaexMonitorImportDTOs().subList(getStartIndex(page, pageSize),getEndIndex(page, pageSize, yundaexMonitorImportRedisDTO.getNotPassYundaexMonitorImportDTOs()));
				}
				result.setRecords(list);
				result.setTotalRecords(yundaexMonitorImportRedisDTO.getNotPassYundaexMonitorImportDTOs().size());
				LGR.info("查询位通过的韵达监控数据redis成功");
			} catch (Exception e) {
				LGR.error("查询未通过的韵达监控数据redis失败", e);
			}
		}
        return result;
	}
	
	private int getStartIndex(int page, int pageSize){
		return (page - 1) * pageSize;
	}
	
	private int getEndIndex(int page, int pageSize, List<?> curList) {
		return page * pageSize < curList.size() ? page * pageSize : curList.size();
	}

	@Override
	public YundaexmonitorGradeInfoDTO getYundaexGradeInfoByScore(BigDecimal score) {
		YundaexmonitorGradeInfoDTO gradeInfoDTO = new YundaexmonitorGradeInfoDTO();
		YundaexGradeInfo yundaexGradeInfoByScore = yundaexAutomaticRulesTransactionService.getYundaexGradeInfoByScore(score);
		gradeInfoDTO.setGrade(yundaexGradeInfoByScore.getGrade());
		return gradeInfoDTO;
	}


}
	


