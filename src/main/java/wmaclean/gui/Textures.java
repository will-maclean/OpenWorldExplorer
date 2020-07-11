package wmaclean.gui;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Textures {

    public static final String resourcesPath = "src/main/resources/";

    public static final BufferedImage grassTexture = loadImage("grassTexture2.png");
    public static final BufferedImage dirtTexture = loadImage("dirtTexture.png");
    public static final BufferedImage playerSprite = loadImage("playerSprite.png");
    public static final BufferedImage treeSprite = loadImage("treeSprite.png");

    private static BufferedImage loadImage(String name) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(resourcesPath.concat(name)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}
