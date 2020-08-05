package by.htp.committee.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.committee.command.Command;
import by.htp.committee.command.CommandParametr;

public class GoToProgrammingRegistration implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		session.setAttribute(CommandParametr.PATH, CommandParametr.PATH_PROG_REG_PAGE_COM);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher(CommandParametr.PATH_PROG_REG_PAGE);
		requestDispatcher.forward(request, response);

	}

}
