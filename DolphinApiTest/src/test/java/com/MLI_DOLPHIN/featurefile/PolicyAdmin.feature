@Policyadmin 
Feature: To test the functionality of policy admin for create and update 
Background: 
	Given set pre set of data 
	
		|jsonFile        |url                   |header|
		|PolicyAdmin.json|/developer/policyadmin|x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY|
	Scenario Outline:
	When I want to send the request for policyAdmin
	Then I want to validate the response code "<reponseCode>"
	And I want to validate the message code "<msgCode>"
	And I want to validate the message description "<msgDescription>"
		
Examples:
|reponseCode|msgCode|msgDescription|
|200        |200    |Success       |		
