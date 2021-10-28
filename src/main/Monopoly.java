package src.main;

import java.util.Random;
import java.util.Scanner;

import src.main.Controller.*;
import src.main.Model.*;
import src.main.View.*;
import src.main.Service.*;

public class Monopoly{
    /**
     * Declare the borad, using a list of object.
     */
    //static Random rand = new Random();
    
    static int currentPlayer;
    static int numberOfDice = 2;
    static int numberOfSide = 4;
    static int MaximumNumberOfPlayer = 6;
    static int MinimumNumberOfPlayer = 2;

    static GameStatus gameStatusModel;
    static StatusController statusController;

    public static void main(String[] args) {
        /**
         * This funcion is the main function of Monopoly.
         */
        // init board using list, make a list of square, from 0 to 19
        System.out.println(UserInterface.PrintWelcomeMessage());
        // Ask for start game or load game
        int action = -1;
        Scanner userInput = new Scanner(System.in);
        while(!(action == 1 || action == 2)){
            System.out.print(UserInterface.PrintActionInputMessage());
            while(!(userInput.hasNextInt())){
                System.out.print(UserInterface.PrintActionInputMessage());
                userInput.next();
            }
            action = userInput.nextInt();
        }
        switch (action) {
            case 1:
                // request a number from a range, use for the number of player.
                // Ask for number of players input.
                int numberOfPlayer;
                Scanner myObj = new Scanner(System.in);
                while(true) {
                    System.out.print(UserInterface.PrintNumberOfPlayerInputMessage(MinimumNumberOfPlayer,MaximumNumberOfPlayer));
                    numberOfPlayer = myObj.nextInt();
                    if (MinimumNumberOfPlayer <= numberOfPlayer && numberOfPlayer <= MaximumNumberOfPlayer){
                        InitSqaure(numberOfPlayer);
                        break;
                    }
                    else{
                        System.out.println("The game only accommodates "+ MinimumNumberOfPlayer +" to " + MaximumNumberOfPlayer +" players. Enter again.");
                    }
                }
                // start the game
                boolean keepTheGameRun = true;
                while(keepTheGameRun) {
                    GameStart();
                    // ask for input if player want to continue;
                        // break if necessary
                    // ask for input if player want to change to number of player;
                        // call InitSquare if necessary
                }
                // end
                break;
            case 2:
                //
                break;
            default:
                break;
        }
    }

    public static void GameStart(){
        int turns = StatusController.getCurrentNumberOfPlayers();
        while(true){
            System.out.println(UserInterface.PrintRoundStartedMessage());
            for(int i = 0; i < turns; i++){
                System.out.println(UserInterface.PrintTurnStartedMessage(i+1));
                if(PlayerService.getPlayerBankruptcy(i)) continue;
                PlayerService.setCurrentPlayer(i); 
                if(PlayerService.getDaysInJail(i) > 0) {
                    // Check the player in jail days, if it is not -1, thats mean the player is in jail.
                }
                System.out.println(UserInterface.PrintRequestRollDiceMessage());
                int[] dice = ActionController.rollingDice();
                System.out.println(UserInterface.PrintRollDiceResultMessage(dice));
                PlayerMakeAMove(dice[0] + dice[1]);
                // turns--;
                System.out.println(UserInterface.PrintTurnEndedMessage());
            }
            System.out.println(UserInterface.PrintRoundEndedMessage());
            StatusController.RoundEnd();
        }
    }

    public static void InitSqaure(int numberOfPlayer) {
        gameStatusModel = new GameStatus(numberOfPlayer, numberOfPlayer);
        statusController = new StatusController(gameStatusModel);
        PlayerService.setPlayers(numberOfPlayer);
        for(int i = 0; i < numberOfPlayer; i++) {
            BoardService.setSquare(i);
        }
        for(int i = 0; i < numberOfPlayer; i++) {
            PlayerService.setPlayer(i);
            // Ask for name input for each player.
        }
    }

    public static void bankruptcy(int id) {
        /**
         * This function will done all the things after a player has a
         * negative amount of money.
         * Set all of his/her properties to unowned.
         */
        for(int i = 0; i < BoardService.getSquare().length; i++){
            if(BoardService.getBoardOwner(i) == id) BoardService.setBoardId(i, -1);
        }
    }

    public static void PlayerMakeAMove(int move) {
        /**
         * This function will help player to make a move.
         */
        // check if player is in jail
        // return if jail is not break
        // int oldpos = current player pos;
        // int newpos = oldpos + move;
        // if newpos > 19, newpos -= 20;
        // current player pos = newpos; // delete old pos and add new pos in Square.java
        // newpos sqaure effect

        // Finally, Check player money. If less than 0, declare bankruptcies.
    }

    public static void SquarePurchase(int squareId) {
        int landPrice = BoardService.getBoardPrice(squareId);
        currentPlayer = PlayerService.getCurrentPlayer();
        int balance = PlayerService.getPlayerMoney(currentPlayer);
        if(balance >= landPrice){
            // confirm message, return if fail to confirm.
            PlayerService.setPlayersMoney(currentPlayer, balance-landPrice);
            BoardService.setBoardOwner(squareId, currentPlayer);
            // successful message
        }else{
            // fail to pay message
        }
    }

    public static void SquarePayRent(int squareId) {
        int landRent = BoardService.getBoardRent(squareId);
        int owner = BoardService.getBoardOwner(squareId);
        currentPlayer = PlayerService.getCurrentPlayer();
        int balance = PlayerService.getPlayerMoney(currentPlayer);
        PlayerService.setPlayersMoney(currentPlayer, balance-landRent);
        PlayerService.setPlayersMoney(owner, PlayerService.getPlayerMoney(owner) + landRent); // todo, need to fix if the renter don't have money.
        // money remaining message
    }
}