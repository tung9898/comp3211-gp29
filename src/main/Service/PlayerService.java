package Service;
import Model.Player;

public class PlayerService {

    public static Player[] players;
    public static int currentPlayer;

    public static Player[] getPlayers(){
        return players;
    }

    public static void setPlayers(int number){
        players = new Player[number];
    }

    public static void setPlayer(int number){
        players[number] = new Player(number);
    }

    public static void setPlayersMoney(int playerNum, int money){
        players[playerNum].setMoney(money);
    }

    public static int getPlayerMoney(int playerNum){
        return players[playerNum].getMoney();
    }

    public static void setCurrentPlayer(int number) {
        currentPlayer = number;
    }

    public static int getCurrentPlayer() {
        return currentPlayer;
    }

    public static boolean getPlayerBankruptcy(int playerNum) {
        return players[playerNum].getBankruptcy();
    }

    public static int getDaysInJail(int playerNum) {
        return players[playerNum].getDaysInJail();
    }

    public static int getPlayerCurrentSquare(int playerNum){
        return players[playerNum].getCurrentSquare();
    }

}
