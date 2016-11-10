package com.cana.asset.service.transaction.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.cana.asset.dao.mapper.gen.SpecialProgramMapper;
import com.cana.asset.dao.po.SpecialProgram;
import com.cana.asset.service.transaction.IAssetPoolTransactionService;
import com.cana.asset.service.transaction.ISpecialProgramLogTransactionService;
import com.cana.asset.service.transaction.IUnderlyingAssetTransactionService;
import com.cana.vbam.common.asset.dto.AssetPacketDTO;
import com.cana.vbam.common.asset.enums.SpecialProgramStatus;
import com.cana.vbam.common.member.vo.UserVo;
import com.cana.vbam.common.utils.MoneyArithUtil;
import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.util.DateUtils;

@Service
public class AssetPoolTransactionServiceImpl implements IAssetPoolTransactionService {

	@Resource
	private SpecialProgramMapper specialProgramMapper;
	
	@Resource
	private IUnderlyingAssetTransactionService underlyingAssetTransactionService;
	
	@Resource
	private ISpecialProgramLogTransactionService specialProgramLogTransactionService;
	
	@Override
	public void packet(AssetPacketDTO packetDTO, UserVo userVo) {
		String id = packetDTO.getId(); 
		if(StringUtils.isBlank(id))
			throw WebException.instance("专项计划编号为空");
		SpecialProgram needPacketSpecialProgram = specialProgramMapper.selectByPrimaryKey(id);
		if(needPacketSpecialProgram == null || needPacketSpecialProgram.getDeleted())
			throw WebException.instance("专项计划编号异常");
		//专项计划 必须是立项状态才能进行封包
		if(!SpecialProgramStatus.CREATE.name().equals(needPacketSpecialProgram.getStatus()))
			throw WebException.instance("非立项状态，不能进行封包");
		Long gross = MoneyArithUtil.convertStringToMoney(packetDTO.getOriginAssetpoolScale());
		if(gross <=0)
			throw WebException.instance("该资产池规模不能进行封包");
		if(DateUtils.compareDate(packetDTO.getEncapsulationDate(), DateUtils.format(new Date(), 19))==1)
			throw WebException.instance("封包日期不能大于当天");
		SpecialProgram specialProgram = new SpecialProgram();
		specialProgram.setId(id);
		specialProgram.setGross(gross);
		specialProgram.setEncapsulationDate(packetDTO.getEncapsulationDate());
		specialProgram.setStatus(SpecialProgramStatus.PACKAGE.name());
		specialProgramMapper.updateByPrimaryKeySelective(specialProgram);
		//资产池入池日志
		specialProgramLogTransactionService.insertSpecialProgramLog(needPacketSpecialProgram, gross, SpecialProgramStatus.PACKAGE, userVo);
	}
	
	/**
	 * 管理页面 赎回
	 * @param underlyingAssetId
	 * @return
	 */
	@Override
	public void redeemAssetPool(UserVo userVo, String underlyingAssetId) {
		underlyingAssetTransactionService.redeemAssetPool(userVo, underlyingAssetId);
	}

	/**
	 * 管理页面 待入池
	 * @param underlyingAssetId
	 */
	@Override
	public void outAssetPoolAndKeepBind(UserVo userVo, String underlyingAssetId) {
		underlyingAssetTransactionService.outAssetPoolAndKeepBind(userVo, underlyingAssetId);
	}

	/**
	 * 管理页面 出池
	 * @param userId
	 * @param underlyingAssetId
	 */
	@Override
	public void outAssetPoolAndDelete(UserVo userVo, String underlyingAssetId) {
		underlyingAssetTransactionService.outAssetPoolAndDelete(userVo, underlyingAssetId);
	}

	@Override
	public void enterAssetPool(UserVo userVo, List<String> underlyingAssetIds) {
		underlyingAssetTransactionService.enterAssetPool(userVo, underlyingAssetIds);
	}


}
