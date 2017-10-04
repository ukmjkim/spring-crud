package com.mjkim.springmvc.step8.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mjkim.springmvc.step8.dao.UserDao;
import com.mjkim.springmvc.step8.model.User;
import com.mjkim.springmvc.step8.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao dao;
	
	public User findById(int id) {
		return dao.findById(id);
	}
	public User findBySSO(String sso) {
		return dao.findBySSO(sso);
	}
	public void saveUser(User user) {
		dao.save(user);
	}
	public void updateUser(User user) {
		User entity = dao.findById(user.getId());
		if (entity != null) {
			entity.setssoId(user.getssoId());
			entity.setPassword(user.getPassword());
			entity.setfirstName(user.getfirstName());
			entity.setlastName(user.getlastName());
			entity.setEmail(user.getEmail());
			entity.setUserProfiles(user.getUserProfiles());
		}
	}
	public void deleteUserBySSO(String sso) {
		dao.deleteBySSO(sso);
	}
	public List<User> findAllUsers() {
		return dao.findAllUsers();
	}
	public boolean isUserSSOUnique(Integer id, String sso) {
		User user = findBySSO(sso);
		return (user == null || ((id != null) && (user.getId() == id)));
	}
}
