package com.citizant.seleniumtest;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * This is a simple Selenium test case that hit http://www.practiceselenium.com
 * 
 */

public class PassionTeaTest {

	private WebDriver driver;
	private ScreenshotHelper screenshotHelper;
	private String baseUrl = "http://www.practiceselenium.com/";

	@Before
	public void openBrowser() {
		String base = System.getProperty("app.baseurl");
		if (base == null) {
			base = baseUrl;
		}

		// Use local Chrome Browser for testing
		driver = new ChromeDriver();
		// set timeout to 30 seconds
		new WebDriverWait(driver, 30);
		driver.get(base);
		screenshotHelper = new ScreenshotHelper();
	}

	@After
	public void saveScreenshotAndCloseBrowser() throws IOException {
		screenshotHelper.saveScreenshot("screenshot.png");
		driver.quit();
	}

	/*
	 * This is the test case for the application
	 */
	@Test
	public void pageTitle() throws IOException {
		System.out.println("Page Title : " + driver.getTitle());
		assertEquals("The page title should equal Welcome at the start of the test.", "Welcome", driver.getTitle());

		WebElement herbalTeaLink = driver.findElement(By.id("wsb-button-00000000-0000-0000-0000-000450914890"));
		delay(2);
		// click on the link
		herbalTeaLink.click();

		// Check if the tea element is there
		WebElement greenTeaCheckoutBtn = driver.findElement(By.id("wsb-button-00000000-0000-0000-0000-000451955160"));
		// Click on checkout link
		delay(5);
		greenTeaCheckoutBtn.click();

		// Fill the form fields
		WebElement emailTxt = driver.findElement(By.id("email"));
		emailTxt.sendKeys("hello@word.com");
		WebElement nameTxt = driver.findElement(By.id("name"));
		nameTxt.sendKeys("Hello World");
		WebElement addressTxt = driver.findElement(By.id("address"));
		addressTxt.sendKeys("123 Elm St, Chantilly, VA 20151");
		WebElement cardTypeTxt = driver.findElement(By.id("card_type"));
		cardTypeTxt.sendKeys("VISA");
		WebElement cardNumberTxt = driver.findElement(By.id("card_number"));
		cardNumberTxt.sendKeys("1234 5678 9011 1123");
		WebElement cardNameTxt = driver.findElement(By.id("cardholder_name"));
		cardNameTxt.sendKeys("Hello World");
		WebElement cardVerTxt = driver.findElement(By.id("verification_code"));
		cardVerTxt.sendKeys("123");
		delay(5);
		// Submit Form
		WebElement submitBtn = driver.findElement(By.className("btn-primary"));
		submitBtn.click();
		delay(5);
		// Back to Home Page
		WebElement homeLink = driver.findElement(By.linkText("Welcome"));
		homeLink.click();

		delay(10);
	}

	private class ScreenshotHelper {
		public void saveScreenshot(String screenshotFileName) throws IOException {
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(screenshotFileName));
		}
	}

	private void delay(long seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (Exception e) {

		}
	}
}
