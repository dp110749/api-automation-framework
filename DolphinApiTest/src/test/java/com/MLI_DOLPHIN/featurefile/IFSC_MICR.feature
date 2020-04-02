@IFSC_MICR_API
Feature: POST: To Test the feature of IFSC MICR API
Description:The Purpose of this api to fetch the deatis of IFSC and MICR

@Post
Scenario Outline: To test the functionality of IFSC MICR Api when we pass the valid
                  IFSC code 
    Given I want to set the initial End Point URL as "<URL>" for test case "<TestName>"
    When I set header as "<Headers>"
    When I hit the API with requestbody and Post method "<RequestBody>"
    Then I try to verify rsponse status code is "<StatusCode>" 
    And I try to validate the response time response type and response app id
    And I want to validate if response body contains specific string "bnkmicrCode" and "bnkIfscCode"
   
    Examples:
    
      | TestName          |             URL                             |  Headers                                         | RequestBody        | StatusCode |
      | IFSC_MICR API TEST| /developer/microservices/mli/qc/micrifsc/v1 |x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY| IFSC_MICR_API.json |    200     |
      
@Post
Scenario Outline: To test the functionality of IFSC MICR Api when we pass the valid
                  MICR code 
    Given I want to set the initial End Point URL as "<URL>" for test case "<TestName>"
    When I set header as "<Headers>"
    When I hit the API with requestbody "<RequestBody>" with passing the micrcode "<jsonKey>" value "<micrCode>" and replacing the value of ifsc "<keyOfIfsc>" and "<ifscValue>" 
    Then I try to verify rsponse status code is "<StatusCode>" 
    And I try to validate the response time response type and response app id
    And I want to validate if response body contains specific string "bnkmicrCode" and "bnkIfscCode"
   
    Examples:
    
      | TestName          |             URL                             |  Headers                                         | RequestBody        | jsonKey      |micrCode  |keyOfIfsc   |ifscValue| StatusCode|
      | IFSC_MICR API TEST| /developer/microservices/mli/qc/micrifsc/v1 |x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY| IFSC_MICR_API.json | bankMicrCode |110002479 |bankIfscCode|         |   200     |
    
@PostFail
Scenario Outline: To test the functionality of IFSC MICR Api when we pass the 
                  MICR code and IFSC Code as blank
    Given I want to set the initial End Point URL as "<URL>" for test case "<TestName>"
    When I set header as "<Headers>"
    When I hit the API with requestbody "<RequestBody>" with passing the micrcode "<jsonKey>" value "<micrCode>" and replacing the value of ifsc "<keyOfIfsc>" and "<ifscValue>" 
    Then I try to verify rsponse status code is "<StatusCode>" 
    And I try to validate the response time response type and response app id
    And I want to validate if response body contains specific string "Mandatory Parameter is missing"
   
    Examples:
    
      | TestName          |             URL                             |  Headers                                         | RequestBody        | jsonKey      |micrCode |keyOfIfsc   |ifscValue| StatusCode|
      | IFSC_MICR API TEST| /developer/microservices/mli/qc/micrifsc/v1 |x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY| IFSC_MICR_API.json | bankMicrCode |         |bankIfscCode|         |   200     |
      
                
@PostFial
Scenario Outline: To test the functionality of IFSC MICR Api when we pass the valid
                  ifsc and micr code 
                  
    Given I want to set the initial End Point URL as "<URL>" for test case "<TestName>"
    When I set header as "<Headers>"
    When I hit the API with requestbody and Post method "<RequestBody>" jsonkey is "<jsonKey>" and micr Code "<micrCode>"
    Then I try to verify rsponse status code is "<StatusCode>" 
    And I try to validate the response time response type and response app id
    And I want to validate if response body contains specific string "msgDescription" and "Either MICR or IFSC should be present"
   
      Examples:
    
      | TestName          |             URL                             |  Headers                                         | RequestBody        |jsonKey     |micrCode  | StatusCode|
      | IFSC_MICR API TEST| /developer/microservices/mli/qc/micrifsc/v1 |x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY| IFSC_MICR_API.json |bankMicrCode|110002479 |   200     |
    
 @PostFial
Scenario Outline: To test the functionality of IFSC MICR Api when we pass the Header
                  as null 
    Given I want to set the initial End Point URL as "<URL>" for test case "<TestName>"
    When I set header as "<Headers>"
    When I hit the API with requestbody and Post method "<RequestBody>"
    Then I try to verify rsponse status code is "<StatusCode>" 
    And I want to validate if response body contains specific string "Forbidden"
   
    Examples:
    
      | TestName          |             URL                             |  Headers        | RequestBody        | StatusCode |
      | IFSC_MICR API TEST| /developer/microservices/mli/qc/micrifsc/v1 |  x-api-key:null | IFSC_MICR_API.json |    403     |
    
    
    
    
    
    
            