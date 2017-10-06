package com.mjkim.springmvc.step11.dao;

import java.util.List;
import com.mjkim.springmvc.step11.model.UserDocument;

public interface UserDocumentDao {
	List<UserDocument> findAll();
	UserDocument findById(int id);
	void save(UserDocument userDocument);
	List<UserDocument> findAllByUserId(int userId);
	void deleteById(int id);
}
