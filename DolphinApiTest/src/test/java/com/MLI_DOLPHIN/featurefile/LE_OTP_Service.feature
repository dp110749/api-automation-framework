@OTP_AllTest
Feature: To Test the functionality of LE OTP product 
Background: 
Given To set the pre request set of data for OTP
|EndPointUrl           |Header                                             |RequestFile         |CorrelationId  |InsuredGender|SumAssured   |
|/developer/lepremium  |x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY |LE_OTP_Service.json |1202122        |M            |2500000      | 

@OTP_PositiveTest
Scenario Outline: To test the functionality generated premium when pass sumAssurd 250000
When Lets send the POST request for OTP product
Then Lets validate the reponse code for OTP"<ResponseCode>"
And Lets validate the response Msg Code for OTP"<ResponseMsgCode>"
And Lets validate the response message for OTP"<ResponseMsg>"
And Let validate the committed premium for OTP"<CommittedPremium>"

Examples:
|ResponseCode|ResponseMsgCode|ResponseMsg |CommittedPremium |
|200         |200            |Success     | 4307            |

@OTP_PositiveTest001
Scenario Outline: To test the functionality generated illustration when pass sumAssurd 250000
Given Set the url for illustration"<endPointulr>"
When Lets send the POST request for OTP product
Then Lets validate the reponse code for OTP"<ResponseCode>"
And Lets validate the response Msg Code for OTP"<ResponseMsgCode>"
And Lets validate the response message for OTP"<ResponseMsg>"
And Lets validate the response of illustration

Examples:
|ResponseCode|ResponseMsgCode|ResponseMsg |    endPointulr           |
|200         |200            |Success     |/developer/leillustration |

@OTP_PositiveTest002
Scenario Outline: To test the functionality generated illustration when pass diffrent input sumAssurd 
Given Set the url for illustration"<endPointulr>"
And Lets Set the SumAssurd in request"<SumAssurd>"
When Lets send the POST request for OTP product
Then Lets validate the reponse code for OTP"<ResponseCode>"
And Lets validate the response Msg Code for OTP"<ResponseMsgCode>"
And Lets validate the response message for OTP"<ResponseMsg>"
And Lets validate the response of illustration

Examples:
|ResponseCode|ResponseMsgCode|ResponseMsg |    endPointulr           |SumAssurd |
|200         |200            |Success     |/developer/leillustration |5000000   |
|200         |200            |Success     |/developer/leillustration |3000000   |

@OTP_PositiveTest003
Scenario Outline: To test the functionality generated premium when pass diffrent input sumAssurd 
Given Lets Set the SumAssurd in request"<SumAssurd>"
When Lets send the POST request for OTP product
Then Lets validate the reponse code for OTP"<ResponseCode>"
And Lets validate the response Msg Code for OTP"<ResponseMsgCode>"
And Lets validate the response message for OTP"<ResponseMsg>"
And Let validate the committed premium for OTP"<CommittedPremium>"

Examples:
|ResponseCode|ResponseMsgCode|ResponseMsg |SumAssurd |CommittedPremium |
|200         |200            |Success     |5000000   |7139             |
|200         |200            |Success     |3000000   |5168.4           |

@OTP_NegativeTest001
Scenario Outline: To test the functionality when pass sumAssurd Zero
Given Lets Set the SumAssurd in request"<SumAssurd>"
When Lets send the POST request for OTP product
Then Lets validate the reponse code for OTP"<ResponseCode>"
And Lets validate the response Msg Code for OTP"<ResponseMsgCode>"
And Lets validate the response message for OTP"<ResponseMsg>"

Examples:
|ResponseCode|ResponseMsgCode|ResponseMsg |SumAssurd  |
|200         |500            |Failure     |0.0        |
|200         |500            |Failed      |           |

@OTP_NegativeTest002
Scenario Outline: To test the functionality when pass sumAssurd as negative
Given Lets Set the SumAssurd in request"<SumAssurd>"
When Lets send the POST request for OTP product
Then Lets validate the reponse code for OTP"<ResponseCode>"
And Lets validate the response Msg Code for OTP"<ResponseMsgCode>"
And Lets validate the response message for OTP"<ResponseMsg>"


Examples:
|ResponseCode|ResponseMsgCode|ResponseMsg |SumAssurd  |
|200         |500            |Failure     |-5000000   |

@OTP_NegativeTest003
Scenario Outline: To test the functionality when pass invalid insurd gender as 
Given Lets Set the insurd gender"<insurdGender>"
When Lets send the POST request for OTP product
Then Lets validate the reponse code for OTP"<ResponseCode>"
And Lets validate the response Msg Code for OTP"<ResponseMsgCode>"
And Lets validate the response message for OTP"<ResponseMsg>"

Examples:
|ResponseCode|ResponseMsgCode|ResponseMsg |insurdGender  |
|200         |500            |Failure     |P             |
|200         |500            |Failure     |              |

@OTP_NegativeTest004
Scenario Outline: To test the functionality when pass invalid endpoint 
Given Set the url for illustration"<endPointulr>"
When Lets send the POST request for OTP product
Then Lets validate the reponse code for OTP"<ResponseCode>"

Examples:
|ResponseCode |    endPointulr             |
|403          |/developer/leillustrationsd |

@OTP_NegativeTest005
Scenario Outline: To test the functionality when pass header as null 
Given Set the header for OTP"<header>"
When Lets send the POST request for OTP product
Then Lets validate the reponse code for OTP"<ResponseCode>"

Examples:
|ResponseCode |    header  |
|403          |x-api-key:  |

@OTP_NegativeTest006
Scenario Outline: To test the functionality when pass correlation id as null 
Given Lets Set the correlationId for OTP"<correlationId>"
When Lets send the POST request for OTP product
Then Lets validate the reponse code for OTP"<ResponseCode>"
And Lets validate the response Msg Code for OTP"<ResponseMsgCode>"
And Lets validate the response message for OTP"<ResponseMsg>"

Examples:
|ResponseCode|ResponseMsgCode|ResponseMsg |correlationId |
|200         |400            |Bad Request |              |


   