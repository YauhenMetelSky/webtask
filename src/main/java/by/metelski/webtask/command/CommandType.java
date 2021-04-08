package by.metelski.webtask.command;

import by.metelski.webtask.command.impl.UnknownCommand;
import by.metelski.webtask.command.impl.toSignUpCommand;
import by.metelski.webtask.command.impl.FindUsersByNameCommand;
import by.metelski.webtask.command.impl.LogInCommand;
import by.metelski.webtask.command.impl.SignUpCommand;
import by.metelski.webtask.command.impl.FindAllUsersCommand;

public enum CommandType {
	// TODO Sort commands
	UNKNOWN_COMMAND {
		{
			this.command = new UnknownCommand();
		}
	},
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
	SIGN_UP {
		{
			this.command = new SignUpCommand();
		}
	},
	TO_SIGN_UP {
		{
			this.command = new toSignUpCommand();
		}
	};

	Command command;

	public Command getCurrentCommand() {
		return command;
	}
}
