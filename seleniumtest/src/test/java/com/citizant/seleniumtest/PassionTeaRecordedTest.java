package com.citizant.seleniumtest;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * 
 * Example of a recorded script
 *
 */
public class PassionTeaRecordedTest {
	private WebDriver driver;
	private Map<String, Object> vars;
	JavascriptExecutor js;

	@Before
	public void setUp() {
		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void checkout() {
		// Test name: checkout
		// Step # | name | target | value
		// 1 | open | / |
		driver.get("http://www.practiceselenium.com/");
		// 2 | setWindowSize | 1936x1056 |
		driver.manage().window().setSize(new Dimension(1936, 1056));
		// 3 | click | css=#wsb-element-00000000-0000-0000-0000-000450914895 img |
		driver.findElement(By.cssSelector("#wsb-element-00000000-0000-0000-0000-000450914895 img")).click();
		// 4 | click | css=#wsb-button-00000000-0000-0000-0000-000451955160 >
		// .button-content |
		driver.findElement(By.cssSelector("#wsb-button-00000000-0000-0000-0000-000451955160 > .button-content"))
				.click();
		// 5 | click | id=email |
		driver.findElement(By.id("email")).click();
		// 6 | type | id=email | hello@word.com
		driver.findElement(By.id("email")).sendKeys("hello@word.com");
		// 7 | click | id=name |
		driver.findElement(By.id("name")).click();
		// 8 | type | id=name | Hello Word
		driver.findElement(By.id("name")).sendKeys("Hello Word");
		// 9 | type | id=address | 123 Elm St, Chantilly, VA 20151
		driver.findElement(By.id("address")).sendKeys("123 Elm St, Chantilly, VA 20151");
		// 10 | click | id=card_type |
		driver.findElement(By.id("card_type")).click();
		// 11 | select | id=card_type | label=Visa
		{
			WebElement dropdown = driver.findElement(By.id("card_type"));
			dropdown.findElement(By.xpath("//option[. = 'Visa']")).click();
		}
		// 12 | click | id=card_type |
		driver.findElement(By.id("card_type")).click();
		// 13 | click | id=card_number |
		driver.findElement(By.id("card_number")).click();
		// 14 | type | id=card_number | 1234 5678 9011 1123
		driver.findElement(By.id("card_number")).sendKeys("1234 5678 9011 1123");
		// 15 | click | id=cardholder_name |
		driver.findElement(By.id("cardholder_name")).click();
		// 16 | type | id=cardholder_name | Hello World
		driver.findElement(By.id("cardholder_name")).sendKeys("Hello World");
		// 17 | type | id=verification_code | 123
		driver.findElement(By.id("verification_code")).sendKeys("123");
		// 18 | click | css=.btn-primary |
		driver.findElement(By.cssSelector(".btn-primary")).click();
	}
}
