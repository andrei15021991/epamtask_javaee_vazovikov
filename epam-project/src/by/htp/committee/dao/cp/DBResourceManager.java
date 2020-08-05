package by.htp.committee.dao.cp;

import java.util.ResourceBundle;

public class DBResourceManager {

	private static final DBResourceManager instance = new DBResourceManager();
	private ResourceBundle bundle = ResourceBundle.getBundle("resource.db");

	private DBResourceManager() {

	}

	public static DBResourceManager getInstance() {
		return instance;
	}

	public String getValue(String key) {
		return bundle.getString(key);
	}
}
