package wmaclean.items;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

public class SpawnedItemHandler {

    private List<Item> items;

    public SpawnedItemHandler(){
        this.items = new LinkedList<>();
    }

    public void render(Graphics g){
        for(Item item : this.items){
            item.render(g);
        }
    }

    public void add(Item item){
        this.items.add(item);
    }

    public void remove(Item item){
        this.items.remove(item);
    }
}
