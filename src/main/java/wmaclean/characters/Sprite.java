package wmaclean.characters;

import wmaclean.gui.Textures;
import wmaclean.tile.TileChunk;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Sprite {
    private BufferedImage spriteSheet = null;
    private BufferedImage[][] spriteArray;
    private int w;
    private int h;
    private int wSprite;
    private int hSprite;

    public Sprite(String file, int w, int h, int[] order){
        this(file, w, h);

        assert order.length == h;

        BufferedImage[][] newArray = new BufferedImage[h][w];

        for(int i = 0; i < order.length; i++){
            int index = order[i];
            newArray[index] = this.spriteArray[i];
        }

        this.spriteArray = newArray;
    }

    public Sprite(String file, int w, int h){
        this.w = w;
        this.h = h;

        System.out.println("Loading: " + file + "...");
        spriteSheet = loadSprite(file);

        wSprite = this.spriteSheet.getWidth() / w;
        hSprite = this.spriteSheet.getHeight() / h;

        loadSpriteArray();
    }

    public void setWidth(int width){
        this.w = width;
        this.wSprite = this.spriteSheet.getWidth() / this.w;
    }

    public void setHeight(int h) {
        this.h = h;
        this.hSprite = this.spriteSheet.getHeight() / this.h;
    }

    private BufferedImage loadSprite(String file){
        BufferedImage sprite = null;
        try{
            sprite = Textures.loadImage(file);
        } catch (Exception e){
            System.out.println("ERROR: Could not load file:" + file);
        }

        return sprite;
    }

    private void loadSpriteArray(){
        this.spriteArray = new BufferedImage[h][w];

        for(int x = 0; x < h; x++){
            for(int y = 0; y < w; y++){
                spriteArray[x][y] = getSprite(y, x);
            }
        }
    }

    private BufferedImage getSprite(int x, int y) {
        assert this.spriteSheet != null;
        return this.spriteSheet.getSubimage(x * wSprite, y * hSprite, wSprite, hSprite);
    }

    public BufferedImage[][] getSpriteArray(){
        return this.spriteArray;
    }
}
