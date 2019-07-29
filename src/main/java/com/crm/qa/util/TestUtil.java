package com.crm.qa.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.base.TestBase;

public class TestUtil extends TestBase{
	static public long Page_Load_Timeout=20L;
	static public long Implict_Wait=10L;

//	public void switchToFrame(String frameName) {
//		driver.switchTo().frame(frameName);
//	}
	public void switchToFrame() {
		driver.switchTo().frame("mainpanel");
	}
	public void waitInTime(int timeSec,WebElement wEle) {
		WebDriverWait wait=new WebDriverWait(driver, timeSec);
		wait.until(ExpectedConditions.elementToBeClickable(wEle));
	}
}
