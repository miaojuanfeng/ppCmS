package com.joyhong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyhong.dao.UserDeviceMapper;
import com.joyhong.model.UserDevice;
import com.joyhong.service.UserDeviceService;

@Service
public class UserDeviceServiceImpl implements UserDeviceService{
	
	@Autowired
	private UserDeviceMapper userDeviceMapper;

	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int deleteByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return userDeviceMapper.deleteByUserId(userId);
	}

	public int insert(UserDevice record) {
		// TODO Auto-generated method stub
		return userDeviceMapper.insert(record);
	}

	public int insertSelective(UserDevice record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public UserDevice selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public int updateByPrimaryKeySelective(UserDevice record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKey(UserDevice record) {
		// TODO Auto-generated method stub
		return 0;
	}

}
