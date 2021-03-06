package wmaclean;

import org.junit.Test;
import wmaclean.chunk.TileChunk;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void gameStartLogic(){
        // just make sure chunks are smaller than the screen

        assertTrue(Game.WIDTH > TileChunk.CHUNK_SIZE);
        assertTrue(Game.HEIGHT > TileChunk.CHUNK_SIZE);
    }

    @Test
    public void between() {
        assertTrue(Game.between(0, 10, 5));
        assertFalse(Game.between(0, 1, 5));
    }

    @Test
    public void checkVisibleChunks() {
        Game game = new Game();
        assertEquals(1, game.getVisibleChunks().size());

        int expectedChunks = (Game.WIDTH / TileChunk.CHUNK_SIZE) * (Game.HEIGHT / TileChunk.CHUNK_SIZE);
        for(int i = 0; i < Game.WIDTH / TileChunk.CHUNK_SIZE - 1; i++){
            game.checkVisibleChunks();
        }
        assertEquals(game.getVisibleChunks().size(), expectedChunks);

        game.checkVisibleChunks();
        assertEquals(game.getVisibleChunks().size(), expectedChunks);
    }
}