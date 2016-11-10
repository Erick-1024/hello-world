package com.cana.asset.server.test;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cana.asset.dao.po.SpecialProgram;
import com.cana.asset.service.IUnderlyingAssetImportService;
import com.cana.asset.service.transaction.IABSSpecialProgramTransactionService;
import com.cana.member.api.IMemberQueryApi;
import com.cana.vbam.common.asset.dto.SpecialProgramListDTO;
import com.cana.vbam.common.asset.dto.SpecialProgramListRequestDTO;
import com.cana.vbam.common.asset.dto.SpecialProgramQueryDTO;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.member.vo.UserVo;
import com.google.gson.Gson;
import com.travelzen.framework.common.PageList;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:META-INF/spring/*.xml", "classpath*:spring/asset-service-*.xml"})
public class SpecialProgramTest {

	@Resource
	private IABSSpecialProgramTransactionService specialProgramTransactionService;

	private Gson gson = new Gson();

	private UserVo userVo;
	private String factorId = "201607050033";

	@Resource
	private IMemberQueryApi memberQueryApi;

	@Resource
	private IUnderlyingAssetImportService underlyingAssetImportService;

	@Before
	public void before() {
		userVo = memberQueryApi.findUserById(factorId);
	}

	@Test
	public void getSpecialProgramList() {
		SpecialProgramListRequestDTO request = new SpecialProgramListRequestDTO();
		request.setForUnderlyingAssetEnter(true);
		request.setPageSize(20);
		ListResult<SpecialProgramListDTO> result = specialProgramTransactionService.querySpecialProgramList(request, userVo);
		System.out.println("total:" + result.getTotalNum());
		System.out.println("size:" + result.getData().size());
		for (SpecialProgramListDTO program : result.getData()) {
			System.out.println(program.getStatus() + "\t" + program.getId());
		}
	}

}
