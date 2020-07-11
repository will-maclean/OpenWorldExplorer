package wmaclean.tile;

import wmaclean.Game;
import wmaclean.gui.Textures;

import java.awt.Graphics;

public class TreeTile extends Tile{

    Tile backGroundTile;

    public TreeTile(Game game, int tileSize, int x, int y, Tile backGroundTile) {
        super(game, tileSize, x, y, true);
        this.backGroundTile = backGroundTile;
    }

    public TreeTile(Game game, int tileSize, int x, int y){
        super(game, tileSize, x, y, true);

        if(Math.random() > 0.5){
            this.backGroundTile = new GrassTile(this.game, this.tileSize, this.x, this.y);
        }else{
            this.backGroundTile = new DirtTile(this.game, this.tileSize, this.x, this.y);
        }
    }

    @Override
    public void render(Graphics g) {

        this.backGroundTile.render(g);

        g.drawImage(Textures.treeSprite,
                (int)(this.x + this.game.posX),
                (int)(this.y + this.game.posY - this.tileSize),
                this.tileSize,
                2 * this.tileSize,
                null);
    }
}
