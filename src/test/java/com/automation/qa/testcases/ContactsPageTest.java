package com.automation.qa.testcases;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

@Test(groups= {"ContactPageClass"})
public class ContactsPageTest extends TestBase {

	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	String sheetName="contacts";
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

	@Test(priority = 1)
	public void verifyContactPageLabel() {
		Assert.assertTrue(contactsPage.verifyContactLabel(), "contacts label is missing on the page");
	}

	@Test(priority = 2)
	public void selectSingleContactsTest() throws InterruptedException {
		contactsPage.selectContactsByName("Mukta Sharma");
		Thread.sleep(2000);

	}

	@Test(priority = 3)
	public void selectMultipleContactsTest() throws InterruptedException {
		contactsPage.selectContactsByName("Mukta Sharma");
		contactsPage.selectContactsByName("Tom Peter");
		Thread.sleep(5000);

	}

	
	@DataProvider
	public Object[][] getCRMTestData() throws EncryptedDocumentException, InvalidFormatException, IOException {
		Object[][] data= TestUtil.getTestData(sheetName);
		return data;
	}
	
	
	@Test(priority = 4,enabled=false)
	public void validateCreateNewContactTest1() throws InterruptedException {
		homePage.clickOnNewContactLink();
		contactsPage.createNewContact("Mr.", "Vettrivell", "Rajan", "KAR");
	}


	@Test(priority = 5,dataProvider="getCRMTestData")
	public void validateCreateNewContactTest(String title,String firstName,String lastName,String company) throws InterruptedException {
		homePage.clickOnNewContactLink();
		contactsPage.createNewContact(title,firstName,lastName,company);
	}
	
	@AfterMethod
	public void tearDown() throws InterruptedException {
		driver.quit();
	}

}
