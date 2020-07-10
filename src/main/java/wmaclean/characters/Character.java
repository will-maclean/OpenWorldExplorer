package wmaclean.characters;

import wmaclean.Game;

import java.awt.Graphics;

public abstract class Character {

    protected float x, y, width, height, velX, velY;
    protected Game game;
    public final CharID id;

    public Character(final float x, final float y, Game game, CharID charID){
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

    public abstract void render(Graphics g);

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

    public abstract void tick();
}
