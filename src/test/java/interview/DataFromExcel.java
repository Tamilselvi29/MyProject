package interview;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class DataFromExcel {

	public static Object[][] getData(String sheetName)
			throws EncryptedDocumentException, InvalidFormatException, IOException {

		FileInputStream f = new FileInputStream("e:/selvi/emp.xlsx");
		Workbook wb = WorkbookFactory.create(f);
		Sheet sheet = wb.getSheet(sheetName);

		int lastRow = sheet.getLastRowNum();
		short lastCol = sheet.getRow(0).getLastCellNum();
		

		System.out.println("Last Row = " + lastRow + "  Last column " + lastCol);

		Object[][] data = new Object[lastRow][lastCol];

		for (int i = 0; i < lastRow; i++) {
			for (int j = 0; j < lastCol; j++) {
				data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
				System.out.println(data[i][j]);
                
			}
		}
		return data;

	}

}
