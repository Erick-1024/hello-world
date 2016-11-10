package com.travelzen.framework.lucene.index.test.reflection;

import static org.junit.Assert.fail;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

import org.junit.Test;

import com.travelzen.framework.lucene.index.test.student.StudentIndexBean;
import com.travelzen.framework.lucene.index.test.student.generateStudentUtil;

public class CollectionType {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCollectionType() throws NoSuchFieldException, SecurityException {
		StudentIndexBean b = generateStudentUtil.getStudents().get(0);
		Field f = b.getClass().getField("markDate");
		ParameterizedType p = (ParameterizedType) f.getGenericType();
		System.out.println(p.getActualTypeArguments()[0]);

	}

	@Test
	public void testReflection() {
		StudentIndexBean stu = new StudentIndexBean();
		stu.setAddress("shanghai");
		Class<? extends StudentIndexBean> stuClass = stu.getClass();
		Field[] fieldList = stuClass.getFields();
		for (Field f : fieldList) {
			System.out.println(f.getName());
			System.out.println(f.getType());
		}
	}
	
	@Test
	public void testReflectionRead() throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		StudentIndexBean stu = new StudentIndexBean();
		stu.setRemark("fdafafads");
		PropertyDescriptor pd = new PropertyDescriptor("remark", stu.getClass());
		// Method writerM = pd.getWriteMethod();
		Method readM = pd.getReadMethod();
		System.out.println(readM.invoke(stu));
	}
	
}
