package fr.ynov.pokemon.domain;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Player {
    private String name;
    private Inventory inventory;
    private List<Pokemon> pokemon;

    // Constructor

    public Player(String name) {
        this.name = name;
        this.inventory = new Inventory();
        this.pokemon = new ArrayList<>();
        createInventory();
    }

    // Methods

    public void createInventory() {
        Random rand = new Random();
        int numberOfPotions = rand.nextInt(10) + 1;
        for (int i = 0; i < numberOfPotions; i++) {
            inventory.addItem(new Heal());
        }

    }

    /// Getters and Setters

    public Inventory getInventory() {
        return inventory;
    }

}
