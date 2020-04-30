@LE_LLPSService
Feature:  
	To test the functionality of LE LLPS Service of premium and illustration 

Background: 

	Given : set the request url header and request body 
	
		|url_illustation                                               |url_premium                                               |header                                              |requestfile   |
		|/developer/microservices/mli/api/life-engage/illustration/lpps |/developer/microservices/mli/api/life-engage/premium/lpps| x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY | LE_LLPS.json |
		
Scenario Outline: 
	To test the functinality when user send the valid request for Illustration 

	When :I want to send the request 
	Then :I want to validate the response code "<statusCode>" 
	And :I want to validate the response payload data 
	And :I want to validate the response message "<responseMessage>" 
	And :I want to validate the repsonse appid and response time 
	
	Examples: 
	
		|statusCode|responseMessage|
		|   200    |   Success     |
		
Scenario Outline: 
	To test the functionality when user send the header as null 

	Given : I want to set header value null "<header>" 
	When :I want to send the request 
	Then :I want to validate the response code "<statusCode>" 
	
	Examples: 
	
		|header         |statusCode|responseMessage|
		|x-api-key:null |  403     |               |
		
Scenario Outline: 
	To test the functionality when user send invalid url  

	Given : I want to set invalid url "<url>" and url type "<urltype>"
	When :I want to send the request 
	Then :I want to validate the response code "<statusCode>" 
	
	Examples: 
	
		|url                                                 |urltype            |statusCode   |
		|developer/microservices/mli/api/life-engage/premium |illustrationurl    |  404        | 		
		
Scenario Outline: 
	To test the functionality when user remove the paylod field and send the request 

	Given : I want to set inputdata in request
	|inputData                | oparationType |
	|{"nameOfInsured":"Akash"}| removeData    |
	
	
	When :I want to send the request 
	Then :I want to validate the response code "<statusCode>" 
	And :I want to validate the response error message "<responseMessage>"
	Examples: 
	
		 | statusCode  | responseMessage|
		 |  400        | Bad Request    |		

Scenario Outline: 
	To test the functionality when user generate premium for LE LLPS 

	When :I want to send the request 
	Then :I want to validate the response code "<statusCode>" 
	And :I want to validate the response error message "<responseMessage>"
	Examples: 
	
		 | statusCode  | responseMessage|
		 |  400        | Bad Request    |		


#Scenario Outline: 
#	To test the functionality when user send the  invaild data in request 

#	Given : I want to set inputdata in request "<inputdata>" and perform "<oparation>"
#	When :I want to send the request 
#	Then :I want to validate the response code "<statusCode>" 
	
#	Examples: 
	
#		|inputdata                   |oparation     | statusCode  |
#		|{"nameOfInsured":"Abhishek"}|  replaceData |             | 		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
    		