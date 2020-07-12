package wmaclean.gui;

import wmaclean.time.Clock;
import wmaclean.time.TimeOfDay;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Textures {

    public static final String resourcesPath = "src/main/resources/";

    public static  BufferedImage grassTexture = loadImage("grassTexture2.png");
    public static  BufferedImage dirtTexture = loadImage("dirtTexture.png");
    public static  BufferedImage treeSprite = loadImage("treeSprite.png");

    public static BufferedImage loadImage(String name) {
        BufferedImage img = null;
        TimeOfDay timeOfDay = Clock.getTimeOfDay();
        try {
            img = ImageIO.read(new File(resourcesPath.concat(name)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return timeOfDay.tintImage(img);
    }

    public static void timeChanged(){
        Textures.grassTexture= loadImage("grassTexture2.png");
        Textures.dirtTexture = loadImage("dirtTexture.png");
        Textures.treeSprite = loadImage("treeSprite.png");
    }
}
