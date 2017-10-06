package com.mjkim.springmvc.step10.service;

import java.util.List;
import com.mjkim.springmvc.step10.model.UserProfile;

public interface UserProfileService {
	UserProfile findById(int id);
	UserProfile findByType(String type);
	List<UserProfile> findAll();
}