package by.metelski.webtask.command;

import by.metelski.webtask.command.async.FindAllActiveSchedulesByDoctorIdAsyncCommand;
import by.metelski.webtask.command.async.FindAllAppointmentsByDoctorIDAndDateAsyncCommand;
import by.metelski.webtask.command.async.FindTimeIntervalsByScheduleIdAsyncCommand;
import by.metelski.webtask.command.async.UnknownCommandAsync;

/**
 * Enum contains all types of asynchronous commands
 * @author Yauhen Metelski
 *
 */
public enum CommandTypeAsync {
	
	FIND_ALL_APPOINTMENTS_BY_DOCTOR_AND_DATE_ASYNC{
		{
			this.command=new FindAllAppointmentsByDoctorIDAndDateAsyncCommand();
		}
	},
	FIND_ALL_ACTIVE_SCHEDULES_BY_DOCTOR_ASYNC{
		{
			this.command= new FindAllActiveSchedulesByDoctorIdAsyncCommand();
		}
	},

	FIND_TIME_INTERVALS_BY_SCHEDULE_ID_ASYNC {
		{
			this.command = new FindTimeIntervalsByScheduleIdAsyncCommand();
		}
	},
	UNKNOWN_COMMAND{
		{
			this.command = new UnknownCommandAsync();
		}
	};
					
	CommandAsync command;

	/**
	 * @return {@link CommandAsync}
	 */
	public CommandAsync getCurrentCommand() {
		return command;
	}
}