import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Controller.*;
import Model.*;
import View.*;

public class Monopoly{
    /**
     * Declare the borad, using a list of object.
     */
    
    static int currentPlayer;
/*     static int numberOfDice = 2;
    static int numberOfSide = 4; */
    static int MaximumNumberOfPlayer = 6;
    static int MinimumNumberOfPlayer = 2;

    static GameStatus gameStatusModel;
    static GameStatusView gameStatusView;
    static GameStatusController statusController;

    static SquareController squareController = new SquareController();

    public static void main(String[] args) {
        /**
         * This function is the main function of Monopoly.  
         */

        // init board using list, make a list of square, from 0 to 19
        System.out.println(UserInterface.gsv.printWelcome());
        // Ask for start game or load game
        int action = -1;
        Scanner userInput = new Scanner(System.in); // need userInput.close(); ?
        // 3 for testing save file function
        while(!(action == 1 || action == 2 || action == 3)){
            System.out.print(UserInterface.printBeginActionInput());
            while(!(userInput.hasNextInt())){
                System.out.print(UserInterface.printBeginActionInput());
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
                    System.out.print(UserInterface.pv.printNumberOfPlayerInput(MinimumNumberOfPlayer, MaximumNumberOfPlayer));
                    numberOfPlayer = myObj.nextInt();
                    if (MinimumNumberOfPlayer <= numberOfPlayer && numberOfPlayer <= MaximumNumberOfPlayer){
                        InitSqaure(numberOfPlayer);
                        break;
                    }
                    else{
                        System.out.println(UserInterface.pv.printNumberOfPlayerInputError(MinimumNumberOfPlayer, MaximumNumberOfPlayer));
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
                // Load file
                String loadFileName = askForLoadFileName(IoController.getFilesList());
                // Load the data from file
                JSONObject loadObject = IoController.loadFile(loadFileName);
                try{
                    JSONObject gameStatusObject = (JSONObject) loadObject.get("GameStatus");
                    
                    GameStatusController.setGameStatus( ((Long) gameStatusObject.get("TotalNumberOfPlayers")).intValue(), 
                                                                ((Long) gameStatusObject.get("CurrentNumberOfPlayers")).intValue(),
                                                                ((Long) gameStatusObject.get("Rounds")).intValue());
                    
                    
                    PlayerController.setPlayers((JSONArray) gameStatusObject.get("Players"), GameStatusController.getTotalNumberOfPlayers());
                    System.out.println("Load successfully~");
                }catch(NullPointerException e){
                    System.out.println("Error occur!\n" + e + "\n Load failed!");
                }catch(Exception e){
                    System.out.println("Error occur!\n" + e + "\n Load failed!");
                }
                break;
            case 3:
                // Save file
                // Ask user to input file name
                userInput = new Scanner(System.in);
                System.out.print("Input your file name:");
                while (!userInput.hasNext("[^\\/:*?\"<>|]")) {
                    System.out.print("Input your file name (no \\/:*?\"<>|):");
                    userInput.next();
                }
                String saveFileName = userInput.next();

                // Save file
                IoController.saveFile(PlayerController.getPlayersList(), GameStatusController.getGameStatusMap(), saveFileName);
                // ActionController.saveFile(PlayerController.getPlayers(), gameStatusModel);
                break;
            default:
                break;
        }
    }

    public static void GameStart(){
        /**
         * This function is to run each game round. 
         */
        int turns = GameStatusController.getCurrentNumberOfPlayers();
        while(true){
            statusController.printRoundStarted();
            for(int i = 0; i < turns; i++){
                System.out.println(UserInterface.sysv.printTurnStarted(i+1));
                int currentPos[] = new int[20];
                currentPos[PlayerController.getPlayerCurrentSquare(i)] = 1;
                System.out.println(UserInterface.gsv.printPlayerPositionInMP(currentPos));
                if(PlayerController.getPlayerBankruptcy(i)) continue;
                PlayerController.setCurrentPlayer(i); 
                if(PlayerController.getDaysInJail(i) > 0) {
                    // Check the player in jail days, if it is not -1, thats mean the player is in jail.
                }
                System.out.println(UserInterface.printRequestRollDice());
                int[] dice = Controller.rollingDice();
                System.out.println(UserInterface.sysv.printRollDiceResult(dice));
                PlayerMakeAMove(dice[0] + dice[1]);
                // turns--;
                System.out.println(UserInterface.sysv.printTurnEnded());
            }
            System.out.println(UserInterface.gsv.printRoundEnded());
            if(GameStatusController.RoundEnd() || GameStatusController.getCurrentNumberOfPlayers() == 1){
                PlayerController.CheckWinner();
            }
        }
    }

    public static void InitSqaure(int numberOfPlayer) {
        /**
         * This function is to set some important data when the game starts. 
         */
        gameStatusModel = new GameStatus(numberOfPlayer, numberOfPlayer);
        statusController = new GameStatusController(gameStatusModel, gameStatusView);

        PlayerController.setPlayers(numberOfPlayer);
        SquareController.initBoard();
        for(int i = 0; i < numberOfPlayer; i++) {
            PlayerController.setPlayer(i);
            // Ask for name input for each player.
        }
    }

    public void bankruptcy(int id) {
        /**
         * This function will done all the things after a player has a
         * negative amount of money.
         * Set all of his/her properties to unowned.
         */
        for(int i = 0; i < squareController.getSquare().length; i++){
            if(squareController.getBoardOwner(i) == id) squareController.setBoardId(i, -1);
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

    public int EffectCenter(int squareId) {
        /**
         * This function is to redirect the sqaure to its belonging effect
         */
        switch(squareId+1){    
            case 1:    
                return squareController.GoSalary();
            case 9:
            case 13:
            case 19:
                return squareController.ChanceSalary();
            case 4:
                return PlayerController.PayTax();
            default:     
                return squareController.NoEffect();
       }    
    }

    public void SquarePurchase(int squareId) {
        /**
         * This function is to handle the player’s purchase of property. 
         */
        int landPrice = squareController.SquarePrice(squareId);
        currentPlayer = PlayerController.getCurrentPlayer();
        int balance = PlayerController.getPlayerMoney(currentPlayer);
        if(balance >= landPrice){
            // confirm message, return if fail to confirm.
            PlayerController.setPlayersMoney(currentPlayer, balance-landPrice);
            squareController.setBoardOwner(squareId, currentPlayer);
            // successful message
        }else{
            // fail to pay message
        }
    }

    public void SquarePayRent(int squareId) {
        /**
         * This function is to handle the player’s payment of rent and the owner’s receipt of rent.
         */
        int landRent = squareController.SquareRent(squareId);
        int owner = squareController.getBoardOwner(squareId);
        currentPlayer = PlayerController.getCurrentPlayer();
        int balance = PlayerController.getPlayerMoney(currentPlayer);
        PlayerController.setPlayersMoney(currentPlayer, balance-landRent);
        PlayerController.setPlayersMoney(owner, PlayerController.getPlayerMoney(owner) + landRent); // todo, need to fix if the renter don't have money.
        // money remaining message
    }

    public static String askForLoadFileName(String[] filenames){
        // Ask user to select the file
        Scanner userInput = new Scanner(System.in);
        int fileNumber = -1;
        do{
            // Print files' information
            System.out.println(UserInterface.iosv.printFileExistMessage());
            for(int i = 0; i < filenames.length; i++){
                System.out.printf("[%1$s] %2$s %n", i, filenames[i]);
            }
            System.out.print(UserInterface.iosv.printFileChoiceInput());

            // Validate the input is or not a number
            while(!(userInput.hasNextInt())){
                System.out.print(UserInterface.iosv.printFileChoiceInput());
                userInput.next();
            }
            fileNumber = userInput.nextInt();
       }while(!(fileNumber >= 0 && 
                fileNumber < filenames.length && 
                filenames[fileNumber].contains(".json")));
        return filenames[fileNumber];
    }
}