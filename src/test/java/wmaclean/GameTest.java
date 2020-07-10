package wmaclean;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void between() {
        assertTrue(Game.between(0, 10, 5));
        assertFalse(Game.between(0, 1, 5));
    }
}