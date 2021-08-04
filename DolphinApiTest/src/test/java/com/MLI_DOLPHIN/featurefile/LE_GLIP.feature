@AllScenariosOfGLIP
Feature: Test the functionality of premium calculation and illustration for GLIP product

  Background: 
    Given Set the prerequest data for GLIP product
      | EndPoint             | Header                                             | RequestFile          | purchasePrice | variant | ProductName                              | AgeOfInsured |
      | /developer/lepremium | x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY | LE_GLIP_Service.json |    2190676.00 | IAJ     | Max Life Guaranteed Lifetime Income Plan |           50 |

  @SmockTesting
  Scenario Outline: Send the valid request for GLIP product and validate the response
    When Lets send the POST request for GLIP
    Then Lets validate the response code for GLIP"<ResponseCode>"
    And Lets validate the response message for GLIP"<ResponseMessage>"
    And Lets validate the response of AFYP"<ResponseAFYP>"
    And Lets validate the response of Anualised premium"<AnualisedPremium>"
    And Lets validate the response of installment Premium"<InstallmentPremium>"
    And Lest Validate the response of anualincome"<AnualIncome>"

    Examples: 
      | ResponseCode | ResponseMessage | ResponseAFYP | AnualisedPremium | InstallmentPremium | AnualIncome  |
      |          200 | Success         |      2190676 |          2190676 |        2230108.168 | 142437.75352 |

  @SmockTesting
  Scenario Outline: Send the valid request for GLIP product and validate the response
    Given Lets Set url for illustration"<EndPointUrl>"
    When Lets send the POST request for GLIP
    Then Lets validate the response code for GLIP"<ResponseCode>"
    And Lets validate the response message for GLIP"<ResponseMessage>"

    Examples: 
      | EndPointUrl               | ResponseCode | ResponseMessage |
      | /developer/leillustration |          200 | Success         |

  @FunctionalTesting
  Scenario Outline: Test the functionality of GLIP LE premium Calcualtion with multiple set of data
    Given Lets Set the input data of"<InputPurchasePrice>"
    When Lets send the POST request for GLIP
    Then Lets validate the response code for GLIP"<ResponseCode>"
    And Lets validate the response message for GLIP"<ResponseMessage>"
    And Lets validate the response of AFYP"<ResponseAFYP>"
    And Lets validate the response of Anualised premium"<AnualisedPremium>"
    And Lets validate the response of installment Premium"<InstallmentPremium>"
    And Lest Validate the response of anualincome"<AnualIncome>"

    Examples: 
      | InputPurchasePrice | ResponseCode | ResponseMessage | ResponseAFYP | AnualisedPremium | InstallmentPremium | AnualIncome |
      |            1000000 |          200 | Success         |      1000000 |          1000000 |            1018000 |       64390 |
      |            5000000 |          200 | Success         |      5000000 |          5000000 |            5090000 |      325100 |

  @FunctionalTesting
  Scenario Outline: Test the functionality of GLIP LE illustration generation with multiple set of data
    Given Lets Set the input data of"<InputPurchasePrice>"
    And Lets Set url for illustration"<EndPointUrl>"
    When Lets send the POST request for GLIP
    Then Lets validate the response code for GLIP"<ResponseCode>"
    And Lets validate the response message for GLIP"<ResponseMessage>"

    Examples: 
      | EndPointUrl               | InputPurchasePrice | ResponseCode | ResponseMessage |
      | /developer/leillustration |            1000000 |          200 | Success         |
      | /developer/leillustration |            5000000 |          200 | Success         |

  @FunctionalTesting
  Scenario Outline: Test the functionality of GLIP LE premium Calcualtion when pass insured age less than 45
    Given Lets Set the insured age for GLIP"<InsuredAge>"
    When Lets send the POST request for GLIP
    Then Lets validate the response code for GLIP"<ResponseCode>"
    And Lets validate the response message for GLIP"<ResponseMessage>"

    Examples: 
      | InsuredAge | ResponseCode | ResponseMessage |
      |         49 |          200 | Failure         |
      |         81 |          200 | Failure         |

  @FunctionalTesting
  Scenario Outline: Test the functionality of GLIP when pass invalid url
    Given Lets Set url for illustration"<EndPointUrl>"
    When Lets send the POST request for GLIP
    Then Lets validate the response code for GLIP"<ResponseCode>"

    Examples: 
      | EndPointUrl                 | ResponseCode |
      | /developer/leillustrationsw |          403 |
      | /developer/lepremiums1      |          403 |

  @FunctionalTesting
  Scenario Outline: Test the functionality of GLIP when null header
    Given Lets Set the header"<Header>"
    When Lets send the POST request for GLIP
    Then Lets validate the response code for GLIP"<ResponseCode>"

    Examples: 
      | Header         | ResponseCode |
      | x-api-key:null |          403 |
