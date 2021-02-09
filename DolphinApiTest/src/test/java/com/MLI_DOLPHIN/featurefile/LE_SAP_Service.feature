@AllTestOf_SAP
Feature: To Test the functionality of SAP service
Background:
Given Set pre set of test data for SAP
|EndPointUrl          |Header                                             |JsonRequestFile    |
|/developer/lepremium |x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY |LE_SAP_Service.json|

@SAP_forPremiumCalculation_01
Scenario Outline:To Test premium calculater for SAP
When I want send the request for SAP
Then I want to validate the responseCode"<ResponseCode>"
And I want to validate the responseMsgCode"<ResponseMsgCode>"
And I want to validate the responseMessage"<ResponseMessage>"
And I want to validate the calaculated Sumassured"<OutPutSumAssurred>"

Examples:
|ResponseCode|ResponseMsgCode|ResponseMessage|OutPutSumAssurred |
|200         | 200           | Success       |550998.5931169256 |

@SAP_forPremiumCalculation_02
Scenario: To test the functionality when user send the new premium value
Given pass the set of test data 
|TestData                       |OparationType   |ResponseCode|ResponseMsgCode|ResponseMesg|OutPutData          |
|{"committedPremium": "150000"} | changeData     | 200        |200            |Success     | 688748.241396157   |
|{"committedPremium": ""}       | changeData     | 200        |500            |Failure     |                    |
|{"committedPremium": ""}       | removeData     | 200        | 500           |Failure     |                    |

@SAP_Positive_02	
Scenario Outline:To Test Illustration for SAP
Given I want to set url for Illustration"<IllustrationUrl>"
When I want send the request for SAP
Then I want to validate the responseCode"<ResponseCode>"
And I want to validate the responseMsgCode"<ResponseMsgCode>"
And I want to validate the responseMessage"<ResponseMessage>"
And I want to validate illustration is generated or not

Examples:
|ResponseCode|ResponseMsgCode|ResponseMessage|IllustrationUrl           |
|200         | 200           | Success       |/developer/leillustration |

@SAP_Negative_02		
Scenario Outline:To test the functionality when user send correlation ID as null for SAP
Given To Set the correlatinKey"<Key>" and value"<Value>" in the request for SAP
When I want send the request for SAP
Then I want to validate the responseMessage"<message>"  

Examples:
 |Key               |Value |message      |
 |X-Correlation-ID  |      |Bad Request  | 
 
 @SAP_Negative_03
Scenario Outline:To test the functionality when user send header value null for SAP
Given Set the test data for SAP"<inputData>"
When I want send the request for SAP
Then I want to validate the responseCode"<responseCode>"

Examples:
|responseCode |inputData  |
|403          |x-api-key: |

@SAP_Negative_04
Scenario Outline:To test the functionality when user send invalid url for SAP
Given Set the test data for SAP"<inputData>"
When I want send the request for SAP
Then I want to validate the responseCode"<responseCode>"

Examples:
|responseCode |inputData                     |
|403          | /developer/sssleillustration |

 
 
 
 
