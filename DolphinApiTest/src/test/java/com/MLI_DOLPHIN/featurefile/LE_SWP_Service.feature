@SWP_ServiceTest
Feature: To test the functionality of SWP product for premium and illustration
Background:
Given Set the pre request test data for SWP
|EndPointUrl          |Header                                              |RequestFile         |correlationId  |commitedPremium |productName                |MethodType|
|/developer/lepremium |x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY  |LE_SWP_Service.json |123433         |260000          |Max Life Smart Wealth Plan |POST      |

@SWP_PositiveTest001
Scenario: To calculate the premium when pass premium 125000 for SWP
Given To set the expected data 
|ExpResponseCode|ExpResponseMsgCode|ExpResponseMessage|ExpATP       |
|200            |200               |Success           |260000    | 
When Send the POST request for SWP
Then Lets Validate the response code for SWP
And Lets Validate response Msg Code and message for SWP
And Lets validate calculated AFYP value 

@SWP_PositiveTest002
Scenario Outline: To generate illustration for SWP
Given To set the expected data 
|ExpResponseCode|ExpResponseMsgCode|ExpResponseMessage|ExpATP       |
|200            |200               |Success           |             | 
Given Set the endPoint url"<endpoint_URL>"
When Send the POST request for SWP
Then Lets Validate the response code for SWP
And Lets Validate response Msg Code and message for SWP
And Lets validate illustration is generated or not

Examples:
|endpoint_URL              |
|/developer/leillustration | 

@SWP_PositiveTest003
Scenario Outline: : To generate the AFYP when user pass premium 160000
Given To set the expected data 
|ExpResponseCode|ExpResponseMsgCode|ExpResponseMessage|ExpAFYP       |
|200            |200               |Success           | 160000   | 

Given Set the premium in request"<committedPremium>"
When Send the POST request for SWP
Then Lets Validate the response code for SWP
And Lets Validate response Msg Code and message for SWP
And Lets validate calculated AFYP value

Examples:
|committedPremium |
|160000           |

@SWP_PositiveTest004
Scenario Outline: : To generate the illustration when user pass premium 160000
Given To set the expected data 
|ExpResponseCode|ExpResponseMsgCode|ExpResponseMessage|ExpAFYP  |
|200            |200               |Success           |         | 
Given Set the premium in request"<committedPremium>"
Given Set the endPoint url"<endpoint_URL>"
When Send the POST request for SWP
Then Lets Validate the response code for SWP
And Lets Validate response Msg Code and message for SWP
And Lets validate illustration is generated or not
Examples:
|committedPremium  |endpoint_URL              |
|160000            |/developer/leillustration | 

@SWP_Negative_01
Scenario Outline: : To generate the AFYP when user pass premium -160000
Given To set the expected data 
|ExpResponseCode|ExpResponseMsgCode|ExpResponseMessage|ExpAFYP  |
|200            |500               |Failure           |         | 

Given Set the premium in request"<committedPremium>"
When Send the POST request for SWP
Then Lets Validate the response code for SWP
And Lets Validate response Msg Code and message for SWP
Examples:
|committedPremium  |
|-160000           |

@SWP_Negative_02
Scenario Outline: : To generate the illustration when user pass premium -160000
Given To set the expected data 
|ExpResponseCode|ExpResponseMsgCode|ExpResponseMessage|ExpAFYP  |
|200            |500               |Failure           |         | 
Given Set the premium in request"<committedPremium>"
Given Set the endPoint url"<endpoint_URL>"
When Send the POST request for SWP
Then Lets Validate the response code for SWP
And Lets Validate response Msg Code and message for SWP
Examples:
|committedPremium  |endpoint_URL               |
|-160000            |/developer/leillustration | 

@SWP_Negative_03
Scenario Outline: : To test the functionality of SWP when user pass correlation id as null
Given To set the expected data 
|ExpResponseCode|ExpResponseMsgCode|ExpResponseMessage|ExpAFYP  |
|200            |400               |Bad Request       |         | 
Given Set the correlationID"<CorrelationID>"
When Send the POST request for SWP
Then Lets Validate the response code for SWP
And Lets Validate response Msg Code and message for SWP
Examples:
|CorrelationID |
|              |

@SWP_Negative_04
Scenario Outline: : To test the functionality of SWP when user pass header as null
Given To set the expected data 
|ExpResponseCode|ExpResponseMsgCode|ExpResponseMessage|ExpAFYP  |
|403            |                  | Forbiden         |         | 
Given Set the header"<header>"
When Send the POST request for SWP
Then Lets Validate the response code for SWP
Examples:
|header          |
|x-api-key:      |

@SWP_Negative_05
Scenario Outline: : To generate the illustration when user pass invalid endPoint
Given To set the expected data 
|ExpResponseCode|ExpResponseMsgCode|ExpResponseMessage|ExpAFYP  |
|403            |                  |                  |         | 

Given Set the endPoint url"<endpoint_URL>"
When Send the POST request for SWP
Then Lets Validate the response code for SWP

Examples:
 |endpoint_URL                 |
 |/developer/leillustrationsed | 




 
 
