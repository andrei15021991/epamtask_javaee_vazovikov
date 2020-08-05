package by.htp.committee.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.htp.committee.command.Command;
import by.htp.committee.command.CommandParametr;
import by.htp.committee.entity.User;
import by.htp.committee.service.ServiceException;
import by.htp.committee.service.ServiceProvider;
import by.htp.committee.service.UserService;

public class RegistrationCommand implements Command {
	private final Logger LOGGER = Logger.getLogger(RegistrationCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String login = request.getParameter(CommandParametr.LOGIN);
		String password = request.getParameter(CommandParametr.PASSWORD);
		String fName = request.getParameter(CommandParametr.FNAME);
		String lName = request.getParameter(CommandParametr.LNAME);
		String passport = request.getParameter(CommandParametr.PASSPORT);
		String role = request.getParameter(CommandParametr.ROLE);
		String faculty = request.getParameter(CommandParametr.FACULTY);
		String firstSubject = request.getParameter(CommandParametr.FIRST);
		String secondSubject = request.getParameter(CommandParametr.SECOND);
		String thirdSubject = request.getParameter(CommandParametr.THIRD);
		String ag = request.getParameter(CommandParametr.GPA);

		User user = new User();

		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		UserService userService = null;

		try {
			userService = serviceProvider.getUserService();

			Integer gpa = userService.resGPA(Integer.parseInt(firstSubject), Integer.parseInt(secondSubject),
					Integer.parseInt(thirdSubject), Integer.parseInt(ag));

			user.setLogin(login);
			user.setPassword(password);
			user.setfName(fName);
			user.setlName(lName);
			user.setPassport(passport);
			user.setRole(role);
			user.setFaculty(faculty);
			user.setGpa(gpa);

			userService.registration(user);

			response.sendRedirect(CommandParametr.PATH_LOGIN_PAGE_COM);

		} catch (NumberFormatException | ServiceException e) {
			LOGGER.error(e.getMessage());

			if (faculty.equals(CommandParametr.ECONOMICS)) {
				response.sendRedirect(CommandParametr.PATH_EC_REG_PAGE_COM);
			} else {
				response.sendRedirect(CommandParametr.PATH_PROG_REG_PAGE_COM);
			}
		}

	}

}
