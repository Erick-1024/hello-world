package com.cana.front.common.cana;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.travelzen.framework.core.exception.WebException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.monitor.RequestIdentityHolder;


public class CanaHandlerExceptionResolver implements HandlerExceptionResolver, Ordered {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest aReq, HttpServletResponse aRes, Object aHandler, Exception exception) {
        logger.error("", exception);
        ModelAndView mav = new ModelAndView("page/error");
        String errorMsg = "未知异常";
		if (exception instanceof WebException) {
			errorMsg = exception.getMessage();
		}
        mav.addObject("errorMsg", errorMsg);
        mav.addObject("rpid", RequestIdentityHolder.get().getRpid());
        mav.addObject("serverTime", DateTimeUtil.format(new Date(), DateTimeUtil.DATE_TIME_PATTERN));
        return mav;
    }

}
