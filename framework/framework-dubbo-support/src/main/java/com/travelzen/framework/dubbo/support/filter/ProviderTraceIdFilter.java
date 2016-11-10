package com.travelzen.framework.dubbo.support.filter;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;

@Activate(group = {Constants.PROVIDER}, order = Integer.MIN_VALUE)
public class ProviderTraceIdFilter implements Filter{

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		String traceId = generateTraceIdIfMissing(invocation);
		MDC.put("rpid", traceId);
		return invoker.invoke(invocation);
	}

    private String generateTraceIdIfMissing(Invocation invocation) {
    	String traceId = invocation.getAttachment("traceId");
    	if(StringUtils.isBlank(traceId)){
    		traceId = RpcContext.getContext().getRemoteAddressString() + "-" + RandomStringUtils.randomAlphanumeric(10);
    	}
		return traceId;
	}

}
