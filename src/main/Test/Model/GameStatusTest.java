package Test.Model;

import org.junit.jupiter.api.Test;

import Model.GameStatus;

import static org.junit.jupiter.api.Assertions.*;

class GameStatusTest {
    GameStatus gs = new GameStatus(5, 5, 20);
    GameStatus gs2 = new GameStatus(3, 3);
    @Test
    void setTotalNumberOfPlayers() {
        gs.setCurrentNumberOfPlayers(5);
    }

    @Test
    void getTotalNumberOfPlayers() {
        gs.setCurrentNumberOfPlayers(5);
        assertEquals(5, gs.getTotalNumberOfPlayers());
        assertEquals(3, gs2.getTotalNumberOfPlayers());
    }

    @Test
    void setCurrentNumberOfPlayers() {
        gs.setCurrentNumberOfPlayers(4);
    }

    @Test
    void getCurrentNumberOfPlayers() {
        gs.setCurrentNumberOfPlayers(4);
        assertEquals(4, gs.getCurrentNumberOfPlayers());
        assertEquals(3, gs2.getTotalNumberOfPlayers());
    }

    @Test
    void setRounds() {
        gs.setRounds(5);
        assertEquals(5, gs.getRounds());
    }

    @Test
    void getRounds() {
        gs2.setRounds(3);
        assertEquals(3, gs2.getTotalNumberOfPlayers());
    }
}