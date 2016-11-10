package com.travelzen.framework.lucene.index.test.student;

import java.util.ArrayList;
import java.util.List;

public class generateTeacherUtil {

	public static List<TeacherIndexBean> getTeachers() {
		List<TeacherIndexBean> tList = new ArrayList<>();
		TeacherIndexBean t1 = new TeacherIndexBean();
		t1.setName("美丽");
		t1.setAddress("上海的");
		TeacherIndexBean t2 = new TeacherIndexBean();
		t2.setName("和海");
		t2.setAddress("男周的的");
		tList.add(t1);
		tList.add(t2);
		return tList;
	}

}
