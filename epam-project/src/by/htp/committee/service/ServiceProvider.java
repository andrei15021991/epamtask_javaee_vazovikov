package by.htp.committee.service;

import by.htp.committee.service.impl.UserServiceImpl;

public final class ServiceProvider {

	private static final ServiceProvider instance = new ServiceProvider();
	private UserService userService = new UserServiceImpl();

	public ServiceProvider() {

	}

	public static ServiceProvider getInstance() {
		return instance;
	}

	public UserService getUserService() {
		return userService;
	}

}
