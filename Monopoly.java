import java.util.Random;

public class Monopoly{
    /**
     * Declare the borad, using a list of object.
     */
    static Random rand = new Random();
    static Square[] board = new Square[20];
    static Player[] players;
    static int CurrentPlayer;
    static int numberOfDice = 2;
    static int numberOfSide = 4;
    static int MaximumNumberOfPlayer = 6;
    static int MinimumNumberOfPlayer = 2;


    public static void main(String[] args) {
        /**
         * This funcion is the main function of Monopoly.
         */
        // init board using list, make a list of square, from 0 to 19
        // request a number from a range, use for the number of player.
        // Ask for number of players input.
        int numberOfPlayer = 4;
        if(MinimumNumberOfPlayer <= numberOfPlayer && numberOfPlayer <= MaximumNumberOfPlayer) InitSqaure(numberOfPlayer);
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
    }

    public static void GameStart(){
        int turns = GameStatus.CurrentNumberOfPlayers;
        while(true){
            for(int i = 0; i < turns; i++){
                if(players[i].bankruptcy) continue;
                CurrentPlayer = i;
                if(players[i].DaysInJail > 0) {
                    // Check the player in jail days, if it is not -1, thats mean the player is in jail.
                }
                int[] dice = rollingDice();
                PlayerMakeAMove(dice[0] + dice[1]);
                // turns--;
            }
            GameStatus.RoundEnd();
        }
    }

    public static void InitSqaure(int numberOfPlayer) {
        GameStatus.setTotalNumberOfPlayers(numberOfPlayer);
        GameStatus.setCurrentNumberOfPlayers(numberOfPlayer);
        players = new Player[numberOfPlayer];
        for(int i = 0; i < board.length; i++) {
            board[i].setId(i);
            board[i].setOwner(-1);
        }
        for(int i = 0; i < numberOfPlayer; i++) {
            players[i].setId(i);
            players[i].setCurrentSquare(0);
            players[i].setMoney(SquareEffect.GoSalary());
            // Ask for name input for each player.
        }
    }

    public static void bankruptcy(int id) {
        /**
         * This function will done all the things after a player has a
         * negative amount of money.
         * Set all of his/her properties to unowned.
         */
        for(int i = 0; i < board.length; i++){
            if(board[i].getOwner() == id) board[i].setId(-1);
        }
        players[id].bankruptcy = true;
        GameStatus.setCurrentNumberOfPlayers(GameStatus.getCurrentNumberOfPlayers()-1);
    }

    public static int[] rollingDice() {
        /**
         * This function is simulating a rolling dice.
         */
        int[] r = new int[numberOfDice];
        for(int i = 0; i < numberOfDice; i++){
            r[i] = rand.nextInt(numberOfSide);
        }
        return r;
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
        // get the integer of that square price and the amount of money that player have.
        int landPrice = board[squareId].getPrice();
        int balance = players[CurrentPlayer].getMoney();
        // check if the player has enough money to make this deal.
        if(balance >= landPrice){
            // confirm message, return if fail to confirm.
            players[CurrentPlayer].setMoney(balance-landPrice);
            board[squareId].setOwner(CurrentPlayer);
            // successful message
        }else{
            // fail to pay message
        }
    }

    public static void SquarePayRent(int squareId) {
        // get the integer of that square rent and the amount of money that player have.
        int landRent = board[squareId].getRent();
        int owner = board[squareId].getOwner();
        int balance = players[CurrentPlayer].getMoney();
        // directly pay the rent for the player.
        players[CurrentPlayer].setMoney(balance-landRent);
        players[owner].setMoney(players[owner].getMoney()+landRent);
        // money remaining message
    }
}