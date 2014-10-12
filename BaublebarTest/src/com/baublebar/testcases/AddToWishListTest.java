package com.baublebar.testcases;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.baublebar.pages.BaublebarPage;
import com.baublebar.util.TestUtil;

public class AddToWishListTest extends TestBase {
	
	@Test(dataProvider="getAddToWishListData")
	public void addToWishList(Hashtable<String,String> data) throws Throwable{
		APPLICATION_LOGS.debug("Executing the add to wish list test");		
		if(!TestUtil.isExecutable("AddToWishListTest", xls) || data.get("Runmode").equals("N"))throw new SkipException("Skipping the test");		
		BaublebarPage landingPage = getLandingPage();
		landingPage.verifyAnItemToWishList(data.get("AccountLogIn"),data.get("AccountPwd"));
		isLoggedIn=true;				
		APPLICATION_LOGS.debug("Add to wish list Test Completed - In Landing page");
		APPLICATION_LOGS.debug("************************************************");
		
	}
	
	
	@DataProvider
	public Object[][] getAddToWishListData(){
		return TestUtil.getData("AddToWishListTest", xls);
	}	

	
}
