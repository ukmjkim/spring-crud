package com.mjkim.springmvc.step11.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mjkim.springmvc.step11.dao.UserDao;
import com.mjkim.springmvc.step11.model.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {
	public User findById(int id) {
		User user = getByKey(id);
		return user;
	}

	public User findBySSO(String ssoId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("ssoId", ssoId));
		User user = (User) criteria.uniqueResult();
		return user;
	}

	public void save(User user) {
		persist(user);
	}

	public void deleteBySSO(String ssoId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("ssoId", ssoId));
		User user = (User) criteria.uniqueResult();
		delete(user);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		Criteria criteria = createEntityCriteria();
		criteria.addOrder(Order.asc("firstName"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<User> users = (List<User>) criteria.list();
		return users;
	}
}
