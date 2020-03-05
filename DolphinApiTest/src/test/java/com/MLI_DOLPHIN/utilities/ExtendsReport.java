package com.MLI_DOLPHIN.utilities;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtendsReport extends TestListenerAdapter {
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;

	public void onStart(ITestContext testContent) {
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/TestReport/myReport.html");
//		htmlReporter = new ExtentHtmlReporter("C:\\Users\\AbhishekkumarSingh\\workspace\\DolphinApiTest\\TestReport\\myReport.html");
		htmlReporter.config().setDocumentTitle("Dolphin API Automation Report");
		htmlReporter.config().setReportName("Functional Testing");
		htmlReporter.config().setTheme(Theme.DARK);

		/**/
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Project Name", "Dolphin API");
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Envirament", "QA");
		extent.setSystemInfo("User", "Abhishek");
	}

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.PASS, "Test Case PASSED Is" + result.getName());
	}

	public void onTestFail(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.FAIL, "Test Case FAILED Is" + result.getName());
		test.log(Status.FAIL, "Test Case FAILED Is" + result.getThrowable());
	}

	public void onTestSkip(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.FAIL, "Test Case SKIP Is" + result.getName());
		test.log(Status.FAIL, "Test Case SKIP Is" + result.getThrowable());
	}

	public void onFinished(ITestContext testContent) {
		extent.flush();
	}
}
