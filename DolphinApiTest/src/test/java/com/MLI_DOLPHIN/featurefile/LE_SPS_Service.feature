@SPSTest
Feature: To Test functionality of SPS service
Background: Set the test data
Given Set the request test data for SPS

|url                   |header                                             |requestFile|MethodType|
|/developer/lepremium  |x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY |LE_SPS.json|POST      |

Scenario Outline: 
	To test the Functionality LE SPS servcie when user send Valid Request for Premium 
	When I want send the request 
	Then I want to validate the response data"<sumAssured>" 
	And I want to validate the response Msgcode"<msgCode>" 
	And I want to validate the response message"<message>" 
	And I want to validate the response AppId and time 
	Examples: 
		|msgCode|message |sumAssured   |
		|200    |Success |250000       |
		

@NegativeTest_1		
Scenario: To test the functionality when user send the TRAD product instead of ULIP.
   Given I want to set the test data 
		|msgCode|message |TestData                                          |oparatioType |
		|500    |Failed |{"productName":"Max Life Fast Track Super Plan"}  |changeData   |
		|500    |Failed |{"ageOfInsured": ""}                              |changeData   |
		|500    |Failed |{"committedPremium":"000000.00"}                  |changeData   |
   When I want send the request 
   Then I want to validate the response Msgcode 
   And  I want to validate the response message
   And  I want to validate the response AppId and time
 
 @NegativeTest_2
 Scenario Outline: To test the functionality when user send the invalid url
 
 Given I want to set the test Data"<url>"
 When I want send the request
 And I want to validate the response Msgcode"<responseCode>"  
 Examples:
 |responseCode|url                    |
 |403         |/developer/lepremiumxx |
 
 @NegativeTest_3
 Scenario Outline: To test the functionality when user send the invalid header 
 
 Given I want to set the test Data"<header>"
 When I want send the request
 And I want to validate the response Msgcode"<responseCode>"  
 Examples:
 |responseCode|header                            |
 |403         |x-api-key:DTUDHv9UVG8cVTasdas     |
 
 @NegativeTest_4		
Scenario: To test the functionality when user send bad request(remove the filed from request)
   Given I want to set the test data 
		|msgCode |message              |TestData                                       |oparatioType |
		|500     |Unknown product name |{"productName":"Max Life Shiksha Plus Super"}  |removeData   |
   When I want send the request
   Then I want to validate the error message
                
 @NegativeTest_5		
Scenario Outline:To test the functionality when user send bad request(pass correlation ID)
   Given I want to set the correlatinKey"<Key>" and value"<Value>" in the request
   When I want send the request
 #  Then I want to validate the response error message"<message>"
And I want to validate the response message"<message>"  
 Examples:
 |Key               |Value |message       |
 |X-Correlation-ID  |      | 400          | 
 
 @NegativeTest_6
 Scenario Outline: To test the functionality when user send the valid request for illustration
 
 Given I want to set the test Data"<url>"
 When I want send the request
 And I want to validate the response Msgcode"<responseCode>"  
 Examples:
 |responseCode|url                       |
 |200         |/developer/leillustration |
  
