package com.qa.linkedin.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.log4testng.Logger;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	public static WebDriver driver = null;
	public WebDriverWait wait = null;
	private Logger log = Logger.getLogger(TestBase.class);

//read the properties from properties file
	public String readPropertiesValue(String key) throws IOException {
		log.info("create object for properties class");
		Properties prop = new Properties();
		log.info("read the values from properties file");
		try {
			FileInputStream fis = new FileInputStream(new File(System.getProperty("user.dir")
					+ "\\src\\test\\java\\com\\qa\\linkedin\\config\\config.properties"));

			prop.load(fis);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop.getProperty(key);

	}

	@BeforeSuite
	public void setup() throws IOException {
		log.info("Sterted executing setup()");
		log.debug("fetching browser value from config.properties file");
		String browserName = readPropertiesValue("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			log.info("set the chromedriver exe path");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		log.info("maximize the window");
		driver.manage().window().maximize();

		log.info("create object for WebDriverWait class");
		wait = new WebDriverWait(driver, Duration.ofMillis(4000));

		log.debug("launching the application:" + readPropertiesValue("applicationUrl"));

		driver.get(readPropertiesValue("applicationUrl"));

	}

	@AfterSuite
	public void afterClass() {
		if (driver != null) {
			log.debug("close the browser");
			driver.quit();
		}
	}

}
