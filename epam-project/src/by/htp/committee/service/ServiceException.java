package by.htp.committee.service;

public class ServiceException extends Exception {
	private static final long serialVersionUID = -6321616161566730716L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Exception e) {
		super(e);
	}

	public ServiceException(String message, Exception e) {
		super(message, e);
	}

}
