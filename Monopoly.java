public class Monopoly{
    /**
     * Declare the borad, using a list of object.
     */
    Square[] board = new Square[20];
    Player[] players;
    public static void main(String[] args) {
        /**
         * This funcion is the main function of Monopoly.
         */
        // init board using list, make a list of square, from 0 to 19
        // request a number from 2 to 6, use for the number of player.
        // Ask for number of players input.

        // loop
        // press enter to roll the dice.
    }

    public void InitSqaure(int numberOfPlayer) {
        GameStatus.TotalNumberOfPlayers = numberOfPlayer;
        GameStatus.CurrentNumberOfPlayers = numberOfPlayer;
        players = new Player[numberOfPlayer];
        for(int i = 0; i < numberOfPlayer; i++) {
            board[i].Id = i;
            board[i].Landlord = -1;
            board[i].PriceTag = SquareEffect.SquarePrice(i);
            board[i].Rent = SquareEffect.SquareRent(i);
            board[i].InitplayerInSquare();
        }
        for(int i = 0; i < numberOfPlayer; i++) {
            players[i].Id = i;
            players[i].CurrentSquare = 0;
            players[i].Money = SquareEffect.GoSalary();
            // Ask for name input for each player.
        }
    }

    public void bankruptcy(int id) {
        /**
         * This function will done all the things after a player has a
         * negative amount of money.
         * Set all of his/her properties to unowned.
         */
    }

    public int[] rollingDice() {
        /**
         * This function will randomly generate 2 number in a specific range.
         */
        int[] r = new int[2];
        r[0] = 0;
        r[1] = 0;
        //PlayerMakeAMove();
        return r; // maybe no need to return
    }

    public void PlayerMakeAMove(int move) {
        /**
         * This function will help player to make a move.
         */
        // check if player is in jail
        // return if jail is not break
        // int old_pos = current player pos;
        // int new_pos = old_pos + move;
        // current player pos = new_pos; // delete old pos and add new pos in Square.java
        // new_pos sqaure effect

        // Finally, Check player money. If less than 0, declare bankruptcies.
    }

    public void SquarePurchase(int squareId) {

    }

    public void SquarePayRent(int squareId) {
        
    }
}