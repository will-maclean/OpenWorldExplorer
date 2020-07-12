package wmaclean.characters.npc;

import wmaclean.Game;

import java.util.Random;

public abstract class PassiveAnimal extends NPC {

    public static final int MAX_DIST_OFF_SCREEN = Math.max(Game.WIDTH * 4, Game.HEIGHT * 4);

    public PassiveAnimal(float x, float y, Game game, float speed) {
        super(x, y, game);

        this.velX = 0;
        this.velY = 0;
        this.speed = speed;

        this.spriteIndex = 0;
        this.ticksSinceFrameChange = 0;
    }

    @Override
    public void tick() {

        super.tick();

        if(Math.random() > 0.98){
            newRandomDirection();
        }
    }

    private void newRandomDirection() {
        Random r = new Random();

        int i = r.nextInt(4);
        switch (i){
            case 1:
                this.velX = this.speed;
                this.velY = 0;
                this.moving = true;
                break;
            case 2:
                this.velX = -1 * this.speed;
                this.velY = 0;
                this.moving = true;
                break;
            case 3:
                this.velY = -1 * this.speed;
                this.velX = 0;
                this.moving = true;
                break;
            case 4:
                this.velY =  this.speed;
                this.velX = 0;
                this.moving = true;
                break;
            default:
                this.velX = 0;
                this.velY = 0;
                this.moving = false;
                break;
        }
    }
}
