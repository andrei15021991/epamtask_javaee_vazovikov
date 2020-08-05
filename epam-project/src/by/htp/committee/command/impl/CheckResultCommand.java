package by.htp.committee.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.htp.committee.command.Command;
import by.htp.committee.command.CommandParametr;
import by.htp.committee.entity.User;
import by.htp.committee.service.ServiceException;
import by.htp.committee.service.ServiceProvider;
import by.htp.committee.service.UserService;

public class CheckResultCommand implements Command {
	private final Logger LOGGER = Logger.getLogger(CheckResultCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		UserService userService = null;

		HttpSession session = request.getSession(true);

		User user = (User) session.getAttribute(CommandParametr.USER);

		if (user.getFaculty().equals(CommandParametr.ECONOMICS)) {

			try {
				userService = serviceProvider.getUserService();

				Integer check = userService.checkEconomicsTable();

				if (check > 0) {
					List<User> users = userService.getFromEconomicTable();

					session.setAttribute(CommandParametr.USERS, users);
				}

				session.setAttribute(CommandParametr.CHECK, check);

				response.sendRedirect(CommandParametr.PATH_MAIN_PAGE_COM);

			} catch (ServiceException e) {
				LOGGER.error(e.getMessage());

				String error = e.getMessage();

				session.setAttribute(CommandParametr.ERROR, error);

				response.sendRedirect(CommandParametr.PATH_MAIN_PAGE_COM);
			}

		} else if (user.getFaculty().equals(CommandParametr.PROGRAMMING)) {

			try {
				userService = serviceProvider.getUserService();

				Integer check = userService.checkProgrammingTable();

				session.setAttribute(CommandParametr.CHECK, check);

				response.sendRedirect(CommandParametr.PATH_MAIN_PAGE_COM);

			} catch (ServiceException e) {
				LOGGER.error(e.getMessage());

				String error = e.getMessage();

				session.setAttribute(CommandParametr.ERROR, error);

				response.sendRedirect(CommandParametr.PATH_MAIN_PAGE_COM);
			}
		}

	}

}
