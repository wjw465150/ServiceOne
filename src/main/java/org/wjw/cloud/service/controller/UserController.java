package org.wjw.cloud.service.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.wjw.cloud.service.model.User;

@RestController
public class UserController {

	@RequestMapping(value = "/user", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
	public User list(@RequestBody User user) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		user.setId(request.getLocalPort());
		user.setUsername(user.getUsername().toUpperCase());
		return user;
	}
}