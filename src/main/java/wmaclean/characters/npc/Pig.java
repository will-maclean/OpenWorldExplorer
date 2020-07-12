package wmaclean.characters.npc;

import wmaclean.Game;
import wmaclean.characters.Sprite;
import wmaclean.characters.SpriteDirection;
import wmaclean.tile.TileChunk;

public class Pig extends PassiveAnimal{

    public static final float DEFAULT_SPEED = 2;
    private static final String DEFAULT_SPRITE_PATH = "chars/pig/pig1sprite.png";

    public Pig(int x, int y, Game game) {
        super(x, y, game, DEFAULT_SPEED);

        this.walkingSprite = new Sprite(DEFAULT_SPRITE_PATH, 4, 4);
        this.spriteDirection = SpriteDirection.down;
        this.moving = false;

        this.width = TileChunk.tileSize;
        this.height = TileChunk.tileSize;
    }
}
