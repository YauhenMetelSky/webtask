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
import by.metelski.webtask.entity.DoctorSchedule.Intervals;
import by.metelski.webtask.entity.DoctorScheduleFactory;
import by.metelski.webtask.exception.DaoException;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.dao.ScheduleDao;
import by.metelski.webtask.model.dao.impl.ScheduleDaoImpl;
import by.metelski.webtask.model.service.ScheduleService;
import by.metelski.webtask.model.service.impl.ScheduleServiceImpl;

public class ScheduleServiceImplTest extends Assert {
	private ScheduleDao dao;
	private ScheduleService service;
	private DoctorScheduleFactory factory;
	private MockedStatic<DoctorScheduleFactory> mockedStatic;
	private DoctorSchedule schedule;
	private Map<String, String> data ;
	SortedMap<Intervals,Boolean> scheduleData;
	
    @BeforeTest
    public void setUp() {
    	  	dao = mock(ScheduleDaoImpl.class);
    	  	service = new ScheduleServiceImpl(dao);
    	  	factory = mock(DoctorScheduleFactory.class);
    	  	mockedStatic = Mockito.mockStatic(DoctorScheduleFactory.class); 
    	  	data = new HashMap<>();
    	  	data.put("INTERVAL_09_00","true");
    	  	data.put("INTERVAL_09_30","true");
    	  	data.put("INTERVAL_10_00","false");
    	  	data.put("INTERVAL_10_30","false");
    	  	data.put("INTERVAL_11_00","false");
    	  	data.put("INTERVAL_11_30","true");
    	  	data.put("INTERVAL_12_00","true");
    	  	data.put("INTERVAL_12_30","true");
    	  	scheduleData = new TreeMap<>();
    	  	scheduleData.put(Intervals.INTERVAL_09_00, true);
    	  	scheduleData.put(Intervals.INTERVAL_09_30, true);
    	  	scheduleData.put(Intervals.INTERVAL_10_00, false);
    	  	scheduleData.put(Intervals.INTERVAL_10_30, false);
    	  	scheduleData.put(Intervals.INTERVAL_11_00, false);
    	  	scheduleData.put(Intervals.INTERVAL_11_30, true);
    	  	scheduleData.put(Intervals.INTERVAL_12_00, true);
    	  	scheduleData.put(Intervals.INTERVAL_12_30, true);
    	  	schedule = new DoctorSchedule(1, scheduleData);
    }
	
	@Test
	public void testAddSchedule() throws ServiceException, DaoException {
		boolean expectedResult = true;
		mockedStatic.when(DoctorScheduleFactory::getInstance).thenReturn(factory);	
		when(factory.build(data)).thenReturn(schedule);
		when(dao.addSchedule(schedule)).thenReturn(true);
		boolean actualResult =service.addSchedule(data);
		Assert.assertEquals(actualResult, expectedResult);
	}
}
