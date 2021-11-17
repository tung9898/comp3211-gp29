package Test.Model;

import org.junit.jupiter.api.Test;

import Model.Square;

import static org.junit.jupiter.api.Assertions.*;

class SquareTest {
    Square s2 = new Square(4);
    Square s = new Square(0, 1, "Hong Kong", 2000, 50);
    @Test
    void setId() {
        s.setId(3);
    }

    @Test
    void getId() {
        s.setId(5);
        assertEquals(5, s.getId());
        assertEquals(4, s2.getId());
    }

    @Test
    void setOwner() {
        s.setOwner(2);
    }

    @Test
    void getOwner() {
        s.setOwner(5);
        assertEquals(5, s.getOwner());
    }

    @Test
    void getName() {
        s.setName("New Territories");
        assertEquals("New Territories", s.getName());
    }

    @Test
    void setName() {
        s.setName("Poly");
        assertEquals("Poly", s.getName());
    }

    @Test
    void getPrice() {
        s.setPrice(1800);
        assertEquals(1800, s.getPrice());
    }

    @Test
    void setPrice() {
        s.setPrice(5000);
        assertEquals(5000, s.getPrice());
    }

    @Test
    void getRent() {
        s.setRent(3000);
        assertEquals(3000, s.getRent());
    }

    @Test
    void setRent() {
        s.setRent(2222);
        assertEquals(2222, s.getRent());
    }
}