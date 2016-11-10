package com.cana.asset.server.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.asset.dao.po.SpecialProgram;
import com.cana.asset.service.IAssetPoolService;
import com.cana.vbam.common.asset.enums.SpecialProgramStatus;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:META-INF/spring/*.xml", "classpath*:spring/asset-service-*.xml" })

public class AssetPoolTest {

	@Resource
	private IAssetPoolService assetPoolService;

	@Test
	public void queryGrossBySpecialProgramIds() {
		List<SpecialProgram> specialPrograms = new ArrayList<>();
		SpecialProgram specialProgram = new SpecialProgram();
		specialProgram.setId("160902145048101");
		specialProgram.setStatus(SpecialProgramStatus.CREATE.name());
		specialPrograms.add(specialProgram);
		
		SpecialProgram specialProgram1 = new SpecialProgram();
		specialProgram1.setId("test001");
		specialProgram1.setStatus(SpecialProgramStatus.PACKAGE.name());
		specialPrograms.add(specialProgram1);
		
		SpecialProgram specialProgram2 = new SpecialProgram();
		specialProgram2.setId("test002");
		specialProgram2.setStatus(SpecialProgramStatus.ISSUE.name());
		specialPrograms.add(specialProgram2);
		
		Map<String, Long> gross = assetPoolService.queryGrossBySpecialProgramIds(specialPrograms);
		System.err.println(gross);
	}
}
