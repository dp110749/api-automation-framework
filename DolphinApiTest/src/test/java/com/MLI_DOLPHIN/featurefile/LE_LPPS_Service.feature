@LE_LPPSService 
Feature:  
	To test the functionality of LE LLPS Service of premium and illustration generator 

Background: 

	Given : set the request url header and request body 
	
		|url_illustation           |url_premium         |header                                              |requestfile   |
		|/developer/leillustration |/developer/lepremium| x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY | LE_LPPS.json |

@PositiveTest		
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

@NegativeTest		
Scenario Outline: 
	To test the functionality when user send the header as null 

	Given : I want to set header value null "<header>" 
	When :I want to send the request 
	Then :I want to validate the response code "<statusCode>" 
	
	Examples: 
	
		|header         |statusCode|responseMessage|
		|x-api-key:null |  403     |               |

@NegativeTest		
Scenario Outline: 
	To test the functionality when user send invalid url  

	Given : I want to set invalid url "<url>" and url type "<urltype>" 
	When :I want to send the request 
	Then :I want to validate the response code "<statusCode>" 
	
	Examples: 
	
		|url                      |urltype            |statusCode   |
		|developer/leaaillustration |illustrationurl    |  403        | 		

@NegativeTes		
Scenario Outline: 
	To test the functionality when user remove the paylod field and send the request 

	Given : I want to set inputdata in request 
		|inputData          | oparationType |
		|{"channel": "K"}   | removeData    |
		
		
	When :I want to send the request 
	Then :I want to validate the response code "<statusCode>" 
	And :I want to validate the response message "<responseMessage>"
	Examples: 
	
		| statusCode  | responseMessage|
		|  200        | Failed         |		
		
@PositiveTest5		
Scenario Outline: 
	To test the functionality when user send the request for premium generator 
		
	When :I want to send the request for premium 
	
	Then :I want to validate the response code "<statusCode>" 
	And :I want to validate the response message "<responseMessage>"
	And :I want to validate the repsonse appid and response time 
	And :I want to validate the respone premiunm field 
	Examples: 
	
		| statusCode  | responseMessage|
		|  200        | Success        |	

@NegativeTest		
Scenario Outline: 
	To test the functionality when user send the request with ageOfInsured as empty 
	
	Given : i want set the data for testing 
		|inputData            |oparationToperform|
		|{"ageOfInsured":""}| changeData       |
		
	When :I want to send the request for premium 
	Then :I want to validate the response code "<statusCode>" 
	And :I want to validate the response message "<responseMessage>" 
	Examples:  
	
		| statusCode  | responseMessage|
		|  200        |Failed          |

@NegativeTest	
Scenario Outline: 
	To test the functionality when user send the request with ageOfInsured as empty 
	
	Given : i want set the data for testing 
		|inputData            |oparationToperform|
		|{"ageOfInsured":"33"}| removeData       |
		
	When :I want to send the request for premium 
	Then :I want to validate the response code "<statusCode>" 
	And :I want to validate the response message "<responseMessage>"
	Examples: 
	
		| statusCode  | responseMessage|
		|  200        | Failed         |		

@NegativeTest0000		
Scenario Outline: 
	To test the functionality when user send invalid url for premium 
	Given : I want to set invalid url "<url>" and url type "<urltype>" 
	When :I want to send the request for premium 
	Then :I want to validate the response code "<statusCode>" 
	
	Examples: 
	
		|url                  |urltype       |statusCode   |
		|developer/aqremiums  |premiumurl    |  403        | 		
		
			
		#Scenario Outline: 
		#	To test the functionality when user send the  invaild data in request 
		
		#	Given : I want to set inputdata in request "<inputdata>" and perform "<oparation>"
		#	When :I want to send the request 
		#	Then :I want to validate the response code "<statusCode>" 
		
		#	Examples: 
		
		#		|inputdata                   |oparation     | statusCode  |
		#		|{"nameOfInsured":"Abhishek"}|  replaceData |             | 		
		
		
		
		
		
		
		
