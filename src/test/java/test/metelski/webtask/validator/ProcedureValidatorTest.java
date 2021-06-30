package test.metelski.webtask.validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import by.metelski.webtask.validator.ProcedureValidator;

public class ProcedureValidatorTest extends Assert {

	@Test(dataProvider = "isValidNameData")
	public void testIsValidName(String name, boolean expectedResult) {
		boolean actualResult = ProcedureValidator.isValidName(name);
		assertEquals(actualResult, expectedResult);
	}
	@Test(dataProvider = "isOnlyNumbersData")
	public void testIsOnlyNumbers(String text,boolean expectedResult) {
		boolean actualResult = ProcedureValidator.isOnlyNumbers(text);
		assertEquals(actualResult, expectedResult);
	}
	@Test(dataProvider = "isValidImageNameData")
	public void testIsValidImageName(String text,boolean expectedResult) {
		boolean actualResult = ProcedureValidator.isValidImageName(text);
		assertEquals(actualResult, expectedResult);
	}
	@Test(dataProvider = "isValidPriceData")
	public void testIsValidPrice(String text,boolean expectedResult) {
		boolean actualResult = ProcedureValidator.isValidPrice(text);
		assertEquals(actualResult, expectedResult);
	}
	@Test(dataProvider = "isValidDescriptionData")
	public void testIsValidDescription(String text,boolean expectedResult) {
		boolean actualResult = ProcedureValidator.isValidDescription(text);
		assertEquals(actualResult, expectedResult);
	}
	@DataProvider
	public Object[][] isValidNameData() {
		return new Object[][] { 
			{ "Super procedure", true }, 
			{ "Магнолия", true },
			{ "Three words name", true },
			{ "Very very very very very many words", true },
			{ "Very very very very very very very very too many words", false },
			{ "Очень очень очень очень много слов", true },
			{"Procedure-procedure",true},
			{"Процедура",true},
			{"",false},
			{" ",false},
			{ "<Notname>", false },
			{ "Number345", false }, 
			{ "punct.,punct", false } 
			};
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
	public Object[][] isValidImageNameData() {
		return new Object[][] { 
			{ "Image.jpg", true }, 
			{ "image.jpg", true },
			{ "image_image.jpg", true },
			{ "iMAGe.jpg", true },
			{ "image.gpg", false },
			{ "картинка.jpg", true },
			{"Картинка.jpg",true},
			{"",false},
			{" ",false},
			{ "<Notimage>", false },
			{ "Number345", false }, 
			{ "punct.,punct", false } 
			};
	}
	@DataProvider
	public Object[][] isValidPriceData() {
		return new Object[][] { 
			{ "11.15", true }, 
			{ "123.00", true },
			{ "1.12", true },
			{ "12345678.01", true },
			{ "123456789.12", false },
			{ "1", false },
			{"12.123",false},
			{"12,12",false},
			{"",false},
			{" ",false},
			{ "<Notprice>", false },
			{ "Number345", false }, 
			{ "punct.,punct", false } 
			};
	}
	@DataProvider
	public Object[][] isValidDescriptionData() {
		return new Object[][] { 
			{ "Super procedure description", true }, 
			{ "description ... with , and.", true },
			{ "описание на русском языке .", true },
			{ "Good", true },
			{ "<", false },
			{ "<Very", false },
			{ "Try to hide<between words",false},
			{">",false},
			{"",false},
			{" ",false},
			{ "<Notname>", false },
			{ "Number345 >", false }, 
			};
	}
}
