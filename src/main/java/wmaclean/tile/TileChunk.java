package wmaclean.tile;

import wmaclean.Game;

import java.awt.Graphics;
import java.util.Random;

public class TileChunk {

    private static final int numTilesWidth = 16;
    public static final int tileSize = 24;
    public static final int CHUNKSIZE = numTilesWidth * tileSize;

    public final Tile[][] tiles;
    private final Game game;

    private TileChunk left = null;
    private TileChunk right = null;
    private TileChunk up = null;
    private TileChunk down = null;

    public Coordinate[] corners; // [upper left, upper right, lower left, lower right]

    private boolean visible;

    public final int x, y;

    private final Random r;

    public TileChunk(final int x, final int y, Game game){
        this(x, y, game, true);
    }

    public TileChunk(final int x, final int y, Game game, boolean visible){
        this.game = game;
        this.r = new Random();
        this.x = x;
        this.y = y;
        this.visible = visible;
        this.tiles = createChunk();

        this.corners = new Coordinate[]{
                new Coordinate(x, y),
                new Coordinate(x + CHUNKSIZE, y),
                new Coordinate(x, y + CHUNKSIZE),
                new Coordinate(x + CHUNKSIZE, y + CHUNKSIZE)
        };
    }

    private Tile[][] createChunk() {
        Tile[][] tiles = new Tile[numTilesWidth][numTilesWidth];

        // todo - make this bit smarter, use the other tile types
        for(int i = 0; i < tiles.length; i++){
            for(int j = 0; j < tiles[0].length; j++){
                if(this.r.nextInt(10) > 8){
                    tiles[i][j] = new DirtTile(this.game, tileSize, this.x + j * tileSize, this.y + i * tileSize);
                }else{
                    tiles[i][j] = new GrassTile(this.game, tileSize, this.x + j * tileSize, this.y + i * tileSize);
                }
            }
        }

        return tiles;
    }

    public void render(Graphics g) {

        if(!this.visible){
            return;
        }

        for (int i = 0; i < this.tiles[0].length; i++) {
            for (int j = 0; j < this.tiles[0].length; j++) {
                this.tiles[i][j].render(g);
            }
        }
    }

    public TileChunk getLeft() {
        return left;
    }

    public TileChunk getRight() {
        return right;
    }

    public TileChunk getUp() {
        return up;
    }

    public TileChunk getDown() {
        return down;
    }

    public void setLeft(TileChunk left) {
        this.left = left;
    }

    public void setRight(TileChunk right) {
        this.right = right;
    }

    public void setUp(TileChunk up) {
        this.up = up;
    }

    public void setDown(TileChunk down) {
        this.down = down;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public String toString() {
        return "(" + this.corners[0].x + ", " + this.corners[0].y + ") "
                + "(" + this.corners[1].x + ", " + this.corners[1].y + ") "
                + "(" + this.corners[2].x + ", " + this.corners[2].y + ") "
                + "(" + this.corners[3].x + ", " + this.corners[3].y + ")";
    }
}
