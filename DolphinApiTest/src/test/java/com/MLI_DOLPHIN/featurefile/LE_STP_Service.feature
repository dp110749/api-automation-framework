@AllTest_of_STP
Feature: To test the functionality of STP product for premium and illustration
Background:
Given Set the pre request test data for STP
|EndPointUrl          |Header                                             |RequestFile         |
|/developer/lepremium |x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY  |LE_STP_Service.json | 

@PremiumGenerator_001
Scenario: To Test premium calculator 
Given I want to set output data for STP
|ResponseCode|ResponseMsgCode|ResponseMessage|OutPutSumAssurred |
|200         | 200           | Success       |7000000.00        |

When I want to send the request for STP
Then I want to validate response code for STP 
And I want to validate response MsgCode for STP
And I want to validate response Message for STP
And I want to validate response output data for STP
And I want to validate the response time for STP

@illustrationGenerator_001
Scenario Outline:: To test the functionality of illustration generator  for STP
Given I want to set output data for STP
|ResponseCode|ResponseMsgCode|ResponseMessage|OutPutSumAssurred |
|200         | 200           | Success       |null              |
Given I want to set the endpoint url for illustration"<endPoint>" 
When I want to send the request for STP
Then I want to validate response code for STP 
And I want to validate response MsgCode for STP
And I want to validate response Message for STP

Examples:
|endPoint                  |
|/developer/leillustration |

@PremiumGenerator_002
Scenario: To test the functionality of premium generator with multiple set of data for STP
Given I want to set multiple set of data for STP
|inputData                    |oparationType   |MsgCode|Message    |expectedSumAssured|ExpectedpATP|
|{"sumAssured": "4000000.00"} |changeData      |200    |Success    |4000000.00        |7520        |
|{"sumAssured": "2800000.00"} |changeData      |200    |Success    |2800000.00        |5264        |
|{"sumAssured": ""}           |changeData      |500    |Failed     |null              |null        |
|{"sumAssured": ""}           |removeData      |500    |Failed     |null              |null        |

@illustration_002
Scenario Outline: To test the functionality of illustration generator with multiple set of data for STP
Given I want to set the endpoint url for illustration"<endPoint>" 
Given I want to set multiple set of data for STP to generate illustration
|inputData                    |oparationType   |MsgCode|Message    |
|{"sumAssured": "4000000.00"} |changeData      |200    |Success    |
|{"sumAssured": "2800000.00"} |changeData      |200    |Success    |


Examples:
|endPoint                  |
|/developer/leillustration |

@illustration_Negative_001
Scenario Outline: To test the functionality of illustraion generator when sumAssured is less with require 
Given I want to set the endpoint url for illustration"<endPoint>" 
Given I want to set multiple set of data for STP to generate illustration
|inputData                    |oparationType   |MsgCode|Message    |
|{"sumAssured": "100000.00"}  |changeData      |500    |Failure    |

Examples:
|endPoint                  |
|/developer/leillustration |

@premium_Negative_001
Scenario: To test the functionality of premium generator when sumAssured is less with require 
Given I want to set multiple set of data for STP to generate illustration
|inputData                    |oparationType   |MsgCode|Message    |
|{"sumAssured": "200000.00"}  |changeData      |500    |Failure    |

#@premium_Negative_001
#Scenario: To test the functionality of premium generator when sumAssured is less with require 
#Given I want to set multiple set of data for STP to generate illustration
#|inputData                    |oparationType   |MsgCode|Message    |
#|{"sumAssured": "200000.00"}  |changeData      |500    |Failure    |

@illustration_Negative_002
Scenario Outline: To test the functionality of STP when pass wrong endpoint url
Given I want to set the endpoint url for illustration"<endPoint>" 
Given I want to set output data for STP
|ResponseCode|ResponseMsgCode|ResponseMessage |OutPutSumAssurred |
|403         |   null        | null           |   null           |

When I want to send the request for STP
Then I want to validate response code for STP 

Examples:
|endPoint                 |
|/developer/leilustration |

@illustration_Negative_003
Scenario Outline: To test the functionality of STP when pass wrong endpoint url
Given I want to set the header for request"<Header>" 
Given I want to set output data for STP
|ResponseCode|ResponseMsgCode|ResponseMessage |OutPutSumAssurred |
|403         |   null        | Forbidden      |   null           |

When I want to send the request for STP
Then I want to validate response code for STP 
And I want to validate the forbidden message

Examples:
|Header        |
|x-api-key:    |

@illustration_Negative_004
Scenario Outline: To test the functionality of STP when pass correlationId
Given To Set the correlatinKey"<Key>" and value"<Value>" for STP 
Given I want to set output data for STP
|ResponseCode|ResponseMsgCode|ResponseMessage   |OutPutSumAssurred |
|200         |   400        | Bad Request       |   null           |

When I want to send the request for STP
Then I want to validate response code for STP 
And I want to validate the bad request message

Examples:
|Key               |Value |
|X-Correlation-ID  |      |


