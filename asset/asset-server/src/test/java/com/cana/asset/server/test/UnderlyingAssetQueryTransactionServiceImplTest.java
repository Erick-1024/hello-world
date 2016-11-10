package com.cana.asset.server.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.asset.service.transaction.IUnderlyingAssetQueryTransactionService;
import com.cana.vbam.common.asset.enums.UnderlyingAssetPoolStatus;
import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetDTO;
import com.cana.vbam.common.asset.underlyingasset.dto.UnderlyingAssetQueryDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:META-INF/spring/*.xml", "classpath*:spring/asset-service-*.xml" })
public class UnderlyingAssetQueryTransactionServiceImplTest {

	@Resource
	private IUnderlyingAssetQueryTransactionService underlyingAssetQueryTransactionServiceImpl;
	
	@Test
	public void updateIndexQuery(){
		UnderlyingAssetQueryDTO queryDTO = new UnderlyingAssetQueryDTO();
		queryDTO.setAssetPoolStatus(UnderlyingAssetPoolStatus.ENTERING);
		queryDTO.setLoadExtraData(Boolean.TRUE);
		List<UnderlyingAssetDTO> queryUnderlyingAssetData = underlyingAssetQueryTransactionServiceImpl.queryUnderlyingAssetData(queryDTO);
		System.out.println(queryUnderlyingAssetData.size());
	}
}
