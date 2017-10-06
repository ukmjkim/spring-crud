package com.mjkim.springmvc.step10.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mjkim.springmvc.step10.model.User;
import com.mjkim.springmvc.step10.service.UserService;
import com.mjkim.springmvc.step10.dao.UserDao;

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
			entity.setSsoId(user.getSsoId());
			entity.setPassword(user.getPassword());
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
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