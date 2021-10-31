package Service;

import Model.GameStatus;
import Model.Player;

public class GameStatusService {
    // temp. data,
    // rounds = 1
    public static GameStatus gs = new GameStatus(5, 3);

    public static Player p1 = new Player("qaq", 1, 88, 0, 3, false);

    public static Player p2 = new Player("T^T", 0, 898, 1, 777, false);

    public static Player[] getPlayers(){
        Player[] Players = new Player[2];
        Players[0] = p1;
        Players[1] = p2;
        return Players;
    }

    // service start
    public static GameStatus gameStatus;
}
