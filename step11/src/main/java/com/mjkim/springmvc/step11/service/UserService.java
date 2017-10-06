package com.mjkim.springmvc.step11.service;

import java.util.List;
import com.mjkim.springmvc.step11.model.User;

public interface UserService {
	List<User> findAllUsers();
	User findById(int id);
	User findBySSO(String ssoId);
	void saveUser(User user);
	void updateUser(User user);
	void deleteUserBySSO(String ssoId);
	boolean isUserSSOUnique(Integer id, String ssoId);
}
