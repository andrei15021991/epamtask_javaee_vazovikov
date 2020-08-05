package by.htp.committee.dao;

public final class DAOParametr {

	private DAOParametr() {

	}

	public static final String SQL_AUT = "select * from users inner join users_detail on users.id = users_detail.users_id where users.login=? and users.password=?";
	public static final String CHECK_PROGRAMMING_TABLE = "select count(id) from programming";
	public static final String CHECK_ECONOMICS_TABLE = "select count(id) from economics";
	public static final String SQL_REG_1 = "insert into users (login, password, role) values (?, ?, ?)";
	public static final String SQL_REG_2 = "insert into users_detail (fname, lname, passport, gpa, faculty, users_id) values (?,?,?,?,?,(select id from users where login=? and password=?))";
	public static final String USERS_LOGIN = "select login from users";
	public static final String GET_NUMBER_OF_ROWS = "select count(id) from users_detail where faculty != 'admin'";
	public static final String GET_USERS = "select * from users_detail limit ?,?";
	public static final String GET_ALL_USERS = "select * from users_detail where passport != 'admin'";
	public static final String ADD_ECONOMICS_USER = "insert into economics (fname, lname, passport, gpa) values (?, ?, ?, ?)";
	public static final String ADD_PROGRAMMING_USER = "insert into programming (fname, lname, passport, gpa) values (?, ?, ?, ?)";
	public static final String GET_FROM_ECONOMICS = "select * from economics";
	public static final String GET_FROM_PROGRAMMING = "select * from programming";

	public static final String ID = "id";
	public static final String FNAME = "fname";
	public static final String LNAME = "lname";
	public static final String PASSPORT = "passport";
	public static final String GPA = "gpa";
	public static final String FACULTY = "faculty";
	public static final String ROLE = "role";
	public static final String ADMIN = "admin";

}
