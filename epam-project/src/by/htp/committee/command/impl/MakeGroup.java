package by.htp.committee.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

public class MakeGroup implements Command {
	private final Logger LOGGER = Logger.getLogger(MakeGroup.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		UserService userService = null;

		List<User> users = null;

		List<User> economics = null;
		List<User> programming = null;

		try {
			userService = serviceProvider.getUserService();

			users = userService.getAllUsers();

			economics = new ArrayList<User>();
			programming = new ArrayList<User>();

			for (User u : users) {
				if (u.getFaculty().equals(CommandParametr.ECONOMICS)) {
					economics.add(u);
				} else {
					programming.add(u);
				}
			}

			Collections.sort(economics, new SortByGPA());
			Collections.sort(programming, new SortByGPA());

			for (int i = 0; i < 4; i++) {
				userService.addEconomicsUser(economics.get(i));
				userService.addProgrammingUser(programming.get(i));
			}

		} catch (ServiceException e) {
			LOGGER.error(e.getMessage());

			response.sendRedirect(CommandParametr.PATH_ADMIN_PAGE_COM);
		}

	}

	private class SortByGPA implements Comparator<User> {

		@Override
		public int compare(User o1, User o2) {

			if (o1.getGpa() > o2.getGpa()) {
				return -1;
			} else if (o1.getGpa() < o2.getGpa()) {
				return 1;
			}

			return 0;
		}

	}

}
