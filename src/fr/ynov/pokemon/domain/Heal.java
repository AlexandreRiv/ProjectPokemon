package fr.ynov.pokemon.domain;

public class Heal extends Potions {
    private final int heal;


    public Heal() {
        super("Heal");
        this.heal = 150;
    }


    public void useHeal(Pokemon pokemon) {
       if (pokemon.getHp() < pokemon.getMaxHp()){
           if (pokemon.getHp() + heal >= pokemon.getMaxHp()) {
               pokemon.setHp(pokemon.getMaxHp());
           }else {
                pokemon.setHp(pokemon.getHp() + heal);
           }
       }
    }

    @Override
    public void use(Pokemon pokemon , Player player) {
        Heal heal = (Heal) player.getInventory().getInventory().stream()
                .filter(items -> items instanceof Heal)
                .findFirst().orElse(null);
        if (pokemon.isFainted()) {
            System.out.println("This pokemon is already fainted");
        } else {
            if (heal != null) {
                heal.useHeal(pokemon);
                System.out.println(pokemon.getName() + " has been healed");
                player.getInventory().removeItem(heal);
            } else {
                System.out.println("You don't have any heal potion");
            }
        }
    }
}
