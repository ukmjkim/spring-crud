package com.mjkim.springmvc.step14;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;
 
import org.springframework.web.client.RestTemplate;
 
import com.mjkim.springmvc.step14.model.User;

public class SpringRestTestClient {
	public static final String REST_SERVICE_URI = "http://localhost:8080/step14";
	
	private static int lastCreatedId = 0;

	@SuppressWarnings("unchecked")
	private static void listAllUsers() {
		System.out.println("Testing listAllUsers API-----------");
		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap<String, Object>> usersMap = restTemplate.getForObject(REST_SERVICE_URI+"/user/", List.class);
		
		if (usersMap != null) {
			for (LinkedHashMap<String, Object> map : usersMap) {
				System.out.println("User : id="+map.get("id")+", Name="+map.get("firstName")+", Age="+map.get("lastName"));
			}
		} else {
			System.out.println("No user exist ----------------");
		}
	}
	
	private static void getUser() {
		System.out.println("Testing getUser API-----------");
		RestTemplate restTemplate = new RestTemplate();
		User user = restTemplate.getForObject(REST_SERVICE_URI+"/user/1", User.class);
		System.out.println(user);
	}
	
	private static void createUser() {
		System.out.println("Testing create user API-----------");
		RestTemplate restTemplate = new RestTemplate();
		User user = new User();
		user.setSsoId("sarah");
		user.setPassword("password");
		user.setFirstName("Sarah");
		user.setLastName("Lee");
		user.setEmail("sarah@gmail.com");
		
		System.out.println(user);

		URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/user/", user, User.class);
		String urlStr = uri.toASCIIString();
		System.out.println("Location: " + urlStr);
		String[] paths = urlStr.split("/");
		lastCreatedId = Integer.parseInt(paths[paths.length -1]);
		System.out.println("lastCreatedId: " + lastCreatedId);
	}
	
	private static void updateUser() {
		System.out.println("Testing update user API-----------");
		RestTemplate restTemplate = new RestTemplate();
		User user = new User();
		user.setId(1);
		user.setSsoId("mjkim");
		user.setPassword("0000");
		user.setFirstName("MJ");
		user.setLastName("KIM");
		user.setEmail("mjkim@gmail.com");
		restTemplate.put(REST_SERVICE_URI+"/user/1", user);
		System.out.println(user);
	}
	
	private static void deleteUser() {
		System.out.println("Testing delete user API-----------");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(REST_SERVICE_URI+"/user/"+lastCreatedId);
	}
	
	public static void main(String[] args) {
        listAllUsers();
        getUser();
        createUser();
        listAllUsers();
        updateUser();
        listAllUsers();
        deleteUser();
        listAllUsers();
	}
}
