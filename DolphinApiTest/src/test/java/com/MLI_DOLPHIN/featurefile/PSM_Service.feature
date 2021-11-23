@PSM_AllTest
Feature: To Test the functionality of PSM service
Background: Set the preRequest set of data 
Given Set the data in request 
|endPoint       |Header                                             |RequestFile     |CorrelationId|Income  |Need        |channel|planGroup|productFamily|insurdAge|IsPremiumHalfOfIncome |IsForm2 |IsInsuredAge18|isinsuredtakenRider|STP|IrpScore | DFA|LifeCycleBase |TriggerBased|GrowthFund |HighGrowth|SuperGrowthFund|BalanceFund |MethodType  |                   
|/developer/psm |x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY |PSM_Service.json|31212        |1500000 |PROTECTION  |F      |U2NF20   |ULIP         |30       |Y                     |Y       |Y             |Y                  |Y  | 8       | N  | N            | N          | N         |N         |      N        | N          |POST        |

@PSM_PossitiveTest
Scenario Outline: To test the functionality Fail case PSM recomendtation for ULIP
When Lets Send the POST request for PSM
Then Lets valiate the response status code"<RespStatusCode>"
And Lets Validate the response message code"<RespMsgCode>"
And Lets Validate teh response Message"<RespMsg>"
And Lets validate the psmRecommendationCheck"<PSMrecomendationCheck>"

Examples:
|RespStatusCode|RespMsgCode|RespMsg |PSMrecomendationCheck|
| 200          | 200       |Success |FAIL                 | 

@PSM_PossitiveTest001
Scenario Outline: To test the functionality Pass case of PSM recomendtation for TRAD AND ULIP Rule 1
Given Lets Set the income of Insurd"<InsurdIcome>"
And Lets Set the channle"<Channel>"
And Lets Set the plangroup"<PlanGroup>"
And Lets Set the product Family"<ProductFamily>"
And Lets Set the insurdAge"<InsurdAge>"
And Lets Set the need"<Need>"
When Lets Send the POST request for PSM
Then Lets valiate the response status code"<RespStatusCode>"
And Lets Validate the response message code"<RespMsgCode>"
And Lets Validate teh response Message"<RespMsg>"
And Lets validate the psmRecommendationCheck"<PSMrecomendationCheck>"

Examples:
|RespStatusCode|RespMsgCode|RespMsg |PSMrecomendationCheck|InsurdIcome|Channel|PlanGroup |ProductFamily|InsurdAge|         Need                    |
| 200          | 200       |Success |PASS                 |1000000    | A     |TFSTP     |TRAD         |30       |PROTECTION                       |
| 200          | 200       |Success |PASS                 |1500000    | A     |U2NIFS    |ULIP         |30       |CHILDRENS MARRIAGE OR EDUCATION  | 
| 200          | 200       |Success |PASS                 |600000     | A     |U2NF20    |ULIP         |32       |WEALTH ACCUMULATION OR INVESTMENT| 
| 200          | 200       |Success |PASS                 |499999     | A     |LPPS20    |TRAD         |32       |RETIREMENT                       |   
| 200          | 200       |Success |PASS                 |1200000    | A     |ESMI15    |TRAD         |35       |REGULAR INCOME                   |   

@PSM_PossitiveTest002
Scenario Outline: To test the functionality FAIL case of PSM recomendtation for TRAD AND ULIP Rule 1
Given Lets Set the income of Insurd"<InsurdIcome>"
And Lets Set the channle"<Channel>"
And Lets Set the plangroup"<PlanGroup>"
And Lets Set the product Family"<ProductFamily>"
And Lets Set the insurdAge"<InsurdAge>"
And Lets Set the need"<Need>"
When Lets Send the POST request for PSM
Then Lets valiate the response status code"<RespStatusCode>"
And Lets Validate the response message code"<RespMsgCode>"
And Lets Validate teh response Message"<RespMsg>"
And Lets validate the psmRecommendationCheck"<PSMrecomendationCheck>"

Examples:
|RespStatusCode |RespMsgCode |RespMsg |PSMrecomendationCheck|InsurdIcome |Channel|PlanGroup  |ProductFamily|InsurdAge |           Need                   |
| 200           | 200        |Success |FAIL                 |1100000     | A     |TFSTP      |ULIP         |30        |PROTECTION                        |
| 200           | 200        |Success |FAIL                 |1300000     | A     |U2NIFS     |TRAD         |30        |CHILDRENS MARRIAGE OR EDUCATION   | 
| 200           | 200        |Success |FAIL                 |700000      | A     |U2NF20     |TRAD         |32        |WEALTH ACCUMULATION OR INVESTMENT | 
| 200           | 200        |Success |FAIL                 |499999      | A     |LPPS20     |ULIP         |32        |RETIREMENT                        |   
| 200           | 200        |Success |FAIL                 |1700000     | A     |ESMI15     |ULIP         |35        |REGULAR INCOME                    |   

@PSM_PossitiveTest003
Scenario Outline: To test the functionality FAIL case of PSM recomendtation for TRAD AND ULIP Rule 2
Given Lets Set the Insured income Half"<IsInsuredIncomeHalf>"
And Lets Set the isForm"<IsForm2>"
And Lets Set the Is Insured taken Rider"<IsinsuredTakenR>"
And Lets Set the Is insured age"<IsInsuredAge18>"
And Lets Set the product Family"<ProductFamily>"
And Lets Set the plangroup"<PlanGroup>"
When Lets Send the POST request for PSM
Then Lets valiate the response status code"<RespStatusCode>"
And Lets Validate the response message code"<RespMsgCode>"
And Lets Validate teh response Message"<RespMsg>"
#And Lets validate the psmRecommendationCheck"<PSMrecomendationCheck>"
And Lets validate the payorIncomeCheck"<PayorIncomeCheck>"
And Lets validate the PSMPayorCheck"<PSMpayorCheck>"

Examples:
|RespStatusCode |RespMsgCode |RespMsg   |PayorIncomeCheck|PSMpayorCheck|IsInsuredIncomeHalf | IsForm2 |IsinsuredTakenR |IsInsuredAge18 |ProductFamily|PlanGroup|
| 200           | 200        |Success   | PASS           |  PASS       |   Y                |  Y      |   Y            |      Y        |TRAD         |EPRSCL   |
| 200           | 200        |Success   | FAIL           |  PASS       |   N                |  Y      |   Y            |      Y        |TRAD         |EPRSCL   |
| 200           | 200        |Success   | FAIL           |  PASS       |   N                |  N      |   N            |      N        |TRAD         |EPRSCL   |
| 200           | 200        |Success   | PASS           |  FAIL       |   Y                |  Y      |   N            |      Y        |TRAD         |EPRSCL   |
| 200           | 200        |Success   | PASS           |  PASS       |   Y                |  Y      |   Y            |      Y        |ULIP         |U2NF20   |
| 200           | 200        |Success   | FAIL           |  PASS       |   N                |  Y      |   Y            |      Y        |ULIP         |U2NF20   |
| 200           | 200        |Success   | FAIL           |  PASS       |   N                |  N      |   N            |      N        |ULIP         |U2NF20   |   
| 200           | 200        |Success   | PASS           |  PASS       |   Y                |  Y      |   N            |      Y        |ULIP         |U2NF20   |


@PSM_PossitiveTest004
Scenario Outline: To test the functionality of PSM ULIP Rule 3(PSMIrpFundCheck)
Given Lets Set the Stp value"<STP>"
And Lets Set the irpScore"<IrpScore>"
And Lets Set the product Family"<ProductFamily>"
And Lets Set the plangroup"<PlanGroup>"
And Lets Set the DFA value"<DFA>"
And Lets Set the LifeCycleBase value"<LifeCycleBase>"
And Lets Set the TriggerBase value"<TriggerBase>"
And Lest Set the FundGrowth value"<FundGrowth>"
And Lest Set the HighFundGrowth value"<HighGrowthFund>"
And Lest Set the SuperFundGrowth value"<SuperGrowthFund>"
And Lest Set the Balanced Fund Value"<BalancedFund>"
When Lets Send the POST request for PSM
Then Lets valiate the response status code"<RespStatusCode>"
And Lets Validate the response message code"<RespMsgCode>"
And Lets Validate teh response Message"<RespMsg>"
And Lets Validate the PsmFundCheck"<IRPFundcheck>"
And Lets Validate the RiskProfile"<RiskProfile>"

Examples:
|RespStatusCode |RespMsgCode |RespMsg   |IRPFundcheck | RiskProfile     | STP   |IrpScore  |ProductFamily    |PlanGroup|DFA |LifeCycleBase|TriggerBase|FundGrowth|HighGrowthFund|SuperGrowthFund|BalancedFund|
| 200           | 200        |Success   | FAIL        |  Conservative   |  Y    |4         |ULIP             |U2NF20   | N  | N           | N         |  N       | N            |  N            |N           |
| 200           | 200        |Success   | PASS        |  Conservative   |  N    |4         |ULIP             |U2NF20   | N  | N           | N         |  N       | N            |  N            |Y           |
| 200           | 200        |Success   | FAIL        |  Balanced       |  N    |8         |ULIP             |U2NF20   | Y  | N           | N         |  N       | N            |  N            |Y           |
| 200           | 200        |Success   | PASS        |  Balanced       |  Y    |8         |ULIP             |U2NF20   | N  | N           | N         |  N       | N            |  N            |Y           |
| 200           | 200        |Success   | FAIL        |  Aggressive     |  N    |13        |ULIP             |U2NF20   | N  | N           | N         |  N       | Y            |  N            |N           |
| 200           | 200        |Success   | PASS        |  Aggressive     |  Y    |13        |ULIP             |U2NF20   | N  | N           | N         |  N       | N            |  N            |N           |
| 200           | 200        |Success   | FAIL        | very Aggressive |  N    |14        |ULIP             |U2NF20   | N  | N           | N         |  Y       | N            |  N            |N           |
| 200           | 200        |Success   | PASS        | very Aggressive |  Y    |14        |ULIP             |U2NF20   | N  | N           | N         |  N       | N            |  N            |N           |

@NegativeTest_01
Scenario Outline: To test the functionality of PSM Service when pass correlationId as null
Given Lets set the correlation id for PSM"<CorrelationID>"
When Lets Send the POST request for PSM
Then Lets valiate the response status code"<RespStatusCode>"
And Lets Validate the response message code"<RespMsgCode>"
And Lets Validate teh response Message"<RespMsg>"
Examples:
|RespStatusCode |RespMsgCode |RespMsg   |CorrelationID|
| 200           | 400        |Failure   |             |

@NegativeTest_02
Scenario Outline: To test the functionality of PSM Service when pass invalid EndPoint Url
Given Lets set the endPointUrlfor PSM"<url>"
When Lets Send the POST request for PSM
Then Lets valiate the response status code"<RespStatusCode>"
Examples:
|RespStatusCode   |url               |
| 403             |developer/psm/dsd |

