package com.automation.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase {

	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;

	public ContactsPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		loginPage = new LoginPage();
		contactsPage = new ContactsPage();
		testUtil = new TestUtil();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		testUtil.switchToFrame();
		contactsPage = homePage.clickOnContactsLink();
	}

	@Test(priority = 1,enabled=false)
	public void verifyContactPageLabel() {
		Assert.assertTrue(contactsPage.verifyContactLabel(), "contacts label is missing on the page");
	}

	@Test(priority = 2,enabled=false)
	public void selectSingleContactsTest() throws InterruptedException {
		contactsPage.selectContactsByName("Mukta Sharma");
		Thread.sleep(2000);

	}

	@Test(priority = 3,enabled=false)
	public void selectMultipleContactsTest() throws InterruptedException {
		contactsPage.selectContactsByName("Mukta Sharma");
		contactsPage.selectContactsByName("Tom Peter");
		Thread.sleep(5000);

	}

	@Test(priority = 4)
	public void validateCreateNewContactTest() throws InterruptedException {
		homePage.clickOnNewContactLink();
		contactsPage.createNewContact("Mr.", "Vettrivell", "Rajan", "KAR");
		
	}

	@AfterMethod
	public void tearDown() throws InterruptedException {
		driver.quit();
	}

}
