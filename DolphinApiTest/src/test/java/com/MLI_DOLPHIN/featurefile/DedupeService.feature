@DedupeAllScenarios 
Feature: To test the functionality of Dedupe servcie 

Background: 

	Given Set the request test data 
	
		|url                            |header                                             | requestFile  | 
		|/developer/dev-dolphin-dedupe  |x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY | Dedupe.json  |
		
Scenario Outline: 
	To test the Functionality of Dedupe servcie when user send valid set if data in request 

	When i want to send the request for Dedupe Servcie 
	Then Lets validate response code  of DedupeService "<msgCode>" 
	And Lets validate response appId response time  of DedupeService 
	And Lets validate the response message  of DedupeService "<msg>" 
	
	Examples: 
		|msgCode |msg     |
		|200     |Success |
		
Scenario: 
	To test the functionality when pass the mandotary parameter as empty 

	Given i want to set the test data 
	
		|testData                 | operationType    | testurl                       |msgCode |msg      |
		|{"panNo":"ACKPK5279R"}   | changeData       | /developer/dev-dolphin-dedupe | 200    | Success |
		|{ "dob":""}              | changeData       | /developer/dev-dolphin-dedupe | 500    | Failure |
		|{"firstName":"ANIL"}     | removeData       | /developer/dev-dolphin-dedupe | 500    | Failure |
@Test 
Scenario Outline: 
	To test the functionality of Api when pass the Correlation as null
	
	Given I want to Set the test data in request "<correlationIDKey>" and "<correlationValue>" 
	When i want to send the request for Dedupe Servcie 
	Then Lets validate response code  of DedupeService "<responseCode>" 
	#	And Lets validate response appId response time  of DedupeService 
	And Lets validate the response message  of DedupeService "<reponseMessage>" 
	
	Examples: 
		|correlationIDKey |correlationValue|responseCode|reponseMessage|
		|X-Correlation-ID |                |200         | Failure      |
		