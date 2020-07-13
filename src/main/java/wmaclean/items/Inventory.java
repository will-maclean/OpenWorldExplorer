package wmaclean.items;

import java.util.LinkedList;
import java.util.List;

public class Inventory {

    private static final int DEFAULT_MAX_SIZE = 32;

    private final List<Item> items;
    private final int maxSize;


    public Inventory() {
        this(DEFAULT_MAX_SIZE);
    }

    public Inventory(int maxSize) {
        this.maxSize = Math.max(maxSize, 1);
        this.items = new LinkedList<>();
    }

    public int size(){
        return this.items.size();
    }

    public Item get(int i){
        return this.items.get(i);
    }

    public void add(Item item) throws InventoryFullException {
        if(this.isFull()){
            throw new InventoryFullException();
        }else{
            this.items.add(item);
        }
    }

    public boolean isFull(){
        return this.items.size() >= this.maxSize;
    }
}
