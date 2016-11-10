package com.travelzen.framework.dubbo.support.filter;

import org.apache.commons.lang3.StringUtils;
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
import com.dianping.cat.message.Transaction;
import com.google.gson.Gson;
import com.travelzen.framework.cat.CatContext;

@Activate(group = {Constants.PROVIDER}, order = Integer.MIN_VALUE + 10)
public class ProviderCatRemoteCallFilter implements Filter{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		Transaction child = Cat.newTransaction("rpc-server", invoker.getInterface().getSimpleName() + "->" + invocation.getMethodName());
		try{
			String catContextJson = invocation.getAttachment("catContext");
			if(StringUtils.isNotBlank(catContextJson)){
                CatContext catContext = new Gson().fromJson(catContextJson, CatContext.class);
                Cat.logRemoteCallServer(catContext);
			}
		}catch(Exception e){
			logger.error("", e);
		}
		
		try{
			Result result = invoker.invoke(invocation);
			child.setStatus(Transaction.SUCCESS);
			return result;
		}catch(RpcException e){
			child.setStatus(e);
			Cat.logError(e);
			throw e;
		}finally{
			child.complete();
		}
	}

}
