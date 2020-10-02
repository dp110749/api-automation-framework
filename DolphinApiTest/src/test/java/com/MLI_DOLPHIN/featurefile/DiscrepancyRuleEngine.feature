 @DiscrepancyRuleEngineTest 
Feature: To check the functionality of Discrepancy Rule Engine service 

Background: 

	Given set the input request testdata 
		|url                                 |header                                             | requestFile               | 
		|/developer/discrepancy-rule-engine  |x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY | DiscrepancyRuleEngine.json|
		
@PositiveTestDiscrepancy1 
Scenario Outline: 
	To test the functionality when user send the valid request for Discrepancy Rule Engine 

	When i want to send the request for Discrepancy Rule Engine 
	Then Lets validate response code "<msgCode>" 
	And Lets validate response appId response time 
	And Lets validate the response message "<msg>" 
	
	Examples: 
		|msgCode |msg     |
		|200     |Success |
		
@NegativeTestDiscrepancy1 
Scenario: 
To test the functionality when user send the invalid request for Discrepancy Rule Engine 
Given :Set testdata
	|testData                      | operationType | testurl                            |msgCode|msg     |
	|{ "isInsuredGreaterFlag":"A"} | changeData    | /developer/discrepancy-rule-engine |400    |Failure |
	|{ "isInsuredGreaterFlag":""}  | removeData    | /developer/discrepancy-rule-engine |400    |Failure |
	|{ "isInsuredGreaterFlag":"2"} | changeData    | /developer/discrepancy-rule-engine |400    |Failure |
	
									
@PositiveTestDiscrepancy2 
Scenario Outline: 
To test the functionality when user send the valid request for Discrepancy Rule Engine 
Given :Set the proposerGenderFlag data into request "<apiKey>" and "<apiValue>"
When i want to send the request for Discrepancy Rule Engine 
Then Lets validate response code "<msgCode>" 
And Lets validate the response message "<msg>" 
				
 Examples: 
	|msgCode|msg    |apiKey            |apiValue|			
	| 200   |Success|proposerGenderFlag|Y       |			
				
@NegativeTestDiscrepancy2				
Scenario Outline: 
	 To test the functionality when user send the null header for Discrepancy Rule Engine
			 
	Given :Set the header values "<header>" 
	When i want to send the request for Discrepancy Rule Engine 
	Then Lets validate response code "<msgCode>" 
	And Lets validate the response message "<msg>" 
	
	Examples: 
		|header         |msgCode |msg      |
		|x-api-key:null |        |Forbidden|

				
@PositiveTestDiscrepancy3 
Scenario Outline: 
To test the functionality when user send the valid request for Discrepancy Rule Engine 
Given :Set the dobProposerFlag data into request "<apiKey>" and "<apiValue>"
When i want to send the request for Discrepancy Rule Engine 
Then Lets validate response code "<msgCode>" 
And Lets validate the response message "<msg>" 
				
 Examples: 
	|msgCode|msg    |apiKey         |apiValue|			
	| 200   |Success|dobProposerFlag|Y       |			

@NegativeTestDiscrepancy3
Scenario Outline: 
To test the functionality when user send the x-correlation-ID as null for Discrepancy Rule Engine 		
Given :Set the x-correlation-ID data into request "<apiKey>" and "<apiValue>" 
When i want to send the request for Discrepancy Rule Engine
Then Lets validate response code "<msgCode>" 
And Lets validate the response message "<msg>" 

Examples: 
	|msgCode |msg     |apiKey           | apiValue |
	|400     |Failure |X-Correlation-ID |          |
				
				
@NegativeTestDiscrepancy4
Scenario: 
	To test the functionality when user send the invalid request for Discrepancy Rule Engine 
 Given :Set testdata 
	
		|testData               | operationType|testUrl                             |msgCode |msg     |
		|{ "productName": "" }  | changeData   | /developer/discrepancy-rule-engine | 400    | Failure|
						
@NegativeTestDiscrepancy5
Scenario: 
	To test the functionality when user send the invalid request for Discrepancy Rule Engine 
 Given :Set testdata 
	
		|testData               | operationType|testUrl                             |msgCode |msg      |
		|{ "propNatFlag": "*&"} | changeData   | /developer/discrepancy-rule-engine | 400    | Failure |
		|{ "propNatFlag": "" }  | changeData   | /developer/discrepancy-rule-engine | 400    | Failure |
		|{ "propNatFlag": "24"} | changeData   | /developer/discrepancy-rule-engine | 400    | Failure |

