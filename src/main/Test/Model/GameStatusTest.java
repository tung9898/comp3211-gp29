package Model;

import org.junit.jupiter.api.Test;

import Model.GameStatus;

import static org.junit.jupiter.api.Assertions.*;

class GameStatusTest {
    GameStatus gs = new GameStatus(5, 5, 20);
    GameStatus gs2 = new GameStatus(3, 3);
    @Test
    void TotalNumberOfPlayers1() {
        for(int i = 6; i >= 0; i--){
            gs.setTotalNumberOfPlayers(i);
            assertEquals(i, gs.getTotalNumberOfPlayers());
        }
    }

    @Test
    void TotalNumberOfPlayers2() {
        for(int i = 6; i >= 0; i--){
            gs2.setTotalNumberOfPlayers(i);
            assertEquals(i, gs2.getTotalNumberOfPlayers());
        }
    }

    @Test
    void CurrentNumberOfPlayers1() {
        for(int i = 6; i >= 0; i--){
            gs.setCurrentNumberOfPlayers(i);
            assertEquals(i, gs.getCurrentNumberOfPlayers());
        }
    }

    @Test
    void CurrentNumberOfPlayers2() {
        for(int i = 6; i >= 0; i--){
            gs2.setCurrentNumberOfPlayers(i);
            assertEquals(i, gs2.getCurrentNumberOfPlayers());
        }
    }

    @Test
    void CurrentPlayer1() {
        for(int i = 6; i >= 0; i--){
            gs.setCurrentPlayer(i);
            assertEquals(i, gs.getCurrentPlayer());
        }
    }

    @Test
    void CurrentPlayer2() {
        for(int i = 6; i >= 0; i--){
            gs2.setCurrentPlayer(i);
            assertEquals(i, gs2.getCurrentPlayer());
        }
    }

    @Test
    void Rounds1() {
        for(int i = 0; i <= 100; i++){
            gs.setRounds(i);
            assertEquals(i, gs.getRounds());
        }
    }

    @Test
    void Rounds2() {
        for(int i = 0; i <= 100; i++){
            gs2.setRounds(i);
            assertEquals(i, gs2.getRounds());
        }
    }
}