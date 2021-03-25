package by.metelski.webtask.command;

import by.metelski.webtask.command.impl.EmptyCommand;
import by.metelski.webtask.command.impl.FindByNameCommand;
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
	EMPTY_COMMAND{
		{
		this.command = new EmptyCommand();
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
			this.command = new FindByNameCommand();
		}
	},
	FIND_BY_SURNAME{
		{
//		this.command = new FindByIdCommand();
		}
	};
	Command command;
	public Command getCurrentCommand() {
		return command;
	}
}
