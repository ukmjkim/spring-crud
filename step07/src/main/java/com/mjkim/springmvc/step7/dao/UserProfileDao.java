package com.mjkim.springmvc.step7.dao;

import java.util.List;
import com.mjkim.springmvc.step7.model.*;

public interface UserProfileDao {
	List<UserProfile> findAll();
	UserProfile findByType(String type);
	UserProfile findById(int id);
}
