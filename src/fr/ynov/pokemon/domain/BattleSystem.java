package fr.ynov.pokemon.domain;

import fr.ynov.pokemon.factory.PokemonFactory;
import gui.frame.LifeBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class BattleSystem {

    private LifeBar lifeBar;
    private Random random = new Random();
    private final PokemonFactory pokemonFactory;
    private final Player player;

    // Constructor

    public BattleSystem() {
        this.player = new Player("PlayerTest");
        random = new Random();
        pokemonFactory = new PokemonFactory();

    }


    // Methods

    public Moves selectEnemyMove() {
        List<Moves> moves = pokemonFactory.getOpponentPokemon().getMoves();
        List<Moves> availableMoves = new ArrayList<>();

        for (Moves move : moves) {
            if (move.getPp() > 0) {
                availableMoves.add(move);
            }
        }

        if (availableMoves.isEmpty()) {
            return moves.getFirst(); // Just return the first move for simplicity
        }

        // select a random move from the available moves
        return availableMoves.get(random.nextInt(availableMoves.size()));
    }


    private void executeMove(Pokemon attacker, Pokemon defender, Moves move) {

        move.isUsable();

        // Check if move hits
        if (random.nextInt(100) >= move.getAccuracy()) {
            System.out.println(attacker.getName() + "'s attack missed!");
            return;
        }

        // Calculate damage
        double typeEffectiveness = defender.getTypeEffectiveness(move.getType());

        if (typeEffectiveness == 0) {
            System.out.println("It doesn't affect " + defender.getName() + "...");
            return;
        }

        int damage = calculateDamage(attacker, defender, move, typeEffectiveness);
        defender.setHp(defender.getHp() - damage);
    }


    /**
     * Calculate damage based on the attacker's stats, defender's stats, move power, and type effectiveness.
     *
     * @param attacker The attacking Pokemon
     * @param defender The defending Pokemon
     * @param move     The move being used
     * @param typeEffectiveness The type effectiveness of the move against the defender
     * @return The calculated damage
     */
    private int calculateDamage(Pokemon attacker, Pokemon defender, Moves move, double typeEffectiveness) {

        double baseDamage = ((2 * attacker.getLevel() / 5.0 + 2) * move.getPower() * attacker.getAttack() / defender.getDefense()) / 50.0 + 2;

        double finalDamage = baseDamage * typeEffectiveness;

        return Math.max(1, (int) finalDamage);
    }


    // Execute move in the GUI
    public void executeMoveGUI(Pokemon attacker, Pokemon defender, Moves move) {
        executeMove(attacker, defender, move);
        if (lifeBar != null) {
            lifeBar.updateHealth();
        }
    }

    // Methods end

    // Getter & Setter

    public PokemonFactory getPokemonFactory() {
        return pokemonFactory;
    }

    public void setLifeBar(LifeBar lifeBar) {
        this.lifeBar = lifeBar;
    }

    public Player getPlayer() {
        return player;
    }

}