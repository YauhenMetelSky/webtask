package test.metelski.webtask.validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import by.metelski.webtask.validator.DataValidator;

public class DataValidatorTest extends Assert {

	@Test(dataProvider = "isOnlyNumbersData")
	public void testIsOnlyNumbers(String text,boolean expectedResult) {
		boolean actualResult = DataValidator.isOnlyNumbers(text);
		assertEquals(actualResult, expectedResult);
	}
	
	@Test(dataProvider = "isTimeFormatValidData")
	public void testIsTimeFormatValid(String text,boolean expectedResult) {
		boolean actualResult = DataValidator.isTimeFormatValid(text);
		assertEquals(actualResult, expectedResult);
	}
	@Test(dataProvider = "isDateFormatValidData")
	public void testIsDateFormatValid(String text,boolean expectedResult) {
		boolean actualResult = DataValidator.isDateFormatValid(text);
		assertEquals(actualResult, expectedResult);
	}
	@DataProvider
	public Object[][] isOnlyNumbersData() {
		return new Object[][] { 
			{ "12345", true }, 
			{ "025", true },
			{ "1234567890", true },
			{ "12345678901", false },
			{ "25 24", false },
			{ "f275", false },
			{ "127i27", false },
			{ "25,4", false },
			{"35:4",false},
			{"",false},
			{" ",false},
			{ "<Notnumber>", false },
			{ "345.14", false }, 
			{ "45.,", false } 
			};
	}
	@DataProvider
	public Object[][] isTimeFormatValidData() {
		return new Object[][] { 
			{ "01:15:00", true }, 
			{ "15:00:00", true },
			{ "150000", false },
			{ "12345678901", false },
			{ "24 24", false },
			{ "f275", false },
			{ "127i27", false },
			{ "25,4", false },
			{"35:4",false},
			{"",false},
			{" ",false},
			{ "<Notnumber>", false },
			{ "345.14", false }, 
			{ "45.,", false } 
			};
	}
	@DataProvider
	public Object[][] isDateFormatValidData() {
		return new Object[][] { 
			{ "2021-06-03", true }, 
			{ "2021-06", false },
			{ "2021-0603", false },
			{ "12345678901", false },
			{ "24 24", false },
			{ "f275", false },
			{ "127i27", false },
			{ "25,4", false },
			{"35:4",false},
			{"",false},
			{" ",false},
			{ "<Notnumber>", false },
			{ "345.14", false }, 
			{ "45.,", false } 
			};
	}
}
