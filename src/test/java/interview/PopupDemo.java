package interview;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PopupDemo {
	static WebDriver driver;
    static String url;
    
	public static void main(String[] args) throws InterruptedException, IOException {
		startUp();
		//alertMethod("http://demo.guru99.com/test/delete_customer.php");
		//popupMethod("http://demo.guru99.com/popup.php");
		//selectSingleInDropDown("http://demo.guru99.com/test/newtours/register.php");
		//selectMultipleInDropDown( "http://jsbin.com/osebed/2");
		authentication_popup("http://the-internet.herokuapp.com/basic_auth");
		//authentication_popup("http://admin:admin@the-internet.herokuapp.com/basic_auth"); //http://username:pwd@www.website.com
		
		//tearDown();
	}
	
	
	
	public static void startUp() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	public static void tearDown() {
		driver.close();
		driver.quit();
	}

	
	public static void authentication_popup(String url) throws IOException {
		driver.get(url);
		Runtime.getRuntime().exec("E:\\selvi\\autoITScripts\\authwindow.exe");
	}
	
	
	
	public static void selectSingleInDropDown(String url) {
		driver.get(url);
		
		JavascriptExecutor js=(JavascriptExecutor)driver;
		//js.executeScript("alert('hi');");
		//js.executeScript("argument(0).scrollIntoView(true);", countryDropDown);
		//js.executeScript("window.scrollBy(0,600)");
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)"); 
		
		WebElement countryDropDown = driver.findElement(By.name("country"));
		System.out.println("Displayed "+ countryDropDown.isDisplayed());
	    WebDriverWait wait= new WebDriverWait(driver,10);
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("country")));		
		Select select=new Select(countryDropDown);
		select.selectByValue("CANADA");
		select.selectByVisibleText("INDIA");
	}
	
	public static void selectMultipleInDropDown(String url) {
		driver.get(url);
		WebElement fruits = driver.findElement(By.id("fruits"));
		
		Select select=new Select(fruits);
		select.selectByValue("banana");
		select.selectByVisibleText("Orange");
		select.selectByIndex(3);
		
	}
	public static void alertMethod(String url) throws InterruptedException {
		driver.get(url);
		driver.findElement(By.name("cusid")).sendKeys("2");
		driver.findElement(By.name("submit")).sendKeys(Keys.ENTER);

		Alert alert = driver.switchTo().alert();
		System.out.println("Message in alert = " + alert.getText());
		alert.accept();

		Thread.sleep(5000);
	}
	
	public static void popupMethod(String url) throws InterruptedException {
		driver.get(url);
		String parentWindow=driver.getWindowHandle();
		System.out.println("Parent Window = " + parentWindow);
		
		driver.findElement(By.xpath("//a[contains(text(),'Click Here')]")).click();
	
		for(String s : driver.getWindowHandles()) {
			
			System.out.println("window handle :"+ s);
			
			if (!parentWindow.equalsIgnoreCase(s)) {
				System.out.println("Moving to Child window "+ s);
				driver.switchTo().window(s);
			}
		}
		
		driver.findElement(By.name("emailid")).sendKeys("abc@abc.com");
		driver.findElement(By.name("btnLogin")).click();
	    driver.close(); //closing the child window
	    Thread.sleep(5000);
	    System.out.println("Moving to parent " + parentWindow);
		driver.switchTo().window(parentWindow);
		Thread.sleep(5000);
			
	}

}
