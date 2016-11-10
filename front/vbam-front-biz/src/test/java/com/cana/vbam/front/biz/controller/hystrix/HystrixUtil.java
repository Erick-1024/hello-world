/*package com.cana.vbam.front.biz.controller.hystrix;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cana.vbam.common.dto.ListResult;
import com.cana.vbam.common.setting.dto.CanaCalendarExcelDTO;
import com.cana.vbam.common.setting.dto.CanaCalendarRequest;
import com.netflix.hystrix.HystrixCommand.Setter;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;

@Component
public class HystrixUtil {

	public ListResult<?> getCanaCalendarList(CanaCalendarRequest canaCalendarRequest) {
		Setter setter = Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("Setting"));	//GroupKey对命令进行分组,最小配置
		setter.andCommandKey(HystrixCommandKey.Factory.asKey("canaCalendar"))	//每个CommandKey代表一个依赖抽象,相同的依赖要使用相同的CommandKey名称。依赖隔离的根本就是对相同CommandKey的依赖做隔离.
		.andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("dubboThreadPool"))	//虽然在业务上都是相同的组，但是需要在资源上做隔离时，可以使用HystrixThreadPoolKey区分.
		.andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(2).withQueueSizeRejectionThreshold(2))		////服务线程池数量
		.andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(10000)	//依赖超时时间。默认1s
				.withCircuitBreakerErrorThresholdPercentage(50)		// 熔断器在一定时间内达到50的错误率时触发
				.withCircuitBreakerSleepWindowInMilliseconds(5000)	//断开时间。次处为5s
				.withCircuitBreakerRequestVolumeThreshold(10)	//判断熔断条件的最少次数，默认20
				.withMetricsRollingStatisticalWindowInMilliseconds(5000));	//判断熔断条件的时间间隔默认为10s
		return new CanaCalendarGetListCommand(setter, canaCalendarRequest).execute();
	}
	
	public List<CanaCalendarExcelDTO> getCanaCalendarExcelList(CanaCalendarRequest canaCalendarRequest) {
		Setter setter = Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("Setting"));	//GroupKey对命令进行分组,最小配置
		setter.andCommandKey(HystrixCommandKey.Factory.asKey("canaCalendar"))	//每个CommandKey代表一个依赖抽象,相同的依赖要使用相同的CommandKey名称。依赖隔离的根本就是对相同CommandKey的依赖做隔离.
		.andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("dubboThreadPool"))	//虽然在业务上都是相同的组，但是需要在资源上做隔离时，可以使用HystrixThreadPoolKey区分.
		.andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(2))		////服务线程池数量
		.andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(10000)	//依赖超时时间。默认1s
				.withCircuitBreakerErrorThresholdPercentage(50)		// 熔断器在一定时间内达到50的错误率时触发
				.withCircuitBreakerSleepWindowInMilliseconds(5000)	//断开时间。次处为5s
				.withCircuitBreakerRequestVolumeThreshold(10)	//判断熔断条件的最少次数，默认20
				.withMetricsRollingStatisticalWindowInMilliseconds(5000));	//判断熔断条件的时间间隔默认为10s
		return new CanaCalendarGetExcelListCommand(setter, canaCalendarRequest).execute();
	}
	
}*/