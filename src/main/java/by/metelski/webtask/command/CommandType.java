package by.metelski.webtask.command;

import by.metelski.webtask.command.impl.UnknownCommand;
import by.metelski.webtask.command.impl.UpdateAppointmentCommand;
import by.metelski.webtask.command.impl.SendEmailCommand;
import by.metelski.webtask.command.impl.ToSignUpCommand;
import by.metelski.webtask.command.impl.UnblockUserCommand;
import by.metelski.webtask.command.impl.FindUsersByNameCommand;
import by.metelski.webtask.command.impl.FindUsersBySurnameCommand;
import by.metelski.webtask.command.impl.FindUsersPaginationCommand;
import by.metelski.webtask.command.impl.FinishAppointmentCommand;
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
import by.metelski.webtask.command.impl.ActivateAccountCommand;
import by.metelski.webtask.command.impl.ActivateProcedureCommand;
import by.metelski.webtask.command.impl.AddAppointmentCommand;
import by.metelski.webtask.command.impl.AddProcedureCommand;
import by.metelski.webtask.command.impl.AddDoctorScheduleCommand;
import by.metelski.webtask.command.impl.BlockUserCommand;
import by.metelski.webtask.command.impl.CancelAppointmentCommand;
import by.metelski.webtask.command.impl.ChangeDoctorScheduleCommand;
import by.metelski.webtask.command.impl.ChangePersonalInfoCommand;
import by.metelski.webtask.command.impl.ChangeProcedureCommand;
import by.metelski.webtask.command.impl.ChangeUserRoleAdminCommand;
import by.metelski.webtask.command.impl.ChangeUserRoleDoctorCommand;
import by.metelski.webtask.command.impl.ChangeUserRoleUserCommand;
import by.metelski.webtask.command.impl.ConfirmAppointmentCommand;
import by.metelski.webtask.command.impl.DeactivateProcedureCommand;
import by.metelski.webtask.command.impl.FindAllActiveSchedulesByDoctorCommand;
import by.metelski.webtask.command.impl.FindAllAppointmentsByDoctorIdCommand;
import by.metelski.webtask.command.impl.ToChangeAppointmentCommand;
import by.metelski.webtask.command.impl.ToChangePersonalInfoPageCommand;
import by.metelski.webtask.command.impl.ToChangeProcedureCommand;
import by.metelski.webtask.command.impl.ToChangeScheduleCommand;
import by.metelski.webtask.command.impl.FindAllProceduresCommand;
import by.metelski.webtask.command.impl.FindAllSchedulesByDoctorCommand;
import by.metelski.webtask.command.impl.FindAllSchedulesByIdCommand;
import by.metelski.webtask.command.impl.FindAllSchedulesCommand;
import by.metelski.webtask.command.impl.FindAllUsersCommand;
import by.metelski.webtask.command.impl.FindSchedulesByDoctorPaginationCommand;
import by.metelski.webtask.command.impl.FindSchedulesPaginationCommand;
import by.metelski.webtask.command.impl.FindTodaysAppointmentsCommand;
import by.metelski.webtask.command.impl.FindAllNewAppointmentsCommand;
import by.metelski.webtask.command.impl.FindAllAppointmentsByUserIdCommand;
import by.metelski.webtask.command.impl.FindUserByEmailCommand;

/**
 * Enum contains all types of commands
 * @author Yauhen Metelski
 *
 */
public enum CommandType {
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
	CHANGE_DOCTOR_SCHEDULE{
		{
		this.command=new ChangeDoctorScheduleCommand();	
		}
	},
	CHANGE_PERSONAL_INFO{
		{
			this.command= new ChangePersonalInfoCommand();
		}
	},
	CHANGE_PROCEDURE{
		{
			this.command= new ChangeProcedureCommand();
		}
	},
	CHANGE_USER_ROLE_ADMIN{
		{
		this.command=new ChangeUserRoleAdminCommand();	
		}
	},
	CHANGE_USER_ROLE_DOCTOR{
		{
		this.command=new ChangeUserRoleDoctorCommand();	
		}
	},
	CHANGE_USER_ROLE_USER{
		{
			this.command = new ChangeUserRoleUserCommand();
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
	FINISH_APPOINTMENT{
		{
		this.command= new FinishAppointmentCommand();	
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
	FIND_ALL_SCHEDULES{
		{
		this.command=new FindAllSchedulesCommand();	
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
	FIND_ALL_APPOINTMENTS_BY_DOCTOR_ID{
		{
			this.command=new FindAllAppointmentsByDoctorIdCommand();
		}
	},
	FIND_ALL_APPOINTMENTS_BY_USER_ID {
		{
			this.command = new FindAllAppointmentsByUserIdCommand();
		}
	},
	FIND_ALL_ACTIVE_SCHEDULES_BY_DOCTOR{
		{
			this.command= new FindAllActiveSchedulesByDoctorCommand();
		}
	},
	FIND_ALL_SCHEDULES_BY_DOCTOR {
		{
			this.command = new FindAllSchedulesByDoctorCommand();
		}
	},
	FIND_ALL_SCHEDULES_BY_ID {
		{
			this.command = new FindAllSchedulesByIdCommand();
		}
	},
	FIND_BY_EMAIL {
		{
			this.command = new FindUserByEmailCommand();
		}
	},

	FIND_BY_NAME {
		{
			this.command = new FindUsersByNameCommand();
		}
	},
	FIND_BY_SURNAME{
		{
			this.command = new FindUsersBySurnameCommand();
		}
	},
	FIND_SCHEDULES_BY_DOCTOR_PAGINATION{
		{
			this.command=new FindSchedulesByDoctorPaginationCommand();
		}
	},
	FIND_SCHEDULES_PAGINATION{
		{
			this.command=new FindSchedulesPaginationCommand();
		}
	},
	FIND_TODAYS_APPOINTMENTS{
		{
			this.command=new FindTodaysAppointmentsCommand();
		}
	},
	FIND_USERS_PAGINATION{
		{
		this.command=new FindUsersPaginationCommand();	
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
			this.command = new SendEmailCommand();
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
	TO_CHANGE_PERSONAL_INFO{
		{
			this.command= new ToChangePersonalInfoPageCommand();
		}
	},
	TO_CHANGE_PROCEDURE{
		{
			this.command=new ToChangeProcedureCommand();
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

	/**
	 * @return {@link Command}
	 */
	public Command getCurrentCommand() {
		return command;
	}
}
