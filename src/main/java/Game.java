import java.util.Random;
import java.util.Scanner;

public class Game {
    private final int ROCK = 0;
    private final int PAPER = 1;
    private final int SCISSORS = 2;
    /* final <- Mockito es sensible a las palabras final y las modificaciones del mock*/
    private Scanner scanner;
    private Random random;

    /**
     * Inicializa scanner y random para el juego
     * */
    public Game() {
        scanner = new Scanner(System.in);
        random = new Random();
    }

    /**
     * Comienza y se desarrolla el juego, cada que se ejecute play() se inicializa un nuevo juego
     * */
    public void play() {
        String choice = gameMenu();

        int tieNum = 0, winNum = 0, lossNum = 0, choiceNum; //Se inicializan las variables
        while (!choice.equals("quit")) {
            choiceNum = 4;
            choiceNum = getUserChoiceNum(choice, choiceNum);

            int compNum = getComputerChoice();
            if (isTie(choiceNum, compNum)) { //Cases
                System.out.println("It's a tie");
                tieNum++;
            } else if ( isUserRockBeatsComputerScissors(choiceNum, compNum) || isUserScissorsBeatsComputerPaper(choiceNum, compNum)
                    || isUserPaperBeatsComputerRock(choiceNum, compNum) ) {
                System.out.println("you win!");
                winNum++;
            } else { //otherwise computer wins
                System.out.println("you lose.");
                lossNum++;
            }

            choice = playAgain(winNum, lossNum, tieNum);
        }
    }

    private int getUserChoiceNum(String choice, int choiceNum) {
        switch (choice) {
            case "rock": //assign numbers to string
                choiceNum = ROCK;
                break;
            case "paper":
                choiceNum = PAPER;
                break;
            case "scissors":
                choiceNum = SCISSORS;
                break;
            default:
                choiceNum = getChoiceNum(choiceNum); //not valid responses
        }
        return choiceNum;
    }

    private int getChoiceNum(int choiceNum) {
        while(choiceNum == 4) { //continue while user input is still not valid
            String choice = invalidAnswer();
            switch (choice) {
                case "rock":
                    choiceNum = ROCK;
                    break;
                case "paper":
                    choiceNum = PAPER;
                    break;
                case "scissors":
                    choiceNum = SCISSORS;
                    break;
                case "quit":
                    System.exit(0); //quit program
            }
        }
        return choiceNum;
    }

    private String gameMenu() {
        System.out.println("Let's play Rock, Paper, Scissors!");
        System.out.println("Say \"Rock\", \"Paper\", or \"Scissors\" to indicate your choice. Otherwise say \"Quit\" to quit.");
        return scanner.nextLine().toLowerCase();//prompt response //change to lowercase for consistency
    }

    private int getComputerChoice() {
        int compNum = (random.nextInt(3)); //computer generate random num
        if (compNum == ROCK) System.out.println("Computer chose rock"); //print computer choice
        if (compNum == PAPER) System.out.println("Computer chose paper");
        if (compNum == SCISSORS) System.out.println("Computer chose scissors");
        return compNum;
    }

    private String invalidAnswer() {
        System.out.println("Sorry, it looks like you didn't enter a correct input. Try again.");
        return scanner.nextLine().toLowerCase();
    }

    private String playAgain(int winNum, int lossNum, int tienum) {
        System.out.println("wins:" + winNum + "\nloses:" + lossNum + "\nties:" + tienum); //print out number of wins, ties, and loses
        System.out.println("Let's play again! \n \n"); //start game again
        System.out.println("Say \"Rock\", \"Paper\", or \"Scissors\" to indicate your choice. Otherwise say \"Quit\" to quit.");
        return scanner.nextLine().toLowerCase(); //prompt for new user input
    }

    private boolean isTie(int choiceNum, int compNum) {
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