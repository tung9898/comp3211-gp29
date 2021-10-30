package Test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Model.*;

public class MonopolyTest {
    Player player1 = new Player(0);
    Square GO = new Square(0);
    Square CENTRAL = new Square(1);
    Square INCOME_TAX = new Square(3);
    Square CHANCE = new Square(8);
    Square GO_TO_JAIL = new Square(15);

    @Test
    void testPlayer(){
        //Case 1: Test the getter and setter methods in Player class
        //Player id should be ranged from 0 to (numberOfPlayers-1)
        assertEquals(0,player1.getId());
        assertEquals(0,player1.getMoney());
        assertEquals(0,player1.getCurrentSquare());
        assertEquals("Player 1",player1.getName());
        assertEquals(-1,player1.getDaysInJail());
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

        //Case 2: Test toString method
        assertEquals("player [id=1, name=Amy]",player1.toString());
    }

    @Test
    void testGameStatus(){
    }

    @Test
    void testSquare(){
        //Case 1: Test the getter and setter methods in Square class
        //Square id should be ranged from 0 to 19. Unowned or squares' id should be -1.
        assertEquals(0,GO.getId());
        assertEquals(-1,GO.getOwner());
        assertEquals(1,CENTRAL.getId());
        assertEquals(-1,CENTRAL.getOwner());
        CENTRAL.setOwner(2);
        assertEquals(2,CENTRAL.getOwner());
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

        //***not yet tested getEffect()
    }

    @Test
    void testSquareEffect(){
        assertEquals(1500,SquareEffect.GoSalary());

        //Case 1: Test if the random value is generated within -300 to 200 in Monopoly class
        int result = SquareEffect.ChanceSalary();
        assertTrue(-300 <= result && result <= 200);

        //***not yet tested PayTax()
    }

    @Test
    void testIoStorage(){

    }
}
