package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static WebEventListener eventListener;
	public static EventFiringWebDriver e_driver;
	
	public TestBase(){
		prop=new Properties(); 
		FileInputStream ip;
		try {
			//System.out.println("Current Directory : " + System.getProperty("user.dir"));
			ip = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/crm" 
					+"/qa/config/config.properties");
				
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void initialization() {
		String browserName=prop.getProperty("browser");
		
		if(browserName.equals("chrome")) {
			System.out.println("Chrome Selected");
			System.setProperty("webdriver.chrome.driver","e:/chromedriver.exe");
			driver= new ChromeDriver();
		}else if(browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver","e:/geckodriver.exe");
			driver=new FirefoxDriver();
		}
	
		/*
		 * //================================ code for if we need to use
		 * WebdriverEventListener e_driver=new EventFiringWebDriver(driver); //Now
		 * create object of Eventlistner to register it with EventFiringDriver
		 * eventListener = new WebEventListener(); e_driver.register(eventListener);
		 * driver=e_driver; //================================
		 */		
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT,TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		String url=prop.getProperty("url");
		
		driver.get(url);
	}
	
}
