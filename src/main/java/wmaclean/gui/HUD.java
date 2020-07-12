package wmaclean.gui;

import wmaclean.Game;
import wmaclean.tile.TileChunk;
import wmaclean.time.Clock;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD implements Renderable{

    private static final int DEFAULT_X = Game.WIDTH / 24,
            DEFAULT_Y = Game.HEIGHT / 24,
            DEFAULT_FONTSIZE = TileChunk.tileSize / 2;
    public static final Color DEFAULT_COLOUR = Color.WHITE;

    private final int x, y, fontSize;
    private final Color textColor;

    public HUD(){
        this(DEFAULT_X, DEFAULT_Y, DEFAULT_FONTSIZE, DEFAULT_COLOUR);
    }

    HUD(int x, int y){
        this(x, y, DEFAULT_FONTSIZE, DEFAULT_COLOUR);
    }

    HUD(int x, int y, int fontSize, Color textColor){
        this.x = x;
        this.y = y;
        this.fontSize = fontSize;
        this.textColor = textColor;
    }

    @Override
    public void render(Graphics g) {
        renderTime(g, x, y);
    }

    private void renderTime(Graphics g, int x, int y) {
        String timeString = Clock.getTimeString();
        g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
        g.setColor(this.textColor);
        g.drawString(timeString, x, y);
    }
}
