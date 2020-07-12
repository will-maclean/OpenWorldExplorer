package wmaclean.characters;

import wmaclean.Game;
import wmaclean.tile.TileChunk;

import java.awt.Graphics;

public class Player extends Character{

    public static final float DEFAULT_SPEED = 2;
    private static final String DEFAULT_SPRITE_PATH = "chars/char2/";

    public Player(int x, int y, Game game) {
        super(x, y, game, CharID.Player);

        this.speed = DEFAULT_SPEED;
        this.spritePath = DEFAULT_SPRITE_PATH;

        this.width = (int) (TileChunk.tileSize * 1.6);
        this.height = (int) (TileChunk.tileSize * 1.8);
        this.velX = 0;
        this.velY = 0;

        this.walkingSprite = new Sprite(spritePath + "walking.png", 9, 4);
        this.spriteDirection = SpriteDirection.down;
        this.moving = false;
        this.spriteIndex = 0;
        this.ticksSinceFrameChange = 0;
    }
}
