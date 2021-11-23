@SSPTest
Feature: Test functionality of LE SPP service
Background:
Given Set the prerequiest set of data for LE SPP Service 
|EndPoint              |Header                                              |RequestFile         |AgeOfInsured|purchasePrice |ProductName                 |Channel|MethodType|
|/developer/lepremium  |x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY  |LE_SPP_Service.json | 45         |800000        |Max Life Saral Pension Plan |A      |POST      |

@SmokTesting
Scenario Outline: Test the valid spp premium caculator service with valid set of test data
When Send the valid request for SPP Service 
Then Lets Validate the response code for SPP Service"<ResponseCode>" 
And Lets Validate the response MSG For SPP Service"<ResponseMSG>" 
And Lets Validate the response AFYP of"<AFYP>"
And Lets Validate the PremiumWithoutGST of"<PremiumWithoutGST>"
And Lets Validate the PremiumWithGST"<PremiumWithGST>" 

Examples: 
|ResponseCode |ResponseMSG |AFYP  |PremiumWithoutGST |PremiumWithGST| 
|200          |Success     |800000|800000            |814400        |

@SmokTesting
Scenario Outline: Test the valid spp illustration generator service with valid set of test data
Given Set the endPoint url for Spp illustration Service"<EndPointUrl>" 
When Send the valid request for SPP Service 
Then Lets Validate the response code for SPP Service"<ResponseCode>" 
And Lets Validate the response MSG For SPP Service"<ResponseMSG>" 

Examples: 
|EndPointUrl               |ResponseCode |ResponseMSG |
|/developer/leillustration |200          |Success     |


@FunctionalTest
Scenario Outline: Test the Functionality of SPP Service with valid set of data 
Given Lets set the AgeOfInsured is"<AgeOfInsured>"
And Lets Set the purchasePrice for SSP Is"<PurchasePrice>"
When Send the valid request for SPP Service
Then Lets Validate the response code for SPP Service"<ResponseCode>" 
And Lets Validate the response MSG For SPP Service"<ResponseMSG>" 
And Lets Validate the response AFYP of"<AFYP>"
And Lets Validate the PremiumWithoutGST of"<PremiumWithoutGST>"
And Lets Validate the PremiumWithGST"<PremiumWithGST>" 

Examples:
|AgeOfInsured|PurchasePrice|ResponseCode|ResponseMSG|AFYP   |PremiumWithoutGST|PremiumWithGST|
|41          |500000       |200         |Success    |500000 |500000           |509000        |
|50          |1200000      |200         |Success    |1200000|1200000          |1221600       |

@FunctionalTest
Scenario Outline: Test the Functionality of SPP Service with invalid set of data 
Given Lets set the AgeOfInsured is"<AgeOfInsured>"
And Lets Set the purchasePrice for SSP Is"<PurchasePrice>"
And Lets Set the product name is"<ProductName>"
And Lets Set the channel name is"<Channel>"
When Send the valid request for SPP Service
Then Lets Validate the response code for SPP Service"<ResponseCode>" 
And Lets Validate the response MSG For SPP Service"<ResponseMSG>" 

Examples:
|Channel|ProductName                   |AgeOfInsured|PurchasePrice|ResponseCode|ResponseMSG|
|A      |Max Life Saral Pension Plan   |39          |500000       |500         |Failure    |                 
|A      |Max Life Saral Pension Plan   |40          |99000        |500         |Failure    |
|A      |Max Life Saral Pension Plan   |40          |             |500         |Failure    |
|B      |Max Life Saral Pension Plan   |40          |120000       |500         |Failure    |
|A      |XPZ                           |40          |120000       |500         |Failure    |

@FunctionalTest
Scenario Outline: Test the Functionality of SPP Service with valid set of data 
Given Set the endPoint url for Spp illustration Service"<EndPointUrl>"
And Lets set the AgeOfInsured is"<AgeOfInsured>"
And Lets Set the purchasePrice for SSP Is"<PurchasePrice>" 
When Send the valid request for SPP Service
Then Lets Validate the response code for SPP Service"<ResponseCode>" 
And Lets Validate the response MSG For SPP Service"<ResponseMSG>" 

Examples:
|EndPointUrl               |AgeOfInsured|PurchasePrice|ResponseCode|ResponseMSG|
|/developer/leillustration |41          |500000       |200         |Success    |
|/developer/leillustration |50          |1200000      |200         |Success    |

