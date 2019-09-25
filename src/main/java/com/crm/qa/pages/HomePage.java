package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;


public class HomePage extends TestBase {

	@FindBy(xpath = "//td[contains(text(),'User: Tamilselvi Rajan')]")
	WebElement usernameLabel;

	@FindBy(xpath = "//a[@title='Contacts']")
	WebElement contactsLink;

	@FindBy(xpath = "//a[contains(text(),'Deals']")
	WebElement dealsLink;

	@FindBy(xpath = "//a[contains(text(),'Tasks']")
	WebElement tasksLink;

	//@FindBy(xpath = "//a[text()='New Contact']")
	WebElement newContactLink;

	// Initializing the page Objects
	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	public String verifyHomePageTitle() {
		return driver.getTitle();
	}

	public boolean verifyCorrectUserName() {
		return usernameLabel.isDisplayed();
	}

	public ContactsPage clickOnContactsLink() {
		contactsLink.click();
		return new ContactsPage();
	}

	public DealsPage clickOnDealsLink() {
		dealsLink.click();
		return new DealsPage();
	}

	public TasksPage clickOnTasksLink() {
		tasksLink.click();
		return new TasksPage();
	}

	public void clickOnNewContactLink() throws InterruptedException {
		Actions action = new Actions(driver);
		action.moveToElement(contactsLink).build().perform();
		Thread.sleep(2000);
		
		newContactLink= driver.findElement(By.xpath("//a[contains(text(),'New Contact') and @title='New Contact']"));

		//String js = "arguments[0].style.height='auto'; arguments[0].style.visibility='visible';";
		//String js = "arguments[0].style.visibility='visible';";
		String js = "arguments[0].click()";
		((JavascriptExecutor) driver).executeScript(js, newContactLink);
		//newContactLink.click();
	}

}
