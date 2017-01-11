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
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * Selenium Test Case
 */
public class HomePageTest {
	
	  private String baseUrl;
	  private WebDriver driver;
	  private ScreenshotHelper screenshotHelper;
	  private WebDriverWait driverWait;
	  
	  private WebElement addUserButton;
	  private WebElement emailField;
	  private WebElement firstNameField;
	  private WebElement lastNameField;
	  private WebElement addButton;
	  
	  @Before
	  public void openBrowser() {
	    baseUrl = System.getProperty("webdriver.base.url");
	    if(baseUrl == null) {
	    	baseUrl = "http://localhost:8080/usermanager";
	    }
	    driver = new ChromeDriver();
	    driverWait = new WebDriverWait(driver, 30);
	    driver.get(baseUrl);
	    screenshotHelper = new ScreenshotHelper();
	  }
	  
	  @After
	  public void saveScreenshotAndCloseBrowser() throws IOException {
	    screenshotHelper.saveScreenshot("screenshot.png");
	    driver.quit();
	  }
	  
	  @Test
	  public void pageTitleAfterSearchShouldBeginWithDrupal() throws IOException {
		System.out.println(driver.getTitle());
	    assertEquals("The page title should equal Google at the start of the test.", "Echoweb", driver.getTitle());
	    
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
	    		Thread.sleep(5000);
	    	} catch (Exception e){
	    		
	    	}
	    	addButton = driver.findElement(By.id("btnSubmit"));
	    	addButton.click();  
	    	try{
	    		Thread.sleep(5000);
	    	} catch (Exception e){
	    		
	    	}
	    	driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("d_"+"abc@xyz.com")));
	    	
	    	WebElement deleteButton = driver.findElement(By.id("d_"+"abc@xyz.com"));
	    	
	    	deleteButton.click();
	    	
	    	try{
	    		Thread.sleep(5000);
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
