package com.mjkim.springmvc.step11.dao;

import java.util.List;
import com.mjkim.springmvc.step11.model.User;

public interface UserDao {
	User findById(int id);
	User findBySSO(String ssoId);
	void save(User user);
	void deleteBySSO(String ssoId);
	List<User> findAllUsers();
}
