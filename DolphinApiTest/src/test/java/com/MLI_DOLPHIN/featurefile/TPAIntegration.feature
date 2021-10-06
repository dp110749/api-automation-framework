@TPAIntegrationTest
Feature: To check the functionality of TPA Integration service

  Background: 
    Given set input request testdata
      | url                     | header                                             | requestFile         | ProductName | SmokeClass |
      | developer/medicalreport | x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY | TPAIntegration.json | TSWPPL      | Yes        |

  @PositiveTestTPA1
  Scenario Outline: 
    To test the functionality when the user sends the valid request for TPA Integration

    When Send the request for TPA Integration
    Then Validate response message "<msg>"
    And Validate message code "<msgCode>"
    And Validate response code "<msgCode>"

    Examples: 
      | msgCode | msg     |
      |     200 | Success |

  @NegativeTestTPA1
  Scenario Outline: 
    
    To test the functionality of TPA when sending the proposal number as null in the request

    Given :Set proposalNumber for TPA Integration"<ProposalNumber>"
    When Send the request for TPA Integration
    Then Validate response message "<msg>"
    And Validate message code "<msgCode>"

    Examples: 
      | ProposalNumber | msgCode | msg     |
      |                |     400 | Failure |
    

  @PositiveTestTPA2
  Scenario Outline: 
    To test the functionality of TPA Service when the user sends the Different ProductName

    Given :Set the Input Value for TPA Integration in request"<productName>"
    When Send the request for TPA Integration
    Then Validate response message "<msg>"
    And Validate message code "<msgCode>"
    And Validate response code "<msgCode>"

    Examples: 
      | productName | msgCode | msg     |
      | ESMIL       |     200 | Success |
      | PPS10       |     200 | Success |
      | TSWPPL      |     200 | Success |

  @NegativeTestTPA2
  Scenario Outline: 
    To test the functionality when user send the null header for TPA Integration

    Given :Set header values for TPA Integration "<header>"
    When Send the request for TPA Integration
    Then Validate response message "<message>"

    #	And Validate message code "<msgCode>"
    Examples: 
      | header         | msgCode | message   |
      | x-api-key:null |         | Forbidden |

  @PositiveTestTPA3
  Scenario Outline: 
    To test the functionality of TPA Service when user sends the different input of SmokeClass

    Given Set the Input Value of SmokeClass for TPA"<SmokeClass>"
    When Send the request for TPA Integration
    Then Validate response message "<msg>"
    And Validate message code "<msgCode>"

    Examples: 
      | msgCode | msg     | SmokeClass |
      |     200 | Success | No         |
      |     200 | Success | Yes        |
      |     400 | Failure |            |

  @NegativeTestTPA3
  Scenario Outline: 
    To test the functionality when user send the x-correlation-ID as null for TPA Integration

    Given :Set the Input Value for TPA Integration in request "<apiKey>" and "<apiValue>"
    When Send the request for TPA Integration
    Then Validate response message "<msg>"
    And Validate message code "<msgCode>"

    Examples: 
      | msgCode | msg     | apiKey           | apiValue |
      |     400 | Failure | X-Correlation-ID |          |

  @NegativeTestTPA4
  Scenario Outline: 
    To test the functionality when user send the invalid request for TPA Integration

    Given Set the data for TAP"<HouseNoAptNameSociety>"
    When Send the request for TPA Integration
    Then Validate response message "<msg>"
    And Validate message code "<msgCode>"

    Examples: 
      | HouseNoAptNameSociety | msgCode | msg     |
      |                       |     400 | Failure |

  @NegativeTestTPA5
  Scenario Outline: 
    To test the functionality TPA Integration when sends pincode null
    Given :Set PinCode for TPA Integration"<PinCode>"
    When Send the request for TPA Integration
    Then Validate response message "<msg>"
    And Validate message code "<msgCode>"
    
    Examples:
      | PinCode    | msgCode | msg     |
      |            |     400 | Failure |
     
