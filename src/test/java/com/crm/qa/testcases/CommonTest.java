package com.crm.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class CommonTest extends TestBase{

	static LoginPage loginPage;
	static HomePage homePage;
	static TestUtil testUtil;
	
	public CommonTest() {
	super();
	}
	
	@BeforeMethod
	public void setUp() {
		initBrowser();
		loginPage=new LoginPage();
		testUtil=new TestUtil();
//		homePage=loginPage.login(prop.getProperty("userName"), prop.getProperty("password"));
		}
	@AfterMethod
	public void tearDown(){
		closeBrowser();
	}
	
}
