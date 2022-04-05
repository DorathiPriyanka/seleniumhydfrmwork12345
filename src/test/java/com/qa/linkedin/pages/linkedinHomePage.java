package com.qa.linkedin.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class linkedinHomePage extends BasePageWeb {
	private Logger log = Logger.getLogger(linkedinHomePage.class);

//create a constructor
	public linkedinHomePage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "a.nav__logo-link")
	private WebElement linkedinLogo;

	@FindBy(linkText = "Sign in")
	private WebElement signinLink;

	@FindBy(xpath = "//h1[contains(@data-test-id,'hero__headline')]")
	private WebElement linkedinHomePageHeaderText;

	@FindBy(xpath = "//h1[contains(@class,'header__content__heading)']")
//@FindBy(xpath = "//h1[contains(.,'Sign in')]")
	private WebElement signInHeaderText;

	@FindBy(id = "username")
	private WebElement emailEditBox;

	@FindBy(name = "session_password")
	private WebElement passwordEditBox;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement signInBtn;

	String linkedinHomepgTitle = "LinkedIn: Log In or Sign Up";
	String linkedInLoginPgTitle = "LinkedIn Login, Sign in | LinkedIn";

	public void verifyLinkedinHomePageTitle() {
		log.debug("verify the linkedinhomepage title:" + linkedinHomepgTitle);
		wait.until(ExpectedConditions.titleIs(linkedinHomepgTitle));
		Assert.assertEquals(driver.getTitle(), linkedinHomepgTitle);
	}

	public void verifyLinkedinHomePageHeaderText() throws InterruptedException {

		log.debug("verify the linkedinhomepage header text:" + linkedinHomePageHeaderText);
		Assert.assertTrue(isDisplayedElement(linkedinHomePageHeaderText));

	}

	public void verifyLinkedinLogo() throws InterruptedException {
		log.debug("verify the linkedin logo element");
		wait.until(ExpectedConditions.visibilityOf(linkedinLogo));
		Assert.assertTrue(linkedinLogo.isDisplayed(), "Linkedin logo is not present");
	}

	public void clickSigninLink() throws InterruptedException {
		log.debug("click on linkedin homepage signin link");
		click(signinLink);
	}

	public void verifySignInPageTitle() {
		log.debug("verify the signinpage title:" + linkedInLoginPgTitle);
		wait.until(ExpectedConditions.titleIs(linkedInLoginPgTitle));
		Assert.assertEquals(driver.getTitle(), linkedInLoginPgTitle);
	}

	public void verifySignInPageHeaderText() throws InterruptedException {

		log.debug("verify the linkedinSigninpage header text:" + signInHeaderText);
		Assert.assertTrue(isDisplayedElement(signInHeaderText));

	}

	public void clickSigninButton() throws InterruptedException {
		log.debug("click on linkedin homepage signin button");
		click(signInBtn);
	}

	public LinkedinLoggedinPage doLogin(String uname, String pwd) throws InterruptedException {
		log.debug("started executing doLogin(String uname,String pwd)");
		log.debug("clear the content in the email editbox");
		clearText(emailEditBox);
		log.debug("type the" + uname + "in emailEditBox");
		sendKey(emailEditBox, uname);
		log.debug("type the" + pwd + "in the password editbox");
		sendKey(passwordEditBox, pwd);
		clickSigninButton();
		return new LinkedinLoggedinPage();
	}

}
