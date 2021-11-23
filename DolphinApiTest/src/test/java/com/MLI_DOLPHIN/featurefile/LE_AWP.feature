@LE_AWPService 
Feature: LE AWP Service to generate the illustration and premium geneartor 

Background: 

	Given Set the request url and header for IFSC/MICR Service 
	    | requestFile |  illustrationurl          |               header                               |MethodType|
		| LE_AWP.json | /developer/leillustration | x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY | POST     |
		
		
@PositiveTest111	
Scenario Outline: To test the functionality for illustration generator 
	When Send the post request 
	Then I try Validate the response status code "<statusCode>" 
	And I want to validate the response time and request type response app id 
	And I want to validate the illustration generated or not for valid  request 
	And i want to validate success message "<messageCode>" 
	
	Examples: 
		|statusCode|messageCode|
		|  200     |success|
@NegativeTest00001		
Scenario Outline: To test the functionality when send bad request 
	Given I remove the field from payload and send request
	
    	|   testData                  |oparationToperform|  	
    	|{"sumAssured": "6587155.96"} |        removeData|
    	
	When Send the post request 
	Then I try Validate the response status code "<statusCode>" 
#	And i want to validate response error message "<messageCode>" 
	And i want to validate success message "<messageCode>"
	Examples: 
		 |statusCode|messageCode |
		 |  200     |Failure     |
		
@NegativeTest		
Scenario: To test the functionality when user send invalid inputdata in request 
	 
	 Given i want to set the data in request
	 
	    |oparation |  inputData     |
		|changeData|{"nameOfInsured": "Akassh","agentId": "9s7www07"}|
		
	When Send the post request 	
	Then I try Validate the response status code "200" 
	And I want to validate the response time and request type response app id 
	And I want to validate the illustration generated or not for valid  request 
	And i want to validate success message "Success"
	
@NegativeTest
Scenario Outline: To Test the Functionality when send the header as null
   Given i want to set headerValue "<Header>"
   When Send the post request 	
   Then I try Validate the response status code "<StatusCode>"
   
Examples:
        |Header        |StatusCode|
        |x-api-key:Null| 403 |
        
@PositiveTest	
Scenario Outline: To test the functionality to generate premium for AWP 
	Given I want to set request url "<url>" 
	When Send the post request 
	Then I try Validate the response status code "<statuscode>" 
	And I want to validate the response time and request type response app id 
	And I want to validate the premium Amount 
	And i want to validate success message "<responseMessage>"
	
	Examples:
	| url                  |statuscode|responseMessage|
	| /developer/lepremium |200       |success        |
	
@NegativeTest	
Scenario Outline: To test the functionality when user send wrong url for AWP premium

	Given I want to set request url "<url>" 
	When Send the post request  
	Then I try Validate the response status code "<statuscode>"
#	And i want to validate error message "<responseMessage>" 
	
	Examples:
	| url                    |statuscode| 
	| /developer/premium     |403       | 
	
	
@NegativeTest
Scenario Outline:To test the functionality of AWP premiunm service when user send invalid inputdata in request 
	Given I want to set the input data in request 

	    |oparationType|inputData                                        |        url         |
		|changeData   |{"nameOfInsured": "Akassh","agentId": "9s7www07"}|/developer/lepremium|
	When Send the post request	
	Then I try Validate the response status code"<statuscode>" 
	And I want to validate the response time and request type response app id 
#	And I want to validate the illustration generated or not for valid  request 
	And i want to validate success message"<responseMessage>" 
	
	Examples:
      |statuscode| responseMessage    |
	  |  200     | success            |
	
	
	
	
	
