package com.mjkim.springmvc.step10.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.mjkim.springmvc.step10.service.UserProfileService;
import com.mjkim.springmvc.step10.model.UserProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class RoleToUserProfileConverter implements Converter<Object, UserProfile>  {
	static final Logger logger = LoggerFactory.getLogger(RoleToUserProfileConverter.class);

	@Autowired
	UserProfileService userProfileService;
	
	public UserProfile convert(Object element) {
		Integer id = Integer.parseInt((String) element);
		UserProfile profile = userProfileService.findById(id);
		logger.info("Profile : {}",profile);
		return profile;
	}
}