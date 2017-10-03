package com.mjkim.springmvc.step7.service;

import java.util.List;
import com.mjkim.springmvc.step7.model.User;

public interface UserService {
	User findById(int id);
	User findBySSO(String sso);
	void saveUser(User user);
	void updateUser(User user);
	void deleteUserBySSO(String sso);
	List<User> findAllUsers();
	boolean isUserSSOUnique(Integer id, String sso);
}
