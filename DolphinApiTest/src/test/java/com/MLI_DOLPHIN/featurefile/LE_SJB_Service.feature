Feature: To Test the functionality of SJB product 
Background:
Given Set the pre request of data for SJB product 
|EndPointURL          | Header                                             | RequestFile         | CorrelationID | InsuredGender | SumAssured | Channel|
|/developer/lepremium | x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY | LE_SJB_Service.json | 12232         | T             | 1000000    | A      |

@SJB_PositiveTest
Scenario: To generate AFYP amount when user pass sumassured 680000 for SJB
Given Set the Expected data
|ResponseStatusCode|ResponseMsgCode|ResponseMessage|ResponseAFYP|
|200               |200            |Success        |4466.6      |
When send the POST request for SJB
When Lets Validate the response code
And  Lets Validate the response MsgCode and Message
And Lets validate the AFYP Value 