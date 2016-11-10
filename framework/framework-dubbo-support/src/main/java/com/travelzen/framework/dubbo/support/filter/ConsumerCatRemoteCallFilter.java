package com.travelzen.framework.dubbo.support.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;
import com.dianping.cat.Cat;
import com.google.gson.Gson;
import com.travelzen.framework.cat.CatContext;

@Activate(group = {Constants.CONSUMER}, order = Integer.MAX_VALUE - 10)
public class ConsumerCatRemoteCallFilter implements Filter{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		try{
			CatContext catContext = new CatContext();
			Cat.logRemoteCallClient(catContext);
			invocation.getAttachments().put("catContext", new Gson().toJson(catContext));
		}catch(Exception e){
			logger.error("", e);
		}
		return invoker.invoke(invocation);
	}

}
