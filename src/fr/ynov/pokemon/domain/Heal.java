package fr.ynov.pokemon.domain;

public class Heal extends Potions {
    private final int heal;

    //  Constructor

    public Heal() {
        super("Heal");
        this.heal = 50;
    }


    /// heal the Pokemon and remove the heal from the inventory
    public void useHeal(Pokemon pokemon , Player player) {
        Heal heals = (Heal) player.getInventory().getInventory().stream()
                .filter(items -> items instanceof Heal)
                .findFirst().orElse(null);
       if (pokemon.getHp() < pokemon.getMaxHp()){
           if (pokemon.getHp() + heal >= pokemon.getMaxHp()) {
               pokemon.setHp(pokemon.getMaxHp());
               player.getInventory().removeItem(heals);
           }else {
                pokemon.setHp(pokemon.getHp() + heal);
               player.getInventory().removeItem(heals);
           }
       }
    }


    /// Override the use method from Items
    @Override
    public void use(Pokemon pokemon , Player player) {
        Heal heal = (Heal) player.getInventory().getInventory().stream()
                .filter(items -> items instanceof Heal)
                .findFirst().orElse(null);
        if (pokemon.isFainted()) {
            System.out.println("This pokemon is already fainted");
        } else {
            if (heal != null) {
                heal.useHeal(pokemon, player);
            }
        }
    }
}
