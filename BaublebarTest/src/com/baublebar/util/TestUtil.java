package com.baublebar.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.baublebar.testcases.TestBase;

/**
 * Author - Maitri Acharya
 */
public class TestUtil {
	
	/* Test will be skipped if returns false 
	 */
	public static boolean isExecutable(String testName, Xls_Reader xls){
		for(int rowNum=2;rowNum<=xls.getRowCount("Test Cases");rowNum++){
			if(xls.getCellData("Test Cases", "TCID", rowNum).equals(testName)){
				if(xls.getCellData("Test Cases", "Runmode", rowNum).equals("Y"))
					return true;
				else 
					return false;
			}			
		}		
		return false;
	}
	
	public static void takeScreenShot(String fileName) {
		File srcFile = ((TakesScreenshot)(TestBase.driver)).getScreenshotAs(OutputType.FILE);
	    try {
	    	FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"\\screenshots\\"+fileName+".jpg"));	    	
	    //	File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		//    FileUtils.copyFile(srcFile, new File("c:\\maitri.jpg"));
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	
	
	public static Object[][] getData(String testName,Xls_Reader xls){
		int testStartRowNum=0;
		// find the row num from which test starts
		for(int rNum=1;rNum<=xls.getRowCount("Test Data");rNum++){
			if(xls.getCellData("Test Data", 0, rNum).equals(testName)){
				testStartRowNum=rNum;
				break;
			}
		}
		int colStartRowNum=testStartRowNum+1;
		int totalCols=0;
		while(!xls.getCellData("Test Data", totalCols, colStartRowNum).equals("")){
			totalCols++;
		}
		//rows
		int dataStartRowNum=testStartRowNum+2;
		int totalRows=0;
		while(!xls.getCellData("Test Data", 0, dataStartRowNum+totalRows).equals("")){
			totalRows++;
		}
		// extract data
		Object[][] data = new Object[totalRows][1];
		int index=0;
		Hashtable<String,String> table=null;
		for(int rNum=dataStartRowNum;rNum<(dataStartRowNum+totalRows);rNum++){
			table = new Hashtable<String,String>();
			for(int cNum=0;cNum<totalCols;cNum++){
				table.put(xls.getCellData("Test Data", cNum, colStartRowNum), xls.getCellData("Test Data", cNum, rNum));
			}
			data[index][0]= table;
			index++;			
		}
		return data;
	}

	public static boolean setCellData(String data, String colName, int rowNum ,Xls_Reader xls){
		try {
			xls.setCellData("results", colName, rowNum, data);
		} catch(Exception e){			
			e.printStackTrace();
			return false;
		}
		return true;	
	}
}