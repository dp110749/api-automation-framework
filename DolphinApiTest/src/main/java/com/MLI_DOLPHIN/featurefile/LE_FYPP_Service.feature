 @FYPP_PremiumTest 
Feature: To check the functionality of FYPP service for premium and illustration 

Background: 

	Given set the input prequest test data 
		|illustrationUrl            |premiumUrl           |header                                              | requestFile        | 
		| /developer/leillustration |/developer/lepremium | x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY | LE_FYPPService.json|
		
@PositiveTest 
Scenario Outline: 
	To test the functionality when user send the valid request for illustration generator 

	When i want to send the request for illustration generator 
	Then i want to validate response code "<StatusCode>" 
	And i want to validate response app id response time 
	And i want validate the response message "<responseMessage>" 
	And i want check the illustration is generated or not 
	
	Examples: 
		|StatusCode|responseMessage|
		|200       | Success       |
		
@NegativeTest 
Scenario: 
To test the functionality when user send the invalid request for illustration generator 
Given :Set testing data 

	|inputData              | oparationType|url                        |msgCode |Responsemessage   |
	|{ "productCode": "21"} | changeData   | /developer/leillustration | 500    | Failed           |
	|{ "productCode": "" }  | changeData   | /developer/leillustration | 500    | Failed           |
	|{ "productCode": "24"} | removeData   | /developer/leillustration |        | Bad Request      |
				
Scenario Outline: 
	: To test the functionality when user send the null header for illustration generator
			 
	Given :Set the header value "<header>" 
	When i want to send the request for illustration generator 
	Then i want to validate response code "<StatusCode>" 
	And i want to validate response app id response time 
	And i want validate the response message "<responseMessage>" 
	
	Examples: 
		|header         |StatusCode|responseMessage|
		|x-api-key:null |400       | Fobiden       |
				
Scenario Outline: 
: To test the functionality when user send the x-core-relation ID as null for illustration generator 		
Given : Set the x-correlation id data in request "<apiKey>" and "<apiValue>" 
When i want to send the request for illustration generator 
Then i want to validate response code "<statusCode>" 
And i want validate the response message "<responseMessage>" 

Examples: 
	|statusCode|responseMessage|apiKey           | apiValue |
	|400       |Bad Request    |X-Correlation-ID |          |
				
				
@PositiveTest 
Scenario Outline: 
To test the functionality when user send the valid request for premium generator 

When i want to send the request for premium generator 
Then i want to validate response code "<StatusCode>" 
And i want to validate response app id response time 
And i want validate the response message "<responseMessage>" 
#	And i want check premium amount "<premiumAmountpartA>" and "<premiumAmountPartB>"

Examples: 
	|StatusCode|premiumAmountpartA|premiumAmountPartB|msgCode|Msg|			
	| 200      |500000            |Part B-4%         |200    | Success|			
				
@NegativeTest 
Scenario: 
	To test the functionality when user send the invalid request for premium generator 
	Given :Set testing data 
	
		|inputData              | oparationType|url                   |msgCode |Responsemessage   |
		|{ "productCode": "21"} | changeData   | /developer/lepremium | 500    | Failed           |
		|{ "productCode": "" }  | changeData   | /developer/lepremium | 500    | Failed           |
		|{ "productCode": "24"} | removeData   | /developer/lepremium |        | Bad Request      |
						
						
 