package com.crm.qa.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.crm.qa.base.TestBase;

public class TakeScrshot extends TestBase{
	public static void takeScreenshotTest() throws IOException {
		File scrFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File destFile=new File(System.getProperty("user.dir")+"/Screenshot/"+System.currentTimeMillis()+".png");
		FileUtils.copyFile(scrFile, destFile);
	}
}
