package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IoStorageTest {
    IoStorage is = new IoStorage();
    IoStorage is2 = new IoStorage("location");
    @Test
    void setFileLocation() {
        is.setFileLocation("Desktop");
        assertEquals("Desktop", is.getFileLocation());
    }

    @Test
    void getFileLocation() {
        assertEquals("location", is2.getFileLocation());
    }
}