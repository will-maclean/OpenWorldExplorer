package wmaclean.tile;

import org.junit.Test;

import static org.junit.Assert.*;

public class CoordinateTest {

    @Test
    public void testEquals() {
        Coordinate c1 = new Coordinate(10, 20);
        Coordinate c2 = new Coordinate(10, 20);
        Coordinate c3 = new Coordinate(20, 20);

        assertEquals(c1, c2);
        assertNotEquals(c1, c3);
    }
}