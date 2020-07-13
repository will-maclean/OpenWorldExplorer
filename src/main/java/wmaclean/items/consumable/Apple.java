package wmaclean.items.consumable;

import wmaclean.gui.Textures;

import java.awt.Graphics;

public class Apple extends Consumable{

    private static final String imagePath = "appleSprite.png";

    public Apple() {
        super(imagePath, imagePath);
    }

    public void render(Graphics g, int width, int height){
        if(this.spawned){
            this.render(g, (int)this.pos.x, (int)this.pos.y, width, height);
        }
    }
}
