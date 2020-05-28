Feature: Website Crawler Accuracy
  Scenario: Valid website with some emails, good links, and bad links
    Given The individual is interested in website: "https://alextest333.htmlsave.net"
    And The individual is expecting 3 emails
    And The individual is expecting 2 good-links and 2 bad-links
    Then The individual is "correct"

  Scenario: Valid website with some emails, good links, and bad links
    Given The individual is interested in website: "https://alextest333.htmlsave.net"
    And The individual is expecting 1 emails
    And The individual is expecting 3 good-links and 1 bad-links
    Then The individual is "incorrect"

  Scenario: Valid website with the correct email searched for
    Given The individual is interested in website: "https://alextest333.htmlsave.net"
    And The individual is looking for email: "testemail1@gmail.com"
    Then The individual "does" find it

  Scenario: Valid website without the email searched for
    Given The individual is interested in website: "https://alextest333.htmlsave.net"
    And The individual is looking for email: "thisemailnotonpage@gmail.com"
    Then The individual "does not" find it