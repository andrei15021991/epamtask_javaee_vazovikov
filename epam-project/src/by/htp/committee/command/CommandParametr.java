package by.htp.committee.command;

public final class CommandParametr {

	private CommandParametr() {

	}

	public static final String LOGIN = "login";
	public static final String PASSWORD = "password";
	public static final String FNAME = "fname";
	public static final String LNAME = "lname";
	public static final String PASSPORT = "passport";
	public static final String ROLE = "role";
	public static final String FACULTY = "faculty";
	public static final String FIRST = "firstsubject";
	public static final String SECOND = "secondsubject";
	public static final String THIRD = "thirdsubject";
	public static final String GPA = "gpa";
	public static final String ADMIN = "admin";
	public static final String USER = "user";
	public static final String ERROR_AUT = "error_aut";
	public static final String ECONOMICS = "economics";
	public static final String CHECK = "check";
	public static final String ERROR = "error";
	public static final String PROGRAMMING = "programming";
	public static final String PATH = "path";
	public static final String LOCAL = "local";
	public static final String CURRENT_PAGE = "currentPage";
	public static final String RECORDS_PER_PAGE = "recordsPerPage";
	public static final String USERS = "users";
	public static final String NO_OF_PAGES = "noOfPages";

	public static final String PATH_ADMIN_PAGE_COM = "Controller?command=go_to_admin_page";
	public static final String PATH_MAIN_PAGE_COM = "Controller?command=go_to_main_page";
	public static final String PATH_LOGIN_PAGE_COM = "Controller?command=go_to_login_page";
	public static final String PATH_EC_REG_PAGE_COM = "Controller?command=go_to_economics_registration";
	public static final String PATH_PROG_REG_PAGE_COM = "Controller?command=go_to_programming_registration";

	public static final String PATH_ADMIN_PAGE = "WEB-INF/jsp/admin_page.jsp";
	public static final String PATH_EC_REG_PAGE = "WEB-INF/jsp/registration_economics.jsp";
	public static final String PATH_LOGIN_PAGE = "WEB-INF/jsp/login_page.jsp";
	public static final String PATH_MAIN_PAGE = "WEB-INF/jsp/main_page.jsp";
	public static final String PATH_PROG_REG_PAGE = "WEB-INF/jsp/registration_programming.jsp";

}
