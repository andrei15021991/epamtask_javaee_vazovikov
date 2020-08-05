package by.htp.committee.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.committee.command.Command;
import by.htp.committee.command.CommandParametr;

public class GoToLoginPage implements Command {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		session.setAttribute(CommandParametr.PATH, CommandParametr.PATH_LOGIN_PAGE_COM);

		RequestDispatcher dispatcher = request.getRequestDispatcher(CommandParametr.PATH_LOGIN_PAGE);
		dispatcher.forward(request, response);
	}

}
