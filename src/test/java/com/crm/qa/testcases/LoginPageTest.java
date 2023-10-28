package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.crm.qa.report.RetryListener;

public class LoginPageTest extends CommonTest{
	
	public LoginPageTest() {
		super();
	}
	/*@Test(priority = 1)
	public void loginPageTitleTest() {
		String title=loginPage.validateLoginPageTitle();
		System.out.println("Title of Login Page is : "+title);
		Assert.assertEquals(title, "CRMPRO - CRM software for customer relationship management, sales, and support.");
	}
	@Test(priority = 2)
	public void phpLogoImageTest() {
		boolean flag=loginPage.validatePhpImage();
		Assert.assertTrue(flag);
	}
	@Test(priority = 0,retryAnalyzer= RetryListener.class)*/
	@Test(priority = 0)
	public void loginTest() {
		homePage=loginPage.login(prop.getProperty("userName"), prop.getProperty("password"));
	}
}
