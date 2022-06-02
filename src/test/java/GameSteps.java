import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameSteps {
    //Hay que tener cuidado con cucumber, es fragil
    private final int ROCK = 0;
    private final int PAPER = 1;
    private final int SCISSORS = 2;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @InjectMocks
    private Game game;
    @Mock
    Scanner scanner;
    @Mock
    Random random;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        System.setOut(new PrintStream(out));
    }

    @Given("the user will choose {string}")
    public void theUserWillChoose(String userSelection) {
        when(scanner.nextLine()).thenReturn(userSelection).thenReturn("Quit");
    }

    @And("the computer will choose {string}")
    public void theComputerWillSchoose(String computerSelection) {
        int selection = -1;
        switch (computerSelection) {
            case "scissors":
                selection = SCISSORS;
                break;
            case "rock":
                selection = ROCK;
                break;
            case "paper":
                selection = PAPER;
                break;
        }
        when(random.nextInt(3)).thenReturn(selection);
    }

    @When("they play")
    public void theyPlay() {
        game.play();
    }

    @Then("verify that the computer chose {string}")
    public void verifyThatTheComputerChose(String computerSelection) {
        assertTrue(out.toString().contains("Computer chose "+computerSelection));
    }

    @And("the user wins")
    public void theUserWins() {
        assertTrue(out.toString().contains("you win!"));
        assertTrue(out.toString().contains("wins:1"));
    }

    @And("the user lose")
    public void theUserLose() {
        assertTrue(out.toString().contains("you lose."));
        assertTrue(out.toString().contains("loses:1"));
    }

    @And("they tie")
    public void theyTie() {
        assertTrue(out.toString().contains("It's a tie"));
        assertTrue(out.toString().contains("loses:0"));
        assertTrue(out.toString().contains("ties:1"));
    }
}
