package com.crm.qa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase{

	//Page factory Object Repository
	@FindBy(name="username")
	WebElement username;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(xpath ="//*[@value=\"Login\"]")
	WebElement loginBtn;
	
	@FindBy(xpath = "//*[@id=\"navbar-collapse\"]/ul/li[2]/a")
	WebElement signUpBtn;

	@FindBy(xpath = "//img[contains(@class, 'img-responsive')]")
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

	public void enterUsername(String uname) {
		username.clear();
		username.sendKeys(uname);
	}

	public void enterPassword(String pwd) {
		password.clear();
		password.sendKeys(pwd);
	}

	public void clickLoginButton() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
    	js.executeScript("arguments[0].click();", loginBtn);
	}

	public String getErrorMessage() {
		WebElement errorElement = driver.findElement(org.openqa.selenium.By.cssSelector(".error-message"));
		return errorElement != null ? errorElement.getText() : null;
	}
}

