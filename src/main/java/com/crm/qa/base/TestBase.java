package com.crm.qa.base;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.crm.qa.util.TestUtil;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	public TestBase() {
		try {
			prop=new Properties();
			FileInputStream input=new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/crm/qa/config/config.properties");
			prop.load(input);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	public static void initBrowser() {
		String driverPath=System.getProperty("user.dir")+"/resources/driver";
		String _browser=prop.getProperty("Browser");
		if(_browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", driverPath+"/chromedriver.exe" );
			driver=new ChromeDriver();
		}else if(_browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", driverPath+"/geckodriver.exe" );
			driver=new FirefoxDriver();
		}else if(_browser.equalsIgnoreCase("internet explorer")) {
			System.setProperty("webdriver.ie.driver", driverPath+"/IEDriverServer.exe" );
			driver=new InternetExplorerDriver();
		}else {
			System.out.println("Invalid Driver Name!!!");
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.Page_Load_Timeout, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.Implict_Wait, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
	}
	public static void closeBrowser() {
		driver.close();
	}

}
