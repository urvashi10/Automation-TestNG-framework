package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class MyProfilePage extends TestBase{

	
	@FindBy(xpath = "//*[text()='First Name']/following::div[1]")
	WebElement firstNameTxtbox;
	

	public MyProfilePage() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyFistNametxtBox() {
		return firstNameTxtbox.isDisplayed();
	}
	
	public boolean verifyPageHeaders(String headerName) {		
		return driver.findElement(By.xpath("//*[text()='"+headerName+"']")).isDisplayed();
	}
}
