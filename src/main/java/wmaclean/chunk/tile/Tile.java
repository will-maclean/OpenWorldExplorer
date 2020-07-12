package wmaclean.chunk.tile;

import wmaclean.Game;

import java.awt.Graphics;

public abstract class Tile {

    protected final int tileSize, x, y;
    public final boolean solid;
    protected Game game;

    public Tile(Game game, final int tileSize, final int x, final int y, final boolean solid){
        this.tileSize = tileSize;
        this.x = x;
        this.y = y;
        this.solid = solid;
        this.game = game;
    }

    public abstract void render(Graphics g);
}
