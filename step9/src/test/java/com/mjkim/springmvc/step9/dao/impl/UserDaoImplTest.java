package com.mjkim.springmvc.step9.dao.impl;

import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.mjkim.springmvc.step9.dao.UserDao;
import com.mjkim.springmvc.step9.model.User;

import junit.framework.Assert;

public class UserDaoImplTest extends EntityDaoImplTest {
	@Autowired
	UserDao userDao;

	@Override
	protected IDataSet getDataSet() throws Exception {
		IDataSet dataSets[] = {
			new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("User.xml")),
			new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("UserProfile.xml")),
			new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("User_UserProfile.xml")),
			new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("persistent_logins.xml"))
		};
		return new CompositeDataSet(dataSets);
	}
	
	@Test
	public void findById() {
		Assert.assertNotNull(userDao.findById(1));
		Assert.assertNull(userDao.findById(1000));
	}
	
	@Test
	public void findBySSO() {
		Assert.assertNotNull(userDao.findBySSO("willBeDeleted"));
		Assert.assertNull(userDao.findBySSO("dataNotFound"));
	}


	@Test
	public void save() {
		int currentCount = userDao.findAllUsers().size();
		User user = new User();
		user.setSsoId("new");
		user.setPassword("password");
		user.setFirstName("firstName");
		user.setLastName("lastName");
		user.setEmail("email");
		userDao.save(user);
		Assert.assertEquals(userDao.findAllUsers().size(), currentCount+1);
	}
	
	@Test
	public void deleteBySSO() {
		int currentCount = userDao.findAllUsers().size();
		userDao.deleteBySSO("willBeDeleted");
		Assert.assertEquals(userDao.findAllUsers().size(), currentCount-1);
	}
	
	@Test
	public void findAllUsers() {
		Assert.assertEquals(userDao.findAllUsers().size(), 10);
	}
}
