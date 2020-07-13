package wmaclean.items;

import wmaclean.chunk.Coordinate;
import wmaclean.gui.Textures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Item {

    /**
     * Image to use when rendering items in the world
     */
    protected final BufferedImage gameImage;

    /**
     * Image to use when rendering items in the inventory - probably the same as the gameImage,
     * but can be different
     */
    protected final BufferedImage inventoryImage;

    /**
     * true if the item is in the world, false if in a chest, inventory, etc
     */
    public boolean spawned;

    /**
     * Position of the item if in the world
     */
    protected Coordinate pos;

    protected Item(String gameImagePath, String inventoryImagePath) {
        this.gameImage = Textures.loadImage(gameImagePath);
        this.inventoryImage = Textures.loadImage(inventoryImagePath);
    }

    public void render(Graphics g, int x, int y, int width, int height){
        if(this.spawned){
            g.drawImage(this.gameImage, x, y, width, height, null);
        }else{
            g.drawImage(this.inventoryImage, x, y, width, height, null);
        }
    }
}
