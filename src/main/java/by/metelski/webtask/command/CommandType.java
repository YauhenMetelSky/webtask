package by.metelski.webtask.command;

import by.metelski.webtask.command.impl.UnknownCommand;
import by.metelski.webtask.command.impl.FindUsersByNameCommand;
import by.metelski.webtask.command.impl.LogInCommand;
import by.metelski.webtask.command.impl.ShowAllUsersCommand;

public enum CommandType {
	SORT_BY_ID{;
	{
//		this.command = new SortByIdCommand();
	}
	},
	SORT_BY_NAME{
		{
//		this.command = new SortByAreaCommand();
		}
	},
	SORT_BY_SURNAME{
		{
//		this.command = new SortByAreaCommand();
		}
	},
	UNKNOWN_COMMAND{
		{
		this.command = new UnknownCommand();
		}
	},
	SHOW_ALL_USERS{
		{
			this.command = new ShowAllUsersCommand();
		}
	},
	FIND_BY_ID{
		{
//			this.command = new FindByPerimeterRangeCommand();
		}
	},
	FIND_BY_NAME{
		{
			this.command = new FindUsersByNameCommand();
		}
	},
	FIND_BY_SURNAME{
		{
//		this.command = new FindByIdCommand();
		}
	},
	LOG_IN{
		{
		this.command = new LogInCommand();
		}
	};
	Command command;
	public Command getCurrentCommand() {
		return command;
	}
}
