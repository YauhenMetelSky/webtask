package by.metelski.webtask.command;

import by.metelski.webtask.command.impl.UnknownCommand;
import by.metelski.webtask.command.impl.UpdateAppointmentCommand;
import by.metelski.webtask.command.impl.sendEmailCommand;
import by.metelski.webtask.command.impl.ToSignUpCommand;
import by.metelski.webtask.command.impl.UnblockUserCommand;
import by.metelski.webtask.command.impl.FindUsersByNameCommand;
import by.metelski.webtask.command.impl.LogInCommand;
import by.metelski.webtask.command.impl.LogOutCommand;
import by.metelski.webtask.command.impl.SetLocaleCommand;
import by.metelski.webtask.command.impl.SignUpCommand;
import by.metelski.webtask.command.impl.ToAboutCommand;
import by.metelski.webtask.command.impl.ToAddProcedureCommand;
import by.metelski.webtask.command.impl.ToContactCommand;
import by.metelski.webtask.command.impl.ToMainCommand;
import by.metelski.webtask.command.impl.ToPersonalPageCommand;
import by.metelski.webtask.command.impl.ToServicesCommand;
import by.metelski.webtask.command.impl.ToSignInCommand;
import by.metelski.webtask.command.async.FindAllSchedulesByUserIdAsyncCommand;
import by.metelski.webtask.command.async.FindScheduleByIdAsyncCommand;
import by.metelski.webtask.command.async.FindTimeIntervalsByScheduleIdAsyncCommand;
import by.metelski.webtask.command.impl.ActivateAccountCommand;
import by.metelski.webtask.command.impl.ActivateProcedureCommand;
import by.metelski.webtask.command.impl.AddAppointmentCommand;
import by.metelski.webtask.command.impl.AddProcedureCommand;
import by.metelski.webtask.command.impl.AddDoctorScheduleCommand;
import by.metelski.webtask.command.impl.BlockUserCommand;
import by.metelski.webtask.command.impl.CancelAppointmentCommand;
import by.metelski.webtask.command.impl.ConfirmAppointmentCommand;
import by.metelski.webtask.command.impl.DeactivateProcedureCommand;
import by.metelski.webtask.command.impl.ToChangeAppointmentCommand;
import by.metelski.webtask.command.impl.ToChangeScheduleCommand;
import by.metelski.webtask.command.impl.FindAllProceduresCommand;
import by.metelski.webtask.command.impl.FindAllSchedulesByDoctorCommand;
import by.metelski.webtask.command.impl.FindAllSchedulesByIdCommand;
import by.metelski.webtask.command.impl.FindAllUsersCommand;
import by.metelski.webtask.command.impl.FindAllNewAppointmentsCommand;
import by.metelski.webtask.command.impl.FindAllAppointmentsByUserIdCommand;
import by.metelski.webtask.command.impl.FindUserByEmailCommand;

public enum CommandType {
	// TODO Sort commands
	ACTIVATE {
		{
			this.command = new ActivateAccountCommand();
		}
	},
	ACTIVATE_PROCEDURE {
		{
			this.command=new ActivateProcedureCommand();
		}
	},
	ADD_APPOINTMENT {
		{
			this.command = new AddAppointmentCommand();
		}
	},
	ADD_DOCTOR_SCHEDULE {
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
	CANCEL_APPOINTMENT{
		{
		this.command = new CancelAppointmentCommand();	
		}
	},
	CONFIRM_APPOINTMENT{
		{
			this.command = new ConfirmAppointmentCommand();
		}
	},
	DEACTIVATE_PROCEDURE {
		{
			this.command=new DeactivateProcedureCommand();
		}
	},
	TO_CHANGE_APPOINTMENT {
		{
			this.command = new ToChangeAppointmentCommand();
		}
	},
	FIND_ALL_NEW_APPOINTMENTS {
		{
			this.command = new FindAllNewAppointmentsCommand();
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
	FIND_ALL_APPOINTMENTS_BY_USER_ID {
		{
			this.command = new FindAllAppointmentsByUserIdCommand();
		}
	},
	FIND_ALL_SCHEDULES_BY_DOCTOR {
		{
			this.command = new FindAllSchedulesByDoctorCommand();
		}// FIXME can be one command findByID
	},
	FIND_ALL_SCHEDULES_BY_ID {
		{
			this.command = new FindAllSchedulesByIdCommand();
		}
	},
	FIND_ALL_SCHEDULES_BY_USER_ID_ASYNC {
		{
			this.command = new FindAllSchedulesByUserIdAsyncCommand();
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
	FIND_SCHEDULE_BY_ID_ASYNC {
		{
			this.command = new FindScheduleByIdAsyncCommand();
		}
	},
	FIND_TIME_INTERVALS_BY_SCHEDULE_ID_ASYNC {
		{
			this.command = new FindTimeIntervalsByScheduleIdAsyncCommand();
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
	TO_ADD_PROCEDURE{
		{
			this.command=new ToAddProcedureCommand();
		}
	},
	TO_CHANGE_SCHEDULE {
		{
			this.command= new ToChangeScheduleCommand();
		}
	},
	TO_PERSONAL_PAGE {
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
	UNBLOCK_USER {
		{
			this.command= new UnblockUserCommand();
		}
	},
	UNKNOWN_COMMAND {
		{
			this.command = new UnknownCommand();
		}
	},
	UPDATE_APPOINTMENT{
		{
			this.command = new UpdateAppointmentCommand();
		}
	};

	Command command;

	public Command getCurrentCommand() {
		return command;
	}
}
