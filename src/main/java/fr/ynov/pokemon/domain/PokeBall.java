package fr.ynov.pokemon.domain;

public class PokeBall extends Balls{

    public PokeBall(String name, int prices , int catchRate) {
        super(name , prices , catchRate);
    }

    @Override
    public void use(Pokemon pokemon , Player player) {
    }
}

