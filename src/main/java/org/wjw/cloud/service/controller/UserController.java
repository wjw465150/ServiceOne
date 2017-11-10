package org.wjw.cloud.service.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.wjw.cloud.service.model.User;

@RestController
public class UserController {
	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private DiscoveryClient client;
	
	@RequestMapping(value = "/user", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
	public User list(@RequestBody User user) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		user.setId(request.getLocalPort());
		user.setUsername(user.getUsername().toUpperCase());
		
		ServiceInstance instance = client.getInstances(client.getServices().get(0)).get(0);
		logger.info("/user, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + user);
		
		return user;
	}
}