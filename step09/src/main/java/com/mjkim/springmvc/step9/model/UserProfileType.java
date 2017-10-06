package com.mjkim.springmvc.step9.model;

public enum UserProfileType {
	USER("USER"),
	DBA("DBA"),
	ADMIN("ADMIN");
	
	String userProfileType;
	
	private UserProfileType(String userProfileType) {
		this.userProfileType = userProfileType;
	}
	
	public String getUserProfileType() {
		return userProfileType;
	}
}
