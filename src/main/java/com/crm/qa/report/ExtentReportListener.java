package com.crm.qa.report;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentReportListener implements IReporter{

	private ExtentReports extent;
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
			String outputDirectory) {
		String reportPath = System.getProperty("user.dir")+"/Reports/Extent-Report/"+getDate()+ "/Extent -"+getTime()+".html";
		ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
		extent = new ExtentReports();
		extent.attachReporter(spark);
		
		for (ISuite suite : suites) {
			Map<String, ISuiteResult> result = suite.getResults();

			for (ISuiteResult r : result.values()) {
				ITestContext context = r.getTestContext();

				buildTestNodes(context.getPassedTests(), Status.PASS);
				buildTestNodes(context.getFailedTests(), Status.FAIL);
				buildTestNodes(context.getSkippedTests(), Status.SKIP);
			}
		}

		extent.flush();
	}

	private void buildTestNodes(IResultMap tests, Status status) {
		ExtentTest test;

		if (tests.size() > 0) {
			for (ITestResult result : tests.getAllResults()) {
				test = extent.createTest(result.getMethod().getMethodName());

				test.getModel().setStartTime(getTime(result.getStartMillis()));
				test.getModel().setEndTime(getTime(result.getEndMillis()));

				for (String group : result.getMethod().getGroups())
					test.assignCategory(group);

				if (result.getThrowable() != null) {
					test.log(status, result.getThrowable());
				} else {
					test.log(status, "Test " + status.toString().toLowerCase()
							+ "ed");
				}
			}
		}
	}

	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}
	private String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		String result=dateFormat.format(cal.getTime());
//		String result=date.replace("/", "");
		return result;
	}
	private String getTime() {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String date=dateFormat.format(cal.getTime());
		String result=date.replace(":", "");
		return result;	
	}

}
