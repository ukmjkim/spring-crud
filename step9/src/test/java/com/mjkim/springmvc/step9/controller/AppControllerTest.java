package com.mjkim.springmvc.step9.controller;

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
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.mjkim.springmvc.step9.model.User;
import com.mjkim.springmvc.step9.model.UserProfile;
import com.mjkim.springmvc.step9.service.UserService;

public class AppControllerTest {
	@Mock
	UserService userService;
	
	@Mock
	MessageSource message;

	@Mock
	BindingResult result;

	@Mock
	UserDetails userDetails;

	@Mock
	Authentication authentication;

	@Mock
	SecurityContext securityContext;

	@InjectMocks
	AppController appController;

	@Spy
	List<User> users = new ArrayList<User>();

	@Spy
	ModelMap model;

	@BeforeClass
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		users = getUserList();
	}
	
	@Test
	public void listUsers() {
		when(userService.findAllUsers()).thenReturn(users);
		Assert.assertEquals(appController.listUsers(model), "userslist");
		Assert.assertEquals(model.get("users"), users);
		verify(userService, atLeastOnce()).findAllUsers();
	}

	@Test
	public void newUser() {
		Assert.assertEquals(appController.newUser(model), "registration");
		Assert.assertNotNull(model.get("user"));
		Assert.assertFalse((Boolean) model.get("edit"));
		Assert.assertEquals(((User) model.get("user")).getId(), null); 
	}
	
	@Test
	public void saveUserWithValidationError() {
		when(result.hasErrors()).thenReturn(true);
		doNothing().when(userService).saveUser(any(User.class));
		Assert.assertEquals(appController.saveUser(users.get(0), result, model), "registration");
	}

	@Test
	public void saveUserWithValidationNonUniqueSSO() {
		when(result.hasErrors()).thenReturn(false);
		when(userService.isUserSSOUnique(anyInt(), anyString())).thenReturn(false);
		Assert.assertEquals(appController.saveUser(users.get(0),  result,  model), "registration");
	}

	@Test
	public void saveUserWithSuccess() {
		when(result.hasErrors()).thenReturn(false);
		when(userService.isUserSSOUnique(anyInt(), anyString())).thenReturn(true);
		doNothing().when(userService).saveUser(any(User.class));
		Assert.assertEquals(appController.saveUser(users.get(0), result, model), "registrationsuccess");
		Assert.assertEquals(model.get("success"), "User " + users.get(0).getFirstName() + " " + users.get(0).getLastName() + " registered successfully.");
	}

	@Test
	public void editUser() {
		User user = users.get(0);
		when(userService.findBySSO(anyString())).thenReturn(user);
		when(securityContext.getAuthentication()).thenReturn(authentication);
		when(authentication.getPrincipal()).thenReturn(userDetails);
		when(userDetails.getUsername()).thenReturn(user.getSsoId());
		SecurityContextHolder.setContext(securityContext);
		Assert.assertEquals(appController.editUser(anyString(), model), "registration");
		Assert.assertNotNull(model.get("user"));
		Assert.assertTrue((Boolean) model.get("edit"));
		Assert.assertEquals(((User) model.get("user")).getId(), user.getId()); 
	}
	
	@Test
	public void updateUserWithValidationError() {
		when(result.hasErrors()).thenReturn(true);
		doNothing().when(userService).updateUser(any(User.class));
		Assert.assertEquals(appController.updateUser(users.get(0), result, model), "registration");
	}
	
	@Test
	public void updateUserWithSuccess() {
		when(result.hasErrors()).thenReturn(false);
		doNothing().when(userService).updateUser(any(User.class));
		Assert.assertEquals(appController.updateUser(users.get(0), result, model), "registrationsuccess");
		Assert.assertEquals(model.get("success"), "User " + users.get(0).getFirstName() + " " + users.get(0).getLastName() + " registered successfully.");
	}
	
	@Test
	public void deleteUser() {
		doNothing().when(userService).deleteUserBySSO(anyString());
		Assert.assertEquals(appController.deleteUser("test"), "redirect:/list");
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
