@MyAgentAllScenarios 
Feature: TO Test the functionality of MyAgent MS Service 

Background: 
	Given : Set the preRequest set of data 
	
		|url                | header                                                |jsonFileqwq    |
		|/developer/myagent  | x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY   | MyAgent.json  |
@positiveTest		
Scenario Outline: 
	To Test the functionality when Send Valid Request For MyAgent 

	When I want to send the request for MyAgent 
	Then I want to validate the response code for myAgent"<reponseCode>" 
	And I want to validate the message code for myAgent "<msgCode>" 
	And I want to validate the message description for myAgent "<msgDescription>" 
	And Lets validate response appId response time for MyAgent 
	
	Examples: 
		|reponseCode|msgCode|msgDescription|
		|200        |200    |Success       |
 @positiveTest
Scenario: To test the functionality of myAgent Service the with multiple data 
	Given Set the Test data for MyAgent Service 
	
		|TestData              |oparationType |responseData                   |msg                                              | msgCode  |
		|{"agentId1":"615118"} | changeData   | Radhika Sareen                | Agent Valid                                     | 200      |  
		|{"agentId1":"447660"} | changeData   | Salzina Cruzinha Fernandes    | Agent Valid                                     | 200      | 	
		|{"agentId1":"655393"} |changeData    | null                          | Agent655393 Not Valid : Agent channel does not match  | 200      |		
 
@negativeTest		
Scenario: To test the functionality of myAgent when pass Invalid Agent_ID
	Given Set the Test data for MyAgent Service 
	
		|TestData                |oparationType |responseData            |msg               | msgCode  |
		|{"agentId1":"000000"}   | changeData   | null                   | Failure          | 500      |  
		|{"agentId1":""}         | changeData   | null                   | Failure          | 400      |
		|{"agentId1":"asnasj"}   | changeData   | null                   | Failure          | 500      |
		|{"customerSignDate": ""}| changeData   | null                   | Failure          |400       |
#        |{"agentId1": "447660"}  |removeData    | null                   |Bad Request       |400       |  

@negativeTest			
Scenario Outline: To test the functionality of when send the header as null

	Given I want to set test data"<testheader>"	
	When I want to send the request for MyAgent 
	Then I want to validate the response code for myAgent"<reponseCode>" 
	
	Examples: 
	|testheader        |reponseCode |
	| x-api-key:null   |403         |
	
@negativeTest		
Scenario Outline: To test the functionality of when send the invaild endPoint

	Given I want to set test data"<testheader>"	
	When I want to send the request for MyAgent 
	Then I want to validate the response code for myAgent"<reponseCode>" 
	
	Examples: 
	|testheader               |reponseCode |
	| /developer/myagent121   |403         |
	
	
		
		
		
		
		
		
