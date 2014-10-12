package com.baublebar.testcases;


import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.baublebar.pages.BaublebarPage;
import com.baublebar.util.TestUtil;


public class CreateAccountTest extends TestBase  {
	
	/*@Test
	public void createAccTest() throws Throwable{
		BaublebarPage landingPage = getLandingPage();
		landingPage.createAccount();
	}*/
	
		
	@Test(dataProvider="getCreateAccountData")
	public void createAccTest(Hashtable<String,String> data) throws Throwable {		
		APPLICATION_LOGS.debug("Executing the Login test");		
		if(!TestUtil.isExecutable("CreateAccountTest", xls) || data.get("Runmode").equals("N"))throw new SkipException("Skipping the test");		
		BaublebarPage landingPage = getLandingPage();
		landingPage.createAccount(data.get("Username"),data.get("Password"));
		Assert.assertTrue(landingPage!=null, "Could not login");
		isLoggedIn=true;				
		APPLICATION_LOGS.debug("Log In Test Completed - In Landing page");
		APPLICATION_LOGS.debug("************************************************");		
	}
		
	@DataProvider
	public Object[][] getCreateAccountData(){
		return TestUtil.getData("CreateAccountTest", xls);
	}	

}
