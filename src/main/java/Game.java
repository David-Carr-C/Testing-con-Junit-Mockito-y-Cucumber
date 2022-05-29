import java.util.Random;
import java.util.Scanner;

public class Game {
    private final int ROCK = 0; //Constantes de eleccion
    private final int PAPER = 1;
    private final int SCISSORS = 2;
    private Scanner scanner; //Objetos para el input del juego
    private Random random;

    public Game() {
        scanner = new Scanner(System.in);
        random = new Random();
    }

    public void play() { //Comienza y desarrolla el juego, cada que se ejecute play() se inicializa un nuevo juego
        String choice = gameMenu();

        ScoreBoard scoreBoard = new ScoreBoard();

        while (!choice.equals("quit")) {

            int choiceNum = getUserChoice(choice);
            int compNum = getComputerChoice();

            if (isTie(choiceNum, compNum)) { //Cases
                System.out.println("It's a tie");
                scoreBoard.incrementTies();

            } else if (isUserRockBeatsComputerScissors(choiceNum, compNum)
                    || isUserScissorsBeatsComputerPaper(choiceNum, compNum)
                    || isUserPaperBeatsComputerRock(choiceNum, compNum)) {
                System.out.println("you win!");
                scoreBoard.incrementWins();

            } else { //otherwise computer wins
                System.out.println("you lose.");
                scoreBoard.incrementLoses();
            }

            choice = playAgain(scoreBoard);
        }
    }

    private int getUserChoice(String choice) {
        try {
            return GameChoice.valueOf(choice.toUpperCase()).ordinal();
        }catch (IllegalArgumentException e) {
            return getAValidChoiceNum();
        }
    }

    private int getAValidChoiceNum() {
        while (true) { //continue while user input is still not valid
            String choice = invalidAnswer();
            try {
                return GameChoice.valueOf(choice.toUpperCase()).ordinal();
            } catch (IllegalArgumentException e) {
                if (choice.equals("quit"))
                    System.exit(0);
            }
        }
    }

    private String gameMenu() {
        System.out.println("Let's play Rock, Paper, Scissors!");
        System.out.println("Say \"Rock\", \"Paper\", or \"Scissors\" to indicate your choice. Otherwise say \"Quit\" to quit.");
        return scanner.nextLine().toLowerCase(); //prompt response //change to lowercase for consistency
    }

    private int getComputerChoice() {
        int randomValue = (random.nextInt(3)); //computer generate random num
        if (randomValue == ROCK) System.out.println("Computer chose rock");
        if (randomValue == PAPER) System.out.println("Computer chose paper");
        if (randomValue == SCISSORS) System.out.println("Computer chose scissors");
        return randomValue;
    }

    private String invalidAnswer() {
        System.out.println("Sorry, it looks like you didn't enter a correct input. Try again.");
        return scanner.nextLine().toLowerCase();
    }

    private String playAgain(ScoreBoard scoreBoard) {
        System.out.println("wins:" + scoreBoard.getWins() + "\nloses:" + scoreBoard.getLoses() + "\nties:" + scoreBoard.getTies()); //print out number of wins, ties, and loses
        System.out.println("Let's play again! \n \n");
        System.out.println("Say \"Rock\", \"Paper\", or \"Scissors\" to indicate your choice. Otherwise say \"Quit\" to quit.");
        return scanner.nextLine().toLowerCase();
    }

    private boolean isTie(int choiceNum, int compNum) { //Comparisons
        return choiceNum == compNum;
    }

    private boolean isUserRockBeatsComputerScissors(int choiceNum, int compNum) {
        return choiceNum == ROCK && compNum == SCISSORS;
    }

    private boolean isUserScissorsBeatsComputerPaper(int choiceNum, int compNum) {
        return choiceNum == SCISSORS && compNum == PAPER;
    }

    private boolean isUserPaperBeatsComputerRock(int choiceNum, int compNum) {
        return choiceNum == PAPER && compNum == ROCK;
    }
}