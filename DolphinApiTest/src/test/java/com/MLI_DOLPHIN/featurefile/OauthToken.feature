@OauthApI
Feature: POST: Oauth API Test
  Description: POST: The purpose of this feature is to test Oauth Api.

  @post
  Scenario Outline: POST: Test the Oauth Api with valid request
    Given I want to set URL as "<URL>" for test case "<TestName>"
    When I set headers client id as "<Headers>"
    When I hit the API with requestbody "<RequestBody>"
    Then I try to verify the status code is "<StatusCode>" 
    And I try to verify the response value "payload" is "token" and successmessage "User Token generated successfully"

     Examples:
      |    TestName  |        URL            |  Headers                                                                                 | RequestBody          | StatusCode |
      | OauthApitest | /developer/auth-token |x-client-id: 53jt873l753mcudrhqmuh3g5u8,x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY| OauthApiRequest.json |    200     |

  @postfail
  Scenario Outline: POST: Test the Oauth Api with invalid credential
    Given I want to set URL as "<URL>" for test case "<TestName>"
    When I set headers client id as "<Headers>"
    When I hit the API with invalid credential "<RequestBody>" key "<Jsonkey>" and invalidPassword "<value>"
    Then I try to verify the status code is "<StatusCode>"
    And I try to verify the response message "Wrong credentials"
 
      Examples:
      |    TestName  |        URL            |  Headers                                                                                 | RequestBody          | Jsonkey  | value   |StatusCode|          
      | OauthApitest | /developer/auth-token |x-client-id: 53jt873l753mcudrhqmuh3g5u8,x-api-key:DTUDHv9UVG8cVT3qmhiSv1UcnvCduzLf1CI6zCVY| OauthApiRequest.json | password |  ibps@1234|200    |
 

  @postfail
  Scenario Outline: POST: Test the Oauth Api with invalid credential
     Given I want to set URL as "<URL>" for test case "<TestName>"
     When I set headers client id as "<Headers>"
     When I hit the API with requestbody "<RequestBody>"
     Then I try to verify the status code is "<StatusCode>"
     
      Examples:
      |    TestName  |        URL            |  Headers                              | RequestBody          | StatusCode|          
      | OauthApitest | /developer/auth-token |x-client-id: 53jt873l753mcudrhqmuh3g5u8| OauthApiRequest.json | 403      |

 