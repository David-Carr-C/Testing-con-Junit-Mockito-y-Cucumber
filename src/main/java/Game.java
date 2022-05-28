import java.util.Random;
import java.util.Scanner;

public class Game {
    private final int ROCK = 0;
    private final int PAPER = 1;
    private final int SCISSORS = 2;
    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();

    public void play() {
        //start game
        String choice = gameMenu().toLowerCase();

        //initialize variables
        int tieNum = 0, winNum = 0, lossNum = 0, choiceNum = 0;
        while (!choice.equals("quit")) { //do the following if the user does not put in "quit"
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

            int compNum = getComputerChoice();
            if(isTie(choiceNum, compNum)) { //Cases
                System.out.println("It's a tie");
                tieNum++;
            } else if (isRockBeatsScissors(choiceNum, compNum)) {
                System.out.println("you win!");
                winNum++;
            } else if (isScissorsBeatsPaper(choiceNum, compNum)) {
                System.out.println("you win!");
                winNum++;
            } else if (isPaperBeatsRock(choiceNum, compNum)) {
                System.out.println("you win!");
                winNum++;
            } else { //otherwise computer wins
                System.out.println("you lose.");
                lossNum++;
            }

            choice = playAgain(winNum, lossNum, tieNum).toLowerCase();
        }
    }

    private int getChoiceNum(int choiceNum) {
        while(choiceNum == 0) { //continue while user input is still not valid
            String choice = invalidAnswer().toLowerCase();
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
        String result = scanner.nextLine();
        return result; //prompt response //change to lowercase for consistency
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
        return scanner.nextLine();
    }

    private String playAgain(int winNum, int lossNum, int tienum) {
        System.out.println("wins:" + winNum + "\nloses:" + lossNum + "\nties:" + tienum); //print out number of wins, ties, and loses
        System.out.println("Let's play again! \n \n"); //start game again
        System.out.println("Say \"Rock\", \"Paper\", or \"Scissors\" to indicate your choice. Otherwise say \"Quit\" to quit.");
        return scanner.nextLine(); //prompt for new user input
    }

    private boolean isTie(int choiceNum, int compNum) {
        return choiceNum == compNum;
    }

    private boolean isRockBeatsScissors(int choiceNum, int compNum) {
        return choiceNum == ROCK && compNum == SCISSORS;
    }

    private boolean isScissorsBeatsPaper(int choiceNum, int compNum) {
        return choiceNum == SCISSORS && compNum == PAPER;
    }

    private boolean isPaperBeatsRock(int choiceNum, int compNum) {
        return choiceNum == PAPER && compNum == ROCK;
    }
}