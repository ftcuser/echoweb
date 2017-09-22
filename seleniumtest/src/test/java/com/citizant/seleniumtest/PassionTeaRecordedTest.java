package com.citizant.seleniumtest;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * 
 * Example of a recorded script
 *
 */
public class PassionTeaRecordedTest {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		// -- driver = new FirefoxDriver();
		// ++ changed to Chrome
		driver = new ChromeDriver();
		baseUrl = "http://www.practiceselenium.com";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testPassionTeaTestRecorded() throws Exception {
		driver.get(baseUrl + "/");
		driver.findElement(By.cssSelector(
		 		"#wsb-button-00000000-0000-0000-0000-000450914890 > span.button-content.wsb-button-content")).click();
		driver.findElement(By.cssSelector("span.button-content.wsb-button-content")).click();
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("hello@word.com");
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys("Hello World");
		driver.findElement(By.id("address")).clear();
		driver.findElement(By.id("address")).sendKeys("123 Elm St, Chantilly, VA 20151");
		new Select(driver.findElement(By.id("card_type"))).selectByVisibleText("Visa");
		driver.findElement(By.id("card_number")).clear();
		driver.findElement(By.id("card_number")).sendKeys("1234 5678 9011 1123");
		driver.findElement(By.id("cardholder_name")).clear();
		driver.findElement(By.id("cardholder_name")).sendKeys("Hello World");
		driver.findElement(By.id("verification_code")).clear();
		driver.findElement(By.id("verification_code")).sendKeys("123");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		driver.findElement(By.linkText("Welcome")).click();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
