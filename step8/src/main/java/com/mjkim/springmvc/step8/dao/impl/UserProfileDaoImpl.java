package com.mjkim.springmvc.step8.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mjkim.springmvc.step8.model.UserProfile;
import com.mjkim.springmvc.step8.dao.AbstractDao;
import com.mjkim.springmvc.step8.dao.UserProfileDao;

@Repository("userProfileDao")
public class UserProfileDaoImpl extends AbstractDao<Integer, UserProfile> implements UserProfileDao {
	
	@SuppressWarnings("unchecked")
	public List<UserProfile> findAll() {
		Criteria criteria = createEntityCriteria();
		criteria.addOrder(Order.asc("type"));
		return (List<UserProfile>) criteria.list();
	}

	public UserProfile findByType(String type) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("type", type));
		return (UserProfile) criteria.uniqueResult();
	}

	public UserProfile findById(int id) {
		return getByKey(id);
	}
}
