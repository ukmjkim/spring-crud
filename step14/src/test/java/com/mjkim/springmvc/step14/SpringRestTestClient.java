package com.mjkim.springmvc.step14;

import java.net.URI;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import com.mjkim.springmvc.step14.model.AuthTokenInfo;
import com.mjkim.springmvc.step14.model.User;

public class SpringRestTestClient {
	public static final String REST_SERVICE_URI = "http://localhost:8080/step14";
    
    public static final String AUTH_SERVER_URI = "http://localhost:8080/step14/oauth/token";
    
    public static final String QPM_PASSWORD_GRANT = "?grant_type=password&username=bill&password=abc123";
    
    public static final String QPM_ACCESS_TOKEN = "?access_token=";

	private static int lastCreatedId = 0;

	private static HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		return headers;
	}

	private static HttpHeaders getHeadersWithClientCredentials() {
		String plainClientCredentials = "my-trusted-client:secret";
		String base64ClientCredentials = new String(Base64.encodeBase64(plainClientCredentials.getBytes()));
		
		HttpHeaders headers = getHeaders();
		headers.add("Authorization",  "Basic " + base64ClientCredentials);
		return headers;
	}

	@SuppressWarnings({ "unchecked", "null" })
	private static AuthTokenInfo sendTokenRequest() {
		RestTemplate restTemplate = new RestTemplate();
		
		HttpEntity<String> request = new HttpEntity<String>(getHeadersWithClientCredentials());
		ResponseEntity<Object> response = restTemplate.exchange(AUTH_SERVER_URI+QPM_PASSWORD_GRANT, HttpMethod.POST, request, Object.class);
		LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) response.getBody();
		AuthTokenInfo tokenInfo = null;
		System.out.println(map);
		if (map!=null) {
			tokenInfo = new AuthTokenInfo();
			tokenInfo.setAccess_token((String) map.get("access_token"));
			tokenInfo.setToken_type((String) map.get("token_type"));
			tokenInfo.setRefresh_token((String) map.get("refresh_token"));
			tokenInfo.setExpires_in((int) map.get("expires_in"));
			tokenInfo.setScope((String) map.get("scope"));
			System.out.println(tokenInfo);
		} else {
			System.out.println("No user exist ------------------------");
		}
		
		return tokenInfo;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void listAllUsers(AuthTokenInfo tokenInfo) {
		Assert.notNull(tokenInfo, "Authenticate first please......");

		System.out.println("Testing listAllUsers API-----------");
		HttpEntity<String> request = new HttpEntity<String>(getHeaders());
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List> response = restTemplate.exchange(REST_SERVICE_URI+"/user/"+QPM_ACCESS_TOKEN+tokenInfo.getAccess_token(),
                HttpMethod.GET, request, List.class);

		List<LinkedHashMap<String, Object>> usersMap = (List<LinkedHashMap<String, Object>>) response.getBody();
		
		if (usersMap != null) {
			for (LinkedHashMap<String, Object> map : usersMap) {
				System.out.println("User : id="+map.get("id")+", Name="+map.get("firstName")+", Age="+map.get("lastName"));
			}
		} else {
			System.out.println("No user exist ----------------");
		}
	}
	
	private static void getUser(AuthTokenInfo tokenInfo) {
		Assert.notNull(tokenInfo, "Authenticate first please......");

		System.out.println("Testing getUser API-----------");
		
		HttpEntity<String> request = new HttpEntity<String>(getHeaders());
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<User> response = restTemplate.exchange(REST_SERVICE_URI+"/user/1"+QPM_ACCESS_TOKEN+tokenInfo.getAccess_token(),
                HttpMethod.GET, request, User.class);
		User user = response.getBody();
		System.out.println(user);
	}
	
	private static void createUser(AuthTokenInfo tokenInfo) {
		Assert.notNull(tokenInfo, "Authenticate first please......");

		System.out.println("Testing create user API-----------");

		User user = new User();
		user.setSsoId("sarah");
		user.setPassword("password");
		user.setFirstName("Sarah");
		user.setLastName("Lee");
		user.setEmail("sarah@gmail.com");
		
		System.out.println(user);

		HttpEntity<Object> request = new HttpEntity<Object>(user, getHeaders());
		RestTemplate restTemplate = new RestTemplate();
		URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/user/"+QPM_ACCESS_TOKEN+tokenInfo.getAccess_token(), request, User.class);
		String urlStr = uri.toASCIIString();
		System.out.println("Location: " + urlStr);
		String[] paths = urlStr.split("/");
		lastCreatedId = Integer.parseInt(paths[paths.length -1]);
		System.out.println("lastCreatedId: " + lastCreatedId);
	}
	
	private static void updateUser(AuthTokenInfo tokenInfo) {
		Assert.notNull(tokenInfo, "Authenticate first please......");

		System.out.println("Testing update user API-----------");

		User user = new User();
		user.setId(1);
		user.setSsoId("mjkim");
		user.setPassword("0000");
		user.setFirstName("MJ");
		user.setLastName("KIM");
		user.setEmail("mjkim@gmail.com");
		HttpEntity<Object> request = new HttpEntity<Object>(user, getHeaders());
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(REST_SERVICE_URI+"/user/1"+QPM_ACCESS_TOKEN+tokenInfo.getAccess_token(), request);
		System.out.println(user);
	}
	
	private static void deleteUser(AuthTokenInfo tokenInfo) {
		System.out.println("Testing delete user API-----------");
		HttpEntity<String> request = new HttpEntity<String>(getHeaders());
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(REST_SERVICE_URI+"/user/"+lastCreatedId+QPM_ACCESS_TOKEN+tokenInfo.getAccess_token(),  HttpMethod.DELETE, request, User.class);
	}
	
	public static void main(String[] args) {
		AuthTokenInfo tokenInfo = sendTokenRequest();
        listAllUsers(tokenInfo);
        getUser(tokenInfo);
        createUser(tokenInfo);
        listAllUsers(tokenInfo);
        updateUser(tokenInfo);
        listAllUsers(tokenInfo);
        deleteUser(tokenInfo);
        listAllUsers(tokenInfo);
	}
}
