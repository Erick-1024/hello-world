package com.travelzen.framework.aop;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Transaction;
import com.travelzen.framework.util.TZBeanUtils;

public class CatMonitorAspect {

	private List<String> servicePkgs;

	private List<String> daoPkgs;

	private List<String> controllerPkgs;

	private List<String> boPkgs;

	public List<String> getServicePkgs() {
		return servicePkgs;
	}

	public void setServicePkgs(List<String> servicePkgs) {
		this.servicePkgs = servicePkgs;
	}

	public List<String> getDaoPkgs() {
		return daoPkgs;
	}

	public void setDaoPkgs(List<String> daoPkgs) {
		this.daoPkgs = daoPkgs;
	}

	public List<String> getControllerPkgs() {
		return controllerPkgs;
	}

	public void setControllerPkgs(List<String> controllerPkgs) {
		this.controllerPkgs = controllerPkgs;
	}

	public List<String> getBoPkgs() {
		return boPkgs;
	}

	public void setBoPkgs(List<String> boPkgs) {
		this.boPkgs = boPkgs;
	}

	public Object doAround(ProceedingJoinPoint point) throws Throwable {
		String type = null, name = null;
		String packageName = point.getTarget().getClass().getPackage().toString();
		if (controllerPkgs != null && controllerPkgs.size() != 0) {
			for (String cp : controllerPkgs) {
				if (packageName.contains(cp)) {
					type = "controller";
				}
			}
		}
		if (servicePkgs != null && servicePkgs.size() != 0) {
			for (String sp : servicePkgs) {
				if (packageName.contains(sp)) {
					type = "service";
				}
			}
		}
		if (daoPkgs != null && daoPkgs.size() != 0) {
			for (String dp : daoPkgs) {
				if (packageName.contains(dp)) {
					type = "dao";
				}
			}
		}

		if (boPkgs != null && boPkgs.size() != 0) {
			for (String bp : boPkgs) {
				if (packageName.contains(bp)) {
					type = "bo";
				}
			}
		}

		if (type == null) {
			return point.proceed();
		}

		name = new StringBuilder().append(point.getTarget().getClass().getSimpleName()).append("->").append(point.getSignature().getName()).toString();
		Transaction t = Cat.getProducer().newTransaction(type, name);
		String args = TZBeanUtils.describe(point.getArgs());
		if (StringUtils.isNotBlank(args)) {
			args = args.length() <= 10000 ? args : args.substring(0, 9999);
			Cat.logEvent(type, name, Message.SUCCESS, args);
		}

		try {
			Object ret = point.proceed();
			t.setStatus(Transaction.SUCCESS);
			return ret;

		} catch (Exception e) {
			Cat.getProducer().logError(e);
			t.setStatus(e);
			throw e;

		} finally {
			t.complete();
		}

	}
}
