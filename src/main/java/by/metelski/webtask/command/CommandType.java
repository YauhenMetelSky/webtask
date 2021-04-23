package by.metelski.webtask.command;

import by.metelski.webtask.command.impl.UnknownCommand;
import by.metelski.webtask.command.impl.sendEmailCommand;
import by.metelski.webtask.command.impl.ToSignUpCommand;
import by.metelski.webtask.command.impl.FindUsersByNameCommand;
import by.metelski.webtask.command.impl.LogInCommand;
import by.metelski.webtask.command.impl.SetLocaleCommand;
import by.metelski.webtask.command.impl.SignUpCommand;
import by.metelski.webtask.command.impl.ToAboutCommand;
import by.metelski.webtask.command.impl.ToContactCommand;
import by.metelski.webtask.command.impl.ToMainCommand;
import by.metelski.webtask.command.impl.ToServicesCommand;
import by.metelski.webtask.command.impl.ToSignInCommand;
import by.metelski.webtask.command.impl.FindAllUsersCommand;

public enum CommandType {
	// TODO Sort commands
	FIND_ALL_USERS {
		{
			this.command = new FindAllUsersCommand();
		}
	},
	FIND_BY_NAME {
		{
			this.command = new FindUsersByNameCommand();
		}
	},
	LOG_IN {
		{
			this.command = new LogInCommand();
		}
	},
	SEND_EMAIL {
		{
			this.command = new sendEmailCommand();
		}
	},
	SET_LOCALE {
		{
			this.command = new SetLocaleCommand();
		}
	},
	SIGN_UP {
		{
			this.command = new SignUpCommand();
		}
	},
	TO_ABOUT {
		{
			this.command = new ToAboutCommand();
		}
	},
	TO_CONTACT {
		{
			this.command = new ToContactCommand();
		}
	},
	TO_MAIN {
		{
			this.command = new ToMainCommand();
		}
	},
	TO_SERVICES {
		{
			this.command = new ToServicesCommand();
		}
	},
	TO_SIGN_IN {
		{
			this.command = new ToSignInCommand();
		}
	},
	TO_SIGN_UP {
		{
			this.command = new ToSignUpCommand();
		}
	},
	UNKNOWN_COMMAND {
		{
			this.command = new UnknownCommand();
		}
	};

	Command command;

	public Command getCurrentCommand() {
		return command;
	}
}
