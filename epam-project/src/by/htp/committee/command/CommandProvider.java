package by.htp.committee.command;

import java.util.HashMap;
import java.util.Map;

import by.htp.committee.command.impl.AuthorizationCommand;
import by.htp.committee.command.impl.CheckResultCommand;
import by.htp.committee.command.impl.GoToAdminPage;
import by.htp.committee.command.impl.GoToEconomicsRegistration;
import by.htp.committee.command.impl.GoToLoginPage;
import by.htp.committee.command.impl.GoToMainPage;
import by.htp.committee.command.impl.GoToProgrammingRegistration;
import by.htp.committee.command.impl.LocalizationCommand;
import by.htp.committee.command.impl.LogOutCommand;
import by.htp.committee.command.impl.PaginationCommand;
import by.htp.committee.command.impl.RegistrationCommand;
import by.htp.committee.command.impl.MakeGroup;

public class CommandProvider {
	private Map<CommandName, Command> commands = new HashMap<CommandName, Command>();

	public CommandProvider() {
		commands.put(CommandName.AUTHORIZATION, new AuthorizationCommand());
		commands.put(CommandName.LOCALIZATION, new LocalizationCommand());
		commands.put(CommandName.LOG_OUT, new LogOutCommand());
		commands.put(CommandName.CHECK_RESULT, new CheckResultCommand());
		commands.put(CommandName.REGISTRATION, new RegistrationCommand());
		commands.put(CommandName.PAGINATION, new PaginationCommand());
		commands.put(CommandName.MAKE_GROUP, new MakeGroup());

		commands.put(CommandName.GO_TO_LOGIN_PAGE, new GoToLoginPage());
		commands.put(CommandName.GO_TO_ADMIN_PAGE, new GoToAdminPage());
		commands.put(CommandName.GO_TO_MAIN_PAGE, new GoToMainPage());
		commands.put(CommandName.GO_TO_ECONOMICS_REGISTRATION, new GoToEconomicsRegistration());
		commands.put(CommandName.GO_TO_PROGRAMMING_REGISTRATION, new GoToProgrammingRegistration());
	}

	public Command getCommand(String commandName) {
		Command command;
		command = commands.get(CommandName.valueOf(commandName.toUpperCase()));

		return command;
	}

}
