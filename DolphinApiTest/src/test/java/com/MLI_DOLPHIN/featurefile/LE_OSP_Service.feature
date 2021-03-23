@OSP_AllScenarios
Feature: To Test the functionality of OSP service 
Background:
Given Set the pre request data for OSP
|EndPoint             | Headers                                            |RequestFile           |CorrelationId |Gender|InsurdAge |ComittedPrimium |PaymentMode |
|/developer/lepremium | x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY | LE_OSP_Service.json  |3232323       |M     | 25       | 50000          | 03         | 

@OSP_PostiveTest01
Scenario Outline:: To test the functionnality of OSP premium calculation when pass premium 50000
When Send the POST request for OSP
Then Lets Validate the response code for OSP is"<ResponseCode>"
And Lets Validate the Response msgCode for OSP is"<MSgCode>"
And Lets Validate the repsonse message for OSP is"<RespMessage>"
And Let Validate the SumAssurd for OSP is"<SumAssurd>"
And Let Validate the ModelPremium for OSP is"<ModelPremium>"

Examples:
|ResponseCode | MSgCode |RespMessage |SumAssurd|ModelPremium |    
| 200         | 200     | Success    |550000   | 12500       |

@OSP_PostiveTest02
Scenario Outline:: To test the functionnality of OSP premium calculation when pass Diffrent Set Data
Given Lets Set the Insured Gander is"<InsuredGender>"
And Lest Set the Insured Age is"<InsuredAge>"
And Lets Set the Comitted Premium is"<ComittedPremium>"
And Lets Set the PaymentMode"<PaymentMode>"
When Send the POST request for OSP
Then Lets Validate the response code for OSP is"<ResponseCode>"
And Lets Validate the Response msgCode for OSP is"<MSgCode>"
And Lets Validate the repsonse message for OSP is"<RespMessage>"
And Let Validate the SumAssurd for OSP is"<SumAssurd>"
And Let Validate the ModelPremium for OSP is"<ModelPremium>"

Examples:
|ResponseCode | MSgCode |RespMessage |SumAssurd |ModelPremium |InsuredGender|InsuredAge|ComittedPremium|PaymentMode|   
| 200         | 200     | Success    |880000    | 20000       |M            |35        |80000          | 03        |
| 200         | 200     | Success    |1650000   | 150000      |M            |40        |150000         | 12        |


@OSP_PostiveTest03
Scenario Outline:: To test the functionnality of OSP Illustration when pass premium 50000
Given Lets Set the endpoint Url"<EndPointUrl>"
When Send the POST request for OSP
Then Lets Validate the response code for OSP is"<ResponseCode>"
And Lets Validate the Response msgCode for OSP is"<MSgCode>"
And Lets Validate the repsonse message for OSP is"<RespMessage>"
And Lest Validate the illustration is generated or not
Examples:
|ResponseCode | MSgCode |RespMessage |EndPointUrl               |   
| 200         | 200     | Success    |/developer/leillustration |

@OSP_PostiveTest04
Scenario Outline:: To test the functionnality of OSP illustration generate when pass Diffrent Set Data
Given Lets Set the Insured Gander is"<InsuredGender>"
And Lets Set the endpoint Url"<EndPointUrl>"
And Lest Set the Insured Age is"<InsuredAge>"
And Lets Set the Comitted Premium is"<ComittedPremium>"
And Lets Set the PaymentMode"<PaymentMode>"
When Send the POST request for OSP
Then Lets Validate the response code for OSP is"<ResponseCode>"
And Lets Validate the Response msgCode for OSP is"<MSgCode>"
And Lets Validate the repsonse message for OSP is"<RespMessage>"

Examples:
|ResponseCode | MSgCode |RespMessage | EndPointUrl                  |InsuredGender |InsuredAge|ComittedPremium|PaymentMode|   
| 200         | 200     | Success    | /developer/leillustration    |M             |35        |80000          | 06        |
| 200         | 200     | Success    | /developer/leillustration    |M             |40        |150000         | 12        |


@OSP_NegativeTest01
Scenario Outline:: To test the functionnality of OSP when pass Committed premium as null
Given Lets Set the Comitted Premium is"<ComittedPremium>"
And Lets Set the PaymentMode"<PaymentMode>"
When Send the POST request for OSP
Then Lets Validate the response code for OSP is"<ResponseCode>"
And Lets Validate the Response msgCode for OSP is"<MSgCode>"
And Lets Validate the repsonse message for OSP is"<RespMessage>"

Examples:
|ResponseCode | MSgCode |RespMessage |ComittedPremium|
| 200         | 500     | Failure    |               | 

@OSP_NegativeTest02
Scenario Outline:: To test the functionnality of OSP when pass invalid insurd Age
Given Lest Set the Insured Age is"<InsuredAge>"
When Send the POST request for OSP
Then Lets Validate the response code for OSP is"<ResponseCode>"
And Lets Validate the Response msgCode for OSP is"<MSgCode>"
And Lets Validate the repsonse message for OSP is"<RespMessage>"

Examples:
|ResponseCode | MSgCode |RespMessage  |InsuredAge  |
| 200         | 500     | Failure     |            |

@OSP_NegativeTest03
Scenario Outline:: To test the functionnality of OSP when pass invalid endPoint url
Given Lets Set the endpoint Url"<EndPointUrl>"
When Send the POST request for OSP
Then Lets Validate the response code for OSP is"<ResponseCode>"

Examples:
|ResponseCode | EndPointUrl                |
| 403         |/developer/leillustrationss |

@OSP_NegativeTest04
Scenario Outline:: To test the functionnality of OSP when pass correlationIdAs null
Given Lets Set the correlationId"<correlationID>"
When Send the POST request for OSP
Then Lets Validate the response code for OSP is"<ResponseCode>"
Then Lets Validate the response code for OSP is"<ResponseCode>"
And Lets Validate the Response msgCode for OSP is"<MSgCode>"
And Lets Validate the repsonse message for OSP is"<RespMessage>"

Examples:
|ResponseCode | MSgCode |RespMessage  |correlationID  |
| 200         | 400     |Bad Request  |               |
