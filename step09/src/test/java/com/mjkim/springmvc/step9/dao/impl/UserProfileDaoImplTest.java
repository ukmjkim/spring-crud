package com.mjkim.springmvc.step9.dao.impl;

import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.mjkim.springmvc.step9.dao.UserProfileDao;

import junit.framework.Assert;

public class UserProfileDaoImplTest extends EntityDaoImplTest {
	@Autowired
	UserProfileDao userProfileDao;
	
	@Override
	protected IDataSet getDataSet() throws Exception {
		IDataSet[] dataSet = {
				new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("User.xml")),
				new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("UserProfile.xml")),
				new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("User_UserProfile.xml")),
				new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("persistent_logins.xml"))
		};
		return new CompositeDataSet(dataSet);
	}

	@Test
	public void findByType() {
		Assert.assertNotNull(userProfileDao.findByType("USER"));
		Assert.assertNull(userProfileDao.findByType("NoData"));
	}
	
	@Test
	public void findById() {
		Assert.assertNotNull(userProfileDao.findById(1));
		Assert.assertNull(userProfileDao.findById(100));
	}
	
	@Test
	public void findAll() {
		Assert.assertTrue(userProfileDao.findAll().size() > 0);
	}
}
