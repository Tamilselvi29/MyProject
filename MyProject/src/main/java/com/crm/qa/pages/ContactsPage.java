package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase {

	@FindBy(xpath = "//td[contains(text(),'Contacts')]")
	WebElement contactsLabel;

	@FindBy(xpath = "//select[@name='title']")
	WebElement title;

	@FindBy(id = "first_name")
	WebElement firstName;

	@FindBy(id = "surname")
	WebElement lastName;

	@FindBy(name = "client_lookup")
	WebElement company;

	@FindBy(xpath = "//form[@id='contactForm']//table[1]//tbody//tr[1]//td[1]//input[@type='submit' and @value='Save']")
	WebElement saveBtn;

	// @FindBy(xpath="//a[contains(text(),'Mukta
	// Sharma')]//parent::td[@class='datalistrow']//preceding-sibling::td[@class='datalistrow']//input[@name='contact_id'])")
	// WebElement nameCheckBox;

	public ContactsPage() {
		PageFactory.initElements(driver, this);
	}

	public boolean verifyContactLabel() {
		return contactsLabel.isDisplayed();
	}

	
	/**
	 * 
	 * @param name
	 */
	public void selectContactsByName(String name) {
		driver.findElement(By.xpath("//a[ contains(text(),'" + name
				+ "')]//parent::td[@class='datalistrow']//preceding-sibling::td[@class='datalistrow']//input[@name='contact_id']"))
				.click();
	}

	public void createNewContact(String titleName, String fname, String lname, String comp) {
		Select select = new Select(title);
		select.selectByVisibleText(titleName);

		firstName.sendKeys(fname);
		lastName.sendKeys(lname);
		company.sendKeys(comp);
		saveBtn.click();

	}

}
