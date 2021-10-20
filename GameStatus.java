public class GameStatus{
    int TotalNumberOfPlayers;
    int CurrentNumberOfPlayers;
    int rounds;

    public void setTotalNumberOfPlayers(int totalNumberOfPlayers) {
        /*
         * This function can only run before the first round start.
         * By using this function, the number of total players will be changed;
         * Max value = 6
         * Min value = 2
         */
        if(rounds == 0) TotalNumberOfPlayers = totalNumberOfPlayers;
    }

    public int getTotalNumberOfPlayers() {
        /* 
         * This function return the number of total player,
         * including the player is already bankruptcy.
         */
        return TotalNumberOfPlayers;
    }

    public void setCurrentNumberOfPlayers(int currentNumberOfPlayers) {
        /*
         * This function is to set the current number of players,
         * if a player went bankruptcy, this function will be call.
         * If only 1 player left in the board, winner will be that remaining player.
         */
        CurrentNumberOfPlayers = currentNumberOfPlayers;
        CheckWinner();
    }

    public int getCurrentNumberOfPlayers() {
        /* 
         * This function return the number of current player.
         */
        return CurrentNumberOfPlayers;
    }

    public void RoundEnd() {
        /*
         * This function will be called when all player taking their turns once.
         * This function checks the rounds if it is more than 100.
         */
        rounds++;
        if(rounds > 100) CheckWinner();
    }

    public void CheckWinner(){
        /*
         * This function will be called if there is only 1 player left
         * or after 100 rounds. 
         * This function will check who is the richest player in the game.
         * Tie (multiple winners) is possible.
         */
        /*
         * This function will be called if there is only 1 player left
         * or after 100 rounds. 
         * This function will check who is the richest player in the game.
         * Tie (multiple winners) is possible.
         */
        // todo
    }
}