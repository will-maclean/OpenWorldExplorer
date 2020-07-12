package wmaclean.chunk.tile;

import org.junit.Test;
import wmaclean.Game;
import wmaclean.chunk.TileChunk;

import static org.junit.Assert.*;

public class TileChunkTest {

    @Test
    public void testToString() {
        Game game = new Game();

        TileChunk tileChunk = new TileChunk(0, 0, game);

        String str = tileChunk.toString();

        assertEquals("(0.0, 0.0) (256.0, 0.0) (0.0, 256.0) (256.0, 256.0)", str);
    }
}