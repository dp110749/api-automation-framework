@SJB_ServiceTest
Feature: To Test the functionality of SJB product 
Background:
Given Set the pre request of data for SJB product 
|EndPointURL          | Header                                             | RequestFile         | CorrelationID | InsuredGender | SumAssured | Channel|
|/developer/lepremium | x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY | LE_SJB_Service.json | 12232         | T             | 1000000    | A      |

@SJB_PositiveTest001
Scenario: To generate AFYP amount when user pass sumassured 1000000 for SJB
Given Set the Expected data
|ResponseStatusCode|ResponseMsgCode|ResponseMessage|ResponseAFYP|
|200               |200            |Success        |4466.6      |
When send the POST request for SJB
When Lets Validate the response code
And  Lets Validate the response MsgCode and Message
And Lets validate the AFYP Value

@SJB_PositiveTest002
Scenario Outline: To generate illustration when user pass sumassured 1000000 for SJB
Given Set the Expected data
|ResponseStatusCode|ResponseMsgCode|ResponseMessage|ResponseAFYP|
|200               |200            |Success        |            |
Given Lets set the illustration url"<endPointUrl>"
When send the POST request for SJB
When Lets Validate the response code
And  Lets Validate the response MsgCode and Message
And Lets validate the illustration generated or not

Examples:
|endPointUrl              |
|developer/leillustration |

@SJB_PositiveTest003
Scenario Outline: To generate AFYP amount when user pass different sumassured for SJB
Given Set the Expected data
|ResponseStatusCode|ResponseMsgCode|ResponseMessage|ResponseAFYP|
|200               |200            |Success        |            |
Given Lets set the sumAssurd"<SumAssurd>" and afypvalue"<afypValue>"
When send the POST request for SJB
When Lets Validate the response code
And  Lets Validate the response MsgCode and Message
And Lets validate the AFYP Value
Examples:
|SumAssurd |afypValue |
|2000000   |6659.4    |
|2500000   |8324.25   |

@SJB_PositiveTest004
Scenario Outline: To generate illustration when user pass sumassured 1000000 for SJB
Given Set the Expected data
|ResponseStatusCode|ResponseMsgCode|ResponseMessage|ResponseAFYP|
|200               |200            |Success        |            |
Given Lets set the illustration url"<endPointUrl>"
Given Lets set the sumAssurd"<SumAssurd>" and afypvalue"<afypValue>"
When send the POST request for SJB
When Lets Validate the response code
And  Lets Validate the response MsgCode and Message
And Lets validate the illustration generated or not

Examples:
|SumAssurd |endPointUrl               |afypValue |
|2500000   |developer/leillustration  |          |
|2000000   |developer/leillustration  |          |

@SJB_NegativeTest001
Scenario Outline: To check functionality of premium generator when user pass sumassurd is -2000000
Given Set the Expected data
|ResponseStatusCode|ResponseMsgCode|ResponseMessage|ResponseAFYP|
|200               |500            |Failure        |            |
Given Lets set the sumAssurd"<SumAssurd>" and afypvalue"<afypValue>"
When send the POST request for SJB
When Lets Validate the response code
And  Lets Validate the response MsgCode and Message
Examples:
|SumAssurd  |afypValue |
|-2000000   |          |

@SJB_NegativeTest002
Scenario Outline: To check functionality of illustration when user pass sumassurd is -2000000
Given Set the Expected data
|ResponseStatusCode|ResponseMsgCode|ResponseMessage|ResponseAFYP|
|200               |500            |Failure        |            |
Given Lets set the illustration url"<endPointUrl>"
Given Lets set the sumAssurd"<SumAssurd>" and afypvalue"<afypValue>"
When send the POST request for SJB
When Lets Validate the response code
And  Lets Validate the response MsgCode and Message
Examples:
|SumAssurd  |afypValue |endPointUrl                |
|-2000000   |          |developer/leillustration   |

@SJB_NegativeTest003
Scenario Outline: To check functionality of SJB when pass correlation ID as null
Given Set the Expected data
|ResponseStatusCode|ResponseMsgCode|ResponseMessage|ResponseAFYP|
|200               |400            |Bad Request    |            |
Given Set Correlation id"<CorrelationId>"
When send the POST request for SJB
When Lets Validate the response code
And  Lets Validate the response MsgCode and Message
Examples:

|CorrelationId|
|             |

@SJB_NegativeTest004
Scenario Outline: To check functionality of SJB when pass header as null
Given Set the Expected data
|ResponseStatusCode|ResponseMsgCode|ResponseMessage|ResponseAFYP|
|403               |               |               |            |
Given Set the header for SJB"<header>"
When send the POST request for SJB
When Lets Validate the response code
Examples:

|header     |
|x-api-key: |

 @SJB_NegativeTest005
Scenario Outline: To check functionality of SJB when pass invaild channel
Given Set the Expected data
|ResponseStatusCode|ResponseMsgCode|ResponseMessage|ResponseAFYP|
|200               | 500           | Failure       |            |
Given Set the channel for SJB"<channel>"
When send the POST request for SJB
When Lets Validate the response code
And  Lets Validate the response MsgCode and Message
Examples:

|channel  |
|  M      |

 @SJB_NegativeTest006	
Scenario Outline: To check functionality of SJB when pass invaild Gender
Given Set the Expected data
|ResponseStatusCode|ResponseMsgCode|ResponseMessage|ResponseAFYP|
|200               | 500           | Failure       |            |
Given Set the insurdGender for SJB"<insurdGender>"
When send the POST request for SJB
When Lets Validate the response code
And  Lets Validate the response MsgCode and Message
Examples:

|insurdGender  |
|  M           |

 