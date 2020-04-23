@MYMONEY_API
Feature: POST: To Test the feature of MYMONEY API
Description:The Purpose of this api to fetch the status of premium amount 

@Post
Scenario Outline: To test the functionality of Mymoney Api when we pass the valid
                  Policy number and premium amount  
    Given I want to set the initial End Point URL for mymoney api "<URL>" for test case "<TestName>"
    When I set header for mymoney api "<Headers>"
    When I hit the myMoney API with requestbody and Post method "<RequestBody>"
    Then I try to verify mymoney rsponse status code is "<StatusCode>" 
    And I try to validate the response time response type and response app id for myMoney api
    And I want to validate if mymoney response body contains specific string "collectedAmount" and "bounceAmount"
    And I want to validate the collected amount 
    And i want to validate the bounce amount 
    And i want to validate the clear aamount 
   
    Examples:
    
      | TestName        |             URL                            |  Headers                                         | RequestBody  | StatusCode |
      | MyMoney API TEST| /developer/microservices/mli/qc/mymoney/v1 |x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY| MyMoney.json |    200     |
      
@PostFail
Scenario Outline: To test the functionality of Mymoney Api when we pass the mandatory 
                  parameter as Empty 
    Given I want to set the initial End Point URL for mymoney api "<URL>" for test case "<TestName>"
    When I set header for mymoney api "<Headers>"
    When I hit the myMoney API with requestbody and Post method "<RequestBody>" and policyNoKey "<policyNumberkey>" and PolicyNumValue "<policyNumValue>"
    Then I try to verify mymoney rsponse status code is "<StatusCode>" 
    And I try to validate the response time response type and response app id for myMoney api
    And I want to validate if mymoney api response body contains specific string "Mandatory Parameter is missing"
   
    Examples:
    
      | TestName        |             URL                            |  Headers                                         | RequestBody  |policyNumberkey|policyNumValue| StatusCode|
      | MyMoney API TEST| /developer/microservices/mli/qc/mymoney/v1 |x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY| MyMoney.json |    policyNo   |              |200        |
  
@PostFail
Scenario Outline: To test the functionality of Mymoney Api when we pass the wrong  
                  policy number 
    Given I want to set the initial End Point URL for mymoney api "<URL>" for test case "<TestName>"
    When I set header for mymoney api "<Headers>"
    When I hit the myMoney API with requestbody and Post method "<RequestBody>" and policyNoKey "<policyNumberkey>" and PolicyNumValue "<policyNumValue>"
    Then I try to verify mymoney rsponse status code is "<StatusCode>" 
    And I try to validate the response time response type and response app id for myMoney api
    And I want to validate if mymoney api response body contains specific string "paymentHistoryDetails"
   
    Examples:
    
      | TestName        |             URL                            |  Headers                                         | RequestBody  |policyNumberkey|policyNumValue| StatusCode|
      | MyMoney API TEST| /developer/microservices/mli/qc/mymoney/v1 |x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY| MyMoney.json |    policyNo   | 101070000    |200        |
     
                
@PostFail1
Scenario Outline: To test the functionality of Mymoney Api when we pass the header  
                  null
    Given I want to set the initial End Point URL for mymoney api "<URL>" for test case "<TestName>"
    When I set header for myMoney api "<Headers>"
    When I hit the myMoney API with requestbody and Post method "<RequestBody>"
    Then I try to verify mymoney rsponse status code is "<StatusCode>" 
    And I want to validate if myMoney api response body contains specific string "Forbidden"   
    Examples:
    
      | TestName        |             URL                            |  Headers     | RequestBody  |  StatusCode|   
      | MyMoney API TEST| /developer/microservices/mli/qc/mymoney/v1 |x-api-key:null| MyMoney.json |    403     |

      
   
    
    
    
    
            