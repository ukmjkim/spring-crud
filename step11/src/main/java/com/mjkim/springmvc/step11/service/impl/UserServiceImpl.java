package com.mjkim.springmvc.step11.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mjkim.springmvc.step11.dao.UserDao;
import com.mjkim.springmvc.step11.model.User;
import com.mjkim.springmvc.step11.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao dao;

	public List<User> findAllUsers() {
		return dao.findAllUsers();
	}
	public User findById(int id) {
		return dao.findById(id);
	}
	public User findBySSO(String ssoId) {
		return dao.findBySSO(ssoId);
	}
	public void saveUser(User user) {
		dao.save(user);
	}
	public void updateUser(User user) {
		User entity = dao.findById(user.getId());
		if (entity != null) {
			entity.setSsoId(user.getSsoId());
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setEmail(user.getEmail());
		}
	}
	public void deleteUserBySSO(String ssoId) {
		dao.deleteBySSO(ssoId);
	}
	public boolean isUserSSOUnique(Integer id, String ssoId) {
		User user = findBySSO(ssoId);
		return (user == null || ((id != null) && user.getId() == id));
	}
}
