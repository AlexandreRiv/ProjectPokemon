package fr.ynov.pokemon.domain;

import javax.imageio.ImageReader;

public abstract class Balls extends Items {
    private int prices;
    private int catchRate;

    public Balls(String name , int prices , int catchRate) {
        super(name);
        this.prices = prices;
        this.catchRate = catchRate;
    }
}
