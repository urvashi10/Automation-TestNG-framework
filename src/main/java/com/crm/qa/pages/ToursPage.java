package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class ToursPage extends TestBase{

	
	
	@FindBy(xpath="//*[text()='6 Days Around Thailand']/following::div[14]/a")
	public	WebElement bookNow;
	
	public ToursPage() {
		PageFactory.initElements(driver, this);
	}
	
	public TasksPage clickOnBookNow() {
		bookNow.click();
		return new TasksPage();
	}
	
	public String validatePageUrl() {
		return driver.getCurrentUrl();
	}
}
