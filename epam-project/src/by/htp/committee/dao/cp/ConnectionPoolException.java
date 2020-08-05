package by.htp.committee.dao.cp;

public class ConnectionPoolException extends Exception {
	private static final long serialVersionUID = 4825975043373691276L;

	public ConnectionPoolException() {
		super();
	}

	public ConnectionPoolException(String message) {
		super(message);
	}

	public ConnectionPoolException(Exception e) {
		super(e);
	}

	public ConnectionPoolException(String message, Exception e) {
		super(message, e);
	}

}
