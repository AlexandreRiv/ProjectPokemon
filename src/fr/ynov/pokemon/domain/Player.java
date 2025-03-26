package fr.ynov.pokemon.domain;

import java.util.List;
import java.util.ArrayList;

public class Player {
    private String name;
    private Inventory inventory;
    private List<Pokemon> pokemon;

    public Player(String name) {
        this.name = name;
        this.inventory = new Inventory();
        this.pokemon = new ArrayList<>();
        createInventory();
    }

    public void createInventory() {
        inventory.addItem(new Heal());
    }

    public Inventory getInventory() {
        return inventory;
    }

}
