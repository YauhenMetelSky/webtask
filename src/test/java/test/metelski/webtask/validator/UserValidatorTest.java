package test.metelski.webtask.validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import by.metelski.webtask.validator.UserValidator;

public class UserValidatorTest extends Assert {

	@Test(dataProvider = "isValidNameData")
	public void testIsValidName(String name, boolean expectedResult) {
		boolean actualResult = UserValidator.isValidName(name);
		assertEquals(actualResult, expectedResult);
	}
	
	@Test
	public void testNullIsValidName() {
		boolean expectedResult = false;
		boolean actualResult = UserValidator.isValidName(null);
		assertEquals(actualResult, expectedResult);
	}
	
	@Test(dataProvider="isValidEmailData")
	public void testIsValidEmail(String email,boolean expectedResult) {
		boolean actualResult = UserValidator.isValidEmail(email);
		assertEquals(actualResult, expectedResult);		
	}
	@Test(dataProvider="isValidPasswordData")
	public void testIsValidPassword(String password, boolean expectedResult) {
		boolean actualResult = UserValidator.isValidPassword(password);
		assertEquals(actualResult, expectedResult);
	}
	
	@DataProvider
	public Object[][] isValidEmailData(){
		return new Object[][] {
			{"Ivanov@gmail.com",true},
			{"Ivanov1981@tut.by",true},
			{"ivanov.google.com",false},
			{"ivanov@tutby",false},
			{"@.a",false},
		};
	}

	@DataProvider
	public Object[][] isValidNameData() {
		return new Object[][] { 
			{ "Ivan", true }, 
			{ " Ivan", true },
			{ "Ivan ", true },
			{ "Ольга", true },
			{"Аль Хамауи",true},
			{"Светлана-Ирина",true},
			{"Dana-Victoria",true},
			{ "LongStrangeName", true },
			{"ThisNameIsToLongForRealMen",false},
			{"",false},
			{" ",false},
			{ "<Notname>", false },
			{ "Number345", false }, 
			{ "punct.,punct", false } 
			};
	}
	@DataProvider
	public Object[][] isValidPasswordData(){
		return new Object[][] {
			{"1",true},
			{"123456789123456",true},
			{"1234567891234567",false},
			{"Ё",false},
			{"",false},
			{"12 912",false},
			{"  ",false},
			{"a79866B978c",true}
		};
	}
}
