package test.metelski.webtask.model.service.impl;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;
import java.util.Map;
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
	private DoctorSchedule schedule;
	private Map<String, String> data ;
	
    @BeforeTest
    public void setUp() {
    	  	dao = mock(ScheduleDaoImpl.class);
    	  	service = new ScheduleServiceImpl(dao);
//    	  	factory = mock(DoctorScheduleFactory.class);
//    	  	mockedStatic = Mockito.mockStatic(DoctorScheduleFactory.class); 
    	  	data = new HashMap<>();
    	  	data.put(ParameterAndAttribute.DOCTOR_ID, "1");
    	  	data.put(ParameterAndAttribute.START_TIME, "12:00:00");
    	  	data.put(ParameterAndAttribute.END_TIME, "14:00:00");
    	  	data.put(ParameterAndAttribute.DATE, "2021-09-06");
    	  	User user = new User.Builder()
    	  			.setUserID(1)
    	  			.build();
    	  	schedule = new DoctorSchedule.Builder()
    	  			.setDoctor(user)
    	  			.setStartTime(Time.valueOf("12:00:00"))
    	  			.setEndTime(Time.valueOf("14:00:00"))
    	  			.setDate(Date.valueOf("2021-09-06"))
     	  			.build();
    }
	
	@Test
	public void testAddSchedule() throws ServiceException, DaoException {
		boolean expectedResult = true;
		when(dao.addDoctorSchedule(schedule)).thenReturn(true);
		boolean actualResult =service.addDoctorSchedule(data);
		Assert.assertEquals(actualResult, expectedResult);
	}
}
