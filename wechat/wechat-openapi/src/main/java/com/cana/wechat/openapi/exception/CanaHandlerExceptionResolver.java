package com.cana.wechat.openapi.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.cana.vbam.common.dto.BaseResponse;
import com.google.gson.Gson;
import com.travelzen.framework.core.common.ReturnCode;


public class CanaHandlerExceptionResolver implements HandlerExceptionResolver, Ordered {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest aReq, HttpServletResponse aRes, Object aHandler, Exception exception) {
        logger.error("微信项目异常", exception);
        BaseResponse response = new BaseResponse();
        response.setRetCode(ReturnCode.ERROR);
        response.setRetMsg("未知异常");
        try{
        	aRes.getOutputStream().write(new Gson().toJson(response).getBytes("UTF-8"));
        	aRes.getOutputStream().close();
        }catch(Exception e){
        	logger.error("微信项目异常", e);
        }
        return new ModelAndView();
    }

}
