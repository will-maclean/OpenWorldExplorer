package wmaclean.items.consumable;

import wmaclean.Game;
import wmaclean.chunk.TileChunk;
import wmaclean.gui.Textures;

import java.awt.Graphics;

public class Apple extends Consumable{

    private static final String imagePath = "appleSprite.png";
    private static final int DEFAULT_WIDTH = TileChunk.tileSize / 2;
    private static final int DEFAULT_HEIGHT = TileChunk.tileSize / 2;

    public Apple(Game game) {
        super(imagePath, imagePath, DEFAULT_WIDTH, DEFAULT_HEIGHT, game);
    }

    public void render(Graphics g, int width, int height){
        if(this.spawned){
            this.render(g, this.pos.x.intValue(), this.pos.y.intValue(), width, height);
        }
    }
}
