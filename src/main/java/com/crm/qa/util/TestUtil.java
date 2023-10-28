package com.crm.qa.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.base.TestBase;

public class TestUtil extends TestBase {
	static public long Page_Load_Timeout = 20L;
	static public long Implict_Wait = 10L;
	static public String TestData_Sheet_Path = System.getProperty("user.dir")
			+ "//src//main//java//com//crm//qa//testdata//CrmTestData.xlsx";
	static public Workbook workbook;
	static public Sheet sheet;

//	public void switchToFrame(String frameName) {
//		driver.switchTo().frame(frameName);
//	}
	public void switchToFrame() {
		driver.switchTo().frame("mainpanel");
	}

	public void waitInTime(int timeSec, WebElement wEle) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeSec));
		wait.until(ExpectedConditions.elementToBeClickable(wEle));
	}

	public static Object[][] getTestData(String SheetName) {
		FileInputStream fis;
		try {
			fis = new FileInputStream(TestData_Sheet_Path);
			workbook = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = workbook.getSheet(SheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
			}
		}
		return data;
	}
}
