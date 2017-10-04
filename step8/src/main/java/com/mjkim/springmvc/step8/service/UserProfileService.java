package com.mjkim.springmvc.step8.service;

import java.util.List;
import com.mjkim.springmvc.step8.model.UserProfile;

public interface UserProfileService {
	UserProfile findById(int id);
	UserProfile findByType(String type);
	List<UserProfile> findAll();
}
