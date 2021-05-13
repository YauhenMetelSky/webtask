package by.metelski.webtask.command;

import by.metelski.webtask.command.impl.UnknownCommand;
import by.metelski.webtask.command.impl.sendEmailCommand;
import by.metelski.webtask.command.impl.ToSignUpCommand;
import by.metelski.webtask.command.impl.FindUsersByNameCommand;
import by.metelski.webtask.command.impl.LogInCommand;
import by.metelski.webtask.command.impl.LogOutCommand;
import by.metelski.webtask.command.impl.SetLocaleCommand;
import by.metelski.webtask.command.impl.SignUpCommand;
import by.metelski.webtask.command.impl.ToAboutCommand;
import by.metelski.webtask.command.impl.ToContactCommand;
import by.metelski.webtask.command.impl.ToMainCommand;
import by.metelski.webtask.command.impl.ToPersonalPageCommand;
import by.metelski.webtask.command.impl.ToServicesCommand;
import by.metelski.webtask.command.impl.ToSignInCommand;
import by.metelski.webtask.command.impl.ActivateAccountCommand;
import by.metelski.webtask.command.impl.AddAppointmentCommand;
import by.metelski.webtask.command.impl.AddProcedureCommand;
import by.metelski.webtask.command.impl.AddDoctorScheduleCommand;
import by.metelski.webtask.command.impl.BlockUserCommand;
import by.metelski.webtask.command.impl.FindAllProceduresCommand;
import by.metelski.webtask.command.impl.FindAllUsersCommand;
import by.metelski.webtask.command.impl.FindAllActiveSchedulesCommand;
import by.metelski.webtask.command.impl.FindUserByEmailCommand;

public enum CommandType {
	// TODO Sort commands
	ACTIVATE {
		{
			this.command = new ActivateAccountCommand();
		}
	},
	ADD_APPOINTMENT {
		{
			this.command = new AddAppointmentCommand();
		}
	},
	ADD_SCHEDULE {
		{
			this.command = new AddDoctorScheduleCommand();
		}
	},
	ADD_PROCEDURE {
		{
			this.command = new AddProcedureCommand();
		}
	},
	BLOCK_USER {
		{
			this.command = new BlockUserCommand();
		}
	},
	FIND_ALL_USERS {
		{
			this.command = new FindAllUsersCommand();
		}
	},
	FIND_ALL_PROCEDURES {
		{
			this.command = new FindAllProceduresCommand();
		}
	},
	FIND_BY_NAME {
		{
			this.command = new FindUsersByNameCommand();
		}
	},
	FIND_BY_EMAIL {
		{
			this.command = new FindUserByEmailCommand();
		}
	},
	FIND_All_ACTIVE_SCHEDULES {
		{
			this.command = new FindAllActiveSchedulesCommand();
		}
	},
	LOG_IN {
		{
			this.command = new LogInCommand();
		}
	},
	LOG_OUT {
		{
			this.command = new LogOutCommand();
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
	TO_PERSONAL_PAGE{
		{
			this.command = new ToPersonalPageCommand();
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
