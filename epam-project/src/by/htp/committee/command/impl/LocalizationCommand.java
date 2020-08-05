package by.htp.committee.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.committee.command.Command;
import by.htp.committee.command.CommandParametr;

public class LocalizationCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);

		String path = (String) session.getAttribute(CommandParametr.PATH);
		String local = request.getParameter(CommandParametr.LOCAL);

		session.setAttribute(CommandParametr.LOCAL, local);

		response.sendRedirect(path);

	}

}
