package com.mjkim.springmvc.step9.dao;

import java.util.List;
import com.mjkim.springmvc.step9.model.UserProfile;

public interface UserProfileDao {
	UserProfile findByType(String type);
	UserProfile findById(int id);
	List<UserProfile> findAll();
}
