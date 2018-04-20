package echoweb.ui;

import static org.junit.Assert.*;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * A Selenium Test Case for User Manager Application
 */
public class HomePageTest {

	private WebDriver driver;
	private ScreenshotHelper screenshotHelper;
	private WebDriverWait driverWait;

	private WebElement addUserButton;
	private WebElement emailField;
	private WebElement firstNameField;
	private WebElement lastNameField;
	private WebElement addButton;

	// The URL to the application
	private String baseUrl = "http://localhost:8080/usermanager";

	@Before
	public void openBrowser() {
		String base = System.getProperty("app.baseurl");
		if (base == null) {
			base = baseUrl;
		}

		// Use local Chrome Browser for testing
		// Create a Web driver to control the browser
		driver = new ChromeDriver();
		driverWait = new WebDriverWait(driver, 30);
		driver.get(base);
		
		//Create screenshot
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
	public void pageTitleAfterSearchShouldBeginWithDrupal() throws IOException {

		System.out.println("Page Title : " + driver.getTitle());

		//Step 1: Check the page title of the website
		assertEquals("The page title should equal user manager at the start of the test.", "User Manager",
				driver.getTitle());

		//Step 2: Find the "Add New User" Button
		addUserButton = driver.findElement(By.id("btnAddUser"));

		if (addUserButton.isDisplayed()) {
			System.out.println("Add User button is displayed");
			
			//Step 3: Click the Add new user Button
			addUserButton.click();

			//Step 4: Wait for the page load by check email input field
			driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
			
			//Step 5: Fill the new user form 
			
			//Step 5.1 Find the email input box.
			emailField = driver.findElement(By.id("email"));
			
			//Step 5.2 Set the email value.
			emailField.sendKeys("abc@xyz.com");

			//Step 5.3 Find the firstName field
			firstNameField = driver.findElement(By.id("firstName"));
			
			//Step 5.4 Set first name value
			firstNameField.sendKeys("Aaron");

			//Step 5.5 Find last name field
			lastNameField = driver.findElement(By.id("lastName"));
			
			//Step 5.6 Set last name value
			lastNameField.sendKeys("Smith");
			
			try {
				Thread.sleep(5000);
			} catch (Exception e) {

			}
			
			//Step 6: Find the submit button
			addButton = driver.findElement(By.id("btnSubmit"));
			
			//Step 7: Click the submit button
			addButton.click();
			try {
				Thread.sleep(5000);
			} catch (Exception e) {

			}
			
			//Step 8: Wait for page reload by checking the new email value.
			driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("d_" + "abc@xyz.com")));

			//Step 9: Find the delete button for the new record.
			WebElement deleteButton = driver.findElement(By.id("d_" + "abc@xyz.com"));

			//Step 10: Click on delete button to delete record.
			deleteButton.click();

			try {
				Thread.sleep(5000);
			} catch (Exception e) {

			}
			
			//Step 11: Check if the new record was deleted
			try {
				deleteButton = driver.findElement(By.id("d_" + "abc@xyz.com"));
				assertNotEquals("Failed to delete record", deleteButton,
					null);
			} catch (Exception e) {
				
			}
		}

	}

	private class ScreenshotHelper {

		public void saveScreenshot(String screenshotFileName) throws IOException {
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(screenshotFileName));
		}
	}

}
