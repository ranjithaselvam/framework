package com.atmecs.project.testbase;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.atmecs.project.constants.Constants;

/**
 * Different browser setup to run the script in different different browser.
 * 
 * @author ranjitha.selvam
 *
 */

public class Browsers {

	static WebDriver driver;

	@BeforeTest
	public void browserSetup() throws IOException {

		String url = propertyReader.properties(Constants.config_file, "url");
		String browserName = propertyReader.properties(Constants.config_file, "browserName");

		switch (browserName) {
		case "CHROME":
			System.setProperty("webdriver.chrome.driver", Constants.chrome_file);
			driver = new ChromeDriver();
			break;
		case "FIREFOX":
			System.setProperty("webdriver.gecko.driver", Constants.fireFox_file);
			driver = new FirefoxDriver();
			break;
		case "INTERNETEXPLORER":
			System.setProperty("webdriver.ie.driver", Constants.ie_file);
			driver = new InternetExplorerDriver();

			break;

		}
		driver.get(url);

		driver.manage().window().maximize();
	}

	@AfterTest()
	public void browserClose() {
		driver.close();

	}

}
