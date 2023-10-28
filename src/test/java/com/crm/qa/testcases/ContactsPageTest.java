package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase{

	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;	

	public ContactsPageTest(){
			super();		
	}
	@BeforeMethod
	public void setUp() throws InterruptedException {
		initBrowser();
		testUtil = new TestUtil();
		contactsPage = new ContactsPage();
		loginPage = new LoginPage();
		homePage=loginPage.login(prop.getProperty("userName"), prop.getProperty("password"));
		testUtil.switchToFrame();
		contactsPage = homePage.clickOnContactsLink();
	}
	@Test(priority=1)
	public void verifyContactsPageLabel(){
		Assert.assertTrue(contactsPage.verifyContactsLabel(), "contacts label is missing on the page");
	}
	
	@Test(priority=2)
	public void selectSingleContactsTest(){
		contactsPage.selectContactsByName("test2 test2");
	}
	@Test(priority=3)
	public void selectMultipleContactsTest() {
		contactsPage.selectContactsByName("test2 test2");
		contactsPage.selectContactsByName("ui uiii");
	}
	@DataProvider
	public Object[][] getCrmTestData() {
		Object data[][]=TestUtil.getTestData("contacts");
		return data;
	}
	@Test(priority =4,dataProvider = "getCrmTestData" )
	public void validateCreateNewContact(String title,String fName,String lName,String company) {
		contactsPage=homePage.clickOnNewContactLink();
//		contactsPage.createNewContact("Mr.", "ui", "uiii", "Google");
		contactsPage.createNewContact(title,fName,lName,company);
	}

	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	
	
	
	
}
