package com.mjkim.springmvc.step10.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mjkim.springmvc.step10.dao.UserProfileDao;
import com.mjkim.springmvc.step10.model.UserProfile;
import com.mjkim.springmvc.step10.service.UserProfileService;

@Service
@Transactional
public class UserProfileServiceImpl implements UserProfileService {
	@Autowired
	private UserProfileDao dao;

	public UserProfile findById(int id) {
		return dao.findById(id);
	}
	public UserProfile findByType(String type) {
		return dao.findByType(type);
	}
	
	public List<UserProfile> findAll() {
		return dao.findAll();
	}
}