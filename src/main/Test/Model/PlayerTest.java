package Test.Model;

import org.junit.jupiter.api.Test;

import Model.Player;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    /*
     * Test Case 1
     * - Test the getter and setter methods in Player class
     * - Player id should be ranged from 0 to (numberOfPlayers-1)
     */
    /*
     * Test case 1.1
     * Step 1: test if create object with constructor is true
     * Expected result: Id = 0, Money = 0, CurrentSquare = 0, Name = "Player 1", DaysInJail = -1
     */
    Player player1 = new Player(0);

    @Test
    void TestCase1() {
        Player player2 = new Player("haha", 1);
        assertEquals(1,player2.getId());
        assertEquals(1500,player2.getMoney());
        assertEquals(0,player2.getCurrentSquare());
        assertEquals("haha",player2.getName());
        assertEquals(-1,player2.getDaysInJail());
        assertEquals(false,player2.getBankruptcy());
    }

    @Test
    void TestCase2() {
        Player player3 = new Player("bye", 3, 20, 2, 2, true);
        assertEquals(3,player3.getId());
        assertEquals(20,player3.getMoney());
        assertEquals(2,player3.getCurrentSquare());
        assertEquals("bye",player3.getName());
        assertEquals(2,player3.getDaysInJail());
        assertEquals(true,player3.getBankruptcy());
    }

    @Test
    void Name() {
        player1.setName("hello");
        assertEquals("hello",player1.getName());

        player1.setName("Jeff Bezos");
        assertEquals("Jeff Bezos",player1.getName());
    }

    @Test
    void Id() {
        for(int i = 0; i < 6; i++){
            player1.setId(i);
            assertEquals(i,player1.getId());
        }
    }

    @Test
    void Money() {
        for(int i = 0; i < 1000000; i++){
            player1.setMoney(i);
            assertEquals(i,player1.getMoney());
        }
    }

    @Test
    void CurrentSquare() {
        for(int i = 0; i <= 20; i++){
            player1.setCurrentSquare(i);
            assertEquals(i,player1.getCurrentSquare());
        }
    }

    @Test
    void DaysInJail() {
        for(int i = -1; i <= 3; i++){
            player1.setDaysInJail(i);
            assertEquals(i,player1.getDaysInJail());
        }
    }

    @Test
    void Bankruptcy() {
        player1.setBankruptcy(true);
        assertEquals(true,player1.getBankruptcy());
        player1.setBankruptcy(false);
        assertEquals(false,player1.getBankruptcy());
    }
}