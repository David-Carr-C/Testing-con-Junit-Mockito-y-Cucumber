import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameTest {
    private final int ROCK = 0;
    private final int PAPER = 1;
    private final int SCISSORS = 2;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @InjectMocks
    private Game game; //Clase principal con la cual realizar las pruebas

    @Mock
    Scanner scanner; //Mock a modificar cuando algo ocurra
    @Mock
    Random random;

    @Test
    public void when_writeQuit_then_exitGame() {
        when(scanner.nextLine()).thenReturn("Quit");

        // Capturar todo lo que sale por consola
        ByteArrayOutputStream out = new ByteArrayOutputStream(); //Se crea un objeto de output para usarlo como consola
        System.setOut(new PrintStream(out)); //Creamos un print stream para settearlo como consola

        game.play();
        assertTrue(out.toString().contains("Let's play Rock, Paper, Scissors!"));
    }

    @Test
    public void when_chooseRock_then_beatsScissors() {
        when(scanner.nextLine()).thenReturn("Rock").thenReturn("Quit");
        //Se debe simular lo que pasa en el codigo, si esto es diferente no cargara la circunstancia de 'when'
        when(random.nextInt(3)).thenReturn(SCISSORS);

        System.setOut(new PrintStream(out));//Con JUnit 4+ se puede debuggear el test

        game.play();
        assertTrue(out.toString().contains("Computer chose scissors\n" +
                "you win!\n" +
                "wins:1"));
        assertTrue(out.toString().contains("you win!"));
        assertTrue(out.toString().contains("wins:1"));

    }

    @Test
    public void when_choosePaper_then_beatsRock() {
        when(scanner.nextLine()).thenReturn("Paper").thenReturn("Quit");
        when(random.nextInt(3)).thenReturn(ROCK);
        System.setOut(new PrintStream(out));

        game.play();
        assertTrue(out.toString().contains("Computer chose rock\n" +
                "you win!\n" +
                "wins:1"));
        assertTrue(out.toString().contains("you win!"));
        assertTrue(out.toString().contains("wins:1"));

    }

    @Test
    public void when_chooseScissors_then_beatsPaper() {
        when(scanner.nextLine()).thenReturn("Scissors").thenReturn("Quit");
        when(random.nextInt(3)).thenReturn(PAPER);
        System.setOut(new PrintStream(out));

        game.play();
        assertTrue(out.toString().contains("Computer chose paper\n" +
                "you win!\n" +
                "wins:1"));
        assertTrue(out.toString().contains("you win!"));
        assertTrue(out.toString().contains("wins:1"));
    }

    @Test
    public void when_computerChooseScissors_then_beatsPaper() {
        when(scanner.nextLine()).thenReturn("Paper").thenReturn("Quit");
        when(random.nextInt(3)).thenReturn(SCISSORS);
        System.setOut(new PrintStream(out));

        game.play();
        assertTrue(out.toString().contains("Computer chose scissors\n" +
                "you lose.\n" +
                "wins:0"));
        assertTrue(out.toString().contains("you lose."));
        assertTrue(out.toString().contains("loses:1"));
    }

    @Test
    public void when_bothChooseRock_then_tie() {
        when(scanner.nextLine()).thenReturn("Rock").thenReturn("Quit");
        when(random.nextInt(3)).thenReturn(ROCK);

        System.setOut(new PrintStream(out));

        game.play();
        assertTrue(out.toString().contains("Computer chose rock\n" +
                "It's a tie\n" +
                "wins:0"));
        assertTrue(out.toString().contains("It's a tie"));
        assertTrue(out.toString().contains("loses:0"));
        assertTrue(out.toString().contains("ties:1"));
    }
}
