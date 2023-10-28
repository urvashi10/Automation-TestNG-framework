package com.crm.qa.report;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListener implements IRetryAnalyzer {

	int counter=0;
	int retryCount=3;
	@Override
	public boolean retry(ITestResult result) {
		if(counter<retryCount) {
			counter++;
			return true;
		}
		
		return false;
	}

}
