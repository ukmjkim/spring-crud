package com.mjkim.springmvc.step11.service;

import java.util.List;
import com.mjkim.springmvc.step11.model.UserDocument;

public interface UserDocumentService {
	UserDocument findById(int id);
	List<UserDocument> findAll();
	List<UserDocument> findAllByUserId(int userId);
	void saveDocument(UserDocument userDocument);
	void deleteById(int id);
}
