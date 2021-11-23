@ClientLevelCheckAllScenarios
Feature: To test the functionality of client level check service
Background:
Given To set the pre request data for client level check
|MethodType|EndPointUrl                               |Header                                             |RequestBody                 |XcorrelationId |insuredAge |insuredClientId|currentBaseProductCode|currentBaseSumAssured |currentRiderProduct|currentRiderSumAssured|
|POST      |/developer/dev-dolphin-client-level-check |x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY |ClientLevelCheckService.json|21213          | 6         |6000053009     |  DCCCP               |5000000.00            |VP02               |249567                |

Scenario Outline: To test the functionality of client level check when pass exact allowed sumAssured for base product 
When Send the request for client level check 
Then Validate the response code"<ResponseCode>"
And Validat the response message code"<MsgCode>"
And validate the response message"<Message>"

Examples:
|ResponseCode |MsgCode |Message | 
|200          |200     |Success |

@CLC_PositiveTest001
Scenario Outline: To test the functionality of client level check when pass exact allowed sumAssured for rider 
Given Set the sumassured data for Rider"<RiderSumAssured>"
When Send the request for client level check 
Then Validate the response code"<ResponseCode>"
And Validat the response message code"<MsgCode>"
And validate the response message"<Message>"

Examples:
|RiderSumAssured|ResponseCode |MsgCode |Message | 
|350000         |200          |200     |Success |

@CLC_NegativeTest001
Scenario Outline: To Test the CLC Service when pass base sumAssured as ziro.
Given Set the sumassured data for Rider"<RiderSumAssured>"
When Send the request for client level check 
Then Validate the response code"<ResponseCode>"
And Validat the response message code"<MsgCode>"
And validate the response message"<Message>"

Examples:
|RiderSumAssured|ResponseCode |MsgCode |Message | 
|               |200          |400     |Failure |

@CLC_NegativeTest002
Scenario Outline: To Test the CLC Service when pass base sumAssured as alphabet
Given Set the sumassured data for Rider"<RiderSumAssured>"
When Send the request for client level check 
Then Validate the response code"<ResponseCode>"
And Validat the response message code"<MsgCode>"
And validate the response message"<Message>"

Examples:
|RiderSumAssured   |ResponseCode |MsgCode |Message | 
|  abc             |200          |400     |Failure |









       