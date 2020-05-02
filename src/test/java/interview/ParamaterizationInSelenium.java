package interview;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class ParamaterizationInSelenium {
	
	WebDriver driver;
	
	@Parameters({"url"})
	@BeforeTest
	public void startUp(String url) {
		driver=new ChromeDriver();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@AfterTest
	public void tearDown() {
		
		driver.close();
		driver.quit();
	}
	@Test
	public void getTitle() {
		String title= driver.getTitle();
		Assert.assertEquals(title, "Google"); 
	}
	
	
	@DataProvider(name="userDetails")
	public Object[][] getUserDetails() throws EncryptedDocumentException, InvalidFormatException, IOException {
	    Object[][] data=DataFromExcel.getData("Sheet1");
	    System.out.println("Data = " + data);
		return data;
	}
	
	@Test(dataProvider="userDetails")
	public void verifyData(String no,String name) {
		System.out.println("Eno =" + no + "  Ename = " + name);
//		Assert.assertEquals(eno, "10");;
		
	}
	
	
}
