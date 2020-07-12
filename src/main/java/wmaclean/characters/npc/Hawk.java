package wmaclean.characters.npc;

import wmaclean.Game;
import wmaclean.characters.Sprite;
import wmaclean.characters.SpriteDirection;
import wmaclean.tile.TileChunk;

public class Hawk extends PassiveAnimal{

    public static final float DEFAULT_SPEED = 4;
    private static final String DEFAULT_SPRITE_PATH = "chars/birbs/hawk.png";

    public Hawk(int x, int y, Game game) {
        super(x, y, game, DEFAULT_SPEED);
        loadSprite();
        this.spriteDirection = SpriteDirection.down;
        this.moving = false;

        this.width = TileChunk.tileSize;
        this.height = TileChunk.tileSize;
    }

    @Override
    protected void loadSprite() {
        int hawkSprites = 4;
        int[] order = new int[]{
                2, 1, 3, 0
        };
        this.walkingSprite = new Sprite(DEFAULT_SPRITE_PATH, 3, hawkSprites, order);
    }
}
