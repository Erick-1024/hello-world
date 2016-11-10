package com.travelzen.framework.core.util;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;


public class CollectionUtilTest {

	@Test
	public void removeDuplicate() {
		assertNull(CollectionUtil.removeDuplicate(null));
		System.out.println(CollectionUtil.removeDuplicate(Arrays.asList(new String[]{"1", "2", "3", "1"})));
	}
	
	@Test
	public void isEqualCollection(){
		assertTrue(CollectionUtil.isEqualCollection(null, null));
		assertFalse(CollectionUtil.isEqualCollection(null, Arrays.asList("a")));
		assertFalse(CollectionUtil.isEqualCollection(Arrays.asList("a"), null));
		assertTrue(CollectionUtil.isEqualCollection(Arrays.asList("a"), Arrays.asList("a")));
	}

}
