package com.mjkim.springmvc.step9.service;

import java.util.List;
import com.mjkim.springmvc.step9.model.UserProfile;

public interface UserProfileService {
	UserProfile findById(int id);
	UserProfile findByType(String type);
	List<UserProfile> findAll();
}
