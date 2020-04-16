@LE_AWPService 
Feature: LE AWP Service to generate the illustration and premium geneartor 

Background: 

	Given Set the request url  and header for AWP Service 
	
		| /developer/microservices/mli/api/life-engage/illustration/awp | x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY | 
		| LE_AWP.json | {"addRiderRequired":"Yes","addRiderSumInsured":"1600000"} |
		
		#   When  I Send the POST request 
		
Scenario Outline: To test the functionality for illustration generator 
	When Send the valid request 
	Then I try Validate the response status code "<statusCode>" 
	And I want to validate the response time and request type response app id 
	And I want to validate the illustration generated or not for valid  request 
	And i want to validate success message "<messageCode>" 
	
	Examples: 
		|statusCode|messageCode|
		|  200     |Success|
		
Scenario Outline: To test the functionality when send bad request 
	When I remove the payload field and send request "<Oparation>" 
	Then I try Validate the response status code "<statusCode>" 
#	And I want to validate the response time and request type response app id 
	And I want to validate the illustration generated or not for valid  request 
	And i want to validate response error message "<messageCode>" 
	
	Examples: 
		|Oparation |statusCode|messageCode|
		|removeData|  400     |Bad Request|
		
Scenario: To test the functionality when user send invalid inputdata in request 
	When I send request with invalid data 
	
		|changeData|{"nameOfInsured": "Akassh","agentId": "9s7www07"}|
		
	Then I try Validate the response status code "200" 
	And I want to validate the response time and request type response app id 
	And I want to validate the illustration generated or not for valid  request 
	And i want to validate success message "Success" 
	
Scenario: To test the functionality to generate premium for AWP 
	When I send the request to generate premium "/developer/microservices/mli/api/life-engage/premium/awp" 
	Then I try Validate the response status code "200" 
	And I want to validate the response time and request type response app id 
	And I want to validate the premium Amount 
	And i want to validate success message "Success" 
	
Scenario: To test the functionality when user send wrong url 
	When I send the request to generate premium "/developer/microservices/mli/api/life-engage/premium" 
	Then I try Validate the response status code "405"
	And i want to validate error message "Method Not Allowed" 
	
Scenario: To test the functionality when user send invalid inputdata in request 
	When I send request with invalid data for Awp premium generator 
	
		|changeData|{"nameOfInsured": "Akassh","agentId": "9s7www07"}|/developer/microservices/mli/api/life-engage/premium/awp|
		
	Then I try Validate the response status code "200" 
	And I want to validate the response time and request type response app id 
	And I want to validate the illustration generated or not for valid  request 
	And i want to validate success message "Success" 
	
	
	
	
	
	
