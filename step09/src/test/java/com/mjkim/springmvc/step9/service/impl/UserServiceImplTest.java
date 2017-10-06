package com.mjkim.springmvc.step9.service.impl;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.mjkim.springmvc.step9.dao.UserDao;
import com.mjkim.springmvc.step9.model.User;
import com.mjkim.springmvc.step9.model.UserProfile;

public class UserServiceImplTest {
	@Mock
	UserDao dao;

	@InjectMocks
	UserServiceImpl userService;
	
	@Spy
	List<User> users = new ArrayList<User>();
	
	@BeforeClass
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		users = getUserList();
	}
	
	@Test
	public void findById() {
		User user = users.get(0);
		when(dao.findById(anyInt())).thenReturn(user);
		Assert.assertEquals(userService.findById(user.getId()), user);
	}
	
	@Test
	public void findBySSO() {
		User user = users.get(0);
		when(dao.findBySSO(anyString())).thenReturn(user);
		Assert.assertEquals(userService.findBySSO(user.getSsoId()), user);
	}

	@Test
	public void saveUser() {
		doNothing().when(dao).save(any(User.class));
		userService.saveUser((User) any(User.class));
		verify(dao, atLeastOnce()).save(any(User.class));
	}

	@Test
	public void updateUser() {
		User user = users.get(0);
		when(dao.findById(anyInt())).thenReturn(user);
		userService.updateUser(user);
		verify(dao, atLeastOnce()).findById(anyInt());
	}

	@Test
	public void deleteUserBySSO() {
		doNothing().when(dao).deleteBySSO(anyString());
		userService.deleteUserBySSO(anyString());
		verify(dao, atLeastOnce()).deleteBySSO(anyString());
	}

	@Test
	public void isUserSSOUnique() {
		User user = users.get(0);
		when(dao.findBySSO(anyString())).thenReturn(user);
		Assert.assertEquals(userService.isUserSSOUnique(user.getId(), user.getSsoId()), true);
	}

	@Test
	public void findAllUsers() {
		when(dao.findAllUsers()).thenReturn(users);
		Assert.assertEquals(userService.findAllUsers(), users);
	}

	public List<User> getUserList() {
		Set<UserProfile> userProfiles = new HashSet<UserProfile>();
		UserProfile userProfile1 = new UserProfile();
		userProfile1.setId(1);
		userProfile1.setType("USER");
		userProfiles.add(userProfile1);

		User user1 = new User();
		user1.setId(1);
		user1.setSsoId("sso1");
		user1.setPassword("password");
		user1.setFirstName("firstName1");
		user1.setLastName("lastName1");
		user1.setEmail("email1");
		user1.setUserProfiles(userProfiles);

		User user2 = new User();
		user2.setId(2);
		user2.setSsoId("sso2");
		user2.setPassword("password");
		user2.setFirstName("firstName2");
		user2.setLastName("lastName2");
		user2.setEmail("email2");
		user2.setUserProfiles(userProfiles);
		
		users.add(user1);
		users.add(user2);
		return users;
	}
}
