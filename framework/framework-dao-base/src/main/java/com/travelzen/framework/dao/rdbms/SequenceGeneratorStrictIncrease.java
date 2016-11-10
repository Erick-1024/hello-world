package com.travelzen.framework.dao.rdbms;

import org.apache.commons.lang.StringUtils;

/**
 * 在SequenceGenerator基础上实现的序列号生成器,　原生成器会发生跳号现象.　此生成器严格递增(即每次都会到数据库读取)
 * 
 * @since 2014-4-4 上午10:47:56
 * @author cheguangai
 * @TODO TODO
 */
public class SequenceGeneratorStrictIncrease {
	private BatchSequenceDaoImpl batchSequenceDao;

	public synchronized String getNextSeq(String sequenceName, int width) throws Exception {
		long seq = batchSequenceDao.getNextSeq(sequenceName, 1);// 每次只取一个序列
		return formatSequence(String.valueOf(seq), width);
	}

	public synchronized String getNextSeq(String sequenceName) throws Exception {
		long seq = batchSequenceDao.getNextSeq(sequenceName, 1);// 每次只取一个序列
		return String.valueOf(seq);
	}

	/**
	 * 
	 * description: 将传入的字符串的长度限制为一定值
	 * 
	 * @param is
	 * @param width
	 * @return
	 */
	private static String formatSequence(String is, int width) {
		if (is.length() < width) {
			is = StringUtils.leftPad(is, width, '0');
		} else {
			is = StringUtils.right(is, width);
		}
		return is;
	}

	public BatchSequenceDaoImpl getBatchSequenceDao() {
		return batchSequenceDao;
	}

	public void setBatchSequenceDao(BatchSequenceDaoImpl batchSequenceDao) {
		this.batchSequenceDao = batchSequenceDao;
	}
	
}
