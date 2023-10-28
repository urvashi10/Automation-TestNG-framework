package com.crm.qa.base;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebDriverEvent;



public class TestBase {
	
	public static WebDriver driver;
	public static Logger log=Logger.getLogger(TestBase.class);
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebDriverEvent event;
	public TestBase() {
		try {
			prop=new Properties();
			FileInputStream input=new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/crm/qa/config/config.properties");
			prop.load(input);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	static {
		PropertyConfigurator.configure(System.getProperty("user.dir")+"/src/main/resources/log4j.properties");
		BasicConfigurator.configure();
	}
	public static void initBrowser() {
		String _browser=prop.getProperty("Browser");
		if(_browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}else if(_browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}else if(_browser.equalsIgnoreCase("internet explorer")) {
			WebDriverManager.iedriver().setup();
			driver=new InternetExplorerDriver();
		}else {
			System.out.println("Invalid Driver Name!!!");
		}
//		e_driver=new EventFiringWebDriver(driver);
//		event=new WebDriverEvent();
//		e_driver.register(event);
//		driver=e_driver;

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.Page_Load_Timeout, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.Implict_Wait, TimeUnit.SECONDS);
		log.info("Driver Lauched!!!");
		driver.get(prop.getProperty("url"));
	}
	public static void closeBrowser() {
		driver.close();
	}

}
