package echoweb.ui;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * Selenium Test Case
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
	  
	  // private String seleniumHub = "http://192.168.60.135:4444/wd/hub";
	  // private String baseUrl =  "http://192.168.60.135:8080/usermanager";
	  private String seleniumHub = "http://localhost:4444/wd/hub";
	  private String baseUrl =  "http://172.18.0.119:9090/usermanager";
	  
	  @Before
	  public void openBrowser() {
	  
	   // driver = new ChromeDriver();
	    //driver = new HtmlUnitDriver();
	    //((HtmlUnitDriver)driver).setJavascriptEnabled(true);

		  
		//try to get Selenium HUB and bas test URL from JVM parameters
		String hub =  System.getProperty("selenium.hub");
		if(hub == null) {
			hub  = seleniumHub;
		}
		
		String base = System.getProperty("app.baseurl");
		if(base == null) {
			base = baseUrl;
		}
		
		System.out.println("The app URL : "  + base);
		  
	    URL hubUrl = null;
	    try{
	    	hubUrl = new URL(hub);
	    }catch(Exception e){
	    	
	    }
	    
	    Capabilities cap = DesiredCapabilities.chrome();
	    driver = new RemoteWebDriver(hubUrl, cap);
	    driverWait = new WebDriverWait(driver, 30);
	    driver.get(base);
	   // screenshotHelper = new ScreenshotHelper();
	  }
	  
	  @After
	  public void saveScreenshotAndCloseBrowser() throws IOException {
	   // screenshotHelper.saveScreenshot("screenshot.png");
	    driver.quit();
	  }
	  
	  @Test
	  public void pageTitleAfterSearchShouldBeginWithDrupal() throws IOException {
		System.out.println("Page Title : " + driver.getTitle());
	    assertEquals("The page title should equal user manager at the start of the test.", "User Manager", driver.getTitle());
	    
	    addUserButton = driver.findElement(By.id("btnAddUser"));
	    
	    if (addUserButton.isDisplayed()) {
	    	System.out.println("Add User button is displayed");
	    	addUserButton.click();
	    	
	    	driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
	    	emailField = driver.findElement(By.id("email"));
	    	emailField.sendKeys("abc@xyz.com");
	    	
	    	firstNameField = driver.findElement(By.id("firstName"));
	    	firstNameField.sendKeys("Aaron");
	    	
	    	lastNameField = driver.findElement(By.id("lastName"));
	    	lastNameField.sendKeys("Smith");
	    	try{
	    		Thread.sleep(2000);
	    	} catch (Exception e){
	    		
	    	}
	    	addButton = driver.findElement(By.id("btnSubmit"));
	    	addButton.click();  
	    	try{
	    		Thread.sleep(2000);
	    	} catch (Exception e){
	    		
	    	}
	    	driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("d_"+"abc@xyz.com")));
	    	
	    	WebElement deleteButton = driver.findElement(By.id("d_"+"abc@xyz.com"));
	    	
	    	deleteButton.click();
	    	
	    	try{
	    		Thread.sleep(2000);
	    	} catch (Exception e){
	    		
	    	}
	    }
	    /*
	    WebElement searchField = driver.findElement(By.name("q"));
	    searchField.sendKeys("Drupal!");
	    searchField.submit();
	    */
	  }
	  
	  private class ScreenshotHelper {
	  
	    public void saveScreenshot(String screenshotFileName) throws IOException {
	      File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	      FileUtils.copyFile(screenshot, new File(screenshotFileName));
	    }
	  }

}
