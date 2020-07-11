package wmaclean.characters;

import wmaclean.gui.Textures;
import wmaclean.tile.Coordinate;
import wmaclean.tile.TileChunk;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

public class Sprite {
    private BufferedImage spriteSheet = null;
    private BufferedImage[][] spriteArray;
    public int w;
    public int h;
    public int wSprite;
    public int hSprite;

    public Sprite(String file){
        w = TileChunk.tileSize;
        h = TileChunk.tileSize;

        System.out.println("Loading: " + file + "...");
        spriteSheet = loadSprite(file );

        wSprite = this.spriteSheet.getWidth() / w;
        hSprite = this.spriteSheet.getHeight() / h;

        loadSpriteArray();
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

    public void setSize(int width, int height){
        this.setWidth(width);
        this.setHeight(height);
    }

    public void setWidth(int width){
        this.w = width;
        this.wSprite = this.spriteSheet.getWidth() / this.w;
    }

    public void setHeight(int h) {
        this.h = h;
        this.hSprite = this.spriteSheet.getHeight() / this.h;
    }

    public int getWidth() {
        return this.w;
    }

    public int getHeight() {
        return h;
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

    public BufferedImage getSpriteSheet() {
        return spriteSheet;
    }

    public BufferedImage[][] getSpriteArray(){
        return this.spriteArray;
    }

    public BufferedImage[] getSpriteArray(int i){
        return this.spriteArray[i];
    }

    public static void drawArray(Graphics2D g,
                                 List<BufferedImage> img,
                                 Coordinate pos,
                                 int width,
                                 int height,
                                 int xOffset,
                                 int yOffset){
        float x = pos.x;
        float y = pos.y;

        for (BufferedImage bufferedImage : img) {
            if (bufferedImage != null) {
                g.drawImage(bufferedImage, (int) x, (int) y, width, height, null);
            }

            x += xOffset;
            y += yOffset;
        }
    }
}
