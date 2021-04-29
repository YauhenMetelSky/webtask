package test.metelski.webtask.command.impl;

import static org.mockito.Mockito.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import by.metelski.webtask.command.Command;
import by.metelski.webtask.command.PagePath;
import by.metelski.webtask.command.RequestParameter;
import by.metelski.webtask.command.Router;
import by.metelski.webtask.command.SessionAttribute;
import by.metelski.webtask.command.impl.FindUsersByNameCommand;
import by.metelski.webtask.exception.ServiceException;
import by.metelski.webtask.model.service.UserService;

public class FindUsersByNameTest extends Assert{
//	private List<User> users;
	private String userName;

    @BeforeTest
    public void setUp() {
    	userName="Ivan";   	
    }
    
	@Test
	public void testExecute() throws ServiceException {
		Router expectedResult = new Router(PagePath.MAIN);
		UserService service = mock(UserService.class);
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession session=mock(HttpSession.class);
		when(session.getAttribute(SessionAttribute.CURRENT_PAGE)).thenReturn(PagePath.MAIN);
		when(request.getParameter(RequestParameter.USER_NAME)).thenReturn(userName);
		when(request.getSession()).thenReturn(session);
		when(service.findUsersByName(userName)).thenReturn(null);
		Command command = new FindUsersByNameCommand();
		Router actualResult =command.execute(request);
		assertEquals(actualResult, expectedResult);
	}	
}
