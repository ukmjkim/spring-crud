package com.mjkim.springmvc.step10.dao;

import java.util.List;

import com.mjkim.springmvc.step10.model.UserProfile;
 
 
public interface UserProfileDao {
 
    List<UserProfile> findAll();
     
    UserProfile findByType(String type);
     
    UserProfile findById(int id);
}