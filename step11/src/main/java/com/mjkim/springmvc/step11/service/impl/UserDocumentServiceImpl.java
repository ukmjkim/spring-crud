package com.mjkim.springmvc.step11.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mjkim.springmvc.step11.dao.UserDocumentDao;
import com.mjkim.springmvc.step11.model.UserDocument;
import com.mjkim.springmvc.step11.service.UserDocumentService;

@Service("userDocumentService")
@Transactional
public class UserDocumentServiceImpl implements UserDocumentService {
	@Autowired
	UserDocumentDao dao;

	public UserDocument findById(int id) {
		return dao.findById(id);
	}

	public List<UserDocument> findAll() {
		return dao.findAll();
	}

	public List<UserDocument> findAllByUserId(int userId) {
		return dao.findAllByUserId(userId);
	}

	public void saveDocument(UserDocument userDocument) {
		dao.save(userDocument);
	}
	public void deleteById(int id) {
		dao.deleteById(id);
	}

}
