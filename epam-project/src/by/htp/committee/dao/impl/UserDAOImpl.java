package by.htp.committee.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.htp.committee.dao.DAOException;
import by.htp.committee.dao.DAOParametr;
import by.htp.committee.dao.UserDAO;
import by.htp.committee.dao.cp.ConnectionPool;
import by.htp.committee.dao.cp.ConnectionPoolException;
import by.htp.committee.dao.cp.ConnectionPoolProvider;
import by.htp.committee.entity.User;

public class UserDAOImpl implements UserDAO {

	@Override
	public User authorization(String login, String password) throws DAOException {
		ConnectionPoolProvider connectionPoolProvider = ConnectionPoolProvider.getInstance();
		ConnectionPool connectionPool = null;

		User user = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connectionPool = connectionPoolProvider.getConnectionPool();
			connection = connectionPool.takeConnection();

			preparedStatement = connection.prepareStatement(DAOParametr.SQL_AUT);
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, password);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt(DAOParametr.ID);
				String fName = resultSet.getString(DAOParametr.FNAME);
				String lName = resultSet.getString(DAOParametr.LNAME);
				String passport = resultSet.getString(DAOParametr.PASSPORT);
				String gpa = resultSet.getString(DAOParametr.GPA);
				String faculty = resultSet.getString(DAOParametr.FACULTY);
				String role = resultSet.getString(DAOParametr.ROLE);

				user = new User();

				if (gpa.equals(DAOParametr.ADMIN)) {
					user.setRole(role);

				} else {

					user.setId(id);
					user.setfName(fName);
					user.setlName(lName);
					user.setPassport(passport);
					user.setGpa(Integer.parseInt(gpa));
					user.setFaculty(faculty);
					user.setRole(role);
				}

			}

		} catch (ConnectionPoolException e) {
			throw new DAOException(e.getMessage());
		} catch (

		SQLException e) {
			throw new DAOException(e.getMessage());

		} finally {
			connectionPool.closeConnection(connection, preparedStatement, resultSet);
		}

		return user;
	}

	@Override
	public Integer checkProgrammingTable() throws DAOException {
		ConnectionPoolProvider connectionPoolProvider = ConnectionPoolProvider.getInstance();
		ConnectionPool connectionPool = null;

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		Integer check = null;

		try {
			connectionPool = connectionPoolProvider.getConnectionPool();
			connection = connectionPool.takeConnection();

			statement = connection.createStatement();

			resultSet = statement.executeQuery(DAOParametr.CHECK_PROGRAMMING_TABLE);
			resultSet.next();

			check = resultSet.getInt(1);

		} catch (ConnectionPoolException e) {
			throw new DAOException(e.getMessage());
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			connectionPool.closeConnection(connection, statement, resultSet);
		}

		return check;
	}

	@Override
	public Integer checkEconomicsTable() throws DAOException {
		ConnectionPoolProvider connectionPoolProvider = ConnectionPoolProvider.getInstance();
		ConnectionPool connectionPool = null;

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		Integer check = null;

		try {
			connectionPool = connectionPoolProvider.getConnectionPool();
			connection = connectionPool.takeConnection();

			statement = connection.createStatement();

			resultSet = statement.executeQuery(DAOParametr.CHECK_ECONOMICS_TABLE);
			resultSet.next();

			check = resultSet.getInt(1);

		} catch (ConnectionPoolException e) {
			throw new DAOException(e.getMessage());
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			connectionPool.closeConnection(connection, statement, resultSet);
		}

		return check;
	}

	@Override
	public void registration(User user) throws DAOException {
		ConnectionPoolProvider connectionPoolProvider = ConnectionPoolProvider.getInstance();
		ConnectionPool connectionPool = null;

		Connection connection = null;
		PreparedStatement preparedStatement1 = null;
		PreparedStatement preparedStatement2 = null;

		try {
			connectionPool = connectionPoolProvider.getConnectionPool();

			connection = connectionPool.takeConnection();
			connection.setAutoCommit(false);

			preparedStatement1 = connection.prepareStatement(DAOParametr.SQL_REG_1);
			preparedStatement1.setString(1, user.getLogin());
			preparedStatement1.setString(2, user.getPassword());
			preparedStatement1.setString(3, user.getRole());

			preparedStatement1.executeUpdate();

			preparedStatement2 = connection.prepareStatement(DAOParametr.SQL_REG_2);
			preparedStatement2.setString(1, user.getfName());
			preparedStatement2.setString(2, user.getlName());
			preparedStatement2.setString(3, user.getPassport());
			preparedStatement2.setString(4, user.getGpa().toString());
			preparedStatement2.setString(5, user.getFaculty());
			preparedStatement2.setString(6, user.getLogin());
			preparedStatement2.setString(7, user.getPassword());

			preparedStatement2.executeUpdate();

			connection.commit();

		} catch (ConnectionPoolException e) {
			throw new DAOException(e.getMessage());
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			connectionPool.closeConnection(connection, preparedStatement1, preparedStatement2);
		}

	}

	@Override
	public List<String> usersLogin() throws DAOException {
		ConnectionPoolProvider connectionPoolProvider = ConnectionPoolProvider.getInstance();
		ConnectionPool connectionPool = null;

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		List<String> logins = null;

		try {
			connectionPool = connectionPoolProvider.getConnectionPool();

			connection = connectionPool.takeConnection();

			statement = connection.createStatement();

			resultSet = statement.executeQuery(DAOParametr.USERS_LOGIN);

			logins = new ArrayList<String>();

			while (resultSet.next()) {
				logins.add(resultSet.getString(1));
			}

		} catch (ConnectionPoolException e) {
			throw new DAOException(e.getMessage());
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			connectionPool.closeConnection(connection, statement, resultSet);
		}

		return logins;
	}

	@Override
	public Integer getNumberOfRows() throws DAOException {

		Integer numOfRows = 0;

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		ConnectionPoolProvider connectionPoolProvider = ConnectionPoolProvider.getInstance();
		ConnectionPool connectionPool = null;

		try {
			connectionPool = connectionPoolProvider.getConnectionPool();

			connection = connectionPool.takeConnection();

			statement = connection.createStatement();

			resultSet = statement.executeQuery(DAOParametr.GET_NUMBER_OF_ROWS);
			resultSet.next();

			numOfRows = resultSet.getInt(1);

		} catch (ConnectionPoolException e) {
			throw new DAOException(e.getMessage());
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			connectionPool.closeConnection(connection, statement, resultSet);
		}

		return numOfRows;
	}

	@Override
	public List<User> getUsers(int currentPage, int recordsPerPage) throws DAOException {
		ConnectionPoolProvider connectionPoolProvider = ConnectionPoolProvider.getInstance();
		ConnectionPool connectionPool = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		List<User> users = null;

		int start = (currentPage * recordsPerPage - recordsPerPage) + 1;

		try {
			connectionPool = connectionPoolProvider.getConnectionPool();

			connection = connectionPool.takeConnection();

			preparedStatement = connection.prepareStatement(DAOParametr.GET_USERS);
			preparedStatement.setInt(1, start);
			preparedStatement.setInt(2, recordsPerPage);

			resultSet = preparedStatement.executeQuery();

			users = new ArrayList<User>();

			while (resultSet.next()) {
				User user = new User();

				user.setId(resultSet.getInt(DAOParametr.ID));
				user.setfName(resultSet.getString(DAOParametr.FNAME));
				user.setlName(resultSet.getString(DAOParametr.LNAME));
				user.setPassport(resultSet.getString(DAOParametr.PASSPORT));
				user.setFaculty(resultSet.getString(DAOParametr.FACULTY));
				user.setGpa(Integer.parseInt(resultSet.getString(DAOParametr.GPA)));

				users.add(user);

			}
		} catch (ConnectionPoolException e) {
			throw new DAOException(e.getMessage());
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			connectionPool.closeConnection(connection, preparedStatement, resultSet);
		}

		return users;
	}

	@Override
	public List<User> getAllUsers() throws DAOException {
		ConnectionPoolProvider connectionPoolProvider = ConnectionPoolProvider.getInstance();
		ConnectionPool connectionPool = null;

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		List<User> users = null;

		try {
			connectionPool = connectionPoolProvider.getConnectionPool();

			connection = connectionPool.takeConnection();

			statement = connection.createStatement();

			resultSet = statement.executeQuery(DAOParametr.GET_ALL_USERS);

			users = new ArrayList<User>();

			while (resultSet.next()) {
				User user = new User();

				user.setfName(resultSet.getString(DAOParametr.FNAME));
				user.setlName(resultSet.getString(DAOParametr.LNAME));
				user.setPassport(resultSet.getString(DAOParametr.PASSPORT));
				user.setFaculty(resultSet.getString(DAOParametr.FACULTY));
				user.setGpa(Integer.parseInt(resultSet.getString(DAOParametr.GPA)));

				users.add(user);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException(e.getMessage());
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			connectionPool.closeConnection(connection, statement, resultSet);
		}

		return users;
	}

	@Override
	public void addEconomicsUser(User user) throws DAOException {
		ConnectionPoolProvider connectionPoolProvider = ConnectionPoolProvider.getInstance();
		ConnectionPool connectionPool = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connectionPool = connectionPoolProvider.getConnectionPool();

			connection = connectionPool.takeConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(DAOParametr.ADD_ECONOMICS_USER);
			preparedStatement.setString(1, user.getfName());
			preparedStatement.setString(2, user.getlName());
			preparedStatement.setString(3, user.getPassport());
			preparedStatement.setString(4, user.getGpa().toString());

			preparedStatement.executeUpdate();

			connection.commit();

		} catch (ConnectionPoolException e) {
			throw new DAOException(e.getMessage());
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			connectionPool.closeConnection(connection, preparedStatement);
		}

	}

	@Override
	public void addProgrammingUser(User user) throws DAOException {
		ConnectionPoolProvider connectionPoolProvider = ConnectionPoolProvider.getInstance();
		ConnectionPool connectionPool = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connectionPool = connectionPoolProvider.getConnectionPool();

			connection = connectionPool.takeConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(DAOParametr.ADD_PROGRAMMING_USER);
			preparedStatement.setString(1, user.getfName());
			preparedStatement.setString(2, user.getlName());
			preparedStatement.setString(3, user.getPassport());
			preparedStatement.setString(4, user.getGpa().toString());

			preparedStatement.executeUpdate();

			connection.commit();

		} catch (ConnectionPoolException e) {
			throw new DAOException(e.getMessage());
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			connectionPool.closeConnection(connection, preparedStatement);
		}

	}

	@Override
	public List<User> getFromEconomicTable() throws DAOException {
		ConnectionPoolProvider connectionPoolProvider = ConnectionPoolProvider.getInstance();
		ConnectionPool connectionPool = null;

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		List<User> users = null;

		try {
			connectionPool = connectionPoolProvider.getConnectionPool();

			connection = connectionPool.takeConnection();

			statement = connection.createStatement();

			resultSet = statement.executeQuery(DAOParametr.GET_FROM_ECONOMICS);

			users = new ArrayList<User>();

			while (resultSet.next()) {
				User user = new User();

				user.setfName(resultSet.getString(DAOParametr.FNAME));
				user.setlName(resultSet.getString(DAOParametr.LNAME));
				user.setPassport(resultSet.getString(DAOParametr.PASSPORT));
				user.setGpa(Integer.parseInt(resultSet.getString(DAOParametr.GPA)));

				users.add(user);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException(e.getMessage());
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			connectionPool.closeConnection(connection, statement, resultSet);
		}

		return users;
	}

	@Override
	public List<User> getFromProgrammingTable() throws DAOException {
		ConnectionPoolProvider connectionPoolProvider = ConnectionPoolProvider.getInstance();
		ConnectionPool connectionPool = null;

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		List<User> users = null;

		try {
			connectionPool = connectionPoolProvider.getConnectionPool();

			connection = connectionPool.takeConnection();

			statement = connection.createStatement();

			resultSet = statement.executeQuery(DAOParametr.GET_FROM_PROGRAMMING);

			users = new ArrayList<User>();

			while (resultSet.next()) {
				User user = new User();

				user.setfName(resultSet.getString(DAOParametr.FNAME));
				user.setlName(resultSet.getString(DAOParametr.LNAME));
				user.setPassport(resultSet.getString(DAOParametr.PASSPORT));
				user.setGpa(Integer.parseInt(resultSet.getString(DAOParametr.GPA)));

				users.add(user);
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException(e.getMessage());
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			connectionPool.closeConnection(connection, statement, resultSet);
		}

		return users;
	}

}
