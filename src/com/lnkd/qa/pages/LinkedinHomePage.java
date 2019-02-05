package com.lnkd.qa.pages;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.lnkd.qa.base.TestBase;

public class LinkedinHomePage extends TestBase{

	public LinkedinHomePage() throws IOException {
		super();
	PageFactory.initElements(driver, this);	
	}

@FindBy(className="lazy-loaded")
WebElement linkedinLogo;

@FindBy(id="login-email")
WebElement email_editbox;

@FindBy(name="session_password")
WebElement password_editbox;

@FindBy(id="login-submit")
WebElement signin_btn;

public void verifylinkedinLogo(){
	wait.until(ExpectedConditions.visibilityOf(linkedinLogo));
	Assert.assertTrue(linkedinLogo.isDisplayed(), "logo is not displayed");
}

public String homePageTitle(){
	return driver.getTitle();
}

public LinkedinLoggedInPage loginTest(String uname,String pwd) throws IOException{
	email_editbox.sendKeys(uname);
	password_editbox.sendKeys(pwd);
	signin_btn.click();
	return new LinkedinLoggedInPage();
}


}
