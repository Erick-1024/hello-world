package com.cana.yundaex.response.api;

import com.cana.yundaex.common.dto.YundaexBaseResponse;
import com.cana.yundaex.common.dto.limit.YdLimitAuditResultRequest;

/**
 * 韵达审核额度结果信息推送
 * @author xiaoyu
 *
 */
public interface IYundaexPushAuditResultApi {
	/**
	 * 推送审核信息
	 * @param ydLimitAuditResultRequest
	 * @return
	 */
	YundaexBaseResponse sendAuditResultToYD(YdLimitAuditResultRequest ydLimitAuditResultRequest);

}
