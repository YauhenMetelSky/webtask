package by.metelski.webtask.command;

/**
 * Contain all page paths
 * @author Yauhen Metelski
 *
 */
public final class PagePath {
	public static final String ABOUT = "/jsp/about.jsp";
	public static final String ADD_PROCEDURE = "/jsp/addprocedure.jsp";
	public static final String ADMIN = "/jsp/admin.jsp";
	public static final String CONTACT = "/jsp/contact.jsp";
	public static final String CHANGE_APPOINTMENT = "/jsp/changeapp.jsp";
	public static final String CHANGE_PERSONAL_INFO = "/jsp/changepersonalinfo.jsp";
	public static final String CHANGE_PROCEDURE = "/jsp/changeprocedure.jsp";
	public static final String CHANGE_SCHEDULE = "/jsp/changeschedule.jsp";
	public static final String DOCTOR = "/jsp/doctor.jsp";
	public static final String ERROR = "/jsp/error.jsp";
	public static final String MAIN = "/jsp/main.jsp";
	public static final String SERVICES = "/jsp/services.jsp";
	public static final String SIGN_IN = "/jsp/signin.jsp";
	public static final String SIGN_UP = "/jsp/signup.jsp";
	public static final String USER = "/jsp/user.jsp";
	
	public static final String TO_ABOUT_PAGE="/controller?command=to_about";
	public static final String TO_ADD_PROCEDURE_PAGE="/controller?command=to_add_procedure";
	public static final String TO_CHANGE_APPOINTMENT_PAGE="/controller?command=to_change_appointment";
	public static final String TO_CHANGE_PROCEDURE_PAGE="/controller?command=to_change_procedure";
	public static final String TO_CHANGE_PERSONAL_INFO_PAGE="/controller?command=to_change_personal_info";
	public static final String TO_CHANGE_SCHEDULE_PAGE="/controller?command=to_change_schedule";
	public static final String TO_CONTACT_PAGE="/controller?command=to_contact";
	public static final String TO_DOCTOR_PAGE="/controller?command=to_doctor";
	public static final String TO_MAIN_PAGE="/controller?command=to_main";
	public static final String TO_PERSONAL_PAGE="/controller?command=to_personal_page";
	public static final String TO_SERVICES_PAGE="/controller?command=to_services";
	public static final String TO_SIGN_IN_PAGE="/controller?command=to_sign_in";
	public static final String TO_SIGN_UP_PAGE="/controller?command=to_sign_up";

	private PagePath() {
	}
}
