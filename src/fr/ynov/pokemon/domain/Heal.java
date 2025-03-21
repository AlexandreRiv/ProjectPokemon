package fr.ynov.pokemon.domain;

public class Heal extends Potions {
    private final int heal;


    public Heal(String name, int prices) {
        super(name, prices);
        this.heal = 20;
    }
}
