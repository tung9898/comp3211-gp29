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
    void setName() {
        player1.setName("Elon Musk");
    }

    @Test
    void getName() {
        player1.setName("Elon Musk");
        assertEquals("Elon Musk",player1.getName());
    }

    @Test
    void setId() {
        player1.setId(1);
    }

    @Test
    void getId() {
        player1.setId(1);
        assertEquals(1,player1.getId());
    }

    @Test
    void setMoney() {
        player1.setMoney(5000);
    }

    @Test
    void getMoney() {
        player1.setMoney(5000);
        assertEquals(5000,player1.getMoney());
    }

    @Test
    void setCurrentSquare() {
        player1.setCurrentSquare(5);
    }

    @Test
    void getCurrentSquare() {
        player1.setCurrentSquare(5);
        assertEquals(5,player1.getCurrentSquare());
    }

    @Test
    void setDaysInJail() {
        player1.setDaysInJail(2);
    }

    @Test
    void getDaysInJail() {
        player1.setDaysInJail(2);
        assertEquals(2,player1.getDaysInJail());
    }

    @Test
    void setBankruptcy() {
        player1.setBankruptcy(true);
    }

    @Test
    void getBankruptcy() {
        player1.setBankruptcy(true);
        assertEquals(true,player1.getBankruptcy());
    }
}