package test.metelski.webtask.model.service.impl;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;

import org.mockito.MockedStatic;
import org.mockito.Mockito;

import by.metelski.webtask.command.ParameterAndAttribute;
import by.metelski.webtask.entity.DoctorSchedule;
import by.metelski.webtask.entity.User;
import by.metelski.webtask.exception.DaoException;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.dao.ScheduleDao;
import by.metelski.webtask.model.dao.impl.ScheduleDaoImpl;
import by.metelski.webtask.model.service.ScheduleService;
import by.metelski.webtask.model.service.impl.ScheduleServiceImpl;

public class ScheduleServiceImplTest extends Assert {
	private ScheduleDao dao;
	private ScheduleService service;
	//FIXME TEST delete or new one
//	private DoctorScheduleFactory factory;
//	private MockedStatic<DoctorScheduleFactory> mockedStatic;
	private DoctorSchedule schedule1;
	private DoctorSchedule schedule2;
	private DoctorSchedule scheduleFromDb;
	private DoctorSchedule scheduleForChange;
	private User doctor1;
	private User doctor2;
	private Map<String, String> data ;
	List<DoctorSchedule> schedulesListDoctor1=new ArrayList<>();
	
    @BeforeTest
    public void setUp() {
    	  	dao = mock(ScheduleDaoImpl.class);
    	  	service = new ScheduleServiceImpl(dao);
//    	  	factory = mock(DoctorScheduleFactory.class);
//    	  	mockedStatic = Mockito.mockStatic(DoctorScheduleFactory.class); 
    	  	data = new HashMap<>();
    	  	data.put(ParameterAndAttribute.DOCTOR_SCHEDULE_ID, "3");
    	  	data.put(ParameterAndAttribute.DOCTOR_ID, "1");
    	  	data.put(ParameterAndAttribute.START_TIME, "12:00:00");
    	  	data.put(ParameterAndAttribute.END_TIME, "14:00:00");
    	  	data.put(ParameterAndAttribute.DATE, "2021-09-06");
    	  	doctor1 = new User.Builder()
    	  			.setUserID(1)
    	  			.build();
    	  	schedule1 = new DoctorSchedule.Builder()
    	  			.setDoctor(doctor1)
    	  			.setStartTime(Time.valueOf("12:00:00"))
    	  			.setEndTime(Time.valueOf("14:00:00"))
    	  			.setDate(Date.valueOf("2021-09-06"))
     	  			.build();
    		schedule2 = new DoctorSchedule.Builder()
    	  			.setDoctor(doctor1)
    	  			.setStartTime(Time.valueOf("12:00:00"))
    	  			.setEndTime(Time.valueOf("14:00:00"))
    	  			.setDate(Date.valueOf("2021-09-07"))
     	  			.build();
    		scheduleFromDb = new DoctorSchedule.Builder()
    				.setId(1)
    	  			.setDoctor(doctor2)
    	  			.setStartTime(Time.valueOf("09:00:00"))
    	  			.setEndTime(Time.valueOf("18:00:00"))
    	  			.setDate(Date.valueOf("2021-09-12"))
     	  			.build();
    		scheduleForChange = new DoctorSchedule.Builder()
    				.setId(3)
    	  			.setStartTime(Time.valueOf("12:00:00"))
    	  			.setEndTime(Time.valueOf("14:00:00"))
    	  			.setDate(Date.valueOf("2021-09-06"))
     	  			.build();
    	  	doctor2 = new User.Builder()
    	  			.setUserID(2)
    	  			.build();
    	  	schedulesListDoctor1.add(schedule1);
    	  	schedulesListDoctor1.add(schedule2);
    }
	
	@Test
	public void testAddDoctorSchedule() throws ServiceException, DaoException {
		boolean expectedResult = true;
		when(dao.addDoctorSchedule(schedule1)).thenReturn(true);
		boolean actualResult =service.addDoctorSchedule(data);
		Assert.assertEquals(actualResult, expectedResult);
	}
	@Test
	public void testFindAllSchedulesByDoctorId() throws DaoException, ServiceException {
		List<DoctorSchedule> expectedResult = schedulesListDoctor1;
		when(dao.findAllSchedulesByDoctor(doctor1)).thenReturn(schedulesListDoctor1);
		List<DoctorSchedule> actualResult = service.findAllSchedulesByDoctorId(1);
		Assert.assertEquals(actualResult, expectedResult);
		
	}
	@Test
	public void testFindAllActiveSchedulesByDoctor() throws DaoException, ServiceException {
		List<DoctorSchedule> expectedResult = schedulesListDoctor1;
		when(dao.findAllSchedulesByDoctor(doctor1)).thenReturn(schedulesListDoctor1);
		List<DoctorSchedule> actualResult = service.findAllSchedulesByDoctorId(1);
		Assert.assertEquals(actualResult, expectedResult);
		
	}
	@Test
	public void testFindScheduleById() throws ServiceException, DaoException {
		Optional<DoctorSchedule> expectedResult=Optional.of(scheduleFromDb);
		when(dao.findScheduleById(1)).thenReturn(expectedResult);
		Optional<DoctorSchedule> actualResult = service.findScheduleById(1);
		assertEquals(actualResult, expectedResult);		
	}
	@Test
	public void testFindScheduleByDateAndDoctor() throws DaoException, ServiceException {
		Optional<DoctorSchedule> expectedResult = Optional.of(scheduleFromDb);
		when(dao.findScheduleByDateAndDoctor(Date.valueOf("2021-09-12"), 2)).thenReturn(Optional.of(scheduleFromDb));
		Optional<DoctorSchedule>actualResult=service.findScheduleByDateAndDoctor(Date.valueOf("2021-09-12"), 2);
		assertEquals(actualResult, expectedResult);
	}
	@Test
	public void testChangeDoctorSchedule() throws DaoException, ServiceException{
		boolean expectedResult = true;
		when(dao.changeDoctorSchedule(scheduleForChange)).thenReturn(true);
		boolean actualResult = service.changeDoctorSchedule(data);
		assertEquals(actualResult, expectedResult);
	}
	@Test
	public void testChangeFieldIsActive() throws DaoException, ServiceException {
		boolean expectedResult=true;
		when(dao.changeFieldIsActive(1, true)).thenReturn(true);
		boolean actualREsult = service.changeFieldIsActive(1, true);
		assertEquals(actualREsult, expectedResult);
	}
	@Test
	public void testFindAllSchedules() throws DaoException, ServiceException {
		List<DoctorSchedule> expectedResult = schedulesListDoctor1;
		when(dao.findAllSchedules()).thenReturn(schedulesListDoctor1);
		List<DoctorSchedule> actualResult = service.findAllSchedules();
		assertEquals(actualResult, expectedResult);
	}
}
