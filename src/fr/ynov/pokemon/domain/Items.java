package fr.ynov.pokemon.domain;

public abstract class Items {

    private String name;

    public Items(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void use(Pokemon pokemon , Player player);
}
