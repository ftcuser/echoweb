package echoweb.ui;

import static org.junit.Assert.fail;

/**
 * An example of a test that was recorded and then modified to run under Chrome. Line with //-- were commented out. Added line are indicated with //++ comment
 */
//-- unneeded import
//import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
//-- unneeded import
//import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//-- unneeded import
//import org.openqa.selenium.firefox.FirefoxDriver;
//-- unneeded import
//import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageRecordedIT {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = new ChromeDriver();
		baseUrl = "http://localhost:8080/usermanager";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testHomePageRecorded() throws Exception {
		// -- driver.get(baseUrl + "/usermanager/#!/employee");
		// ++ removed extra bit of URL
		driver.get(baseUrl + "/#!/employee");
		driver.findElement(By.id("btnAddUser")).click();
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("abc@xyz.com");
		driver.findElement(By.id("firstName")).clear();
		driver.findElement(By.id("firstName")).sendKeys("Aaron");
		driver.findElement(By.id("lastName")).clear();
		driver.findElement(By.id("lastName")).sendKeys("Smith");
		driver.findElement(By.id("btnSubmit")).click();
		// -- for (int second = 0;; second++) {
		// -- if (second >= 60)
		// -- fail("timeout");
		// -- try {
		// -- if (isElementPresent(By.id("d_abc@xyz.com")))
		// -- break;
		// -- } catch (Exception e) {
		// -- }
		// -- Thread.sleep(1000);
		// -- }

		// -- driver.findElement(By.id("d_abc@xyz.com")).click();

		// ++ better way to do
		WebElement delBtn = getWhenVisible(By.id("d_abc@xyz.com"), 60);
		delBtn.click();

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

	// ++ added better wait for element
	public WebElement getWhenVisible(By locator, int timeout) {
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element;
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
