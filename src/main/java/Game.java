import java.util.Random;
import java.util.Scanner;

public class Game {
    private int ROCK = 1;
    private int PAPER = 2;
    private int SCISSORS = 3;

    private Scanner input;
    private Random random;

    public Game() {
        input = new Scanner(System.in);
        random = new Random();
    }

    public void play() {
        //start game
        String choice = gameMenu();
        choice = choice.toLowerCase(); //change to lowercase for consistency

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
                    //not valid responses
                    while(choiceNum == 0) { //continue while user input is still not valid
                        choice = invalidAnswer();
                        choice = choice.toLowerCase();
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
            }

            int compNum = computerChoice();
            if( isTie(choiceNum, compNum) ) { //Cases
                System.out.println("It's a tie");
                tieNum++;
            } else if (isRockBeatsScissors(choiceNum, compNum)) {
                System.out.println("you win!");
                winNum++;
            }
            else if (isScissorsBeatsPaper(choiceNum, compNum)) {
                System.out.println("you win!");
                winNum++;
            }
            else if (isPaperBeatsRock(choiceNum, compNum)) {
                System.out.println("you win!");
                winNum++;
            } else { //otherwise computer wins
                System.out.println("you lose.");
                lossNum++;
            }
            
            choice = playAgain(winNum, lossNum, tieNum);
            choice = choice.toLowerCase();
        }
    }

    private String gameMenu() {
        System.out.println("Let's play Rock, Paper, Scissors!");
        System.out.println("Say \"Rock\", \"Paper\", or \"Scissors\" to indicate your choice. Otherwise say \"Quit\" to quit.");
        return input.nextLine(); //prompt response
    }

    private int computerChoice() {
        int compNum = (random.nextInt(3)) + 1; //computer generate random num
        if (compNum == ROCK) System.out.println("Computer chose rock"); //print computer choice
        if (compNum == PAPER) System.out.println("Computer chose paper");
        if (compNum == SCISSORS) System.out.println("Computer chose scissors");
        return compNum;
    }
    
    private String invalidAnswer() {
        System.out.println("Sorry, it looks like you didn't enter a correct input. Try again.");
        return input.nextLine();
    }

    private String playAgain(int winNum, int lossNum, int tienum) {
        System.out.println("wins:" + winNum + "\nloses:" + lossNum + "\nties:" + tienum); //print out number of wins, ties, and loses
        System.out.println("Let's play again! \n \n"); //start game again
        System.out.println("Say \"Rock\", \"Paper\", or \"Scissors\" to indicate your choice. Otherwise say \"Quit\" to quit.");
        return input.nextLine();//prompt for new user input
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