package fr.ynov.pokemon.domain;

public abstract class Potions extends Items {
    private int prices;

    public Potions(String name, int prices) {
        super(name);
        this.prices = prices;
    }

}
