package com.baublebar.pages;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.baublebar.util.Constants;
import com.baublebar.testcases.TestBase;

public class BaublebarPage extends TestBase {
	
	public WebDriver driver;
	
	@FindBy(css=Constants.discountLink)
	public WebElement discountLink;
	
	@FindBy(name=Constants.email)
	public WebElement email;
	
	@FindBy(css=Constants.getStarted)
	public WebElement getStarted;
	
	@FindBy(css=Constants.loginLink)
	public WebElement loginLink;
	
	@FindBy(css=Constants.haveAnAcc)
	public WebElement haveAnAcc;
	
	@FindBy(xpath=Constants.accountEmail)
	public WebElement accountEmail;
	
	@FindBy(xpath=Constants.accountPassword)
	public WebElement accountPassword;
	
	@FindBy(xpath=Constants.confirmPassword)
	public WebElement confirmPassword;
	
	@FindBy(css=Constants.submit)
	public WebElement submit;
	
	
	@FindBy(xpath=Constants.addToWish)
	public WebElement addToWish;
	
	@FindBy(css=Constants.loginEmail)
	public WebElement loginEmail;
	
	@FindBy(css=Constants.loginPassword)
	public WebElement loginPassword;
	
	@FindBy(css=Constants.loginButton)
	public WebElement loginButton;
	
	@FindBy(xpath=Constants.myAccount)
	public WebElement myAccount;
	
	@FindBy(css=Constants.myWishList)
	public WebElement myWishList;
	
	@FindBy(css=Constants.myItem)
	public WebElement myItem;
	

	public BaublebarPage(WebDriver dr){
		driver = dr;
	}
	
	public void signupForDiscount() throws Throwable{
		driver.manage().deleteAllCookies();
		driver.get(CONFIG.getProperty("applicationURL"));
		discountLink.click();
		this.switchToFrameByIndex(6);
		email.clear();
		email.sendKeys("CI@G.COM");
		getStarted.click();
		//System.out.println("Code is " + driver.findElement(By.id("bouncex_el_18")).getText());
		//System.out.println("Code is " + driver.findElement(By.xpath("//*[@id=\"bouncex_el_18\"]/div/div")).getText());
		
	}
	
	public void createAccount(String accEmail, String accPassword) throws Throwable{
		driver.get(CONFIG.getProperty("applicationURL"));
		loginLink.click();
		haveAnAcc.click();
		accountEmail.clear();
		accountEmail.sendKeys(accEmail);
		accountPassword.clear();
		accountPassword.sendKeys(accPassword);
		confirmPassword.clear();
		confirmPassword.sendKeys(accPassword);
		submit.click();
	}
	
	
	public void verifyAnItemToWishList(String accEmail, String accPassword) throws InterruptedException{
		driver.get("http://staging.baublebar.com/desert-ruby-gem-cuff-bracelet.html");
		addToWish.click();
		String mainWindow =driver.getWindowHandle();
		Set windows=driver.getWindowHandles();
		//this method will you handle of all opened windows
		Iterator iter = windows.iterator();
		while(iter.hasNext()){
			String popupHandle = iter.next().toString();
		    if(!popupHandle.contains(mainWindow)){
		    	driver.switchTo().window(popupHandle);
		  }
		loginEmail.clear();
    	loginEmail.sendKeys(accEmail);
    	
    	loginPassword.clear();
	    loginPassword.sendKeys(accPassword);
	    
	    loginButton.click();
		myAccount.click();
		
		myWishList.click();
		System.out.println("test completed");
	
		String actual = myItem.getText();
		String expected = "DESERT RUBY GEM CUFF";
		Assert.assertEquals(expected, actual);
		
		}

	}
	


	public void switchToFrameByIndex(int index){
       WebElement frame;
       List<WebElement> frameset=driver.findElements(By.tagName("iframe"));
       if (frameset.size() > index) {
           frame=frameset.get(index);
           driver.switchTo().frame(frame);
       }else{
           System.out.println("Number of Frames present are:"+frameset.size());
           System.out.println("Index is greater than the number of frames present.");
       }

   }

}
