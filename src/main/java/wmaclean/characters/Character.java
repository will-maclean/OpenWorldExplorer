package wmaclean.characters;

import wmaclean.Game;

import java.awt.Graphics;

public abstract class Character {

    protected float x, y, width, height, velX, velY;
    protected Game game;
    public final CharID id;

    protected float speed;
    protected String spritePath;
    protected Sprite walkingSprite;
    protected SpriteDirection spriteDirection;
    protected boolean moving;
    protected int spriteIndex;
    protected static final int ticksPerSpriteFrame = 8;
    protected int ticksSinceFrameChange;

    public Character(final float x, final float y, Game game, CharID charID) {
        this.x = x;
        this.y = y;
        this.game = game;
        this.id = charID;
    }

    public float getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void render(Graphics g) {
        if (!this.moving) {
            g.drawImage(this.walkingSprite.getSpriteArray()[this.spriteDirection.directionIndex()][0],
                    (int) (this.x + this.game.posX),
                    (int) (this.y + this.game.posY),
                    (int) this.width,
                    (int) this.height,
                    null);
        } else {
            g.drawImage(this.walkingSprite.getSpriteArray()[this.spriteDirection.directionIndex()][this.spriteIndex],
                    (int) (this.x + this.game.posX),
                    (int) (this.y + this.game.posY),
                    (int) this.width,
                    (int) this.height,
                    null);
        }
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public void tick() {
        this.x += this.velX;
        this.y += this.velY;

        // figure out if we're moving and if so in which direction
        if (this.velX > 0) {
            this.moving = true;
            if (this.spriteDirection == SpriteDirection.right) {
                this.ticksSinceFrameChange++;
                if (this.ticksSinceFrameChange >= ticksPerSpriteFrame) {
                    this.ticksSinceFrameChange = 0;
                    this.spriteIndex = (this.spriteIndex + 1)
                            % this.walkingSprite.getSpriteArray()[this.spriteDirection.directionIndex()].length;
                }
            } else {
                this.spriteDirection = SpriteDirection.right;
                this.ticksSinceFrameChange = 0;
            }
        } else if (this.velX < 0) {
            this.moving = true;
            if (this.spriteDirection == SpriteDirection.left) {
                this.ticksSinceFrameChange++;
                if (this.ticksSinceFrameChange >= ticksPerSpriteFrame) {
                    this.ticksSinceFrameChange = 0;
                    this.spriteIndex = (this.spriteIndex + 1)
                            % this.walkingSprite.getSpriteArray()[this.spriteDirection.directionIndex()].length;
                }
            } else {
                this.spriteDirection = SpriteDirection.left;
                this.ticksSinceFrameChange = 0;
            }
        } else if (this.velY < 0) {
            this.moving = true;
            if (this.spriteDirection == SpriteDirection.up) {
                this.ticksSinceFrameChange++;
                if (this.ticksSinceFrameChange >= ticksPerSpriteFrame) {
                    this.ticksSinceFrameChange = 0;
                    this.spriteIndex = (this.spriteIndex + 1)
                            % this.walkingSprite.getSpriteArray()[this.spriteDirection.directionIndex()].length;
                }
            } else {
                this.spriteDirection = SpriteDirection.up;
                this.ticksSinceFrameChange = 0;
            }
        } else if (this.velY > 0) {
            this.moving = true;
            if (this.spriteDirection == SpriteDirection.down) {
                this.ticksSinceFrameChange++;
                if (this.ticksSinceFrameChange >= ticksPerSpriteFrame) {
                    this.ticksSinceFrameChange = 0;
                    this.spriteIndex = (this.spriteIndex + 1)
                            % this.walkingSprite.getSpriteArray()[this.spriteDirection.directionIndex()].length;
                }
            } else {
                this.spriteDirection = SpriteDirection.down;
                this.ticksSinceFrameChange = 0;
            }
        } else {
            this.moving = false;
        }
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        if (speed >= 0) {
            this.speed = speed;
            ;
        }
    }
}
