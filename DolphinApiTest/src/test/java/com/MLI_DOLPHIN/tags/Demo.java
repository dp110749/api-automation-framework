package com.MLI_DOLPHIN.tags;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Demo {
		
	public static File file;
	public static FileInputStream fileinputstream;
	public static XSSFWorkbook workbook;
	public static XSSFCell cell;
	public static XSSFSheet sheet;
	public static String driveLocation = System.getProperty("user.dir");
	public static String FilePath = null;
	public static String[][] excelData;
	
	public static void main(String[] args) {
		
//		
//		String Request="{\"metadata\":{\"appId\":\"IBPS\",\"soaCorrelationId\":\"12221222211\"},\"payload\":{\"username\":\"ibps\",\"password\":\"Ibps@123\"}}";
//		
//		String ApId="mPro";
//		String APIRequest;
//		String [] devidRequest = Request.split("IBPS");
//		System.out.println("======"+devidRequest.toString());
//		APIRequest=	devidRequest[0]+ApId+devidRequest[1];
//		
//		System.out.println("---"+APIRequest);
				 
		// New Aproach suggested by Naresh
		// String APIRequest = testRecords[1][1];
		// String[] devideAPIReq =APIRequest.split("ChangeAadhar");
		// APIRequest =devideAPIReq[0]+"TestAdhar"+devideAPIReq[1];
		
		String[][] test =getExcelData("NewOauthApi");
		System.out.println(test);
		
		
		
		}
	
	public static String[][] getExcelData(String sheetname) {
		try {
			File file = new File(System.getProperty("user.dir")+"./src/test/resources/SIT_TESTDATA.xlsx");
			FileInputStream fileinputstream = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(fileinputstream);
		   Sheet	sheet = workbook.getSheet(sheetname);
			// int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
			int getActivecolumn = sheet.getRow(0).getLastCellNum();
			// int rowCount = sheet.getPhysicalNumberOfRows();
			int getActiveRow = sheet.getLastRowNum();
		    String [][]	excelData = new String[getActiveRow][getActivecolumn];
			for (int Nrow = 1; Nrow <= getActiveRow; Nrow++) {
				Row row =  sheet.getRow(Nrow);
				for (int Ncolumn = 0; Ncolumn < getActivecolumn; Ncolumn++) {
					 cell = (XSSFCell) sheet.getRow(Nrow).getCell(Ncolumn);
					if(cell.getStringCellValue()!=null){
					DataFormatter df = new DataFormatter();
					excelData[Nrow - 1][Ncolumn] = df.formatCellValue(cell);
					}else if(cell.getStringCellValue()==null){
						XSSFCell cell = (XSSFCell) sheet.getRow(Nrow).getCell(Ncolumn+1);
						DataFormatter df = new DataFormatter();
						excelData[Nrow - 1][Ncolumn] = df.formatCellValue(cell);
					}else{
						System.out.println("Looks Like no Data..");
					}
				}
			 }
		   } catch (Exception e) {
		}
		return excelData;
	}


}
