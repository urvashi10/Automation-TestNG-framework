package com.crm.qa.base;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
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
	public static Logger log = LogManager.getLogger(TestBase.class);
	public static Properties prop;
	public static WebDriverWait wait;
	public TestBase() {
		try {
			prop=new Properties();
			FileInputStream input=new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/crm/qa/config/config.properties");
			prop.load(input);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	// Log4j2 is automatically configured via log4j2.xml or log4j2.properties in the classpath
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
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.Page_Load_Timeout));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.Implict_Wait));
		// Explicit wait helper for tests/page objects
		wait = new WebDriverWait(driver, Duration.ofSeconds(TestUtil.Implict_Wait));
		log.info("Driver Lauched!!!");
		driver.get(prop.getProperty("url"));
	}
	public static void closeBrowser() {
		driver.close();
	}

}
