package by.htp.committee.dao.cp;

public class ConnectionPoolProvider {
	private static final ConnectionPoolProvider instance = new ConnectionPoolProvider();
	private ConnectionPool connectionPool = new ConnectionPool();

	private ConnectionPoolProvider() {

	}

	public static ConnectionPoolProvider getInstance() {
		return instance;
	}

	public ConnectionPool getConnectionPool() {
		return connectionPool;
	}

}
