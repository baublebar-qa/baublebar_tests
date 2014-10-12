package com.baublebar.testcases;

import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.baublebar.pages.BaublebarPage;
import com.baublebar.util.TestUtil;

public class SignupForDiscountTest extends TestBase {
	
	@BeforeSuite
	public void init(){
		initConfigurations();
		initDriver();
	}
	
	@Test
	public void getDiscountTest(Hashtable<String,String> data) throws Throwable{	
		APPLICATION_LOGS.debug("Executing the Login test");		
		if(!TestUtil.isExecutable("SignupForDiscountTest", xls) || data.get("Runmode").equals("N"))throw new SkipException("Skipping the test");		
		BaublebarPage landingPage = PageFactory.initElements(driver, BaublebarPage.class);
		landingPage.signupForDiscount();
		isLoggedIn=true;				
		APPLICATION_LOGS.debug("Sign up In Test Completed - In Landing page");
		APPLICATION_LOGS.debug("************************************************");
	}
}
