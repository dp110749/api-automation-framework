@MYMONEY_API
Feature: POST: To Test the feature of MYMONEY API
  Description:The Purpose of this api to fetch the status of premium amount

  Background: 
    Given Set the prerequest data for my-MoneyService
      | EndPoint           | Header                                             | RequestFile  | PolicyNumber | PreMiumAmount |methodType |
      | /developer/mymoney | x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY | MyMoney.json |    329989917 |         250001 |POST      |

  @SmockTetsing
  Scenario Outline: To test the functionality of Mymoney Api when we pass the valid
                    Policy number and premium amount
    When I hit the myMoney API with requestbody and Post method
    Then I try to verify mymoney rsponse status code is"<StatusCode>"
    And I want to validate the response message for mymoney"<Msg>"
    And I try to validate the response time response type and response app id for myMoney api
    And I want to validate the collected amount"<collectedAmount>"
    And i want to validate the bounce amount"<bounceAmount>"
    And i want to validate the clear amount"<clearAmount>"

    Examples: 
      | StatusCode | Msg     | collectedAmount | bounceAmount | clearAmount |
      |        200 | Success |          250000 |              |             |

  @FunctionalTest
  Scenario Outline: To test the functionality of Mymoney Api when we pass different input parameter
    Given I want to set the policy number for mymoneyApi"<PolicyNumber>"
    And I want to set the premiumAmount for MymoneyApi"<PremiumAmount>"
    When I hit the myMoney API with requestbody and Post method
    Then I try to verify mymoney rsponse status code is"<StatusCode>"
    And I want to validate the response message for mymoney"<ResponseMessage>"
    And I try to validate the response time response type and response app id for myMoney api
    And I want to validate the collected amount"<collectedAmount>"
    And i want to validate the bounce amount"<bounceAmount>"
    And i want to validate the clear amount"<clearAmount>"

    Examples: 
      | PolicyNumber | PremiumAmount | StatusCode | ResponseMessage | collectedAmount | bounceAmount | clearAmount |
      |    284928306 |        240000 |        200 | Success         |              60 |           60 |             |
      |    101145084 |        120000 |        200 | Success         |       115088.97 |    115088.97 |             |

  @FunctionalTest
  Scenario Outline: To test the functionality of Mymoney Api when we pass the wrong
                    policy number

    Given I want to set the policy number for mymoneyApi"<PolicyNumber>"
    When I hit the myMoney API with requestbody and Post method
    Then I try to verify mymoney rsponse status code is"<StatusCode>"
    And I try to validate the response time response type and response app id for myMoney api
    And I want to validate the response status of policy"<StatusOfPolicy>"

    Examples: 
      | PolicyNumber | StatusCode | StatusOfPolicy                |
      |    101070000 |        200 | Policy details does not exist |

   @FunctionalTest
  Scenario Outline: To test the functionality of Mymoney Api when we pass the header
                    null
    Given I set header for myMoney api "<Headers>"
    When I hit the myMoney API with requestbody and Post method
    Then I try to verify mymoney rsponse status code is"<StatusCode>"
    And I want to validate if myMoney api response body contains specific string "Forbidden"

    Examples: 
       | Headers        | StatusCode |
       | x-api-key:null |        403 |
       
   @FunctionalTest
  Scenario Outline: To test the functionality of Mymoney Api when we pass premiumAmount as Null
  
    Given I want to set the premiumAmount for MymoneyApi"<PremiumAmount>"
    When I hit the myMoney API with requestbody and Post method
    Then I try to verify mymoney rsponse status code is"<StatusCode>"
    And I want to validate the response message for mymoney"<ResponseMessage>"
    And I try to validate the response time response type and response app id for myMoney api
        Examples: 
      | PremiumAmount | StatusCode | ResponseMessage |
      |               |        200 | Failure         | 
      
