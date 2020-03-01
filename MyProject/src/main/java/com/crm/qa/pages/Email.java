package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class Email extends TestBase {

	@FindBy(xpath = "//td[contains(text(),'Email')]")
	WebElement contactsLabel;
	
	public Email() {
		PageFactory.initElements(driver,this);
	}
	
}

