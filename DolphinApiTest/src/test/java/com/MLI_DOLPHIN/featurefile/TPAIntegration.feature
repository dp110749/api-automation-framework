@TPAIntegrationTest 
Feature: To check the functionality of TPA Integration service 

Background: 

	Given set input request testdata 
		|url                     |header                                             | requestFile        | 
		|developer/medicalreport |x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY | TPAIntegration.json|
		
@PositiveTestTPA1 
Scenario Outline: 
	To test the functionality when user send the valid request for TPA Integration 
#  Given i want to get the random Proposal number for TPA Integration
#  Given Set new proposal number for TPA Integration
	When Send the request for TPA Integration
Then Validate response message "<msg>"
And Validate message code "<msgCode>"
And Validate response code "<msgCode>"

Examples: 
		|msgCode |msg     |
		|200     |Success |


@NegativeTestTPA1 
Scenario: 
To test the functionality when user send the invalid request for TPA Integration 
Given :Set testdata for TPA Integration
	|testData               | operationType | testurl                  |msgCode |msg      |
	|{ "ProposalNo":""}     | changeData    | /developer/medicalreport | 400    | Failure |
	|{ "FirstName":""}      | removeData    | /developer/medicalreport | 400    | Failure |
	|{ "DateOfBirth":"568"} | changeData    | /developer/medicalreport | 400    | Failure |
		
				

@PositiveTestTPA2 
Scenario Outline: 
To test the functionality when user send the valid request for TPA Integration 
Given :Set the Input Value for TPA Integration in request "<apiKey>" and "<apiValue>"
When Send the request for TPA Integration
Then Validate response message "<msg>"
And Validate message code "<msgCode>"
And Validate response code "<msgCode>"

 Examples: 
	|msgCode|msg     |apiKey     |apiValue|			
	|200    |Success |ProductName|Y       |			

				
@NegativeTestTPA2				
Scenario Outline: 
	 To test the functionality when user send the null header for TPA Integration
			 
	Given :Set header values for TPA Integration "<header>" 
	When Send the request for TPA Integration 
	Then Validate response message "<message>"
#	And Validate message code "<msgCode>" 
	 
	Examples: 
		|header         |msgCode |message  |
		|x-api-key:null |        |Forbidden|

@PositiveTestTPA3 
Scenario Outline: 
To test the functionality when user send the valid request for TPA Integration 
Given :Set the Input Value for TPA Integration in request "<apiKey>" and "<apiValue>"
When Send the request for TPA Integration
Then Validate response message "<msg>"
And Validate message code "<msgCode>"

 Examples: 
	|msgCode|msg     |apiKey     |apiValue|			
	|200    |Success |SmokerClass|false   |			

				
@NegativeTestTPA3				
Scenario Outline: 
To test the functionality when user send the x-correlation-ID as null for TPA Integration 		
Given :Set the Input Value for TPA Integration in request "<apiKey>" and "<apiValue>" 
When Send the request for TPA Integration
Then Validate response message "<msg>"
And Validate message code "<msgCode>" 

Examples: 
	|msgCode|msg     |apiKey           | apiValue |
	|400    |Failure |X-Correlation-ID |          |
								
				
@NegativeTestTPA4
Scenario: 
	To test the functionality when user send the invalid request for TPA Integration 
 Given :Set testdata for TPA Integration
	
		|testData                         | operationType|testUrl                   |msgCode |msg          |
		|{ "HouseNoAptNameSociety": "" }  | changeData   | /developer/medicalreport | 400    | Failure     |
						

@NegativeTestTPA5
Scenario: 
	To test the functionality when user send the invalid request for TPA Integration 
 Given :Set testdata for TPA Integration
	
		|testData               | operationType|testUrl                   |msgCode |msg          |
		|{ "PinCode": "000000"} | changeData   | /developer/medicalreport | 400    | Failure     |
		|{ "PinCode": "" }      | changeData   | /developer/medicalreport | 400    | Failure     |
		|{ "PinCode": ""}       | removeData   | /developer/medicalreport | 400    |Failure      |
						

						