package com.baublebar.testcases;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.baublebar.pages.BaublebarPage;
import com.baublebar.util.Constants;
import com.baublebar.util.CustomLogger;
import com.baublebar.util.Xls_Reader;

/**
 * Author - Maitri Acharya
 */
public class TestBase {

	public static Logger APPLICATION_LOGS = null;
	public static Properties CONFIG=null;
	public static WebDriver driver=null;

	public static boolean isLoggedIn=false;
	
	Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir")+"/src/com/baublebar/data/TestCases.xlsx");
	//public Xls_Reader result_xls = new Xls_Reader(System.getProperty("user.dir")+"/results/results.xlsx");

//	public CustomLogger logger;
	BaublebarPage landingPage =null;
	
	public void initConfigurations(){
		if(CONFIG==null){
		// Logging
		//logger = new CustomLogger();
		APPLICATION_LOGS = Logger.getLogger("devpinoyLogger");
		// config.prop
		 CONFIG = new Properties();
		try {
			FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"/src/com/baublebar/config/config.properties");
			CONFIG.load(fs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
	//Initializes/loads  the driver based on type of browser defined in config file
	public void initDriver(){
		if(driver==null){
			if(CONFIG.getProperty("browser").equals("Mozilla"))
				driver=new FirefoxDriver();
			else if(CONFIG.getProperty("browser").equals("IE")){
				String iePath = System.getProperty("user.dir")+"/IEDriverServer.exe";
				System.out.println(iePath);
				File file = new File(iePath);
				System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
				DesiredCapabilities capabilities = new DesiredCapabilities();
				//capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				//capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				driver = new InternetExplorerDriver(capabilities);
			} else if(CONFIG.getProperty("browser").equals("Chrome")){
				String path = System.getProperty("user.dir")+"/chromedriver";
				System.out.println(path);
				File file = new File(path);
				System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
				DesiredCapabilities capabilities = new DesiredCapabilities();
				driver=new ChromeDriver(capabilities);
			}
		}
		driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}	
	
	public BaublebarPage getLandingPage(){
		if(landingPage==null){
			landingPage = PageFactory.initElements(driver, BaublebarPage.class);
		}
		return landingPage;
	}
	
	//Set the focus on for given window
	//@param	windowTitle
	private void handleMultipleWindows(String windowTitle) {
        Set<String> windows = driver.getWindowHandles();

        for (String window : windows) {
            driver.switchTo().window(window);
            if (driver.getTitle().contains(windowTitle)) {
                return;
            }
        }
    }
	
	//Wait for element to appear/load on page
	public WebElement waitForElement(String xPath) throws InterruptedException{ // Wait function to wait for element    
        for (int second = 0; ; second++){
            if (second >= 60) Assert.fail("timeout");
	            try {
	            	WebElement webElement = driver.findElement(By.xpath(xPath));
	                if (webElement !=null) 
	                    return webElement;
	                }
	                catch (Exception e)   {
	                	Thread.sleep(1000L);	
	             }
                Thread.sleep(1000);
             }  
    }
	
	//Closes the browser
	@AfterSuite
	public void quitDriver(){
		driver.quit();
		driver=null;
	}	
}