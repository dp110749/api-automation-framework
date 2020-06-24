@TPAIntegrationTest 
Feature: To check the functionality of TPA Integration service 

Background: 

	Given set input request testdata 
		|url                       |header                                             | requestFile        | 
		|developer/tpa-integration |x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY | TPAIntegration.json|
		
@PositiveTestTPA1 
Scenario Outline: 
	To test the functionality when user send the valid request for TPA Integration 

	When i want to send the request for TPA Integration 
	Then Validate response code "<msgCode>" 
	And Validate response appId response time 
	And Validate the response message "<msg>" 

Examples: 
		|msgCode |msg     |
		|400     |Failure |

		
@NegativeTestTPA1 
Scenario: 
To test the functionality when user send the invalid request for TPA Integration 
Given :Set testdata for TPA Integration
	|testData               | operationType | testurl                    |msgCode |msg      |
	|{ "FirstName":"AAAA"}  | changeData    | /developer/tpa-integration | 200    | Success |
	|{ "FirstName":""}      | removeData    | /developer/tpa-integration | 400    | Failure |
	|{ "DateOfBirth":"568"} | changeData    | /developer/tpa-integration | 400    | Failure |
				
Scenario Outline: 
	 To test the functionality when user send the null header for TPA Integration
			 
	Given :Set header values for TPA Integration "<header>" 
	When i want to send the request for TPA Integration 
	Then Lets validate response code "<msgCode>" 
	And Validate response appId response time 
	And Validate the response message "<msg>" 
	
	Examples: 
		|header         |msgCode |msg      |
		|x-api-key:null |        |Forbidden|
				
Scenario Outline: 
To test the functionality when user send the x-correlation-ID as null for TPA Integration 		
Given :Set the x-correlation-ID data for TPA Integration into request "<apiKey>" and "<apiValue>" 
When i want to send the request for TPA Integration
Then Validate response code "<msgCode>" 
And Validate the response message "<msg>" 

Examples: 
	|msgCode |msg     |apiKey           | apiValue |
	|400     |Failure |X-Correlation-ID |          |
				
				
@PositiveTestTPA2 
Scenario Outline: 
To test the functionality when user send the valid request for TPA Integration 
Given :Set the ProductName data into request "<apiKey>" and "<apiValue>"
When i want to send the request for TPA Integration 
Then Validate response code "<msgCode>" 
And Validate the response message "<msg>" 

 Examples: 
	|msgCode|msg     |apikey     |apivalue|			
	|200    |Success |ProductName|Y       |			
				
				
@NegativeTestTPA2
Scenario: 
	To test the functionality when user send the invalid request for TPA Integration 
 Given :Set testdata for TPA Integration
	
		|testData                         | operationType|testUrl                     |msgCode |msg          |
#		|{ "HouseNoAptNameSociety": "*&"} | changeData   | /developer/tpa-integration | 500    | Failed      |
		|{ "HouseNoAptNameSociety": "" }  | changeData   | /developer/tpa-integration | 400    | Failure     |
#		|{ "HouseNoAptNameSociety": "24"} | removeData   | /developer/tpa-integration |        | Bad Request |
						
						
 