package com.mjkim.springmvc.step9.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.mjkim.springmvc.step9.service.UserProfileService;
import com.mjkim.springmvc.step9.model.UserProfile;

@Component
public class RoleToUserProfileConverter implements Converter<Object, UserProfile>  {
	@Autowired
	UserProfileService userProfileService;
	
	public UserProfile convert(Object element) {
		Integer id = Integer.parseInt((String) element);
		UserProfile profile = userProfileService.findById(id);
		return profile;
	}
}
