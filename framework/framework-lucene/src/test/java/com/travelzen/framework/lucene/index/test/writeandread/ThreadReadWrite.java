package com.travelzen.framework.lucene.index.test.writeandread;

import java.util.List;

import org.junit.Test;

import com.travelzen.framework.lucene.index.IndexBuilder;
import com.travelzen.framework.lucene.index.init.InitIndexUtil;
import com.travelzen.framework.lucene.index.test.student.StudentIndexBean;
import com.travelzen.framework.lucene.index.test.student.generateStudentUtil;

public class ThreadReadWrite {

	// private static final Logger LOGGER = LoggerFactory.getLogger(TestThreadAddIndex.class);

	@Test
	public void testAddIndex() throws Exception {
		System.out.println("准备增加所有");
		List<StudentIndexBean> stuList = generateStudentUtil.getStudents();
		IndexBuilder indexBuilder = IndexBuilder.getIndexBuilder();
		indexBuilder.addIndexs(stuList);
	}
	
	@Test
	public void testReadAndWriter() throws Exception{
		
		
	}

	public static void main(String[] args) throws Exception {
		
//		InitIndexUtil.initIndex("/data/tzjn//", true, null, false, StudentIndexBean.class);
//		new Thread(new Runnable(){
//			@SuppressWarnings("static-access")
//			@Override
//			public void run() {
//				try {
//					Thread.currentThread().sleep(500);
//				} catch (InterruptedException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				System.out.println("start 1");
//				BooleanQueryExample exa = new BooleanQueryExample();
//				try {
//					exa.multiFieldSearch(Arrays.asList("address","name"),"我");
//				} catch (ParseException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				IndexBuilder ib = IndexBuilder.getIndexBuilder();
//				List<StudentIndexBean> result = null;
//				try {
//					result = ib.searchAll(exa,StudentIndexBean.class);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				System.out.println("个数:"+result.size());
//			}
//		}).start();
//		
//		
//		new Thread(new Runnable(){
//			@SuppressWarnings("static-access")
//			@Override
//			public void run() {
//				try {
//					Thread.currentThread().sleep(1500);
//				} catch (InterruptedException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				System.out.println("start 2");
//				List<StudentIndexBean> stuList = generateStudentUtil.getStudents();
//				IndexBuilder indexBuilder = IndexBuilder.getIndexBuilder();
//				try {
//					indexBuilder.addIndexs(stuList);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				System.out.println("add index ok");
//			}
//		}).start();
//		
//		new Thread(new Runnable(){
//			@SuppressWarnings("static-access")
//			@Override
//			public void run() {try {
//				Thread.currentThread().sleep(10000);
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//				System.out.println("start 3");
//				BooleanQueryExample exa = new BooleanQueryExample();
//				try {
//					exa.multiFieldSearch(Arrays.asList("address","name"),"我");
//				} catch (ParseException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				IndexBuilder ib = IndexBuilder.getIndexBuilder();
//				List<StudentIndexBean> result = null;
//				try {
//					result = ib.searchAll(exa,StudentIndexBean.class);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				System.out.println("===个数:"+result.size());
//			}
//		}).start();
//		
//		
//		
//		new Thread(new Runnable(){
//			@SuppressWarnings("static-access")
//			@Override
//			public void run() {try {
//				Thread.currentThread().sleep(15000);
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//				System.out.println("start 4");
//				BooleanQueryExample exa = new BooleanQueryExample();
//				try {
//					exa.multiFieldSearch(Arrays.asList("address","name"),"我");
//				} catch (ParseException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				IndexBuilder ib = IndexBuilder.getIndexBuilder();
//				List<StudentIndexBean> result = null;
//				try {
//					result = ib.searchAll(exa,StudentIndexBean.class);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				System.out.println("===个数:"+result.size());
//			}
//		}).start();
		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
//		InitIndexUtil.initIndex("/data/tzjn/", true, null, false, StudentIndexBean.class);
		
//		InitIndexUtil.initIndex(null, false, null, false, StudentIndexBean.class);
//		List<StudentIndexBean> stuList = generateStudentUtil.getStudents();
//		IndexBuilder indexBuilder = IndexBuilder.getIndexBuilder();
//		try {
//			indexBuilder.addIndex(stuList.get(0));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		for (int i = 0; i < 20; i++) {
//			new Thread(new Runnable() {
//				@Override
//				public void run() {
//					// latch.countDown();
//					BooleanQueryExample exa = new BooleanQueryExample();
//					try {
//						exa.multiFieldSearch(Arrays.asList("address", "name"), "我");
//					} catch (ParseException e) {
//						e.printStackTrace();
//					}
//					IndexBuilder ib = IndexBuilder.getIndexBuilder();
//					List<StudentIndexBean> result = null;
//					try {
//						result = ib.searchAll(exa, StudentIndexBean.class);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//					System.out.println("=================个数:" + result.size());
//				}
//			}).start();
//		}
//		
//		
//		final CountDownLatch latch = new CountDownLatch(15);
//
//		for (int i = 0; i < 10; i++) {
//			new Thread(new Runnable() {
//				@Override
//				public void run() {
//					latch.countDown();
//					List<StudentIndexBean> stuList = generateStudentUtil.getStudents();
//					IndexBuilder indexBuilder = IndexBuilder.getIndexBuilder();
//					try {
//						long time = (long) (Math.random()*10);
//						Thread.currentThread().sleep(time);
//						indexBuilder.addIndexs(stuList);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//			}).start();
//		}
//
//		for (int i = 0; i < 20; i++) {
//			new Thread(new Runnable() {
//				@Override
//				public void run() {
//					latch.countDown();
//					BooleanQueryExample exa = new BooleanQueryExample();
//					try {
//						exa.multiFieldSearch(Arrays.asList("address", "name"), "我");
//					} catch (ParseException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					IndexBuilder ib = IndexBuilder.getIndexBuilder();
//					List<StudentIndexBean> result = null;
//					try {
//						result = ib.searchAll(exa, StudentIndexBean.class);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//					System.out.println("个数:" + result.size());
//				}
//			}).start();
//		}
		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
//		InitIndexUtil.initIndex("/data/tzjn/", true, null, false, StudentIndexBean.class);
//		new Thread(new Runnable(){
//			@Override
//			public void run() {
//				BooleanQueryExample exa = new BooleanQueryExample();
//				try {
//					exa.multiFieldSearch(Arrays.asList("address", "name"), "我");
//				} catch (ParseException e) {
//					e.printStackTrace();
//				}
//				IndexBuilder ib = IndexBuilder.getIndexBuilder();
//				List<StudentIndexBean> result = null;
//				try {
//					result = ib.searchAll(exa, StudentIndexBean.class);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				System.out.println("11111111111=======个数:" + result.size());
//			}
//		}).start();
//		
//
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				List<StudentIndexBean> stuList = generateStudentUtil.getStudents();
//				IndexBuilder indexBuilder = IndexBuilder.getIndexBuilder();
//				try {
//					indexBuilder.addIndexs(stuList);
//					System.out.println("=======已经写了");
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}).start();
//		
//		
//		new Thread(new Runnable(){
//			@Override
//			public void run() {
//				BooleanQueryExample exa = new BooleanQueryExample();
//				try {
//					exa.multiFieldSearch(Arrays.asList("address", "name"), "我");
//				} catch (ParseException e) {
//					e.printStackTrace();
//				}
//				IndexBuilder ib = IndexBuilder.getIndexBuilder();
//				List<StudentIndexBean> result = null;
//				try {
//					result = ib.searchAll(exa, StudentIndexBean.class);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				System.out.println("!!!!!!!!!!!============个数:" + result.size());
//			}
//		}).start();
		
		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
//		InitIndexUtil.initIndex("/data/tzjn/", true, null, false, StudentIndexBean.class);
//		BooleanQueryExample exa = new BooleanQueryExample();
//		try {
//			exa.multiFieldSearch(Arrays.asList("address", "name"), "我");
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		IndexBuilder ib = IndexBuilder.getIndexBuilder();
//		List<StudentIndexBean> result = null;
//		try {
//			result = ib.searchAll(exa, StudentIndexBean.class);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println("11111111111=======个数:" + result.size());
//
//		List<StudentIndexBean> stuList = generateStudentUtil.getStudents();
//		IndexBuilder indexBuilder = IndexBuilder.getIndexBuilder();
//		try {
//			indexBuilder.addIndexs(stuList);
//			System.out.println("=======已经写了");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		try {
//			result = ib.searchAll(exa, StudentIndexBean.class);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println("!!!!!!!!!!!============个数:" + result.size());
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		for(int i=0;i<5;i++){
			InitIndexUtil.initIndex("/data/tzjn/", true, null, false, StudentIndexBean.class);
			List<StudentIndexBean> stuList = generateStudentUtil.getStudents();
			IndexBuilder indexBuilder = IndexBuilder.getIndexBuilder();
			try {
				indexBuilder.addIndexs(stuList);
				System.out.println("=======已经写了");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
