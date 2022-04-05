package com.qa.linkedin.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LinkedinLoggedinPage extends BasePageWeb {

	private Logger log = Logger.getLogger(LinkedinLoggedinPage.class);

	// create a constructor
	public LinkedinLoggedinPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[contains(@class,'feed-identity-module')]")
	private WebElement profileRailCard;

	// @FindBy(xpath="//img[contains(@class,'global-nav__me-photo ghost-person
	// ember-view')]")
	@FindBy(xpath = "//img[@class='global-nav__me-photo ghost-person ember-view']")
	private WebElement profilePicIcon;

	@FindBy(xpath = "//a[@class='global-nav__secondary-link mv1'][contains(.,'Sign Out')]")
	private WebElement signOutLink;

	@FindBy(xpath = "//input[contains(@class,'search-global-typeahead')]")
	private WebElement searchEditBox;

	String loggedinPageTitle = "Feed | LinkedIn";

	public void verifyLinkedinLoggedinPageTitle() {
		log.debug("verify the linkedin loggedin page title:" + loggedinPageTitle);
		wait.until(ExpectedConditions.titleContains(loggedinPageTitle));
		Assert.assertTrue(driver.getTitle().contains(loggedinPageTitle));
	}

	public void verifyProfileRailCard() throws InterruptedException {

		log.debug("verify the linkedinhomepage header text:" + profileRailCard);
		Assert.assertTrue(isDisplayedElement(profileRailCard));

	}

	public void doLogOut() throws InterruptedException {
		log.debug("started executing doLogOut()");
		wait.until(ExpectedConditions.elementToBeClickable(profilePicIcon));
		wait.until(ExpectedConditions.visibilityOf(profilePicIcon));
		log.debug("click on profile image icon");
		click(profilePicIcon);
		log.debug("wait for the signout link");
		wait.until(ExpectedConditions.elementToBeClickable(signOutLink));
		wait.until(ExpectedConditions.visibilityOf(signOutLink));
		log.debug("click on signout link");
		click(signOutLink);

	}

	public SearchResultsPage doPeopleSearch(String keyword) throws InterruptedException {
		log.debug("started executing doPeopleSearch(String keywprd)");
		log.debug("clear the cntent in the search editbox");
		clearText(searchEditBox);
		log.debug("type the search keyword:" + keyword + "in search editbox");
		sendKey(searchEditBox, keyword);
		log.debug("press the enter key on search editbox");
		searchEditBox.sendKeys(Keys.ENTER);
		return new SearchResultsPage();
	}

}
