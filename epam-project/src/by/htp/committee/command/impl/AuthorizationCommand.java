package by.htp.committee.command.impl;

import java.io.IOException;
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

public class AuthorizationCommand implements Command {
	private final Logger LOGGER = Logger.getLogger(AuthorizationCommand.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter(CommandParametr.LOGIN);
		String password = request.getParameter(CommandParametr.PASSWORD);

		User user = null;
		HttpSession session = null;

		ServiceProvider serviceProvider = null;
		UserService userService = null;

		try {
			serviceProvider = ServiceProvider.getInstance();
			userService = serviceProvider.getUserService();

			user = userService.authorization(login, password);

			if (user != null) {
				session = request.getSession();

				if (user.getRole().equals(CommandParametr.ADMIN)) {
					session.setAttribute(CommandParametr.USER, user);

					response.sendRedirect(CommandParametr.PATH_ADMIN_PAGE_COM);
				} else if (user.getRole().equals(CommandParametr.USER)) {
					session.setAttribute(CommandParametr.USER, user);

					response.sendRedirect(CommandParametr.PATH_MAIN_PAGE_COM);
				}
			}
		} catch (ServiceException e) {
			LOGGER.error(e.getMessage());

			String error = e.getMessage();

			session = request.getSession(true);
			session.setAttribute(CommandParametr.ERROR_AUT, error);

			response.sendRedirect(CommandParametr.PATH_LOGIN_PAGE_COM);
		}

	}

}
