package by.htp.committee.service;

import java.util.List;

import by.htp.committee.entity.User;

public interface UserService {

	User authorization(String login, String password) throws ServiceException;

	Integer checkProgrammingTable() throws ServiceException;

	Integer checkEconomicsTable() throws ServiceException;

	void registration(User user) throws ServiceException;

	List<String> usersLogin() throws ServiceException;

	Integer resGPA(Integer a, Integer b, Integer c, Integer d) throws ServiceException;

	Integer getNumberOfRows() throws ServiceException;

	List<User> getUsers(int currentPage, int recordsPerPage) throws ServiceException;

	List<User> getAllUsers() throws ServiceException;

	void addEconomicsUser(User user) throws ServiceException;

	void addProgrammingUser(User user) throws ServiceException;

	List<User> getFromEconomicTable() throws ServiceException;
}
