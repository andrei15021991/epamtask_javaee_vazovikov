package by.htp.committee.service;

public final class ServiceParametr {

	private ServiceParametr() {

	}

	public static final String E_AUT = "нет такого пользователя (no such user)";
	public static final String E_CHECK_PROGRAMMING_ECONOMICS = "ошибка подключения к бд (db error)";
	public static final String E_REG = "логин недоступен(login is not available)";
	public static final String E_USER_LOGIN = "db error";
	public static final String REG_EXP_LP = "^[a-z0-9_-]{3,16}$";
	public static final String E_LOGIN = "ошибка в поле логин(login mistake)";
	public static final String E_PASSWORD = "ошибка в поле пароль(password mistake)";
	public static final String REG_EXP_FN_LN = "^[А-Яа-я]{3,16}$";
	public static final String E_FN = "ошибка в поле имя(first name mistake)";
	public static final String E_LN = "ошибка в поле фамилия(last name mistake)";
	public static final String REG_EXP_PASSPORT = "^[a-zA-Z0-9]{14}$";
	public static final String E_PASSPORT = "ошибка в поле пасспорт(passport mistake)";
	public static final String REG_EXP_GPA = "^[0-9][0-9]?$|^100$";
	public static final String E_GPA = "указывать от 1 - 100(from 1 - 100)";
	public static final String E_EMPTY_TABLE = "the table is empty";
	public static final String E_DB_ERROR = "db error";
	public static final String E_ADD_USER_EC = "user info error in insert economics";
	public static final String E_ADD_USER_PR = "user info error in insert programming";
}
