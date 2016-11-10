package com.cana.asset.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cana.asset.service.IHomsomSettlementService;
import com.cana.asset.service.convertors.HomsomSettlementConvertor;
import com.cana.homsom.dao.mapper.gen.HomsomSettlementTrackMapper;
import com.cana.homsom.dao.po.HomsomSettlementTrack;
import com.cana.homsom.dao.po.HomsomSettlementTrackExample;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.homsom.dto.HomsomSettlementTrackDTO;
import com.cana.vbam.common.homsom.dto.HomsomSettlementTrackRequestDTO;
import com.travelzen.framework.core.util.StringUtil;

/**恒顺核销履历列表查询
 * @author jiangzhou.Ren
 * @time 2016年10月19日上午10:56:10
 */
@Service
public class HomsomSettlementServiceImpl implements IHomsomSettlementService{
	
	private final Logger logger = LoggerFactory.getLogger(getClass());				
	
	@Resource
	private HomsomSettlementTrackMapper homsomSettlementTrackMapper;
	/**
	 * 核销履历列表查询
	 * @param homsomSettlementTrackRequestDTO
	 * @return
	 */
	@Override
	public ListResult<HomsomSettlementTrackDTO> getSettlementTrackList(HomsomSettlementTrackRequestDTO request) {
		//请求参数校验
		StringUtil.trimObjectFields(request);
		HomsomSettlementTrackExample settlementTrackExample = new HomsomSettlementTrackExample();
		HomsomSettlementTrackExample.Criteria criteria = settlementTrackExample.createCriteria();
		addCriteriaCondition(request, criteria);
		settlementTrackExample.setOrderByClause("create_time desc");
		//设置分页查询
		settlementTrackExample.setLimitStart((request.getPage() -1) * request.getPageSize());
		settlementTrackExample.setLimitEnd(request.getPageSize());
		logger.info("开始查询核销履历列表");
		List<HomsomSettlementTrack> settlementTracks = homsomSettlementTrackMapper.selectByExample(settlementTrackExample);
		int count = homsomSettlementTrackMapper.countByExample(settlementTrackExample);
		List<HomsomSettlementTrackDTO> homsomSettlementDTOs = HomsomSettlementConvertor.convertorHomsomSettlement(settlementTracks,count);
		return ListResult.success(homsomSettlementDTOs, count);
	}

	/**
	 * 核销履历列表添加查询条件
	 * @param request
	 * @param criteria
	 */
	private void addCriteriaCondition(HomsomSettlementTrackRequestDTO request,HomsomSettlementTrackExample.Criteria criteria) {
		// 渠道
		if(null != request.getChannel()){
			criteria.andChannelEqualTo(request.getChannel().name());
		}
		//交易对手
		if(StringUtils.isNotBlank(request.getCounterpartyName())){
			criteria.andCounterpartyNameLike("%" + request.getCounterpartyName() + "%");
		}
		//票号
		if(StringUtils.isNotBlank(request.getTicketNo())){
			criteria.andTicketNoLike("%" + request.getTicketNo() + "%");
		}
		//出票日期
		if(StringUtils.isNotBlank(request.getStartIssueDate())){
			criteria.andIssueDateGreaterThanOrEqualTo(request.getStartIssueDate());
		}
		if(StringUtils.isNotBlank(request.getEndIssueDate())){
			criteria.andIssueDateLessThanOrEqualTo(request.getEndIssueDate());
		}
		//回购日期
		if(StringUtils.isNotBlank(request.getStartBuybackDate())){
			criteria.andBuybackDateGreaterThanOrEqualTo(request.getStartBuybackDate());
		}
		if(StringUtils.isNotBlank(request.getEndBuybackDate())){
			criteria.andBuybackDateLessThanOrEqualTo(request.getEndBuybackDate());
		}
		//核销日期
		if(StringUtils.isNotBlank(request.getStartSettleDate())){
			criteria.andSettleDateGreaterThanOrEqualTo(request.getStartSettleDate());
		}
		if(StringUtils.isNotBlank(request.getEndSettleDate())){
			criteria.andSettleDateLessThanOrEqualTo(request.getEndSettleDate());
		}
	}

	
	
}























