package test.metelski.webtask.model.service.impl;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.mockito.MockedStatic;
import org.mockito.Mockito;

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
	//FIXME delete or new one
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
//    	  	schedule = new DoctorSchedule();//FIXME doesn't work after changes in entity doctorSchedule
    }
	
	@Test
	public void testAddSchedule() throws ServiceException, DaoException {
		boolean expectedResult = true;
//		mockedStatic.when(DoctorScheduleFactory::getInstance).thenReturn(factory);	
//		when(factory.build(data)).thenReturn(schedule);
		when(dao.addDoctorSchedule(schedule)).thenReturn(true);
		boolean actualResult =service.addDoctorSchedule(data);
		Assert.assertEquals(actualResult, expectedResult);
	}
}
