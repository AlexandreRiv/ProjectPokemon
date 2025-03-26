package fr.ynov.pokemon.domain;

public class Heal extends Potions {
    private final int heal;


    public Heal() {
        super("Heal");
        this.heal = 100;
    }

    public void use(Pokemon pokemon) {
       if (pokemon.getHp() < pokemon.getMaxHp()){
           if (pokemon.getHp() + heal >= pokemon.getMaxHp()) {
               pokemon.setHp(pokemon.getMaxHp());
           }else {
                pokemon.setHp(pokemon.getHp() + heal);
           }
       }
    }
}
