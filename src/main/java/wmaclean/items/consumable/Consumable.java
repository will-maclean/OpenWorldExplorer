package wmaclean.items.consumable;

import wmaclean.items.Item;

import java.awt.image.BufferedImage;

public abstract class Consumable extends Item {

    protected int healthChangeOnConsumption;

    public Consumable(String gameImagePath, String inventoryImagePath) {
        super(gameImagePath, inventoryImagePath);
    }
}
