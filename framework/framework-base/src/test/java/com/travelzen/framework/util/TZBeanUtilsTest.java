package com.travelzen.framework.util;

import org.junit.Test;

public class TZBeanUtilsTest {
	
	private class Person{
		private String name;
		private Person relative;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Person getRelative() {
			return relative;
		}
		public void setRelative(Person relative) {
			this.relative = relative;
		}
		
	}

	@Test
	public void describe() throws Exception{
		Person parent = new Person();
		parent.setName("parent");
		Person child = new Person();
		child.setName("child");
		child.setRelative(parent);
		parent.setRelative(child);
		System.out.println(TZBeanUtils.describe(parent));
	}

}
