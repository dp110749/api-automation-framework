@LE_PWPService 
Feature: test the functionality of premium and illustration generator for PWP

Background: 
	Given Set the url header and request 
	
		|  illustrationurl         |     premiumurl         |     header                                         |  reqtestFile     |
		|/developer/leillustration |/developer/lepremium    | x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY |LE_PWPService.json|

@PositiveTest		
Scenario Outline: 
	send the vaild request and verify illustration generated or not 

	Then : Send the request for illustation
	Then : I want to validate the response "<statusCode>" 
	And : I want to validate the response app id and response time 
	And : I want to validate the response message "<responseMessage>" 
	And : I want to validate illustration genrate or not 
	
	Examples: 
		|statusCode|responseMessage|
		|200       |Success        |
@NegativeTest		
Scenario Outline: 
	send the invaild request when user remove the field request

	Given : Set the input test data 
	
		| inputData           | oprationType|
		| {"ageOfInsured":"27"}| removeData  |
		
	Then : Send the request for illustation
	Then : I want to validate the response "<statusCode>"  
	And : I want to validate the response message "<responseMessage>" 	
	
	Examples: 
		|statusCode|responseMessage|
		|200       |Failed    |
@NegativeTest	
Scenario Outline: 
	validate the response when user send the mandotary field as null

	Given : Set the input test data 
	
		| inputData           | oprationType |
		| {"productCode":""}  | changeData  |
		
	Then : Send the request for illustation
	Then : I want to validate the response "<statusCode>" 
	And : I want to validate the response message "<responseMessage>" 
	
	Examples: 
		|statusCode|responseMessage|
		|200       |Failed    |
		
@PositiveTest00000	
Scenario Outline: 
	to check the premium is generated or not 
	Then : Send the request for premium
	Then : I want to validate the response "<statusCode>"
	And : I want to validate the response app id and response time 
	And : I want to validate the response message "<responseMessage>"
	And : I want to validate the premium amount should not be null 
	
	Examples: 
		|statusCode|responseMessage |
		|200       |Success         |
		 
@NegativeTest	
Scenario Outline: 
	validate the response when user send the mandotary field as null

	Given : Set the input test data 
	
		| inputData           | oprationType |
		| {"productCode":""}  | changeData  |		
	Then : Send the request for premium
	Then : I want to validate the response "<statusCode>" 
	And : I want to validate the response message "<responseMessage>" 
	
	Examples: 
		|statusCode|responseMessage|
		|200       |Failed    |
		
 @NegativeTest		
Scenario Outline: 
	send the invaild request when user remove the field request

	Given : Set the input test data 
	
		| inputData           | oprationType|
		| {"ageOfInsured":"27"}| removeData  |
		
	Then : Send the request for premium
	Then : I want to validate the response "<statusCode>"  
#	And : I want to validate the response message "<responseMessage>" 	
	
	Examples: 
		|statusCode|responseMessage|
		|200       |Failed         |
 
@NegativeTest		
Scenario Outline: 
	send the  request with invalid url 

	Given : Set the request url "<invaildUrl>" 
			
	Then : Send the request for premium
	Then : I want to validate the response "<statusCode>"  
	And : I want to validate the response message "<responseMessage>" 	
	
	Examples: 
		|statusCode|responseMessage|   invaildUrl           |
		|403       |Forbidden      |/developersss/lepremium |
		
@NegativeTest		
Scenario Outline: 
	send the  request with X-Correlation-ID as null

	Given : Set the input test data in request "<apiKey>" and "<apiValue>"
			
	Then : Send the request for premium
	Then : I want to validate the response "<statusCode>"  
	And : I want to validate the response message "<responseMessage>" 	
	
	Examples: 
		|statusCode|responseMessage|apiKey    | apiValue |
		|200       |Forbidden      |x-api-key |          |
		
		
		
		
		
		