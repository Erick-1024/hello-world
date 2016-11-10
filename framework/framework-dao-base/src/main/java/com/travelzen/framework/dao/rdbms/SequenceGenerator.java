package com.travelzen.framework.dao.rdbms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.travelzen.framework.dao.mybatis.mapper.IBatchSequenceMapper;

@Component
public class SequenceGenerator {
	private static Map<String,SequencePool> seqMap = new HashMap<String,SequencePool>();
	private static final List<String> ALLOT_ONE_SEQUENCENAME_LIST = Arrays.asList(new String[]{"member_user_id", "plan_version", "expense_version"});
	@Resource
	private IBatchSequenceMapper batchSequenceDao;
	//每次请求分配的序列数个数
	public synchronized String getNextSeq(String sequenceName, int width) {
		SequencePool pool = seqMap.get(sequenceName);
		if(seqMap.get(sequenceName) == null || pool.isEmpty()){
			pool = refillPool(sequenceName);
			seqMap.put(sequenceName, pool);
		}
		return formatSequence(String.valueOf(pool.next()),width);
	}
	
	public synchronized String getNextSeq(String sequenceName) {
		SequencePool pool = seqMap.get(sequenceName);
		if(seqMap.get(sequenceName) == null || pool.isEmpty()){
			pool = refillPool(sequenceName);
			seqMap.put(sequenceName, pool);
		}
		return String.valueOf(pool.next());
	}
	
	private SequencePool refillPool(String sequenceName) {
		int allotment = 100;
		if(ALLOT_ONE_SEQUENCENAME_LIST.contains(sequenceName)){
			allotment = 1;
		}
		long nextSeq = batchSequenceDao.getNextSeq(sequenceName, allotment);
		return new SequencePool(nextSeq, nextSeq + allotment -1); 
	}
	
	/**
	 * 
	 * description: 将传入的字符串的长度限制为一定值
	 * @param is
	 * @param width
	 * @return
	 */
	private static String formatSequence(String is, int width) {
		if (is.length() < width)
			for (; is.length() < width; is = "0" + is);
		else
			is = is.substring(is.length() - width, is.length());
		return is;
	}
	
	private static class SequencePool{
		private long low;
		private long high;
		public SequencePool(long low, long high){
			this.low = low;
			this.high = high;
		}
		public long next() {
			return low++;
		}
		public boolean isEmpty(){
			return low > high;
		}
	}
}
