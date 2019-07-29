package com.crm.qa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase{

	//Page factory Object Repository
	@FindBy(name="username")
	@CacheLookup
	WebElement username;
	
	@FindBy(name="password")
	@CacheLookup
	WebElement password;
	
	@FindBy(xpath ="//*[@value=\"Login\"]")
	@CacheLookup
	WebElement loginBtn;
	
	@FindBy(xpath = "//*[@id=\"navbar-collapse\"]/ul/li[2]/a")
	WebElement signUpBtn;

	@FindBy(xpath = "/html/body/div[2]/div/div[1]/a/img")
	@CacheLookup
	WebElement crmLogo;

	//Initialize the Page Object 
	public LoginPage() {
		PageFactory.initElements(driver,this);
	}
	//Actions of Page
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}
	public boolean validatePhpImage() {
		return crmLogo.isDisplayed();
	}
	public HomePage login(String Username,String Password) {
		username.sendKeys(Username);
		password.sendKeys(Password);
		JavascriptExecutor js = (JavascriptExecutor)driver;
    	js.executeScript("arguments[0].click();", loginBtn);
		return new HomePage();
	}
}

