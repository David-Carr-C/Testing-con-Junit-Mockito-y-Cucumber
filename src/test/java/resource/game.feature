#Cucumber usa la extension .feature, cada feature tendra tests, en este caso se dividiran por escenarios
#Se usan "Comillas" para poder introducir la redaccion a la logica del testing
Feature: Game Rock, Paper or Scissors
  Scenario: The user wins when choose Rock and the computer choose Scissors
    Given the user will choose "Rock"
    And the computer will choose "scissors"
    When they play
    Then verify that the computer chose "scissors"
    And the user wins

  Scenario: The user wins when choose Scissors and the computer choose Paper
    Given the user will choose "Scissors"
    And the computer will choose "paper"
    When they play
    Then verify that the computer chose "paper"
    And the user wins

  Scenario: The user wins when choose Paper and the computer choose Rock
    Given the user will choose "Paper"
    And the computer will choose "rock"
    When they play
    Then verify that the computer chose "rock"
    And the user wins

  Scenario: The user wins when choose Rock and the computer choose Paper
    Given the user will choose "Rock"
    And the computer will choose "paper"
    When they play
    Then verify that the computer chose "paper"
    And the user lose

  Scenario: The user wins when choose Rock and the computer choose Rock
    Given the user will choose "Rock"
    And the computer will choose "rock"
    When they play
    Then verify that the computer chose "rock"
    And they tie
