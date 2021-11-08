@PolicyadminAllScenarios 
Feature: To test the functionality of policy admin for create and update 
Background: 
	Given set pre set of data 
	
		|url                    |header                                             |jsonFile        |MethodType|
		|/developer/policyadmin |x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY |PolicyAdmin.json|POST      |
		
Scenario Outline: 
To Test the functionality when Send Valid Request For create XMl 

When I want to send the request for policyAdmin 
Then I want to validate the response code "<reponseCode>" 
And I want to validate the message code "<msgCode>" 
And I want to validate the message description "<msgDescription>" 
And Lets validate response appId response time for PolicyAdmin 

Examples: 
	|reponseCode|msgCode|msgDescription|
	|200        |200    |Success       |
		
	
	#	When I want to send the request for policyAdmin
		
@NegativeScenario1 
Scenario Outline: To test the functionality when user send the header as null 

Given Set the inputData "<header>" 

When I want to send the request for policyAdmin 

Then I want to validate the response code "<responseCode>" 
And I want to validate the message description "<msgDescription>" 
Examples: 
	|header          |responseCode    |msgDescription  |
	|  x-api-key:    |403             |Forbidden       |
		
@NegativeScenario2 
Scenario Outline: To test the functionality when user send invalidEndPoint 

Given Set the inputData "<url>" 
When I want to send the request for policyAdmin 
Then I want to validate the response code "<responseCode>" 

Examples: 
	|url                        |responseCode  |
	| /developer/policyqqadmin  |403           |
	
@NegativeScenario3 
Scenario: To Test the functionality when user send the invalid request 

Given Set of Test Data for request 
	|inputKey              |inputValue   |msgCode    |messageDesc |
	#	|TRANSTYPE             |000          |400        | Failure    |
	|TRANSTYPE             |             |400        | Failure    |
	|ProductType           |abc          |500        | Failure    |
		
@NegativeScenario4 
Scenario: To Test the functionality when user send the invalid request 

Given Set of Test Data for request 
	|inputKey              |inputValue   |msgCode    |messageDesc |
	|X-Correlation-ID      |             | 400       |Failure           |

@PositiveScenario2	
Scenario: To test the Functionality for Update XML 
Given PreRequest Set of test data 
	| RequestInputKey      |RequestInputValue|msgCode|MessageDesc|
	| TRANSTYPE            | 504             |200    |Success    |
	|  Gender              | F               |200    |Success    |
	|ExactIncome           | 800000          |200    |Success    |		
	|DateOfBirth           | 1993-11-02      |200    |Success    |	
	|RequiredModalPremium  | 300000	         |200    |Success    | 
	
