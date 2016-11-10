package com.cana.repayment.service.bo;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.cana.repayment.dao.mapper.gen.RepaymentDailyBatchTaskItemMapper;
import com.cana.repayment.dao.po.RepaymentDailyBatchTaskItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.travelzen.framework.spring.web.context.SpringApplicationContext;

public class RepaymentDailyBatchTaskItemBO extends RepaymentDailyBatchTaskItem{
	
	private static final long serialVersionUID = -237308223431480878L;
	
	private RepaymentDailyBatchTaskItemMapper taskItemMapper = SpringApplicationContext.getApplicationContext().getBean(RepaymentDailyBatchTaskItemMapper.class);
	
	private RepaymentDailyBatchTaskBO taskBO;

	
	public RepaymentDailyBatchTaskItemBO(String id){
		this(SpringApplicationContext.getApplicationContext().getBean(RepaymentDailyBatchTaskItemMapper.class).selectByPrimaryKey(id));
	}
	
	public RepaymentDailyBatchTaskItemBO(RepaymentDailyBatchTaskItem po){
		BeanUtils.copyProperties(po, this);
	}

	public RepaymentDailyBatchTaskItemBO(RepaymentDailyBatchTaskBO taskBO, RepaymentDailyBatchTaskItem po){
		BeanUtils.copyProperties(po, this);
		this.taskBO = taskBO;
	}
	
	/**
	 * 懒加载taskBO
	 * @return
	 */
	public RepaymentDailyBatchTaskBO lazyLoadTaskBO(){
		if(taskBO != null)
			return taskBO;
		taskBO = new RepaymentDailyBatchTaskBO(getTaskId());
		return taskBO;
	}
	
	/**
	 *  下一个要执行的taskItemBO
	 * @return
	 */
	public RepaymentDailyBatchTaskItemBO nextTaskItemBO(){
		int nextTaskItemSequence = getSequence() + 1;
		if(nextTaskItemSequence < lazyLoadTaskBO().getTaskNum())
			return lazyLoadTaskBO().lazyLoadTaskItemBOs().get(nextTaskItemSequence);
		return null;
	}

	/**
	 * 保存当前状态
	 */
	public void save() {
		taskItemMapper.updateByPrimaryKey(this);
	}

	/**
	 * 更新自定义数据
	 * @param string
	 * @param businessSeq
	 */
	public void updateExtraData(String key, String value) {
		HashMap<String, String> extraData = new HashMap<>();
		if(StringUtils.isNotBlank(getExtraData()))
			extraData = new Gson().fromJson(getExtraData(), new TypeToken<HashMap<String, String>>(){}.getType());
		extraData.put(key, value);
		setExtraData(new Gson().toJson(extraData));
	}

	/**
	 * 获取指定key值的自定义数据
	 * @param key
	 * @return
	 */
	public String extraData(String key) {
		HashMap<String, String> extraData = new HashMap<>();
		if(StringUtils.isNotBlank(getExtraData()))
			extraData = new Gson().fromJson(getExtraData(), new TypeToken<HashMap<String, String>>(){}.getType());
		return extraData.get(key);
	}
}
