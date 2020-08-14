# See
# https://github.com/intuit/karate#syntax-guide
# for how to write feature scenarios
Feature: As an api user I want to retrieve list of the highest starred repositories in the last week.

  #success scenario  
  Scenario: Get a starred repositories with default params
    Given url microserviceUrl
    And path '/repositories'
    When method GET
    Then status 200
    And match header Content-Type contains 'application/json'
    # see https://github.com/intuit/karate#schema-validation
    And match response contains {"status": "SUCCESS"}
  
  Scenario: Get a starred repositories with limit and page
    Given url microserviceUrl
    And path '/repositories'
    And param limit = '10'
    And param page = '1'
    When method GET
    Then status 200
    And match header Content-Type contains 'application/json'
    # see https://github.com/intuit/karate#schema-validation
    And match response contains {"status": "SUCCESS"}
    And match response.items == '#[10]'
      
  #failure scenario  
  Scenario: Get a starred repositories with limit 
    Given url microserviceUrl
    And path '/repositories'
    And param limit = '1000000000000'
    When method GET
    Then status 500
    And match header Content-Type contains 'application/json'
    # see https://github.com/intuit/karate#schema-validation
    And match response contains {"status": "FAILURE"}  

    Scenario: Get a starred repositories with limit and size equals 10 
    Given url microserviceUrl
    And path '/repositories'
    And param limit = '10'
    When method GET
    Then status 200
    And match header Content-Type contains 'application/json'
    # see https://github.com/intuit/karate#schema-validation
    And match response contains {"status": "SUCCESS"}
    And match response.items != '#[9]'
    
       