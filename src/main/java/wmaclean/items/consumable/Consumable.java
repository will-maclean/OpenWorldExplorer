package wmaclean.items.consumable;

import wmaclean.Game;
import wmaclean.items.Item;

public abstract class Consumable extends Item {

    protected int healthChangeOnConsumption;

    public Consumable(String gameImagePath, String inventoryImagePath, int width, int height, Game game) {
        super(gameImagePath, inventoryImagePath, width, height, game);
    }
}
