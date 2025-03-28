package fr.ynov.pokemon.domain;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private final int sizeMax;
    private List<Items> inventory;

    // Constructor

    public Inventory() {
        this.sizeMax = 50;
        this.inventory = new ArrayList<>();
    }

    //methods

    /**
     * @return add item to inventory if inventory is not full
     */
    public void addItem(Items item) {
        if (inventory.size() < sizeMax) {
            inventory.add(item);
        }else {
            System.out.println("Inventory is full");
        }
    }

    public void removeItem(Items item) {
        inventory.remove(item);
    }

    /// Getters and Setters

    public List<Items> getInventory() {
        return inventory;
    }


}
