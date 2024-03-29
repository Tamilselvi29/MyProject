package com.crm.qa.listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;

public class TestNGListener extends TestBase implements ITestListener {

		
	public void onStart(ITestContext iTestContext) {
		  System.out.println("I am in onStart method " + iTestContext.getName());
          //iTestContext.setAttribute("WebDriver", this.driver);
	}

	
	private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }
 
     public void onFinish(ITestContext iTestContext) {
        System.out.println("I am in onFinish method " + iTestContext.getName());
        //Do tier down operations for extentreports reporting!
//        ExtentTestManager.endTest();
//        ExtentManager.getReporter().flush();
    }
 
     public void onTestStart(ITestResult iTestResult) {
        System.out.println("I am in onTestStart method " + getTestMethodName(iTestResult) + " start");
    }
 
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("I am in onTestSuccess method " + getTestMethodName(iTestResult) + " succeed");
        //ExtentReports log operation for passed tests.
       // ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
    }
 
  
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("I am in onTestFailure method " + getTestMethodName(iTestResult) + " failed");
        
        try {
			TestUtil.takeScreenshotAtEndOfTest();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//        //Get driver from BaseTest and assign to local webDriver variable.
//        Object testClass = iTestResult.getInstance();
//        WebDriver webDriver = ((BaseTest) testClass).getDriver();
// 
//        //Take base64Screenshot screenshot.
//        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) webDriver).
//            getScreenshotAs(OutputType.BASE64);
// 
//        //ExtentReports log and screenshot operations for failed tests.
//        ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Failed",
//            ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));
    }
 
     public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("I am in onTestSkipped method " + getTestMethodName(iTestResult) + " skipped");
        //ExtentReports log operation for skipped tests.
        //ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }	
}
