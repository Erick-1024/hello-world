package com.cana.member.service.transaction.impl;

import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cana.common.dao.mapper.gen.PropertiesMapper;
import com.cana.common.dao.po.Properties;
import com.cana.member.dao.mapper.MemberTableLockMapper;
import com.cana.member.dao.mapper.gen.UserMapper;
import com.cana.member.service.transaction.IMockTransactionService;
import com.travelzen.framework.dao.rdbms.SequenceGenerator;

@Service
public class MockTransactionServiceImpl implements IMockTransactionService{
	
	@Resource
	private PropertiesMapper propertiesMapper;
	
	@Resource
	private SequenceGenerator seqGen;
	
	@Resource
	private MemberTableLockMapper tableLockMapper;
	
	@Resource
	private UserMapper userMapper;
	
	public static final AtomicLong retryNum = new AtomicLong(0);

	@Override
	public void addNewProperty(String propertyName, String propertyValue) throws Exception {
		retryNum.addAndGet(1);
		Properties p = new Properties();
		p.setName(propertyName);
		p.setValue(propertyValue);
		propertiesMapper.insertSelective(p);
		propertiesMapper.insertSelective(p);
		
	}

	@Override
	public void requiredNewEffective(String propertyName, String propertyValue) throws Exception {
		System.out.println(seqGen.getNextSeq(propertyName));
		Properties p = new Properties();
		p.setName(propertyName);
		p.setValue(propertyValue);
		propertiesMapper.insertSelective(p);
		propertiesMapper.insertSelective(p);
	}

	@Override
	public void lock() throws Exception {
		//tableLockMapper.lockMemberUserById("201511060501");
		//userMapper.selectByPrimaryKey("201511060501");
		tableLockMapper.lockMemberUserByUsername("test");
		userMapper.selectByPrimaryKey("201511060501");
	}
	
	
	

}
