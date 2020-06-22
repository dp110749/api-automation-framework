 @DiscrepancyRuleEngineTest 
Feature: To check the functionality of Discrepancy Rule Engine service 

Background: 

	Given set the input request testdata 
		|url                                 |header                                             | requestFile               | 
		|/developer/discrepancy-rule-engine  |x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY | DiscrepancyRuleEngine.json|
		
@PositiveTest1212 
Scenario Outline: 
	To test the functionality when user send the valid request for Discrepancy Rule Engine 

	When i want to send the request for Discrepancy Rule Engine 
	Then Lets validate response code "<msgCode>" 
	And Lets validate response appId response time 
	And Lets validate the response message "<msg>" 
	
	Examples: 
		|msgCode |msg     |
		|200     |Success |
		
@NegativeTest121 
Scenario: 
To test the functionality when user send the invalid request for Discrepancy Rule Engine 
Given :Set testdata
	|testData                      | operationType | testurl                            |msgCode |msg      |
	|{ "isInsuredGreaterFlag":"A"} | changeData    | /developer/discrepancy-rule-engine | 400    | Failure |
	|{ "isInsuredGreaterFlag":""}  | removeData    | /developer/discrepancy-rule-engine | 400    | Failure |
	|{ "isInsuredGreaterFlag":"2"} | changeData    | /developer/discrepancy-rule-engine | 400    | Failure |
				
Scenario Outline: 
	 To test the functionality when user send the null header for Discrepancy Rule Engine
			 
	Given :Set the header values "<header>" 
	When i want to send the request for Discrepancy Rule Engine 
#	Then Lets validate response code "<msgCode>" 
	And Lets validate response appId response time 
	And Lets validate the response message "<msg>" 
	
	Examples: 
		|header         |msgCode |msg      |
		|x-api-key:null |        |Forbidden|
				
Scenario Outline: 
To test the functionality when user send the x-correlation-ID as null for Discrepancy Rule Engine 		
Given :Set the x-correlation-ID data into request "<apiKey>" and "<apiValue>" 
When i want to send the request for Discrepancy Rule Engine
Then Lets validate response code "<msgCode>" 
And Lets validate the response message "<msg>" 

Examples: 
	|msgCode |msg     |apiKey           | apiValue |
	|400     |Failure |X-Correlation-ID |          |
				
				
@PositiveTest1111 
Scenario Outline: 
To test the functionality when user send the valid request for Discrepancy Rule Engine 
Given :Set the proposerGenderFlag data into request "<apiKey>" and "<apiValue>"
When i want to send the request for Discrepancy Rule Engine 
Then Lets validate response code "<msgCode>" 
#And Lets validate response app id response time 
And Lets validate the response message "<msg>" 

# Examples: 
#	|StatusCode|proposerGenderFlag|msgCode|msg     |			
#	| 200      |Y                 |200    |Success |			
				
 Examples: 
	|StatusCode|apikey1           |apivalue1|msgCode|msg     |			
	| 200      |proposerGenderFlag|Y        |200    |Success |			
				
				
@NegativeTest 
Scenario: 
	To test the functionality when user send the invalid request for Discrepancy Rule Engine 
 Given :Set testdata 
	
		|testData               | operationType|testUrl               |msgCode |msg          |
#		|{ "productName": "*&"} | changeData   | /developer/lepremium | 500    | Failed      |
		|{ "productName": "" }  | changeData   | /developer/lepremium | 400    | Failure     |
#		|{ "productName": "24"} | removeData   | /developer/lepremium |        | Bad Request |
						
						
 