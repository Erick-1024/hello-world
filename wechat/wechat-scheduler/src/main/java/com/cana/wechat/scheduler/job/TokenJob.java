package com.cana.wechat.scheduler.job;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.cana.wechat.client.IWeChatClinet;
import com.cana.wechat.common.dto.TokenRedisDTO;
import com.cana.wechat.common.dto.TokenReponse;
import com.cana.wechat.common.dto.TokenRequest;
import com.cana.wechat.common.util.Constants;
import com.cana.wechat.service.IWeChatTokenService;

// 如果实例不允许并发执行，一定要加这个标签
@DisallowConcurrentExecution
public class TokenJob extends QuartzJobBean{  
	
	@Resource
	private IWeChatClinet weChatClinet;
	
	@Resource
	private IWeChatTokenService tokenService; 
	
	// 超时时间
	private static long timeOut = 7000;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
    public void executeInternal(JobExecutionContext context) throws JobExecutionException {   	
    	MDC.put(com.cana.vbam.common.utils.Constants.TRACE_ID, RandomStringUtils.randomAlphanumeric(10));
		try {
			TokenRedisDTO token = tokenService.getToken();
			
			// 本次获取与上次获取进行比较，如果大于7200秒则获取一次
			if (token == null || token.getLastTime() + timeOut * 1000 < new Date().getTime()) {
				TokenRequest request = new TokenRequest();
				request.setAppid(Constants.WECHAT_APPID_PARAM); // 第三方用户唯一凭证
				request.setSecret(Constants.WECHAT_SECRET_PARAM); // 第三方用户唯一凭证密钥
				TokenReponse reponse = weChatClinet.token(request);
				tokenService.saveToken(new Date().getTime(), reponse.getAccess_token());
			}
		}catch(Exception e){
			logger.error("获取微信toke失败", e);
			return;
		}finally {
			MDC.clear();
		}
    }
}  
