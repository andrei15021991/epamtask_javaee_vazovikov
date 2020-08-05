package by.htp.committee.dao.cp;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import org.apache.log4j.Logger;

public class ConnectionPool {
	private final Logger LOGGER = Logger.getLogger(ConnectionPool.class);

	private BlockingQueue<Connection> connectionQueue;
	private BlockingQueue<Connection> givenAwayConQueue;

	private String driver;
	private String url;
	private String user;
	private String password;
	private int size;

	public ConnectionPool() {
		DBResourceManager manager = DBResourceManager.getInstance();
		this.driver = manager.getValue(DBParametr.DB_DRIVER);
		this.url = manager.getValue(DBParametr.DB_URL);
		this.user = manager.getValue(DBParametr.DB_USER);
		this.password = manager.getValue(DBParametr.DB_PASSWORD);

		try {
			this.size = Integer.parseInt(manager.getValue(DBParametr.DB_POOL_SIZE));
		} catch (NumberFormatException e) {
			LOGGER.error(e.getMessage());

			size = 5;
		}
	}

	public void initPoolData() throws ConnectionPoolException {

		try {
			Class.forName(driver);

			givenAwayConQueue = new ArrayBlockingQueue<Connection>(size);
			connectionQueue = new ArrayBlockingQueue<Connection>(size);

			for (int i = 0; i < size; i++) {
				Connection connection = DriverManager.getConnection(url, user, password);

				PooledConnection pooledConnection = new PooledConnection(connection);
				connectionQueue.add(pooledConnection);
			}
		} catch (SQLException e) {
			throw new ConnectionPoolException("SQLException in ConnectionPool", e);

		} catch (ClassNotFoundException e) {
			throw new ConnectionPoolException("Can't find driver class", e);

		}
	}

	public void dispose() {
		clearConnectionQueue();
	}

	private void clearConnectionQueue() {
		try {
			closeConnectionsQueue(givenAwayConQueue);
			closeConnectionsQueue(connectionQueue);
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
	}

	public Connection takeConnection() throws ConnectionPoolException {
		Connection connection = null;

		try {
			connection = connectionQueue.take();
			givenAwayConQueue.add(connection);
		} catch (InterruptedException e) {
			throw new ConnectionPoolException("error connectihg to the data source", e);
		}

		return connection;
	}

	private void closeConnectionsQueue(BlockingQueue<Connection> queue) throws SQLException {

		Connection connection;

		while ((connection = queue.poll()) != null) {
			if (!connection.getAutoCommit()) {
				connection.commit();
			}
			((PooledConnection) connection).reallyClose();
		}
	}

	public void closeConnection(Connection con, PreparedStatement pst, ResultSet res) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
			}
		}

		if (pst != null) {
			try {
				pst.close();
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
			}
		}

		if (res != null) {
			try {
				res.close();
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
			}
		}
	}

	public void closeConnection(Connection con, Statement pst, ResultSet res) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
			}
		}

		if (pst != null) {
			try {
				pst.close();
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
			}
		}

		if (res != null) {
			try {
				res.close();
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
			}
		}
	}

	public void closeConnection(Connection con, PreparedStatement pst1, PreparedStatement pst2) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
			}
		}

		if (pst1 != null) {
			try {
				pst1.close();
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
			}
		}

		if (pst2 != null) {
			try {
				pst2.close();
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
			}
		}
	}

	public void closeConnection(Connection con, PreparedStatement pst) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
			}
		}

		if (pst != null) {
			try {
				pst.close();
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
			}
		}

	}

	private class PooledConnection implements Connection {
		private Connection connection;

		public PooledConnection(Connection c) throws SQLException {
			this.connection = c;
			this.connection.setAutoCommit(true);
		}

		public void reallyClose() throws SQLException {
			connection.close();
		}

		public void clearWarnings() throws SQLException {
			connection.clearWarnings();
		}

		public void close() throws SQLException {

			if (connection.isClosed()) {
				throw new SQLException("Attempting to close closed connection.");
			}
			if (connection.isReadOnly()) {
				connection.setReadOnly(false);
			}

			if (!givenAwayConQueue.remove(this)) {
				throw new SQLException("Error deleting connection from the given away connections pool.");
			}

			if (!connectionQueue.offer(this)) {
				throw new SQLException("Error allocating connection in the pool.");
			}
		}

		public void commit() throws SQLException {
			connection.commit();
		}

		public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
			return connection.createArrayOf(typeName, elements);
		}

		public Blob createBlob() throws SQLException {
			return (Blob) connection.createBlob();
		}

		public Clob createClob() throws SQLException {
			return (Clob) connection.createClob();
		}

		public NClob createNClob() throws SQLException {
			return (NClob) connection.createNClob();
		}

		public SQLXML createSQLXML() throws SQLException {
			return connection.createSQLXML();
		}

		public Statement createStatement() throws SQLException {
			return connection.createStatement();
		}

		public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
			return connection.createStatement(resultSetType, resultSetConcurrency);
		}

		public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
				throws SQLException {
			return connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
		}

		public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
			return connection.createStruct(typeName, attributes);
		}

		public boolean getAutoCommit() throws SQLException {
			return connection.getAutoCommit();
		}

		public String getCatalog() throws SQLException {
			return connection.getCatalog();
		}

		public Properties getClientInfo() throws SQLException {
			return connection.getClientInfo();
		}

		public String getClientInfo(String name) throws SQLException {
			return connection.getClientInfo(name);
		}

		public int getHoldability() throws SQLException {
			return connection.getHoldability();
		}

		public DatabaseMetaData getMetaData() throws SQLException {
			return (DatabaseMetaData) connection.getMetaData();
		}

		public int getTransactionIsolation() throws SQLException {
			return connection.getTransactionIsolation();
		}

		public Map<String, Class<?>> getTypeMap() throws SQLException {
			return connection.getTypeMap();
		}

		public SQLWarning getWarnings() throws SQLException {
			return connection.getWarnings();
		}

		public boolean isClosed() throws SQLException {
			return connection.isClosed();
		}

		public boolean isReadOnly() throws SQLException {
			return connection.isReadOnly();
		}

		public boolean isValid(int timeout) throws SQLException {
			return connection.isValid(timeout);
		}

		public String nativeSQL(String sql) throws SQLException {
			return connection.nativeSQL(sql);
		}

		public CallableStatement prepareCall(String sql) throws SQLException {
			return (CallableStatement) connection.prepareCall(sql);
		}

		public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency)
				throws SQLException {
			return (CallableStatement) connection.prepareCall(sql, resultSetType, resultSetConcurrency);
		}

		public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
				int resultSetHoldability) throws SQLException {
			return (CallableStatement) connection.prepareCall(sql, resultSetType, resultSetConcurrency,
					resultSetHoldability);
		}

		public PreparedStatement prepareStatement(String sql) throws SQLException {
			return connection.prepareStatement(sql);
		}

		public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
			return connection.prepareStatement(sql, autoGeneratedKeys);
		}

		public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
			return connection.prepareStatement(sql, columnIndexes);
		}

		public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
			return connection.prepareStatement(sql, columnNames);
		}

		public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
				throws SQLException {
			return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
		}

		public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
				int resultSetHoldability) throws SQLException {
			return connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
		}

		public void rollback() throws SQLException {
			connection.rollback();
		}

		public void setAutoCommit(boolean autoCommit) throws SQLException {
			connection.setAutoCommit(autoCommit);
		}

		public void setCatalog(String catalog) throws SQLException {
			connection.setCatalog(catalog);
		}

		public void setClientInfo(String name, String value) throws SQLClientInfoException {
			connection.setClientInfo(name, value);
		}

		public void setHoldability(int holdability) throws SQLException {
			connection.setHoldability(holdability);
		}

		public void setReadOnly(boolean readOnly) throws SQLException {
			connection.setReadOnly(readOnly);
		}

		public Savepoint setSavepoint() throws SQLException {
			return connection.setSavepoint();
		}

		public Savepoint setSavepoint(String name) throws SQLException {
			return connection.setSavepoint(name);
		}

		public void setTransactionIsolation(int level) throws SQLException {
			connection.setTransactionIsolation(level);
		}

		public boolean isWrapperFor(Class<?> iface) throws SQLException {
			return connection.isWrapperFor(iface);
		}

		public <T> T unwrap(Class<T> iface) throws SQLException {
			return connection.unwrap(iface);
		}

		public void abort(Executor arg0) throws SQLException {
			connection.abort(arg0);
		}

		public int getNetworkTimeout() throws SQLException {
			return connection.getNetworkTimeout();
		}

		public String getSchema() throws SQLException {
			return connection.getSchema();
		}

		public void releaseSavepoint(Savepoint arg0) throws SQLException {
			connection.releaseSavepoint(arg0);
		}

		public void rollback(Savepoint arg0) throws SQLException {
			connection.rollback(arg0);
		}

		public void setClientInfo(Properties arg0) throws SQLClientInfoException {
			connection.setClientInfo(arg0);
		}

		public void setNetworkTimeout(Executor arg0, int arg1) throws SQLException {
			connection.setNetworkTimeout(arg0, arg1);
		}

		public void setSchema(String arg0) throws SQLException {
			connection.setSchema(arg0);
		}

		public void setTypeMap(Map<String, Class<?>> arg0) throws SQLException {
			connection.setTypeMap(arg0);
		}

	}
}
