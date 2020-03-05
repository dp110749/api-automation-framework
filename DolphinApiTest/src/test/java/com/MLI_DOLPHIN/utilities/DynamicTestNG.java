package com.MLI_DOLPHIN.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.testng.Assert;
import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class DynamicTestNG {
	
//	ExcelUtils excelUtils = new ExcelUtils();
	@SuppressWarnings("deprecation")
	public void runTestNGTest(Map<String,String> testngParams) throws Exception {

		//Create an instance on TestNG
		TestNG myTestNG = new TestNG();

		//Create an instance of XML Suite and assign a name for it.
		XmlSuite mySuite = new XmlSuite();
		mySuite.setName("Suite");

		//Create an instance of XmlTest and assign a name for it.
		XmlTest myTest = new XmlTest(mySuite);
		myTest.setName("Testclasess");

		//Add any parameters that you want to set to the Test.
		myTest.setParameters(testngParams);


		List<XmlClass> myClasses = new ArrayList<XmlClass> ();
		
		//---------My comment 
		
		//Reading methods from excel to execute
//		excelUtils.readingexcelFiles("ExecutionPlan");
//		int testcount=0;
//		for(int i=1;i<=excelUtils.lastRow;i++){
//			//Create a list which can contain the classes that you want to run.
//			if(excelUtils.excelData[i][1].toString().equalsIgnoreCase("Yes")) {
//				myClasses.add(new XmlClass("com.amc.Testclasess.execute."+excelUtils.excelData[i][4].toString()));
//				testcount= testcount+1;
//			}else {
//				if(testcount==0 && i== excelUtils.lastRow) {
//					Assert.fail("No Method is calling to execute");
//				}
//			}
//		}


		//Assign that to the XmlTest Object created earlier.
		myTest.setXmlClasses(myClasses);

		/* Test rail incorporation code
		TestListenerAdapter tla = new TestListenerAdapter();
		List<XmlClass> listenerClasses = new ArrayList<XmlClass>();
		listenerClasses.add(new XmlClass("com.amc.listners.TestExecutionListner"));
		listenerClasses.add(new XmlClass("com.amc.listners.TestRailListner"));
		myTestNG.setUseDefaultListeners(true);
		myTestNG.addListener(tla);

		//Create a list of XmlTests and add the Xmltest you created earlier to it.
		myTest.setXmlClasses(listenerClasses);*/

		//Create a list of XmlTests and add the Xmltest you created earlier to it.
		List<XmlTest> myTests = new ArrayList<XmlTest>();
		myTests.add(myTest);

		//add the list of tests to your Suite.
		mySuite.setTests(myTests);

		//Add the suite to the list of suites.
		List<XmlSuite> mySuites = new ArrayList<XmlSuite>();
		mySuites.add(mySuite);

		//Set the list of Suites to the testNG object you created earlier.
		myTestNG.setXmlSuites(mySuites);



		//invoke run() - this will run your class.
		myTestNG.run();
	}
	@Test
	public  void ExecuteScripts () throws Exception
	{
		DynamicTestNG dt = new DynamicTestNG();

		Map<String,String> testngParams = new HashMap<String,String> ();

		try {
			dt.runTestNGTest(testngParams);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
	

