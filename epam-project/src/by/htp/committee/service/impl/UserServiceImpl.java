package by.htp.committee.service.impl;

import java.util.List;

import by.htp.committee.dao.DAOException;
import by.htp.committee.dao.DAOProvider;
import by.htp.committee.dao.UserDAO;
import by.htp.committee.entity.User;
import by.htp.committee.service.ServiceException;
import by.htp.committee.service.ServiceParametr;
import by.htp.committee.service.UserService;

public class UserServiceImpl implements UserService {

	public User authorization(String login, String password) throws ServiceException {
		DAOProvider daoProvider = DAOProvider.getInstance();
		UserDAO userDAO = null;
		User user = null;

		try {
			userDAO = daoProvider.getUserDAO();

			user = userDAO.authorization(login, password);

			if (user == null) {
				throw new ServiceException(ServiceParametr.E_AUT);
			}

		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}

		return user;
	}

	@Override
	public Integer checkProgrammingTable() throws ServiceException {
		DAOProvider daoProvider = DAOProvider.getInstance();
		UserDAO userDAO = null;
		Integer check = null;

		try {
			userDAO = daoProvider.getUserDAO();

			check = userDAO.checkProgrammingTable();

			if (check == null) {
				throw new ServiceException(ServiceParametr.E_CHECK_PROGRAMMING_ECONOMICS);
			}

		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}

		return check;
	}

	@Override
	public Integer checkEconomicsTable() throws ServiceException {
		DAOProvider daoProvider = DAOProvider.getInstance();
		UserDAO userDAO = null;
		Integer check = null;

		try {
			userDAO = daoProvider.getUserDAO();

			check = userDAO.checkEconomicsTable();

			if (check == null) {
				throw new ServiceException(ServiceParametr.E_CHECK_PROGRAMMING_ECONOMICS);
			}

		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}

		return check;
	}

	@Override
	public void registration(User user) throws ServiceException {
		DAOProvider daoProvider = DAOProvider.getInstance();
		UserDAO userDAO = daoProvider.getUserDAO();

		try {
			List<String> logins = userDAO.usersLogin();

			for (String l : logins) {
				if (l.equals(user.getLogin())) {
					throw new ServiceException(ServiceParametr.E_REG);
				}
			}

			validator(user);

			userDAO.registration(user);

		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public List<String> usersLogin() throws ServiceException {
		DAOProvider daoProvider = DAOProvider.getInstance();
		UserDAO userDAO = null;

		List<String> logins = null;

		try {
			userDAO = daoProvider.getUserDAO();

			logins = userDAO.usersLogin();

			if (logins == null) {
				throw new ServiceException(ServiceParametr.E_USER_LOGIN);
			}
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}

		return logins;
	}

	private void validator(User user) throws ServiceException {

		if (!user.getLogin().matches(ServiceParametr.REG_EXP_LP)) {
			throw new ServiceException(ServiceParametr.E_LOGIN);
		} else if (!user.getPassword().matches(ServiceParametr.REG_EXP_LP)) {
			throw new ServiceException(ServiceParametr.E_PASSWORD);
		} else if (!user.getfName().matches(ServiceParametr.REG_EXP_FN_LN)) {
			throw new ServiceException(ServiceParametr.E_FN);
		} else if (!user.getlName().matches(ServiceParametr.REG_EXP_FN_LN)) {
			throw new ServiceException(ServiceParametr.E_LN);
		} else if (!user.getPassport().matches(ServiceParametr.REG_EXP_PASSPORT)) {
			throw new ServiceException(ServiceParametr.E_PASSPORT);
		}
	}

	@Override
	public Integer resGPA(Integer a, Integer b, Integer c, Integer d) throws ServiceException {

		Integer gpa;

		if (!a.toString().matches(ServiceParametr.REG_EXP_GPA)) {
			throw new ServiceException(ServiceParametr.E_GPA);
		} else if (!b.toString().matches(ServiceParametr.REG_EXP_GPA)) {
			throw new ServiceException(ServiceParametr.E_GPA);
		} else if (!c.toString().matches(ServiceParametr.REG_EXP_GPA)) {
			throw new ServiceException(ServiceParametr.E_GPA);
		} else {
			gpa = a + b + c + d;
		}

		return gpa;
	}

	@Override
	public Integer getNumberOfRows() throws ServiceException {
		DAOProvider daoProvider = DAOProvider.getInstance();
		UserDAO userDAO = null;

		Integer numOfRows;

		try {
			userDAO = daoProvider.getUserDAO();

			numOfRows = userDAO.getNumberOfRows();

			if (numOfRows == 0) {
				throw new ServiceException(ServiceParametr.E_EMPTY_TABLE);
			}
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		return numOfRows;
	}

	@Override
	public List<User> getUsers(int currentPage, int recordsPerPage) throws ServiceException {
		DAOProvider daoProvider = DAOProvider.getInstance();
		UserDAO userDAO = null;

		List<User> users = null;

		try {
			userDAO = daoProvider.getUserDAO();

			users = userDAO.getUsers(currentPage, recordsPerPage);

			if (users == null) {
				throw new ServiceException(ServiceParametr.E_EMPTY_TABLE);
			}

		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		return users;
	}

	@Override
	public List<User> getAllUsers() throws ServiceException {
		DAOProvider daoProvider = DAOProvider.getInstance();
		UserDAO userDAO = null;

		List<User> users = null;

		try {
			userDAO = daoProvider.getUserDAO();

			users = userDAO.getAllUsers();

			if (users == null) {
				throw new ServiceException(ServiceParametr.E_DB_ERROR);
			}

		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}

		return users;
	}

	@Override
	public void addEconomicsUser(User user) throws ServiceException {
		DAOProvider daoProvider = null;
		UserDAO userDAO = null;

		if (user == null) {
			throw new ServiceException(ServiceParametr.E_ADD_USER_EC);
		} else {
			try {
				daoProvider = DAOProvider.getInstance();
				userDAO = daoProvider.getUserDAO();

				userDAO.addEconomicsUser(user);

			} catch (DAOException e) {
				throw new ServiceException(e.getMessage());
			}
		}

	}

	@Override
	public void addProgrammingUser(User user) throws ServiceException {
		DAOProvider daoProvider = null;
		UserDAO userDAO = null;

		if (user == null) {
			throw new ServiceException(ServiceParametr.E_ADD_USER_PR);
		} else {
			try {
				daoProvider = DAOProvider.getInstance();
				userDAO = daoProvider.getUserDAO();

				userDAO.addProgrammingUser(user);

			} catch (DAOException e) {
				throw new ServiceException(e.getMessage());
			}
		}

	}

	@Override
	public List<User> getFromEconomicTable() throws ServiceException {
		DAOProvider daoProvider = DAOProvider.getInstance();
		UserDAO userDAO = null;

		List<User> users = null;

		try {
			userDAO = daoProvider.getUserDAO();

			users = userDAO.getFromEconomicTable();

			if (users == null) {
				throw new ServiceException(ServiceParametr.E_EMPTY_TABLE);
			}

		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}

		return users;
	}

}
