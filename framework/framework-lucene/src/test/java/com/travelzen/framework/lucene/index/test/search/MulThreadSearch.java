package com.travelzen.framework.lucene.index.test.search;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.apache.lucene.queryParser.ParseException;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import com.travelzen.framework.lucene.index.IndexBuilder;
import com.travelzen.framework.lucene.index.init.InitIndexUtil;
import com.travelzen.framework.lucene.index.query.bean.BooleanQueryExample;
import com.travelzen.framework.lucene.index.test.student.StudentIndexBean;

public class MulThreadSearch {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	public static void main(String[] args) throws Exception{
		//final CountDownLatch latch = new CountDownLatch(10);
		InitIndexUtil.initIndex("/data/tzjn", true, null, false, StudentIndexBean.class);
		
		for(int i=0;i<40;i++){
			new Thread(new Runnable(){
				@Override
				public void run() {
					BooleanQueryExample exa = new BooleanQueryExample();
					try {
						exa.multiFieldSearch(Arrays.asList("address","name"),"我");
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					IndexBuilder ib = IndexBuilder.getIndexBuilder();
					List<StudentIndexBean> result = null;
					try {
						result = ib.searchAll(exa,StudentIndexBean.class);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(CollectionUtils.isEmpty(result)){
						System.out.println("为空");
					}else{
						System.out.println("个数:"+result.size());
					}
				}
			}).start();
		}
		
	}
	
	
}
