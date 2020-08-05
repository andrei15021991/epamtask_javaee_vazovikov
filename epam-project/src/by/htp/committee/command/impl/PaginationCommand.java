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

public class PaginationCommand implements Command {
	private final Logger LOGGER = Logger.getLogger(PaginationCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int currentPage = Integer.parseInt(request.getParameter(CommandParametr.CURRENT_PAGE));
		int recordsPerPage = Integer.parseInt(request.getParameter(CommandParametr.RECORDS_PER_PAGE));

		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		UserService userService = null;
		HttpSession session = null;

		List<User> users = null;

		try {
			userService = serviceProvider.getUserService();

			users = userService.getUsers(currentPage, recordsPerPage);

			int rows = userService.getNumberOfRows();

			int nOfPages = rows / recordsPerPage;

			if (nOfPages % recordsPerPage > 0) {

				nOfPages++;
			}

			session = request.getSession(true);

			session.setAttribute(CommandParametr.USERS, users);
			session.setAttribute(CommandParametr.NO_OF_PAGES, nOfPages);
			session.setAttribute(CommandParametr.CURRENT_PAGE, currentPage);
			session.setAttribute(CommandParametr.RECORDS_PER_PAGE, recordsPerPage);

			response.sendRedirect(CommandParametr.PATH_ADMIN_PAGE_COM);
		} catch (ServiceException e) {
			LOGGER.error(e.getMessage());

			response.sendRedirect(CommandParametr.PATH_ADMIN_PAGE_COM);
		}

	}

}
