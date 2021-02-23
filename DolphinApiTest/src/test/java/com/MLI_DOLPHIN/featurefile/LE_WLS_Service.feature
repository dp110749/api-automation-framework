@AllScenariosOfWLS
Feature: To test the functionality of WLS Service for premium and illustration

Background:

Given Set the pre test for request
|endPointUrl            |Requestheader                                      |RequestFile         |
|/developer/lepremium   |x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY |LE_WLS_Service.json |

Scenario Outline:To test the functionality of premium calculator for WLS
When I want to send the request
Then I want to validate the response code"<responseCode>"
And  I want to validate the response messageCode for WLS"<msgCode>"
And  I want to validate the response message for WLS"<message>"
And  I want to validate the response data for WLS"<outPutData>"
And  I want to validate the response appId and time

Examples:
|responseCode |msgCode |message  |outPutData       |
|200          |200     |Success  |140300902.71     |

@WLS_illustration
Scenario Outline:To test the functionality to generate illustration for WLS
Given Set the test data for WLS"<inputData>"
When I want to send the request
Then I want to validate the response code"<responseCode>"
And  I want to validate the response messageCode for WLS"<msgCode>"
And  I want to validate the response message for WLS"<message>"
And  I want to validate the response appId and time
And  i want to validate the response data

Examples:
|responseCode |msgCode |message  |inputData                 |
|200          |200     |Success  |/developer/leillustration |

@WLS_Negative_1
Scenario Outline:To test the functionality when user send invalid url for WLS
Given Set the test data for WLS"<inputData>"
When I want to send the request
Then I want to validate the response code"<responseCode>"
And  I want to validate the response appId and time
Examples:
|responseCode |inputData                    |
|403          |/developer121/leillustration |

@WLS_Negative_2
Scenario Outline:To test the functionality when user send header value null for WLS
Given Set the test data for WLS"<inputData>"
When I want to send the request
Then I want to validate the response code"<responseCode>"
And  I want to validate the response appId and time
Examples:
|responseCode |inputData  |
|403          |x-api-key: |

@WLS_Negative_3
Scenario: To test the functionality when user send wrong product name for WLS
Given Set the input request data for WLS

|inputData                                  |oparationType|responseCode |responseMessage|
|{"productName": "Max Life Super Term Plan"}|changeData   |200          |Failed         |
When I want to send the request
Then I want to validate the response code and message 

@WLS_Negative_4
Scenario: To test the functionality when user send product name as null for WLS
Given Set the input request data for WLS

|inputData           |oparationType|responseCode |responseMessage         |
|{"productName": " "}|changeData   |200          |Unknown product name    |
When I want to send the request
Then I want to validate the response code and message 

@WLS_Negative_5
Scenario: Validate the response when user remove the field from the request for WLS
Given Set the input request data for WLS

|inputData                         |oparationType|responseCode |responseMessage |
|{"committedPremium": "2000000.00"}|removeData   |200          |Success     |
When I want to send the request
Then I want to validate the response code and message 

@WLS_Negative_6		
Scenario Outline:To test the functionality when user send correlation ID as null for WLS
Given To Set the correlatinKey"<Key>" and value"<Value>" in the request for WLS
When I want to send the request
Then I want to validate the response message for WLS"<message>"  

Examples:
 |Key               |Value |message       |
 |X-Correlation-ID  |      | Bad Request  | 




