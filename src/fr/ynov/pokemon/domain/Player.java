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

    public void usePotion(Pokemon pokemon) {
        Heal heal = (Heal) inventory.getInventory().stream()
                .filter(items -> items instanceof Heal)
                .findFirst().orElse(null);
        if (pokemon.isFainted()) {
            System.out.println("This pokemon is already fainted");
        } else {
            if (heal != null) {
                heal.use(pokemon);
                System.out.println(pokemon.getName() + " has been healed");
                inventory.removeItem(heal);
            } else {
                System.out.println("You don't have any heal potion");
            }
        }
    }

    public void createInventory() {
        for( int i = 0; i < 10; i++) {
            inventory.addItem(new Heal());
            System.out.println("Heal potion added to inventory");
        }
    }
}
