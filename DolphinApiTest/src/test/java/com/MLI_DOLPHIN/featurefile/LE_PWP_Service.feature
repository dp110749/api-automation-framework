@LE_PWPService 
Feature: test the functionality of premium and illustration generator 

Background: 
	Given Set the url header and request 
	
		|  illustrationurl         |     premiumurl         |     header                                         |  reqtestFile     |
		|/developer/leillustration |/developer/lepremium    | x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY |LE_PWPService.json|

@PositiveTest		
Scenario Outline: 
	send the vaild request and verify illustration generated or not 

	Then : Send the request 
	Then : I want to validate the response "<statusCode>" 
	And : I want to validate the response app id and response time 
	And : I want to validate the response message "<responseMessage>" 
	And : I want to validate illustration genrate or not 
	
	Examples: 
		|statusCode|responseMessage|
		|200       |Success        |
		
Scenario Outline: 
	send the invaild request when user remove the field request

	Given : Set the input test data 
	
		| inputData           | oprationType|
		| {"ageOfPayer":"27"}| removeData  |
		
	Then : Send the request 
	Then : I want to validate the response "<statusCode>"  
	And : I want to validate the response message "<responseMessage>" 	
	
	Examples: 
		|statusCode|responseMessage|
		|400       |Bad Request    |
@NegativeTest12		
Scenario Outline: 
	validate the when user send the mandotary field as null

	Given : Set the input test data 
	
		| inputData           | oprationType |
		| {"productCode":""}  | changeData  |
		
	Then : Send the request 
	Then : I want to validate the response "<statusCode>" 
	And : I want to validate the response message "<responseMessage>" 
	
	Examples: 
		|statusCode|responseMessage|
		|200       |Failed    |
		
		
		
		
		
		