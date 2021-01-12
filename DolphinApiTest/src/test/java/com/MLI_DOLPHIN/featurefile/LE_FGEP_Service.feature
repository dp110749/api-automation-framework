@AllTestScenarioForFGEP
Feature: To Test functionality of pramium and illustration of FGEP Product
Background: 
Given  Set the preRequest test data for FGEP product 
|EndPointUrl          |         Header                                      |RequestFile           |
|/developer/lepremium | x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY  | LE_FGEP_Service.json |

Scenario Outline:To test the functionality of premium generator og FGEP
When I want to send the request for FGEP 
Then I want to validate the responseCode for FGEP"<ResponseCode>"
And I want to validate the responseMSg for FGEP"<ResponseMSG>"
And I want to validate the responseMsgCode for FGEP"<ResponseMSGCode>"
And I want to validate the sumAssured"<SummAssuerd>"
And I want to validate the AFYP"<AFYP>" 
And I want to validate the response time for FGEP

Examples:  
|ResponseCode |ResponseMSG |ResponseMSGCode |SummAssuerd         |AFYP              |
|200          |Success     |200             |377839.4635687189   |51783.54807692308 |

@FGEP_PositiveIllustrationGenerator_01
Scenario Outline: To Test the functionality of illustration generator for FGEP
Given I want to set the illustration endPointUrl"<EndPointUrl>" 
When I want to send the request for FGEP 
Then I want to validate the responseCode for FGEP"<ResponseCode>"
And I want to validate the responseMSg for FGEP"<ResponseMSG>"
And I want to validate the responseMsgCode for FGEP"<ResponseMSGCode>" 
And I want to validate the response time for FGEP
And I want to validate Illustration generated or not 

Examples:
|ResponseCode |ResponseMSG |ResponseMSGCode |EndPointUrl                |
|200          |Success     |200             | /developer/leillustration |

@FGEP_PossitiveTestForPremium_02
Scenario Outline:To test the functionality of premium generator with different data
Given I want to test data in request for FGEP
|TestData                       |OparationType   |
|{"committedPremium": "150000"} | changeData     |

When I want to send the request for FGEP 
Then I want to validate the responseCode for FGEP"<ResponseCode>"
And I want to validate the responseMSg for FGEP"<ResponseMSG>"
And I want to validate the responseMsgCode for FGEP"<ResponseMSGCode>"
And I want to validate the sumAssured"<SummAssuerd>"
And I want to validate the AFYP"<AFYP>" 
And I want to validate the response time for FGEP

Examples:  
|ResponseCode |ResponseMSG |ResponseMSGCode |SummAssuerd         |AFYP               |
|200          |Success     |200             |944598.6589217972   |129200.36538461539 |

@FGEP_PositiveIllustrationGenerator_02
Scenario Outline: To Test the functionality of illustration generator for FGEP
Given I want to set the illustration endPointUrl"<EndPointUrl>"
Given I want to test data in request for FGEP
|TestData                       |OparationType   |
|{"committedPremium": "160000"} | changeData     | 
When I want to send the request for FGEP 
Then I want to validate the responseCode for FGEP"<ResponseCode>"
And I want to validate the responseMSg for FGEP"<ResponseMSG>"
And I want to validate the responseMsgCode for FGEP"<ResponseMSGCode>" 
And I want to validate the response time for FGEP
And I want to validate Illustration generated or not 

Examples:
|ResponseCode |ResponseMSG |ResponseMSGCode |EndPointUrl                |
|200          |Success     |200             | /developer/leillustration |

@FGEP_NegativeTest_01
Scenario Outline:To test the functionality of FGEP When user send invalid Product Name
Given I want to test data in request for FGEP
|TestData                                |OparationType   |
|{"productName": "Max Life Guaranteed "} | changeData     |

When I want to send the request for FGEP 
Then I want to validate the responseCode for FGEP"<ResponseCode>"
And I want to validate the error responseMSg for FGEP"<ResponseMSG>"

Examples:
|ResponseCode |ResponseMSG          |
|200          |Unknown product name |

@FGEP_NegativeTest_02
Scenario Outline: To Test the functionality when user send premium as null for FGEP
Given I want to test data in request for FGEP
|TestData                       |OparationType   |
|{"committedPremium": ""}       | changeData     | 
When I want to send the request for FGEP 
Then I want to validate the responseCode for FGEP"<ResponseCode>"
And I want to validate the responseMSg for FGEP"<ResponseMSG>"
And I want to validate the responseMsgCode for FGEP"<ResponseMSGCode>" 
And I want to validate the response time for FGEP

Examples:
|ResponseCode |ResponseMSG |ResponseMSGCode |
|200          |Failure     |500             | 

@FGEP_NegativeTest_03
Scenario Outline: To Test the functionality of illustration generator for FGEP
Given I want to set the illustration endPointUrl"<EndPointUrl>" 
When I want to send the request for FGEP 
Then I want to validate the responseCode for FGEP"<ResponseCode>"
And I want to validate the fofbidden message"<ResponseMSG>"

Examples:
|ResponseCode |ResponseMSG   |EndPointUrl                  |
|403          |Forbidden     | /developerss/leillustration |

@FGEP_NegativeTest_04
Scenario Outline:Validate the response of FGEP When user send correlation id as null
Given I want to set the key"<Key>" and Value"<Value>" for FGEP 
When I want to send the request for FGEP 
Then I want to validate the responseCode for FGEP"<ResponseCode>"
And I want to validate the responseMSg for FGEP"<ResponseMSG>"

Examples:
 |Key               |Value |ResponseCode|ResponseMSG|
 |X-Correlation-ID  |      |200         |Bad Request|


