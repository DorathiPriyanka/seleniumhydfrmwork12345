package com.qa.linkedin.testcases;

import org.testng.annotations.Test;

import com.qa.linkedin.base.TestBase;
import com.qa.linkedin.pages.LinkedinLoggedinPage;
import com.qa.linkedin.pages.SearchResultsPage;
import com.qa.linkedin.pages.linkedinHomePage;
import com.qa.linkedin.util.ExcelUtils;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.AfterClass;

public class SearchDataDrivenTest extends TestBase {

	private Logger log = Logger.getLogger(SearchDataDrivenTest.class);
	private String path = System.getProperty("user.dir")
			+ "\\src\\test\\java\\com\\qa\\linkedin\\data\\searchdatasheet.xlsx";

	linkedinHomePage lhmpg;
	LinkedinLoggedinPage llpg;
	SearchResultsPage srpg;

	@Test(priority = 1)
	public void doLoginTest() throws InterruptedException, IOException {
		log.debug("executing the doLoginTest()");
		log.debug("*********verify linkedin login page elements and title****************");
		log.debug("verify the linkedin home page and title");
		lhmpg.verifyLinkedinHomePageHeaderText();
		lhmpg.verifyLinkedinHomePageTitle();
		lhmpg.verifyLinkedinLogo();
		log.debug("click on signin link");
		lhmpg.clickSigninLink();

		log.debug("*********verify linkedin login page elements and title****************");

		lhmpg.verifySignInPageTitle();
		log.debug("perform the login test");
		llpg = lhmpg.doLogin(readPropertiesValue("username"), readPropertiesValue("pwd"));
        log.debug("*************login is done****************");
	}

	@Test(dataProvider = "dp", dependsOnMethods= {"doLoginTest"},alwaysRun=true)
	public void searchPeopleTest(String s) throws InterruptedException {
		log.debug("*****perform dosearchpeopletest for: " + s);
		llpg.verifyLinkedinLoggedinPageTitle();
		llpg.verifyProfileRailCard();
		log.debug("type the value " + s + " in search edit box");
		srpg.clickOnHomeTab();
		srpg = llpg.doPeopleSearch(s);
		log.debug("fetch the search results count for:" + s);
		long count = srpg.getAllResultsCount();
		log.debug("search results count for: " + s + " is: " + count);
		log.debug("click on Hometab");
		srpg.clickOnHomeTab();

	}

	@DataProvider
	public Object[][] dp() throws InvalidFormatException, IOException {
		log.debug("read the data to @DataProvider annotation");

		Object[][] data = new ExcelUtils().getTestData(path,"Sheet1");

		return data;
	}

	@BeforeClass
	public void initializeObjects() {

		log.debug("initialize the page objects");
		lhmpg = new linkedinHomePage();
		llpg = new LinkedinLoggedinPage();
		srpg = new SearchResultsPage();
	}

	@AfterClass
	public void logoutTest() throws InterruptedException {

		log.debug("logout from the application");
		llpg.doLogOut();

	}

}
