package com.cana.vbam.front.biz.controller.homsom;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cana.asset.api.IHomsomSettlementApi;
import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.homsom.dto.HomsomSettlementTrackDTO;
import com.cana.vbam.common.homsom.dto.HomsomSettlementTrackRequestDTO;
import com.cana.vbam.common.homsom.enums.Channel;

/**
 * 恒顺controller
 * @author jiangzhou.Ren
 * @time 2016年10月19日下午2:08:14
 */
@Controller
@RequestMapping("/homsom/settlement/track")
public class HomsomSettlementTrackController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private IHomsomSettlementApi homsomSettlementApi;
	
	/**
	 * 恒顺核销履历查询列表页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list/{channel}", method = RequestMethod.GET)
	public String list(Model model) {
		logger.info("进入查询核销履历页面");
		return "page/homsom/track/list";
	}
	
	/**
	 * 恒顺列表数据
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list/{channel}", method = { RequestMethod.POST })
	@ResponseBody
	public ListResult<HomsomSettlementTrackDTO> getSettlementTrackList(HomsomSettlementTrackRequestDTO request, @PathVariable Channel channel) {
		try {
			request.setChannel(channel);
			return homsomSettlementApi.getSettlementTrackList(request);
		} catch (Exception e) {
			logger.error("恒顺核销履历查询列表页面查询失败", e);
			return ListResult.fail("核销履历列表查询失败");
		}
	}
}
