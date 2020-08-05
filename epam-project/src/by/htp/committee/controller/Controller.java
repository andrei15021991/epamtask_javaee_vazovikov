package by.htp.committee.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import by.htp.committee.command.Command;
import by.htp.committee.command.CommandProvider;
import by.htp.committee.dao.cp.ConnectionPool;
import by.htp.committee.dao.cp.ConnectionPoolException;
import by.htp.committee.dao.cp.ConnectionPoolProvider;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final Logger LOGGER = Logger.getLogger(Controller.class);

	public Controller() {
		super();

	}

	@Override
	public void init() throws ServletException {
		ConnectionPoolProvider connectionPoolProvider = ConnectionPoolProvider.getInstance();
		ConnectionPool connectionPool = null;

		try {
			connectionPool = connectionPoolProvider.getConnectionPool();
			connectionPool.initPoolData();

		} catch (ConnectionPoolException e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CommandProvider commandProvider = new CommandProvider();

		String commandName = request.getParameter(ControllerParametr.COMMAND);

		Command command = commandProvider.getCommand(commandName);
		command.execute(request, response);

	}

	@Override
	public void destroy() {
		ConnectionPoolProvider connectionPoolProvider = ConnectionPoolProvider.getInstance();
		ConnectionPool connectionPool = connectionPoolProvider.getConnectionPool();

		connectionPool.dispose();
	}

}
