package by.htp.committee.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.committee.command.Command;
import by.htp.committee.command.CommandParametr;

public class GoToAdminPage implements Command {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher requestDispatcher = request.getRequestDispatcher(CommandParametr.PATH_ADMIN_PAGE);
		requestDispatcher.forward(request, response);

	}

}
