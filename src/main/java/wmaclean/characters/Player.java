package wmaclean.characters;

import wmaclean.Game;
import wmaclean.gui.Textures;
import wmaclean.tile.TileChunk;

import java.awt.Graphics;

public class Player extends Character{

    public static final float speed = 2;

    public Player(int x, int y, Game game) {
        super(x, y, game, CharID.Player);
        this.width = (int) (TileChunk.tileSize * 1.2);
        this.height = (int) (TileChunk.tileSize * 1.8);
        this.velX = 0;
        this.velY = 0;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Textures.playerSprite,
                (int)(this.x + this.game.posX),
                (int)(this.y + this.game.posY),
                (int)this.width,
                (int)this.height,
                null);
    }

    @Override
    public void tick() {
        this.x += this.velX;
        this.y += this.velY;
    }
}
