package fr.ynov.pokemon.domain;


public abstract class Balls extends Items {
    private int prices;
    private int catchRate;

    // Constructor

    public Balls(String name , int prices , int catchRate) {
        super(name);
        this.prices = prices;
        this.catchRate = catchRate;
    }
}
