package com.cana.vbam.rabbitmq;

public class PojoListener {
	public String handleMessage(String foo) {
		return foo.toUpperCase();
	}
}
