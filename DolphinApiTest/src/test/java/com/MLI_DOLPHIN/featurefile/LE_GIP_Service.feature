@AllScenarios_Test_For_GIP
Feature: To test the functionality of premium calculator and illustration generator of GIP
Background:
Given Set the pre request test data for GIP
|EndPointUrl           |Header                                             |RequestFile         |
|/developer/lepremium |x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY  |LE_GIP_Service.json |  

@GIP_forPremiumCalculation_01
Scenario: To Test premium calculater for GIP
Given I want to set the validation Data
|ResponseCode|ResponseMsgCode|ResponseMessage|OutPutSumAssurred |
|200         | 200           | Success       |3208054           |

When I want send the request For GIP
Then I want to validate the responseCode for GIP
And I want to validate the responseMsgCode for GIP
And I want to validate the responseMessage for GIP
And I want to validate the calaculated Sumassured for GIP

@GIP_forIllustration_02
Scenario Outline:To Test the functionality of Illustration generator service
Given I want to set the validation Data
|ResponseCode|ResponseMsgCode|ResponseMessage|OutPutSumAssurred |
|200         | 200           | Success       |                  |

Given I want to set the illustration URL"<illustrationUrl>"
When I want send the request For GIP
Then I want to validate the responseCode for GIP
And I want to validate the responseMsgCode for GIP
And I want to validate the responseMessage for GIP
And I want to validate the Illustration is generated for GIP or not

Examples:
|illustrationUrl| 
|/developer/leillustration|

@GIP_testWithMultipleData
Scenario: To Test the Functionality of GIP premium generator with multiple set of data
Given I want to set the test data in GIP request
|TestData                       |OparationType   |ResponseCode|ResponseMsgCode|ResponseMesg|OutPutData                             |
|{"committedPremium": "150000"} | changeData     | 200        |200            |Success     | 2406041                               |
|{"productName": "Assurd Wealth Plan"}|changeData |200        |               |Unknown product name||
|{"committedPremium": "90000"}  | changeData     | 200        |500            |Failure     | Minimum Premium allowed is Rs. 100000 |
|{"committedPremium": ""}       | removeData     | 200        |               |Bad Request |                                       |


@GIP_NegativeTest_02
Scenario Outline:: To Test the functionality GIP Service when user send the invalid end pooint url
Given I want to set the validation Data
|ResponseCode |ResponseMsgCode|ResponseMessage  |OutPutSumAssurred |
|403          |               | Forbidden       |                  |

Given I want to set the illustration URL"<illustrationUrl>"
When I want send the request For GIP
Then I want to validate the responseCode for GIP
And I want to validate response Error Msg

Examples:
|illustrationUrl| 
|/developeras/leillustration|

@GIP_NegativeTest_03
Scenario: To Test the functionality GIP Service when user send the invalid end pooint url
Given I want to set the validation Data
|ResponseCode |ResponseMsgCode|ResponseMessage  |OutPutSumAssurred |
|403          |               | Forbidden       |                  |

Given Set the pre request test data for GIP
|EndPointUrl           |Header                     |RequestFile         |
|/developer/lepremium  |x-api-key:                 |LE_GIP_Service.json |  

When I want send the request For GIP
Then I want to validate the responseCode for GIP
And I want to validate response Error Msg

@GIP_NegativeTest_04		
Scenario Outline:To test the functionality when user send correlation ID as null for GIP
Given I want to set the validation Data
|ResponseCode |ResponseMsgCode|ResponseMessage  |OutPutSumAssurred |
|200          |               | Bad Request     |                  |

Given To Set the correlatinKey"<Key>" and value"<Value>" in the request for GIP
When I want send the request For GIP
Then I want to validate response Error Msg
And  I want to validate the responseCode for GIP

Examples:
 |Key               |Value |
 |X-Correlation-ID  |      |

