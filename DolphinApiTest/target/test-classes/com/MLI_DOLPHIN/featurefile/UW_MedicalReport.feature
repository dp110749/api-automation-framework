@UW_MedicalReportService 
Feature: To Test The Functionality of UW Medical Report API 
Background: 
	Given i want to set the header url and request 
	
	#       |      ValidData      |   InvalidData       |invalidUrl|header|
	#       |{"category": "T+M"}  |{"category": "T+M+1"}|x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6sss | /developer/microservices/mli/dolphin/api/medicalReport/ReportTrigger|
	#       |{"category": "T"}    |{"category": "T+S"}  |x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLVY | /developer/microservices/mli/dolphin/api/myAgent/ReportTrigger/v1|
	#       |{"category": "T+B"}  |{"category": ""}     |x-api-key:null	||
	
		|Header                                              |Url                       |Requestfile          |
		
		| x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY | /developer/medicalreport |UW_MedicalReport.json|
		
@PositiveTest_MR_1 
Scenario Outline: to test the functionality when user send the valid request with category T+M+B 

	When i want to send the request 
	Then i want to check "<responseStatusCode>" is the output 
	And i want to check the respone time and response type and app ID 
	And i want to check the "<responseMessage>" and "<medicalReportOutPut>" and "<kickoutMsg>" in the response  
	
	Examples: 
		| responseStatusCode | responseMessage |medicalReportOutPut|kickoutMsg                                               |
		|        200         | Success         |    URMU           |Kickout Msg-1: Insured8month pregnant with URMU REQUIRED;|
		
@PositiveTest_MR_2
Scenario Outline: to test the functionality when user send the valid request with category T+M 

Given i want to change the cotegory in request 

	|   inputdata      |actionType|
	|{"category":"T+M"}|changeData|
	
When i want to send the request 
Then i want to check "<responseStatusCode>" is the output 
And i want to check the respone time and response type 
And  i want to check the "<responseMessage>" and "<medicalReportOutPut>" and "<kickoutMsg>" in the response 

Examples: 
	| responseStatusCode | responseMessage |medicalReportOutPut|kickoutMsg                                               |
	|           200      | Success         |    URMU           |Kickout Msg-1: Insured8month pregnant with URMU REQUIRED;|
				

@PositiveTest_MR_3 
Scenario Outline: to test the functionality when user send the valid request with category B+T
 
Given i want to change the cotegory in request 

	|   inputdata      |actionType|
	|{"category":"B+T"}|changeData|
	
When i want to send the request 
Then i want to check "<responseStatusCode>" is the output 
And i want to check the respone time and response type 
And  i want to check the "<responseMessage>" and "<medicalReportOutPut>" and "<kickoutMsg>" in the response 

Examples: 
	| responseStatusCode | responseMessage |medicalReportOutPut|kickoutMsg|
	|           200      | Success         |    SPA            |          |

@PositiveTest_MR_4 
Scenario Outline:
to test the functinality when user send the valid request with category M+B 

Given i want to change the cotegory in request 

	|   inputdata      |actionType|
	|{"category":"M+B"}|changeData|
	
When i want to send the request 
Then i want to check "<responseStatusCode>" is the output 
And i want to check the respone time and response type 
And  i want to check the "<responseMessage>" and "<medicalReportOutPut>" and "<kickoutMsg>" in the response 

Examples: 
	| responseStatusCode | responseMessage |medicalReportOutPut|kickoutMsg                        |
	|           200      | Success         |    MANUW          |Insured life cover exceeds 2 crore|
										
				
@PositiveTest_MR_5 
Scenario Outline: to test the functionality when user send the valid request with category T 

Given i want to change the cotegory in request 

	|   inputdata    |actionType|
	|{"category":"T"}|changeData|
	
 When i want to send the request 
 Then i want to check "<responseStatusCode>" is the output 
 And i want to check the respone time and response type 
 And  i want to check the "<responseMessage>" and "<medicalReportOutPut>" and "<kickoutMsg>" in the response 

  Examples: 
	 | responseStatusCode | responseMessage |medicalReportOutPut|kickoutMsg                                               |
	 |           200      | Success         |    URMU           |Kickout Msg-1: Insured8month pregnant with URMU REQUIRED;|
						
 @PositiveTest_MR_6 
  Scenario Outline: to test the functionality when user send the valid request with category M 
 
	Given i want to change the cotegory in request 
	
		|   inputdata    |actionType|
		|{"category":"M"}|changeData|
		
	When i want to send the request 
	Then i want to check "<responseStatusCode>" is the output 
	And i want to check the respone time and response type 
	And  i want to check the "<responseMessage>" and "<medicalReportOutPut>" and "<kickoutMsg>" in the response 
	
	Examples: 
		| responseStatusCode | responseMessage |medicalReportOutPut|kickoutMsg                        |
		|           200      | Success         |    MANUW          |Insured life cover exceeds 2 crore|

 @NegativeTest_MR_1
  Scenario Outline: to test the functionality when user send the invalid category 
 
	Given i want to change the cotegory in request 
	
		|   inputdata      |actionType|
		|{"category":"X+Y"}|changeData|
		
	When i want to send the request 
	Then i want to check "<responseStatusCode>" is the output 
	And i want to check the respone time and response type 
	And  i want to check the "<responseMessage>" and "<messageCode>" in the response 
	
	Examples: 
		| responseStatusCode | responseMessage |messageCode|
		|           200      | Failure         |    400    |
		
			
@NegativeTest_MR_2 
Scenario Outline: to test the functionality when user send the header as null 

	Given i want to set header in request "<header>"
		
	When i want to send the request 
	Then i want to check "<responseStatusCode>" is the output 	
	And i want to check the "<responseMessage>" in the response 
	
	Examples: 
		|header        | responseStatusCode | responseMessage |
		|x-api-key:null|           403      | Forbidden       |  
		
@NegativeTest_MR_3 
Scenario Outline: to test the functionality when user send the invalid header 

	Given i want to set header in request "<header>"
		
	When i want to send the request 
	Then i want to check "<responseStatusCode>" is the output 	
	And i want to check the "<responseMessage>" in the response 
	
	Examples: 
		|header                                 | responseStatusCode | responseMessage |
		|x-api-key:DTUDHv9UVG8cVT3qmhXXXXXXXxYYY|           403      | Forbidden       |   

		 
@NegativeTest_MR_4
Scenario Outline: to test the functionality when user send the invalid EndPoint url

	Given i want to set url in request "<invaildEndPointUrl>"
		
	When i want to send the request 
	Then i want to check "<responseStatusCode>" is the output 	
	 
	Examples: 
		|invaildEndPointUrl                        |responseStatusCode | 
		|/developer/microservices/medicalreport/v1 |           404     |    
		
		
 @PositiveTest_MR_7 
  Scenario Outline: to test the functionality when user send the category as null
 
	Given i want to change the cotegory in request 
	
		|   inputdata      |actionType|
		|{"category": ""}  |changeData|
		
	When i want to send the request 
	Then i want to check "<responseStatusCode>" is the output 
    And i want to check the "<responseMessage>" in the response	
    
	Examples: 
		| responseStatusCode | responseMessage |
		|           200      |  Failure        |
		
														
 @PositiveTest_MR_8 
  Scenario Outline: to test the functionality when user remove the payload field and send the request 
	Given i want to change the cotegory in request 
	
		|   inputdata      |actionType|
		|{"category": ""}  |changeData|
		
	When i want to send the request 
	Then i want to check "<responseStatusCode>" is the output 
    And i want to check the "<responseMessage>" in the response	
														
	Examples: 
		| responseStatusCode | responseMessage |
		|           200      | Success         |
														
														
														
														
