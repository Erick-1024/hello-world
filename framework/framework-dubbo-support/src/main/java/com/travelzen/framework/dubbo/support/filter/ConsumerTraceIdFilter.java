package com.travelzen.framework.dubbo.support.filter;

import org.slf4j.MDC;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;

@Activate(group = {Constants.CONSUMER}, order = Integer.MAX_VALUE)
public class ConsumerTraceIdFilter implements Filter{

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		invocation.getAttachments().put("traceId", MDC.get("rpid"));
		return invoker.invoke(invocation);
	}

}
