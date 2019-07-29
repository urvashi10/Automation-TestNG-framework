package com.crm.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.MyProfilePage;
import com.crm.qa.pages.TasksPage;
import com.crm.qa.pages.ToursPage;
import com.crm.qa.util.TestUtil;

public class CommonTest extends TestBase{

	static LoginPage loginPage;
	static HomePage homePage;
	static TestUtil testUtil;
	static MyProfilePage myProfile;
	static ToursPage toursPage;
	static TasksPage tourDetailsPage;
	public CommonTest() {
	super();
	}
	
	@BeforeMethod
	public void setUp() {
		initBrowser();
		loginPage=new LoginPage();
		testUtil=new TestUtil();
		myProfile=new MyProfilePage();
		tourDetailsPage=new TasksPage();
		toursPage=new ToursPage();
		homePage=loginPage.login(prop.getProperty("userName"), prop.getProperty("password"));
		}
	@AfterMethod
	public void tearDown(){
		closeBrowser();
	}
	
}
