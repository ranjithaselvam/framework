package com.atmecs.project.report;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;

import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Extentreport {
	static WebDriver driver;

	ExtentHtmlReporter htmlReporter;

	ExtentReports extent;

	ExtentTest test;

	@BeforeSuite
	public void startReport() {


		 htmlReporter = new ExtentHtmlReporter("ExtentReport.html");

		extent = new ExtentReports();

		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("OS", "Windows10");
		extent.setSystemInfo("Browser", "chrome");

		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("Extent Report");
		htmlReporter.config().setReportName("Test Report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
	}

	@AfterMethod
	public void getResult(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " FAILED " , ExtentColor.RED));
			test.fail(result.getThrowable());
			String screenshotPath = Extentreport.getScreenshot(result.getName());
			test.addScreenCaptureFromPath(screenshotPath);
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASSED ", ExtentColor.GREEN));
			String screenshotPath = Extentreport.getScreenshot(result.getName());
			test.addScreenCaptureFromPath(screenshotPath);
		} else {
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " SKIPPED ", ExtentColor.ORANGE));
			test.skip(result.getThrowable());
			String screenshotPath = Extentreport.getScreenshot(result.getName());
			test.addScreenCaptureFromPath(screenshotPath);
		}
	}
	
	
	
	
	
	public static String getScreenshot( String screenshotName) throws Exception {
		
		TakesScreenshot takeScreenShot = (TakesScreenshot) driver;
		File source = takeScreenShot.getScreenshotAs(OutputType.FILE);
		String destination =  "./screenshot/" + screenshotName + ".png";
		File Destination = new File(destination);
		FileUtils.copyFile(source, Destination);

		return destination;
	}

	@AfterSuite
	public void tearDown() {

		extent.flush();
	}

}





















