@LE_FTSP_IllustrationGenerator
Feature: POST: LE FTSP service for Illustration generator and premium generator 
Description:The Purpose of this api to generate the illustration of FTSP product

Scenario Outline: To test the functionality of LE FTSP API when we pass the valid 
          Request
    Given I want to set the request end Point url for "<URL>" for test case "<TestName>"
    When I set the header "<Headers>"
    When I send the complete request body for "<RequestBody>"
    Then I verify the response status code "<StatusCode>"
    And I try to validate the generated illustration is not empty
    And I try to validate the response time response type and response app id for LE FTSP
    And I try to validate response  message  discription "Illustration generated" and correlation id "31873127329799381273192121"
      
    Examples:    
    | TestName                |             URL           |  Headers                                         | RequestBody                        | StatusCode |
    |LE Illustration generator| /developer/leillustration |x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY| LE_FTSP_IllustrationGenerator.json |    200     |      

    Scenario Outline: To test the functionality of LE FTSP API when we pass the Mandatory 
                       parameter(Insured Name ) as empty
         
    Given I want to set the request end Point url for "<URL>" for test case "<TestName>"
    When I set the header "<Headers>"
    When I send the complete request body for "<RequestBody>" and pass the name of insured as empty key is "<insuredNameKey>" and value "<insuredValue>"
    Then I verify the response status code "<StatusCode>"
    And I try to validate the response time response type and response app id for LE FTSP
    And I try to validate response  message  discription "Faliure" and correlation id "31873127329799381273192121"
      
    Examples:    
    | TestName                |             URL           |  Headers                                         | RequestBody                        | StatusCode |insuredNameKey|insuredValue|
    |LE Illustration generator| /developer/leillustration |x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY| LE_FTSP_IllustrationGenerator.json |    200     |nameOfInsured |              |     
    
     Scenario Outline: To test the functionality of LE FTSP API when we pass the Mandatory 
                       parameter(X -Correlation id ) as empty        
    Given I want to set the request end Point url for "<URL>" for test case "<TestName>"
    When I set the header "<Headers>"
    When I send the complete request body for "<RequestBody>" and pass the name of insured as empty key is "<XcorrelationIdKey>" and value "<XcorrelationIDValue>"
    Then I verify the response status code "<StatusCode>"
    And I try to validate the response time response type and response app id for LE FTSP
    And I try to validate response  message  discription "Faliure" and correlation id ""
      
    Examples:    
    | TestName                |             URL                                                |  Headers                                         | RequestBody                        | StatusCode |XcorrelationIdKey|XcorrelationIDValue|
    |LE Illustration generator| /developer/leillustration |x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY| LE_FTSP_IllustrationGenerator.json |    200     |X-Correlation-ID |                   |     
 
  
    Scenario Outline: To test the functionality of LE FTSP API when we send the valid request 
                      for premiunm generator.       
    Given I want to set the request end Point url for "<URL>" for test case "<TestName>"
    When I set the header "<Headers>"
    When I send the complete request body for "<RequestBody>"
    Then I verify the response status code "<StatusCode>"
    And I try to validate the generated premium is partA "50000" and premium partB "50000" and premium partC "50000"
    And I try to validate the response time response type and response app id for LE FTSP
    And I try to validate response  message  discription "Success" and correlation id "31873127329799381273192121"
      
    Examples:    
    | TestName                |             URL      |  Headers                                         | RequestBody                        | StatusCode |
    |LE Illustration generator| /developer/lepremium |x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY| LE_FTSP_IllustrationGenerator.json |    200     |      
  
  
              
    
    