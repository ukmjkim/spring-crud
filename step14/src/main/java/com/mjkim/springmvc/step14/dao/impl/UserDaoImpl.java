package com.mjkim.springmvc.step14.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mjkim.springmvc.step14.controller.AppRestController;
import com.mjkim.springmvc.step14.dao.AbstractDao;
import com.mjkim.springmvc.step14.dao.UserDao;
import com.mjkim.springmvc.step14.model.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {
	static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	public User findById(int id) {
		User user = getByKey(id);
		return user;
	}
	public User findBySSO(String sso) {
		System.out.println("SSO : " + sso);
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("ssoId", sso));
		User user = (User) criteria.uniqueResult();
		return user;
	}
	public void save(User user) {
		try {
		persist(user);
		} catch (Exception ex) {
			logger.debug("Error Message: {}", ex.getMessage());
			System.out.println("ERROR MESSAGE==============: " + ex.getMessage());
		}
	}
	public void deleteBySSO(String sso) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("ssoId", sso));
		User user = (User) criteria.uniqueResult();
		delete(user);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		Criteria criteria = createEntityCriteria().addOrder(Order.desc("firstName"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<User> users = (List<User>) criteria.list();
		return users;
	}

}