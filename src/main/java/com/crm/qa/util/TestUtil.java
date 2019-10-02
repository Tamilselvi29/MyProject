package com.crm.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.crm.qa.base.TestBase;

/*shortcuts in eclipse
Alt+ left arrorw and Alt +  right arrow -- to go to previous location
Ctrl+shft+f -- formats code
Ctrl+shft+o -- to organize import libraries
Ctrl+/ -- single line comment
Ctrl+shift+/ -- to insert block comment
Ctrl+shift+\ -- to remove block comment
Ctrl+f7 --switch to next view
Cntrl+1 -- quick fix 
CTRL+E -- Shows you a list of all open editors.
*/
public class TestUtil extends TestBase {

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 20;
	public static String TESTDATA_SHEET_PATH = "E:\\selvi\\AutomationProject\\myproject\\src\\main\\java\\com\\crm\\qa\\testdata\\FreeCRMTestData.xlsx";

	static Workbook book;
	static Sheet sheet;

	
	public void switchToFrame() {
		driver.switchTo().frame("mainpanel");
	}
	
	
	public static Object[][] getTestData(String sheetName)
			throws EncryptedDocumentException, InvalidFormatException, IOException {

		FileInputStream file = null;
		file = new FileInputStream(TESTDATA_SHEET_PATH);
		book = WorkbookFactory.create(file);
		sheet = book.getSheet(sheetName);

		int lastRow = sheet.getLastRowNum();
		short lastCol = sheet.getRow(0).getLastCellNum();

		Object[][] data = new Object[lastRow][lastCol];

		System.out.println("Last Row = " + lastRow + "  Last column " + lastCol);

		for (int i = 0; i < lastRow; i++) {
			for (int j = 0; j < lastCol; j++) {
				data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
				System.out.println(data[i][j]);
			}
		}

		return data;

	}
	
	public static void takeScreenshotAtEndOfTest() throws IOException {
		File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String currentDir=System.getProperty("user.dir");
		FileUtils.copyFile(srcFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() +".png"));
	}
	

}
