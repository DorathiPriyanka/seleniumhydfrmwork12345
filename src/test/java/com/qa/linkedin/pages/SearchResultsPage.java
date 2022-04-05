package com.qa.linkedin.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class SearchResultsPage extends BasePageWeb {
	private Logger log = Logger.getLogger(SearchResultsPage.class);

	public SearchResultsPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="(//div[contains(@class,'search-results__cluster-bottom-banner')])[1]")
	private WebElement seeAllPeoplesResultsLink;
	
	

	@FindBy(xpath = "//h2[@class='pb2 t-black--light t-14'][contains(.,'About 297,000 results')]")
	private WebElement searchResultsText;

	@FindBy(xpath = "//ul[contains(@class,'global-nav__primary-items')]/li/a")
	private WebElement homeTab;

	String searchResultsPageTitle = "Search | LinkedIn";
	
	
	public void verifySearchResultsPageTitle() {
		log.debug("verify the search results page title:" + searchResultsPageTitle);
		wait.until(ExpectedConditions.titleContains(searchResultsPageTitle));
		Assert.assertTrue(driver.getTitle().contains(searchResultsPageTitle));
	}

	public void clickOnSeeAllResultsLink() throws InterruptedException {
		log.debug("click on SeeAll Results Link");
		click(seeAllPeoplesResultsLink);
	}

	public void clickOnHomeTab() throws InterruptedException {
		log.debug("click on home tab");
		click(homeTab);
	}

	public long getAllResultsCount() throws InterruptedException {

		verifySearchResultsPageTitle();
		if (isElementPresent(By.xpath("//a[@class='app-aware-link'][contains(.,'See all people results')]"))) {
			clickOnSeeAllResultsLink();
			return fetchResultsCount();
		} else {
			return fetchResultsCount();
		}
	}

	public long fetchResultsCount() {
		log.debug("fetch the results count text");
		String resultstxt = searchResultsText.getText();
		log.debug("search results text is:" + resultstxt);
		String[] str = resultstxt.split(" ");
		String s1 = str[1].replace(",", "");
		long count = Long.parseLong(s1);
		return count;

	}

}
