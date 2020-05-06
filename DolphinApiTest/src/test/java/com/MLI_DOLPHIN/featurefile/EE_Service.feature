@EE_ServiceTest
Feature: To test the functionality of EE 
Background: 
	Given :set the url request and header 
	
		|url                        |header                                            |requestFile      |           
		|/developer/execution-engine|x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY| EE_Service.json |

@PositiveTest		
Scenario Outline: to test functionality when user send the valid request 
	When :i want to send the request 
	Then :i want to validate the response "<statuscode>" 
	And  :i want to validate the "<status>" of job 
	Examples: 
		|statuscode|status | 
		|200       |success|
		
@NegativeTest		
Scenario Outline: to test functionality when user send header as null 
	Given : i want to set the "<header>" 
	When :i want to send the request 
	Then :i want to validate the response "<statuscode>" 
	
	Examples: 
		|header        |statuscode|
		|x-api-key:null|403       |
		
@NegativeTest		
Scenario Outline: to test functionality when user send invaild request url 
	Given : i want to set the url "<requestUrl>" 
	When :i want to send the request 
	Then :i want to validate the response "<statuscode>" 
	
	Examples: 
		|requestUrl                                             |statuscode|
		|/developer/microservices/mli/dolphin/api/ee/AutoUWMS/v1|404       |

#@NegativeTest1		
#Scenario Outline: to test functionality when user remove the PolicyAgentCustomerInfo field and send the request
#	Given : i want to remove the field 
#	|inputdata|
#	|{"PolicyAgentCustomerInfo":""}|
	
#	When :i want to send the request 
#	Then :i want to validate the response "<statuscode>" 
#	And : i want to validate the status message "<status>"
	
#	Examples: 
#		                | status  | responseCode |
#		                |         |    200         | 
 