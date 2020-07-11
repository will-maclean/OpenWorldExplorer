package wmaclean.characters;

import org.junit.Test;

import static org.junit.Assert.*;

public class SpriteTest {

    private static final String spritePath = "chars/char1/walking.png";
    private static final int numWidth = 9;
    private static final int numHeight = 4;


    @Test
    public void loadSpriteArray() {
        Sprite sprite = new Sprite(spritePath, numWidth, numHeight);
    }
}