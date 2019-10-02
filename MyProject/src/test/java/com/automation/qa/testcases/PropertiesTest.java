package com.automation.qa.testcases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

class parent
{
	parent(){
		System.out.println("Parent constructor");
	}
}
public class PropertiesTest extends parent{

	Properties p;
	
	@BeforeMethod
	public void setUp()
	{
	p=new Properties();
	FileInputStream ip;
	try {
		ip = new FileInputStream("E:/selvi/AutomationProject/myproject/src/main/java/com/crm" 
				+"/qa/config/config.properties");
		p.load(ip);
	} catch (FileNotFoundException e) {
		e.printStackTrace();

	} catch (IOException e) {
		e.printStackTrace();
	}
	}
	
	@Test
	public void test1()
	{
		String uname=p.getProperty("username");
		String pwd=p.getProperty("password");
		System.out.println("User name =" + uname + " Password = " + pwd);
		
		System.out.println(uname.length() + "  "+ "tamilselvi".length());
		if (uname.equals("tamilselvi")){
			System.out.println("Username passed");
		}
		else
		{
			System.out.println("username failed");
		}
		
	}
	
	
}


	

