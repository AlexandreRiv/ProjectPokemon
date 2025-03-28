package fr.ynov.pokemon.domain;

public abstract class Potions extends Items {


    public Potions(String name) {
        super(name);
    }

    public abstract void use(Pokemon pokemon, Player player);
}
