package fr.ynov.pokemon.domain;

public class Heal extends Potions {
    private final int heal;


    public Heal() {
        super("Heal");
        this.heal = 20;
    }

    public void use(Pokemon pokemon) {
       if (pokemon.getHp() < pokemon.getMaxHp()){
           pokemon.setHp(pokemon.getHp() + heal);
       }
    }
}
