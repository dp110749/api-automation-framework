package com.MLI_DOLPHIN.excelreader;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.MLI_DOLPHIN.baseclass.BaseClass;

public class ExcelFileReader extends BaseClass {

	public static File file;
	public static FileInputStream fileinputstream;
	public static XSSFWorkbook workbook;
	public static XSSFCell cell;
	public static XSSFSheet sheet;
	public static String driveLocation = System.getProperty("user.dir");
	public static String FilePath = null;
	public static String[][] excelData;
	
	public static void getFileName(String FileName) {
		File folder = new File(driveLocation + "/src/test/resources/");
		File[] listOfFiles = folder.listFiles();
		logger.info("messate pr");
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].getName().contains("UAT")) {
				System.out.println("File " + listOfFiles[i].getName());
				FilePath = driveLocation + "/UAT_TESTDATA.xlsx";
				break;
				// System.out.println("Complete path of excel uat"+FilePath);
			} else if (listOfFiles[i].getName().contains("SIT")) {
				System.out.println("File " + listOfFiles[i].getName());
				FilePath = driveLocation + "/SIT_TESTDATA.xlsx";
				// System.out.println("Complete path of excel Sit"+FilePath);
			} else if (listOfFiles[i].getName().contains("PROD")) {
				System.out.println("File " + listOfFiles[i].getName());
				FilePath = driveLocation + "/PROD_TESTDATA.xlsx";
				// System.out.println("Complete path of excel prod"+FilePath);
			} else if (listOfFiles[i].isDirectory()) {
				System.out.println("Directory is not contains related files" + listOfFiles[i].getName());
			}
		}
	}


	// Reading Excel File and get the data
	public static String[][] getExcelData(String sheetname) {
		try {
			file = new File(driveLocation + "/src/test/resources/SIT_TESTDATA.xlsx");
			fileinputstream = new FileInputStream(file);
			workbook = new XSSFWorkbook(fileinputstream);
			sheet = workbook.getSheet(sheetname);
			// int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
			int getActivecolumn = sheet.getRow(0).getLastCellNum();
			// int rowCount = sheet.getPhysicalNumberOfRows();
			int getActiveRow = sheet.getLastRowNum();
			excelData = new String[getActiveRow][getActivecolumn];
			for (int Nrow = 1; Nrow <= getActiveRow; Nrow++) {
				XSSFRow row = sheet.getRow(Nrow);
				for (int Ncolumn = 0; Ncolumn < getActivecolumn; Ncolumn++) {
					 cell = sheet.getRow(Nrow).getCell(Ncolumn);
					if(cell.getStringCellValue()!=null){
					DataFormatter df = new DataFormatter();
					excelData[Nrow - 1][Ncolumn] = df.formatCellValue(cell);
					}else if(cell.getStringCellValue()==null){
						XSSFCell cell = sheet.getRow(Nrow).getCell(Ncolumn+1);
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
	
	public static String readingdata(String sheetName, int rownum, int colnum) throws Exception {
		File file = new File(System.getProperty("user.dir") +"/src/test/resources/SIT_TESTDATA.xlsx");
		FileInputStream fileInputStream = new FileInputStream(file);
		XSSFWorkbook hssfWorkbook = new XSSFWorkbook(fileInputStream);
		XSSFSheet sheet = hssfWorkbook.getSheet(sheetName);
		XSSFCell cell = sheet.getRow(rownum).getCell(colnum);
		DataFormatter df = new DataFormatter();
		String data = df.formatCellValue(cell);
		hssfWorkbook.close();
		return data;
	}
}
