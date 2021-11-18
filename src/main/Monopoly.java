import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Controller.*;
import View.UserInterface;

public class Monopoly{
    /**
     * Declare the board, using a list of object.
     */

//    static int numberOfDice = 2;
//    static int numberOfSide = 4;
    protected static int MaximumNumberOfPlayer = 6;
    protected static int MinimumNumberOfPlayer = 2;

    protected static Controller controller = new Controller();
    protected static GameStatusController gameStatusController = new GameStatusController();
    protected static IoStorageController ioStorageController = new IoStorageController();
    protected static SquareController squareController = new SquareController();
    protected static PlayerController playerController = new PlayerController();

    protected static UserInterface userInterface = new UserInterface();

    protected static int currentPlayer = 0;
    protected final static int boardLength = 20;

    public static void main(String[] args) {
        /*
          This function is the main function of Monopoly.
         */

         Game();
    }

    public static void Game(){
        // init board using list, make a list of square, from 0 to 19
        System.out.println(userInterface.printWelcome());
        // Ask for start game or load game
        int action = -1;
        Scanner userInput = new Scanner(System.in);
        // 3 for testing save file function
        while(!(action == 1 || action == 2)){
            System.out.print(UserInterface.printBeginActionInput());
            while(!(userInput.hasNextInt())){
                if(!(action == 1 || action == 2)){
                    System.out.println(UserInterface.printBeginActionInputError());
                }
                System.out.print(UserInterface.printBeginActionInput());
                userInput.next();
            }
            action = userInput.nextInt();
            if(!(action == 1 || action == 2)){
                System.out.println(UserInterface.printBeginActionInputError());
            }
        }
        
        switch (action) {
            case 1:
                // request a number from a range, use for the number of player.
                // Ask for number of players input.
                int numberOfPlayer;
                Scanner myObj = new Scanner(System.in);
                while(true) {
                    System.out.print(userInterface.printNumberOfPlayerInput(MinimumNumberOfPlayer, MaximumNumberOfPlayer));
                    try{
                        numberOfPlayer = myObj.nextInt();
                        if (MinimumNumberOfPlayer <= numberOfPlayer && numberOfPlayer <= MaximumNumberOfPlayer){
                            InitGame(numberOfPlayer);
                            break;
                        }
                        else{
                            System.out.println(userInterface.printNumberOfPlayerInputError(MinimumNumberOfPlayer, MaximumNumberOfPlayer));
                        }
                    } catch(Exception e){
                        System.out.println(userInterface.printNumberOfPlayerInputError(MinimumNumberOfPlayer, MaximumNumberOfPlayer));
                        myObj = new Scanner(System.in);
                    }
                }
                // start the game
                while (GameStart());
                // ask for input if player want to continue;
                // break if necessary
                // ask for input if player want to change to number of player;
                // call InitSquare if necessary
                break;
            case 2:
                // Load file
                String loadFileName = askForLoadFileName(ioStorageController.getFilesList());
                // Load the data from file
                JSONObject loadObject = ioStorageController.loadFile(loadFileName);
                try{
                    JSONObject gameStatusObject = (JSONObject) loadObject.get("GameStatus");

                    gameStatusController.setGameStatus( ((Long) gameStatusObject.get("TotalNumberOfPlayers")).intValue(),
                            ((Long) gameStatusObject.get("CurrentNumberOfPlayers")).intValue(),
                            ((Long) gameStatusObject.get("Rounds")).intValue());

                    currentPlayer = ((Long) gameStatusObject.get("CurrentPlayer")).intValue();

                    playerController.setPlayers((JSONArray) gameStatusObject.get("Players"), gameStatusController.getTotalNumberOfPlayers());
                    squareController.setBoard((JSONArray) gameStatusObject.get("Board"), boardLength);
                    System.out.println("Load successfully~");
                }catch(NullPointerException e){
                    System.out.println("Error occur!\n" + e + "\n Load failed!");
                }catch(Exception e){
                    System.out.println("Error occur!\n" + e + "\n Load failed!");
                }
                while (GameStart());
                break;
                
            default:
                break;
        }
    }

    public static boolean GameStart(){
        /*
          This function is to run each game round.
         */
        int turns = gameStatusController.getCurrentNumberOfPlayers();
        while(true){
            gameStatusController.printRoundStarted();
            for(; currentPlayer < turns; currentPlayer++){
                playerController.setCurrentPlayer(currentPlayer);
                gameStatusController.setCurrentPlayer(currentPlayer);
                if(playerController.getPlayerBankruptcy(currentPlayer)) {
                    continue;
                }
                System.out.println(userInterface.printTurnStarted(currentPlayer+1, playerController.getPlayerById(currentPlayer)));
                int currentPos[] = new int[20];
                currentPos[playerController.getPlayerCurrentSquare(currentPlayer)] = 1;
                System.out.println(userInterface.printPlayerPositionInMP(currentPos));
                if(playerController.getDaysInJail(currentPlayer) > -1) {
                    // Check the player in jail days, if it is not -1, that's mean the player is in jail.
                    HandleInJail(playerController.getDaysInJail(currentPlayer));
                }
                else{
                    while(true){
                        gameStatusController.printMenu_Style_1();
                        Scanner userInput = new Scanner(System.in);
                        int choice = userInput.nextInt();
                        while(!(choice >= 1 && choice <= 4)){
                            System.out.print(UserInterface.printBeginActionInput());
                            while(!(userInput.hasNextInt())){
                                if(!(choice >= 1 && choice <= 4)){
                                    System.out.println(UserInterface.printBeginActionInputError());
                                }
                                System.out.print(UserInterface.printBeginActionInput());
                                userInput.next();
                            }
                            choice = userInput.nextInt();
                            if(!(choice >= 1 && choice <= 4)){
                                System.out.println(UserInterface.printBeginActionInputError());
                            }
                        }
                        boolean action = false;
                        switch(choice){
                            case 1:
                                int[] dice = controller.rollingDice();
                                System.out.println(userInterface.printRollDiceResult(dice));
                                PlayerMakeAMove(dice[0] + dice[1]);
                                // turns--;
                                System.out.println(userInterface.printTurnEnded());
                                action = true;
                                break;
                            case 2:
                                playerController.printLeaderboard();
                                break;
                            case 3:
                                saveFile();
                                break;
                            case 4:
                                saveFile();
                                action = true;
                                return false;
                            default:
                                break;
                        }
                        if(action) break;
                    }
                }
            }
            currentPlayer = 0;
            System.out.println(userInterface.printRoundEnded());
            if(gameStatusController.RoundEnd() || gameStatusController.getCurrentNumberOfPlayers() == 1){
                playerController.printWinner();
                gameStatusController.printMenu_Style_2();
                Scanner myObj = new Scanner(System.in);
                int choice = myObj.nextInt();
                if (choice==1){
                    //new game
                    Game();
                }
                if (choice==2){
                    return false;
                }
            }
        }
    }

    public static void InitGame(int numberOfPlayer) {
        /*
          This function is to set some important data when the game starts.
         */
        gameStatusController = new GameStatusController(numberOfPlayer);
        playerController.setPlayers(numberOfPlayer);
        SquareController.initBoard();
        for(int i = 0; i < numberOfPlayer; i++) {
            playerController.setPlayer(i);
            playerController.setPlayersMoney(i,1500);
            // Ask for name input for each player.
        }
    }

    public static void bankruptcy(int id) {
        /*
          This function will done all the things after a player has a
          negative amount of money.
          Set all of his/her properties to unowned.
         */
        playerController.setPlayerBankruptcy(id);
        for(int i = 0; i < squareController.getSquare().length; i++){
            if(squareController.getBoardOwner(i) == id) squareController.setBoardId(i, -1);
        }
    }

    public static void PlayerMakeAMove(int move) {
        /*
          This function will help player to make a move.
         */
        // Handle the movement by calculating position
        // If new position > 19, that means the player already passed through GO. Add salary into his amount of money.
        // Set the player's current position and handle the effect of square
        // Finally, Check player's money. If it is less than 0, declare bankruptcy.

        int currentPlayer = playerController.getCurrentPlayer();
        int oldPos = playerController.getPlayerCurrentSquare(currentPlayer);
        int newPos = oldPos + move;
        int salary = squareController.GoSalary();
        if (newPos>19){
            newPos -= 20;
            squareController.printPassGO(salary);
            playerController.setPlayersMoney(currentPlayer, playerController.getPlayerMoney(currentPlayer)+salary);
        }
        playerController.setPlayerCurrentSquare(currentPlayer, newPos);

        EffectCenter(playerController.getPlayerCurrentSquare(currentPlayer),currentPlayer);

        if (playerController.getPlayerMoney(currentPlayer)<0){
            bankruptcy(currentPlayer);
            int currentPlayerNum = gameStatusController.getCurrentNumberOfPlayers();
            gameStatusController.setCurrentNumberOfPlayers(currentPlayerNum - 1);
        }
    }

    public static void HandleInJail(int inJailDays) {
        /*
          This function will
         */
        int choice;
        int[] dice;
        Scanner myObj = new Scanner(System.in);
        int currentPlayer = playerController.getCurrentPlayer();

        System.out.println("You're in jail and cannot make a move.");
        System.out.println("Number of turns in jail: "+(inJailDays+1));
        if (inJailDays==0){
            while(true){
                System.out.println("Enter 1 to roll the dice:");
                try {
                    choice = myObj.nextInt();
                    if (choice==1){
                        dice = controller.rollingDice();
                        System.out.println(UserInterface.printRollDiceResult(dice));
                        if (dice[0]==dice[1]){
                            System.out.println("You succeed to get out of the jail. Congratulation!");
                            playerController.setPlayerDaysInJail(currentPlayer,-1);
                            PlayerMakeAMove(dice[0]+dice[1]);
                            break;
                        }
                        else{
                            System.out.println("You failed to get out of the jail. Try again in the next round.");
                            playerController.setPlayerDaysInJail(currentPlayer,inJailDays+1);
                            break;
                        }
                    }
                    else{
                        System.out.println("You can only enter 1 to continue.");
                    }
                } catch (Exception e) {
                    System.out.println("You can only enter 1 to continue.");
                }
                    
            }
        }
        else{
            while(true){
                System.out.println("Enter 1 to roll the dice, 2 to pay a fine of HKD 150:");
                choice = myObj.nextInt();
                dice = controller.rollingDice();
                if (choice==1){
                    System.out.println(UserInterface.printRollDiceResult(dice));
                    if (dice[0]==dice[1]){
                        System.out.println("You succeed to get out of the jail. Congratulation!");
                        playerController.setPlayerDaysInJail(currentPlayer,-1);
                        PlayerMakeAMove(dice[0]+dice[1]);
                        break;
                    }
                    else{
                        if (inJailDays==2){
                            System.out.println("You failed to get out of the jail. You must pay a fine of HKD 150 to leave.");
                            System.out.println("Fine paid. You can get out of the jail now.");
                            playerController.setPlayersMoney(currentPlayer, playerController.getPlayerMoney(currentPlayer)-150);
                            playerController.setPlayerDaysInJail(currentPlayer,-1);
                            PlayerMakeAMove(dice[0]+dice[1]);
                            break;
                        }
                        else{
                            System.out.println("You failed to get out of the jail. Try again in the next round.");
                            playerController.setPlayerDaysInJail(currentPlayer,inJailDays+1);
                            break;
                        }
                    }
                }
                if (choice==2){
                    System.out.println("Fine paid. You can get out of the jail now.");
                    playerController.setPlayersMoney(currentPlayer, playerController.getPlayerMoney(currentPlayer)-150);
                    playerController.setPlayerDaysInJail(currentPlayer,-1);
                    System.out.println(userInterface.printRollDiceResult(dice));
                    PlayerMakeAMove(dice[0]+dice[1]);
                    break;
                }
                else{
                    System.out.println("You can only enter 1 or 2 to continue.");
                }
            }
        }
    }

    public static void EffectCenter(int squareId, int currentPlayer) {
        /*
          This function is to redirect the square to its belonging effect
         */
        if (squareId!=0){
            System.out.print((squareId+1)+":");
        }
        switch(squareId+1){
            case 9:
            case 13:
            case 19:
                int chance = squareController.ChanceSalary();
                if (chance <0){
                    playerController.printChanceNegative(Math.abs(chance));
                }
                else{
                    playerController.printChancePositive(chance);
                }
                playerController.setPlayersMoney(currentPlayer, playerController.getPlayerMoney(currentPlayer)+chance);
                playerController.printMoney(playerController.getPlayerMoney(currentPlayer));
                break;
            case 4:
                int tax = playerController.PayTax();
                playerController.printPayTax(tax);
                playerController.setPlayersMoney(currentPlayer, playerController.getPlayerMoney(currentPlayer)-tax);
                playerController.printMoney(playerController.getPlayerMoney(currentPlayer));
                break;
            case 1:
                break;
            case 6:
            case 11:
                System.out.println(squareController.SquareName(squareId)+": No effect.");
                break;
            case 16:
                System.out.println(squareController.SquareName(squareId)+": You have to go to jail.");
                playerController.setPlayerDaysInJail(currentPlayer,0);
                playerController.setPlayerCurrentSquare(currentPlayer, 5);
                break;
            default:
                String name = squareController.SquareName(squareId);
                if (squareController.getBoardOwner(squareId)==-1){
                    SquarePurchase(squareId);
                }
                else{
                    int owner = squareController.getBoardOwner(squareId);
                    if (currentPlayer!=owner){
                        SquarePayRent(squareId);
                    }
                    else{
                        System.out.println(name+" is owned by you. No effect.");
                    }
                }
                break;
        }
    }

    public static void SquarePurchase(int squareId) {
        /*
          This function is to handle the player’s purchase of property.
         */
        String name = squareController.SquareName(squareId);
        int landPrice = squareController.SquarePrice(squareId);
        int currentPlayer = playerController.getCurrentPlayer();
        int balance = playerController.getPlayerMoney(currentPlayer);

        squareController.printSquarePurchase(name,landPrice,balance);
        Scanner myObj = new Scanner(System.in);
        int choice;
        while(true){
            while(true){
                System.out.println("Would you like to buy the property? Enter 1 to buy, 2 to do nothing:");
                String input = myObj.nextLine();
                try{
                    choice = Integer.parseInt(input);
                    break;
                } catch (NumberFormatException e){
                    System.out.println("Please input integer inside the range.");
                }
            }
            if(choice==1){
                if(balance >= landPrice){
                    playerController.setPlayersMoney(currentPlayer, balance-landPrice);
                    squareController.setBoardOwner(squareId, currentPlayer);
                    squareController.printSquarePurchaseSuccess(name,playerController.getPlayerMoney(currentPlayer));
                }
                else{
                    squareController.printSquarePurchaseFailed(name,balance);
                }
                break;
            }
            if(choice==2){
                squareController.printSquarePurchaseNo(name);
                break;
            }
            else{
                System.out.println("You can only enter 1 or 2 to continue.");
            }
        }
    }

    public static void SquarePayRent(int squareId) {
        /*
          This function is to handle the player’s payment of rent and the owner’s receipt of rent.
         */
        String name = squareController.SquareName(squareId);
        int landRent = squareController.SquareRent(squareId);
        int currentPlayer = playerController.getCurrentPlayer();
        int owner = squareController.getBoardOwner(squareId);

        squareController.printSquarePayRentMessage(name, (owner+1), landRent);
        playerController.setPlayersMoney(currentPlayer, playerController.getPlayerMoney(currentPlayer)-landRent);
        playerController.setPlayersMoney(owner, playerController.getPlayerMoney(owner)+landRent);
        playerController.printMoney(playerController.getPlayerMoney(currentPlayer));
        System.out.println("Player "+(owner+1)+" have $"+playerController.getPlayerMoney(owner)+" now.");
    }

    public static String askForLoadFileName(String[] filenames){
        // Ask user to select the file
        Scanner userInput = new Scanner(System.in);
        int fileNumber = -1;
        do{
            // Print files' information
            System.out.println("Here are the files:");
            for(int i = 0; i < filenames.length; i++){
                System.out.printf("[%1$s] %2$s %n", i, filenames[i]);
            }
            System.out.print("Input your choice (number only):");

            // Validate the input is or not a number
            while(!(userInput.hasNextInt())){
                System.out.print("Input your choice (number only):");
                userInput.next();
            }
            fileNumber = userInput.nextInt();
        }while(!(fileNumber >= 0 &&
                fileNumber < filenames.length &&
                filenames[fileNumber].contains(".json")));
        return filenames[fileNumber];
    }

    public static void saveFile(){
        // Ask user to input file name
        Scanner userInput = new Scanner(System.in);
        System.out.print("Input your file name:");
        while (!userInput.hasNext("[^\\/:*?\"<>|]*$")) {
            System.out.print("Input your file name (no \\/:*?\"<>|):");
            userInput.next();
        }
        String saveFileName = userInput.next();

        // Save file
        ioStorageController.saveFile(playerController.getPlayersList(), 
                                     gameStatusController.getGameStatusMap(), 
                                     squareController.getBoardList(),
                                     saveFileName);
        
    }
}