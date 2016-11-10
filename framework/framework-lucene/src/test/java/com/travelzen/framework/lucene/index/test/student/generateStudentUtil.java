package com.travelzen.framework.lucene.index.test.student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class generateStudentUtil {
	
	public static List<StudentIndexBean> getStudents(){
		List<StudentIndexBean> students = new ArrayList<>();
		
		StudentIndexBean s1 = new StudentIndexBean("7856241","放得开地方开工积分抵扣看地方15665！@#￥%……&*（）——fgdsg方地方开关机","在所谓撒我",34L,false,"打法华南个扫啊算法撒 ",Arrays.asList("chinese","english"),new Date("1990/03/10"),Arrays.asList(new Date("2014/01/12"),new Date("2013/11/12"),new Date("2012/01/12")));
		StudentIndexBean s2 = new StudentIndexBean("3214324","诺基亚","fdaf我cxz",24L,false,"发达撒范德萨家乐福打算",Arrays.asList("japnie","rose"),new Date("1990/04/10"),Arrays.asList(new Date("2015/01/12"),new Date("2001/01/12"),new Date("2003/01/12")));
		StudentIndexBean s3 = new StudentIndexBean("5435245","诺基亚","n97",2L,true,"发达发达发",Arrays.asList("englis","hanbn"),new Date("1990/05/10"),Arrays.asList(new Date("2004/01/12"),new Date("2000/01/12"),new Date("2005/01/12")));
		StudentIndexBean s4 = new StudentIndexBean("6543256","诺基亚n97","fda4",67L,false,"范德萨个",Arrays.asList("niuxi"),new Date("1990/06/10"),Arrays.asList(new Date("2007/01/12"),new Date("2008/01/12"),new Date("2009/01/12")));
		StudentIndexBean s5 = new StudentIndexBean("415432","诺基亚n97","方面",12L,true,"规范的",Arrays.asList("AAA","BBB","CCC"),new Date("1990/07/10"),Arrays.asList(new Date("2010/01/12"),new Date("2011/01/12"),new Date("2012/01/12")));
		StudentIndexBean s6 = new StudentIndexBean("542623","诺基亚特使6","fdatest",87L,false,"发达f",Arrays.asList("xx","xa"),new Date("1990/08/10"),Arrays.asList(new Date("2013/01/12"),new Date("2014/01/12"),new Date("2015/01/12")));
		StudentIndexBean s7 = new StudentIndexBean("9874365","诺基亚特使77阿斯顿发生的发达沙发睡地方","特使",12L,true,"发达",Arrays.asList("x1","x2"),new Date("1990/09/10"),Arrays.asList(new Date("2001/01/12"),new Date("1914/01/12"),new Date("1814/01/12")));
		StudentIndexBean s8 = new StudentIndexBean("5425143","诺基亚","7",34L,false,"规范撒个",Arrays.asList("y1","y2"),new Date("2005/03/10"),Arrays.asList(new Date("1714/01/12"),new Date("1914/01/12"),new Date("1814/01/12")));
		StudentIndexBean s9 = new StudentIndexBean("431536","我们特使9","7",34L,false,"规范撒个",Arrays.asList("y1","y2"),new Date("2005/03/10"),Arrays.asList(new Date("2012/01/12"),new Date("2011/01/12"),new Date("2013/01/12")));
		StudentIndexBean s10 = new StudentIndexBean("453143","我们特使10","7",34L,false,"规范撒个",Arrays.asList("y1","y2"),new Date("2005/03/10"),Arrays.asList(new Date("2012/01/12"),new Date("2013/01/12"),new Date("2014/01/12")));
		
		students.add(s1);
		students.add(s2);
		students.add(s3);
		students.add(s4);
		students.add(s5);
		students.add(s6);
		students.add(s7);
		students.add(s8);
		students.add(s9);
		students.add(s10);
		
		return students;
	}
	
	
	public static List<StudentIndexBean> getStudents2(){
		List<StudentIndexBean> students = new ArrayList<>();
		
		StudentIndexBean s1 = new StudentIndexBean("7856241","上海哪个路口","寒中1我",34L,false,"打法华南个扫啊算法撒 ",Arrays.asList("chinese","english"),new Date("1990/03/10"),Arrays.asList(new Date("2014/01/12"),new Date("2013/11/12"),new Date("2012/01/12")));
		StudentIndexBean s2 = new StudentIndexBean("3214324","我们特使2","姓名",24L,false,"发达撒范德萨家乐福打算",Arrays.asList("japnie","rose"),new Date("1990/04/10"),Arrays.asList(new Date("2015/01/12"),new Date("2001/01/12"),new Date("2003/01/12")));
		
		students.add(s1);
		students.add(s2);
		
		return students;
	}
	
	
}
