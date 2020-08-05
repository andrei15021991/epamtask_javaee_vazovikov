package by.htp.committee.dao;

import java.util.List;

import by.htp.committee.entity.User;

public interface UserDAO {

	User authorization(String login, String password) throws DAOException;

	Integer checkProgrammingTable() throws DAOException;

	Integer checkEconomicsTable() throws DAOException;

	void registration(User user) throws DAOException;

	List<String> usersLogin() throws DAOException;

	Integer getNumberOfRows() throws DAOException;

	List<User> getUsers(int currentPage, int recordsPerPage) throws DAOException;

	List<User> getAllUsers() throws DAOException;

	void addEconomicsUser(User user) throws DAOException;

	void addProgrammingUser(User user) throws DAOException;

	List<User> getFromEconomicTable() throws DAOException;

	List<User> getFromProgrammingTable() throws DAOException;

}
