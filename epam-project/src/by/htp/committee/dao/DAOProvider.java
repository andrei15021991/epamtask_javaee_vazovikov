package by.htp.committee.dao;

import by.htp.committee.dao.impl.UserDAOImpl;

public final class DAOProvider {

	private static final DAOProvider instance = new DAOProvider();
	private UserDAO userDAO = new UserDAOImpl();

	private DAOProvider() {

	}

	public static DAOProvider getInstance() {
		return instance;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

}
