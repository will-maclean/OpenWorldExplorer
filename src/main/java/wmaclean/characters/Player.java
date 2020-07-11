package wmaclean.characters;

import wmaclean.Game;
import wmaclean.tile.TileChunk;

import java.awt.Graphics;

public class Player extends Character{

    public static final float speed = 2;
    private static final String spritePath = "chars/char2/";
    private final Sprite walkingSprite;
    private SpriteDirection spriteDirection;
    private boolean moving;
    private int spriteIndex;
    private static final int ticksPerSpriteFrame = 8;
    private int ticksSinceFrameChange;

    public Player(int x, int y, Game game) {
        super(x, y, game, CharID.Player);
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

    @Override
    public void render(Graphics g) {

        if(!this.moving){
            g.drawImage(this.walkingSprite.getSpriteArray()[this.spriteDirection.directionIndex()][0],
                    (int)(this.x + this.game.posX),
                    (int)(this.y + this.game.posY),
                    (int)this.width,
                    (int)this.height,
                    null);
        }
        else{
            g.drawImage(this.walkingSprite.getSpriteArray()[this.spriteDirection.directionIndex()][this.spriteIndex],
                    (int)(this.x + this.game.posX),
                    (int)(this.y + this.game.posY),
                    (int)this.width,
                    (int)this.height,
                    null);
        }
    }

    @Override
    public void tick() {
        this.x += this.velX;
        this.y += this.velY;

        // figure out if we're moving and if so in which direction
        if(this.velX > 0){
            this.moving = true;
            if(this.spriteDirection == SpriteDirection.right){
                this.ticksSinceFrameChange++;
                if(this.ticksSinceFrameChange >= ticksPerSpriteFrame){
                    this.ticksSinceFrameChange = 0;
                    this.spriteIndex = (this.spriteIndex + 1)
                            % this.walkingSprite.getSpriteArray()[this.spriteDirection.directionIndex()].length;
                }
            }else{
                this.spriteDirection = SpriteDirection.right;
                this.ticksSinceFrameChange = 0;
            }
        }else if(this.velX < 0){
            this.moving = true;
            if(this.spriteDirection == SpriteDirection.left){
                this.ticksSinceFrameChange++;
                if(this.ticksSinceFrameChange >= ticksPerSpriteFrame){
                    this.ticksSinceFrameChange = 0;
                    this.spriteIndex = (this.spriteIndex + 1)
                            % this.walkingSprite.getSpriteArray()[this.spriteDirection.directionIndex()].length;
                }
            }else{
                this.spriteDirection = SpriteDirection.left;
                this.ticksSinceFrameChange = 0;
            }
        }else if(this.velY < 0){
            this.moving = true;
            if(this.spriteDirection == SpriteDirection.up){
                this.ticksSinceFrameChange++;
                if(this.ticksSinceFrameChange >= ticksPerSpriteFrame){
                    this.ticksSinceFrameChange = 0;
                    this.spriteIndex = (this.spriteIndex + 1)
                            % this.walkingSprite.getSpriteArray()[this.spriteDirection.directionIndex()].length;
                }
            }else{
                this.spriteDirection = SpriteDirection.up;
                this.ticksSinceFrameChange = 0;
            }
        }else if(this.velY > 0){
            this.moving = true;
            if(this.spriteDirection == SpriteDirection.down){
                this.ticksSinceFrameChange++;
                if(this.ticksSinceFrameChange >= ticksPerSpriteFrame){
                    this.ticksSinceFrameChange = 0;
                    this.spriteIndex = (this.spriteIndex + 1)
                            % this.walkingSprite.getSpriteArray()[this.spriteDirection.directionIndex()].length;
                }
            }else{
                this.spriteDirection = SpriteDirection.down;
                this.ticksSinceFrameChange = 0;
            }
        }else{
            this.moving = false;
        }
    }
}
