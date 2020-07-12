package wmaclean.chunk.tile;

import wmaclean.Game;
import wmaclean.gui.Textures;

import java.awt.Graphics;

public class DirtTile extends Tile {
    public DirtTile(Game game, int tileSize, int x, int y) {
        super(game, tileSize, x, y, false);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Textures.dirtTexture,
                (int)(this.x + this.game.posX),
                (int)(this.y + this.game.posY),
                this.tileSize,
                this.tileSize,
                null);
    }
}
