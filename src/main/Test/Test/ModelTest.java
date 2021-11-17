package Test.Test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Model.*;

public class ModelTest {
    /* 
     * Create some objects for test checking 
    */
    Player player1 = new Player(0);
    Square GO = new Square(0);
    Square CENTRAL = new Square(1);
    Square INCOME_TAX = new Square(3);
    Square CHANCE = new Square(8);
    Square GO_TO_JAIL = new Square(15);

    //@RepeatedTest(value = 1, name = "")
    @Test
    @DisplayName("Test player model")
    public void testPlayer(){
        /////////////////////////////////////////////////////////////////////////////
        /*
          Test Case 1
          - Test the getter and setter methods in Player class
          - Player id should be ranged from 0 to (numberOfPlayers-1)

        */
        /////////////////////////////////////////////////////////////////////////////
        /* 
         * Test case 1.1 
         * Step 1: test if create object with constructor is true
         * Expected result: Id = 0, Money = 0, CurrentSquare = 0, Name = "Player 1", DaysInJail = -1
        */
        assertEquals(0,player1.getId());
        assertEquals(0,player1.getMoney());
        assertEquals(0,player1.getCurrentSquare());
        assertEquals("Player 1",player1.getName());
        assertEquals(-1,player1.getDaysInJail());
        /* 
         * Test case 1.2 
         * Step 2: test if every setter is working
         * Expected result = Input value
        */
        player1.setMoney(2000);
        assertEquals(2000,player1.getMoney());
        player1.setCurrentSquare(3);
        assertEquals(3,player1.getCurrentSquare());
        player1.setDaysInJail(2);
        assertEquals(2,player1.getDaysInJail());
        player1.setId(1);
        assertEquals(1,player1.getId());
        player1.setName("Amy");
        assertEquals("Amy",player1.getName());
        player1.setBankruptcy(true);
        assertTrue(player1.getBankruptcy());
    }

    @Test
    @DisplayName("Test game status model")
    public void testGameStatus(){
        /////////////////////////////////////////////////////////////////////////////
        /*
          Test Case 2
          - Test the getter and setter methods in GameStatus class

        */
        /////////////////////////////////////////////////////////////////////////////
        /* 
         * Test case 2.1 
         * Step 1: test if create object with constructor is true
         * Expected result: TotalNumberOfPlayers = 5, CurrentNumberOfPlayers = 5, Rounds = 20
        */
        GameStatus gs = new GameStatus(5, 5, 20);
        assertEquals(5, gs.getTotalNumberOfPlayers());
        assertEquals(5, gs.getCurrentNumberOfPlayers());
        assertEquals(20, gs.getRounds());
    }

    @Test
    @DisplayName("Test square model")
    public void testSquare(){
        /////////////////////////////////////////////////////////////////////////////
        /*
          Test Case 3
          - Test the getter and setter methods in Square class
          - Square id should be ranged from 0 to 19. Unowned or squares' id should be -1.

        */
        /////////////////////////////////////////////////////////////////////////////
        /* 
         * Test case 3.1 
         * Step 1: test if create object with constructor is true (Square GO)
         * Expected result: Id = 0, Owner = -1
        */
        assertEquals(0,GO.getId());
        assertEquals(-1,GO.getOwner());

        /* 
         * Test case 3.2 
         * Step 2: test if create object with constructor is true (Square Central)
         * Expected result: Id = 1, Owner = -1
        */
        assertEquals(1,CENTRAL.getId());
        assertEquals(-1,CENTRAL.getOwner());

        /* 
         * Test case 3.3 
         * Step 3: test setting Owner of (Square Central)
         * Expected result: Owner = 2
        */
        CENTRAL.setOwner(2);
        assertEquals(2,CENTRAL.getOwner());

        /*
        assertEquals("Central",CENTRAL.getName());
        assertEquals(800,CENTRAL.getPrice());
        assertEquals(90,CENTRAL.getRent());
        assertEquals("GO",GO.getName());
        assertEquals("INCOME TAX",INCOME_TAX.getName());
        //kinds of squares except property squares should have a value of -1 for their prices & rents
        assertEquals(0,GO.getPrice());
        assertEquals(0,INCOME_TAX.getPrice());
        assertEquals(0,CHANCE.getPrice());
        assertEquals(0,GO_TO_JAIL.getPrice());
        assertEquals(0,GO.getRent());
        assertEquals(0,INCOME_TAX.getRent());
        assertEquals(0,CHANCE.getRent());
        assertEquals(0,GO_TO_JAIL.getRent());
         */
    }

    @Test
    @DisplayName("Test Io Storage model")
    public void testIoStorage(){
        /////////////////////////////////////////////////////////////////////////////
        /*
          Test Case 4
          - Test the getter and setter methods in Square class
          - Square id should be ranged from 0 to 19. Unowned or squares' id should be -1.

        */
        /////////////////////////////////////////////////////////////////////////////
        /* 
         * Test case 4.1 
         * Step 1: test if create object with constructor is true
         * Expected result: fileLocation = "location"
        */
        IoStorage ioStorage = new IoStorage("location");
        assertEquals("location", ioStorage.getFileLocation());
    }
}
