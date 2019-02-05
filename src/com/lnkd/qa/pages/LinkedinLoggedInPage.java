package com.lnkd.qa.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.lnkd.qa.base.TestBase;

public class LinkedinLoggedInPage extends TestBase {

	public LinkedinLoggedInPage() throws IOException {
		super();
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//*[contains(@class,'profile-rail-card')]")
	WebElement profileCard;
	
	@FindBy(xpath="//*[contains(@class,'nav-item__profile-member-photo nav-item__icon')]")
	WebElement profileimage;
	
	@FindBy(xpath="//*[contains(@data-control-name,'nav.settings_signout')]")
	WebElement signout_link;
	
	@FindBy(xpath="//*[contains(@data-control-name,'nav.search_button')]")
	WebElement search_torch_icon;
	
	@FindBy(xpath="//*[@role='combobox' and @placeholder='Search']")
	WebElement search_editbox;
	
	
	public void verifyprofileCard(){
		wait.until(ExpectedConditions.visibilityOf(profileCard));
		Assert.assertTrue(profileCard.isDisplayed(), "logo is not displayed");
	}

	public void logOut(){
		profileimage.click();
		wait.until(ExpectedConditions.visibilityOf(signout_link));
		signout_link.click();
	}

	public SearchResultsPage searchPeople(String peopleKeyword) throws IOException, InterruptedException{
		search_editbox.clear();
		search_editbox.sendKeys(peopleKeyword);
		search_torch_icon.click();
		Thread.sleep(3000);
		return new SearchResultsPage();
	}
	
	
}
