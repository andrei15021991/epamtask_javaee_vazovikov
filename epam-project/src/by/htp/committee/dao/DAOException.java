package by.htp.committee.dao;

public class DAOException extends Exception {
	static final long serialVersionUID = 5788424410325330694L;

	public DAOException() {
		super();
	}

	public DAOException(String message) {
		super(message);
	}

	public DAOException(Exception e) {
		super(e);
	}

	public DAOException(String message, Exception e) {
		super(message, e);
	}

}
