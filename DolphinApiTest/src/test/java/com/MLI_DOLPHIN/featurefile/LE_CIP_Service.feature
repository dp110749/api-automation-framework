@CIPAllTestScenarios
Feature: To test the functionality of CIP LE Service 
Background:
Given Set pre request data for CIP plan
|EndPoint             |Header                                            |RequestFile        |CorrelationID|InsuredAge|Channel|ComittedPremium|productName|
|/developer/lepremium |x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY|LE_CIP_Service.json|21344        | 27       |K      |15000          |Max Life Cancer Insurance Plan|

@PositiveTest001
Scenario Outline: Test the functionality of CIP plan when pass premium 15000
When Send the POST Request for CIP
Then Lets validate the repsone code"<ResponseCode>"
And Lets Validate the repsonse message Code"<MsgCode>"
And Lets Validate the response Message"<Msg>"
And Lest Validate the response"<AFYP>"

Examples:
|ResponseCode|MsgCode|Msg     |AFYP|
|200         |200    |Success |5760|

@PositiveTest002
Scenario Outline: Test the functionality of CIP illustration plan when pass premium 15000
Given Lets set the Url for CIP"<endPoint>"
When Send the POST Request for CIP
Then Lets validate the repsone code"<ResponseCode>"
And Lets Validate the repsonse message Code"<MsgCode>"
And Lets Validate the response Message"<Msg>"
And Lets validate the CIP Illustration
Examples:
|ResponseCode|MsgCode|Msg     |endPoint                  |
|200         |200    |Success |/developer/leillustration |