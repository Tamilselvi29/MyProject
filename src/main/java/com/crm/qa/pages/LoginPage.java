package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase {

	// Page Factory - OR(Object Repository)
	@FindBy(name = "username")
	WebElement username;
	
	@FindBy(name = "password")
	WebElement password;

	@FindBy(xpath = "//input[@type='submit']")
	WebElement loginBtn;

	@FindBy(xpath = "//a[text()='Sign Up']")
	WebElement signUpBtn;

	@FindBy(xpath = "//a[@class='navbar-brand']/img")
	WebElement crmLogo;

	//initializing the Page Objects using pageFactory
		public LoginPage() {
		PageFactory.initElements(driver, this);
	}
		
		
	//Actions:
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}
	
	public boolean validateCRMImage() {
		return crmLogo.isDisplayed();
	}
	
	public HomePage login(String uname,String pwd) {
		username.sendKeys(uname);
		password.sendKeys(pwd);
		loginBtn.click();
		return new HomePage();
	}
}
