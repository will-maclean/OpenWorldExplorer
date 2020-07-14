package wmaclean.items;

import wmaclean.Game;
import wmaclean.chunk.Coordinate;
import wmaclean.gui.Renderable;
import wmaclean.gui.Textures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Item implements Renderable, Comparable<Item> {

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
    protected boolean spawned;

    /**
     * Position of the item if in the world
     */
    protected Coordinate pos;

    /**
     * keep track of an items width and height when spawned in the world
     */
    protected final int width, height;

    protected final Game game;

    protected Item(String gameImagePath, String inventoryImagePath, int width, int height, Game game) {
        this.gameImage = Textures.loadImage(gameImagePath);
        this.inventoryImage = Textures.loadImage(inventoryImagePath);
        this.width = width;
        this.height = height;
        this.game = game;
    }

    public void render(Graphics g, int x, int y, int width, int height){
        g.drawImage(this.inventoryImage, x, y, width, height, null);
    }

    public void render(Graphics g){
        if(this.spawned){
            g.drawImage(this.gameImage,
                    (int)(this.pos.x.intValue() +  + this.game.posX),
                    (int)(this.pos.y.intValue() +  + this.game.posY),
                    this.width,
                    this.height,
                    null);
        }
    }

    public boolean isSpawned() {
        return spawned;
    }

    public void setSpawned(boolean spawned) {
        this.spawned = spawned;
    }

    @Override
    public int compareTo(Item other) {
        return this.pos.y.compareTo(other.pos.y);
    }

    public void spawn(int x, int y){
        this.pos = new Coordinate(x, y);
        this.spawned = true;
    }
}
