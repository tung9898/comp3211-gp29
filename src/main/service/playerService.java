package service;
import model.player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class playerService {

    public static player p1 = new player("tester", 123);
    public static player p2 = new player("o_O", 0);

    public static player[] getPlayers(){
        player[] players = new player[2];
        players[0] = p1;
        players[1] = p2;
        return players;
    }
}
