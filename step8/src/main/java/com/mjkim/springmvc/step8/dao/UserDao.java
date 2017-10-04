package com.mjkim.springmvc.step8.dao;

import java.util.List;
import com.mjkim.springmvc.step8.model.User;

public interface UserDao {
	User findById(int id);
	User findBySSO(String sso);
	void save(User user);
	void deleteBySSO(String sso);
	List<User> findAllUsers();
}
