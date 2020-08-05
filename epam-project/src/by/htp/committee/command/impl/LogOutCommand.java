package by.htp.committee.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.committee.command.Command;
import by.htp.committee.command.CommandParametr;

public class LogOutCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		session.removeAttribute(CommandParametr.USER);
		session.removeAttribute(CommandParametr.ERROR_AUT);
		session.removeAttribute(CommandParametr.CHECK);

		response.sendRedirect(CommandParametr.PATH_LOGIN_PAGE_COM);

	}

}
