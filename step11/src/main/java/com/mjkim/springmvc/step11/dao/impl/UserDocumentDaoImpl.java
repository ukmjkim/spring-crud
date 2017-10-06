package com.mjkim.springmvc.step11.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mjkim.springmvc.step11.dao.UserDocumentDao;
import com.mjkim.springmvc.step11.model.UserDocument;

@Repository("userDocumentDao")
public class UserDocumentDaoImpl extends AbstractDao<Integer, UserDocument> implements UserDocumentDao {
	@SuppressWarnings("unchecked")
	public List<UserDocument> findAll() {
		Criteria criteria = createEntityCriteria();
		return (List<UserDocument>) criteria.list();
	}

	public UserDocument findById(int id) {
		return getByKey(id);
	}
	public void save(UserDocument userDocument) {
		persist(userDocument);
	}

	@SuppressWarnings("unchecked")
	public List<UserDocument> findAllByUserId(int userId) {
		Criteria criteria = createEntityCriteria();
		Criteria userCriteria = criteria.createCriteria("user");
		userCriteria.add(Restrictions.eq("id",  userId));
		return (List<UserDocument>) criteria.list();
	}

	public void deleteById(int id) {
		delete(getByKey(id));
	}

}
