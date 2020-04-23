@UW_MedicalReportService 
Feature: To Test The Functionality of UW Medical Report API 
Background: 
	Given i want to set the header url and request 
	
	#       |      ValidData      |   InvalidData       |invalidUrl|header|
	#       |{"category": "T+M"}  |{"category": "T+M+1"}|x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6sss | /developer/microservices/mli/dolphin/api/medicalReport/ReportTrigger|
	#       |{"category": "T"}    |{"category": "T+S"}  |x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLVY | /developer/microservices/mli/dolphin/api/myAgent/ReportTrigger/v1|
	#       |{"category": "T+B"}  |{"category": ""}     |x-api-key:null	||
	
		|Header|Url|Requestfile|
		
		| x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY | /developer/microservices/mli/dolphin/api/medicalReport/ReportTrigger/v1 |UW_MedicalReport.json|
		
@Post 
Scenario Outline: to test the functinality when user send the valid request with category T+M+B 

	When i want to send the request 
	Then i want to check "<responseStatusCode>" is the output 
	And i want to check the respone time and response type and app ID 
	And i want to check the "<responseMessage>" and "<medicalReportOutPut>" in the response 
	
	Examples: 
		| responseStatusCode | responseMessage |medicalReportOutPut|
		|        200         | Success         |    MANUW          |
		
@Post 
Scenario Outline: to test the functinality when user send the valid request with category T+M 

Given i want to change the cotegory in request 

	|   inputdata      |actionType|
	|{"category":"T+M"}|changeData|
	
When i want to send the request 
Then i want to check "<responseStatusCode>" is the output 
And i want to check the respone time and response type 
And i want to check the "<responseMessage>" and "<medicalReportOutPut>" in the response 

Examples: 
	| responseStatusCode | responseMessage |medicalReportOutPut|
	|           200      | Success         |    MANUW          |
				
				
@Post 
Scenario Outline: to test the functinality when user send the valid request with category T 

Given i want to change the cotegory in request 

	|   inputdata      |actionType|
	|{"category":"T"}|changeData|
	
When i want to send the request 
Then i want to check "<responseStatusCode>" is the output 
And i want to check the respone time and response type 
And i want to check the "<responseMessage>" and "<medicalReportOutPut>" in the response 

Examples: 
	| responseStatusCode | responseMessage |medicalReportOutPut|
	|           200      | Success         |    SPA            |
						
@Post 
Scenario Outline: to test the functinality when user send the valid request with category M 

	Given i want to change the cotegory in request 
	
		|   inputdata      |actionType|
		|{"category":"M"}|changeData|
		
	When i want to send the request 
	Then i want to check "<responseStatusCode>" is the output 
	And i want to check the respone time and response type 
	And i want to check the "<responseMessage>" and "<medicalReportOutPut>" in the response 
	
	Examples: 
		| responseStatusCode | responseMessage |medicalReportOutPut|
		|           200      | Success         |    MANUW          |
		
@Post 
Scenario Outline: to test the functinality when user send the valid request with category M+B 

Given i want to change the cotegory in request 

	|   inputdata      |actionType|
	|{"category":"M+B"}|changeData|
	
When i want to send the request 
Then i want to check "<responseStatusCode>" is the output 
And i want to check the respone time and response type 
And i want to check the "<responseMessage>" and "<medicalReportOutPut>" in the response 

Examples: 
	| responseStatusCode | responseMessage |medicalReportOutPut|
	|           200      | Success         |    MANUW          |
										
@Post 
Scenario Outline: to test the functinality when user send the valid request with category M+B
 
Given i want to change the cotegory in request 

	|   inputdata      |actionType|
	|{"category":"M+B"}|changeData|
	
When i want to send the request 
Then i want to check "<responseStatusCode>" is the output 
And i want to check the respone time and response type 
And i want to check the "<responseMessage>" and "<medicalReportOutPut>" in the response 

Examples: 
	| responseStatusCode | responseMessage |medicalReportOutPut|
	|           200      | Success         |    MANUW          |
	
												
@Post 
Scenario Outline: to test the functinality when user send the valid request with category B+T 

	Given i want to change the cotegory in request 
	
		|   inputdata      |actionType|
		|{"category":"B+T"}|changeData|
		
	When i want to send the request 
	Then i want to check "<responseStatusCode>" is the output 
	And i want to check the respone time and response type 
	And i want to check the "<responseMessage>" and "<medicalReportOutPut>" in the response 
	
	Examples: 
		| responseStatusCode | responseMessage |medicalReportOutPut|
		|           200      | Success         |    SPA            |
		
@PostFail 
Scenario Outline: to test the functinality when user send the header as null 

	Given i want to set header in request "<header>"
		
	When i want to send the request 
	Then i want to check "<responseStatusCode>" is the output 	
	And i want to check the "<responseMessage>" in the response 
	
	Examples: 
		|header        | responseStatusCode | responseMessage |
		|x-api-key:null|           403      | Forbidden       |  
		
@PostFail 
Scenario Outline: to test the functinality when user send the invalid header 

	Given i want to set header in request "<header>"
		
	When i want to send the request 
	Then i want to check "<responseStatusCode>" is the output 	
	And i want to check the "<responseMessage>" in the response 
	
	Examples: 
		|header                                        | responseStatusCode | responseMessage |
		|x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CVY|           403      | Forbidden       |   
		 
		
		
		
														
														
														
														
														
														
