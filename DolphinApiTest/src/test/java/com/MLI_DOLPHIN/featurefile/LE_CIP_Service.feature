@CIPAllTestScenarios
Feature: To test the functionality of CIP LE Service 
Background:
Given Set pre request data for CIP plan
|EndPoint             |Header                                            |RequestFile        |CorrelationID|InsuredAge|Channel|ComittedPremium|productName|MethodType|
|/developer/lepremium |x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY|LE_CIP_Service.json|21344        | 27       |K      |15000          |Max Life Cancer Insurance Plan|POST|

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

@PositiveTest0031
Scenario Outline: Test the functionality of CIP plan when pass diffrent input premium
Given Lets set the insurd age"<insuredAge>"
And Lets set the channel"<Channle>"
And Lets set the committed premium"<CommittedPremium>"
When Send the POST Request for CIP
Then Lets validate the repsone code"<ResponseCode>"
And Lets Validate the repsonse message Code"<MsgCode>"
And Lets Validate the response Message"<Msg>"
And Lest Validate the response"<AFYP>"

Examples:
|insuredAge|Channle|CommittedPremium |ResponseCode|MsgCode|Msg     |AFYP|
|40        |A      |250000           |200         |200    |Success |11000|
|45        |A      |280000           |200         |200    |Success |14020|
|18        |A      |150000           |200         |500    |Failure |     |

@PositiveTest0032
Scenario Outline: Test the functionality of CIP plan when pass diffrent input premium
Given Lets set the insurd age"<insuredAge>"
And Lets set the Url for CIP"<endPoint>"
And Lets set the channel"<Channle>"
And Lets set the committed premium"<CommittedPremium>"
When Send the POST Request for CIP
Then Lets validate the repsone code"<ResponseCode>"
And Lets Validate the repsonse message Code"<MsgCode>"
And Lets Validate the response Message"<Msg>"

Examples:
|insuredAge|Channle|CommittedPremium |ResponseCode|MsgCode|Msg     |endPoint                  |
|40        |A      |250000           |200         |200    |Success |/developer/leillustration |
|45        |A      |280000           |200         |200    |Success |/developer/leillustration |
|18        |A      |150000           |200         |500    |Failure |/developer/leillustration |

