@Test2 
Feature: To Test the Functionality of Dolphin Push MS Service 

Background: 

	Given set the request data 
		|EndPointUrl           |Header                                            |JsonRequestFile|
		|/developer/dolphinpush|x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY|Dolphin_Push.json|
	
Scenario Outline: 
	To test the Functionality of DolphinPush MS when user send valid request 

	When i want to send the request for DolphinPush Servcie 
	Then Lets validate response code  of DolphinPush "<responseCode>" 
	And Lets validate response appId response time  of DolphinPush 
	And Lets validate the response message  of DolphinPush "<msg>" 
	
	Examples: 
		|responseCode |msg     |
		|200          |Success |
		

Scenario: To Test the dolphin Api With multiple set of data 
	Given Set of test data 
		|Key                               | Value      |responseMessage   |               
		|policyNumber                      | 600782100   |Success           |  
		
	When i want to send the request for DolphinPush Servcie 
	
