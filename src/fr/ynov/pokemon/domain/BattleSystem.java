package fr.ynov.pokemon.domain;

import java.util.*;

public class BattleSystem {
    private Pokemon playerPokemon;
    private Pokemon opponentPokemon;
    private Random random = new Random();
    private Type types;


    public BattleSystem() {
        random = new Random();
        this.types = new Type();
        createPokemon();
    }


    private void createPokemon() {
        // Create player's Pokémon - Charizard
        Type fireType = types.getTypes().get("Fire");
        Type flyingType = types.getTypes().get("Flying");
        playerPokemon = new Pokemon("Charizard",3, 150 , 150 ,84 , 78 , 50 ,  fireType, flyingType);

        // Add moves to Charizard
        Moves flamethrower = new Moves("Flamethrower", 95, 100, 15, 15 , fireType , "");
        Moves fireBlast = new Moves("Fire Blast", 120, 85, 5, 5, fireType , "");
        Moves slash = new Moves("Slash", 70, 100, 20, 20 , types.getTypes().get("Normal") , "");
        Moves earthquake = new Moves("Earthquake", 100, 100, 10, 10 , types.getTypes().get("Ground") , "");

        playerPokemon.addMoves(flamethrower);
        playerPokemon.addMoves(fireBlast);
        playerPokemon.addMoves(slash);
        playerPokemon.addMoves(earthquake);

        // Create enemy Pokémon - Blastoise
        Type waterType = types.getTypes().get("Water");
        opponentPokemon = new Pokemon("Balroise", 6, 100, 150 ,83, 100, 85, waterType, null);

        // Add moves to Blastoise
        Moves hydroPump = new Moves("Hydro Pump", 80, 120, 5, 5, waterType , "");
        Moves surf = new Moves("Surf", 80, 95, 100, 15, waterType , "");
        Moves bite = new Moves("Bite", 60, 100, 25, 25, types.getTypes().get("Normal") , "");
        Moves iceBeam = new Moves("Ice Beam", 95, 100, 10, 10  , types.getTypes().get("Ice") , "");

        opponentPokemon.addMoves(hydroPump);
        opponentPokemon.addMoves(surf);
        opponentPokemon.addMoves(bite);
        opponentPokemon.addMoves(iceBeam);
    }


    public Moves selectEnemyMove() {
        List<Moves> moves = opponentPokemon.getMoves();
        List<Moves> availableMoves = new ArrayList<>();

        for (Moves move : moves) {
            if (move.getPp() > 0) {
                availableMoves.add(move);
            }
        }

        if (availableMoves.isEmpty()) {
            return moves.getFirst(); // Just return the first move for simplicity
        }

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

        if (typeEffectiveness > 1) {
            System.out.println("It's super effective!");
        } else if (typeEffectiveness < 1) {
            System.out.println("It's not very effective...");
        }
    }

    private int calculateDamage(Pokemon attacker, Pokemon defender, Moves move, double typeEffectiveness) {

        double baseDamage = ((2 * attacker.getLevel() / 5.0 + 2) * move.getPower() * attacker.getAttack() / defender.getDefense()) / 50.0 + 2;

        double finalDamage = baseDamage * typeEffectiveness;

        return Math.max(1, (int) finalDamage);
    }

    public Pokemon getPlayerPokemon() {
        return playerPokemon;
    }

    public Pokemon getOpponentPokemon() {
        return opponentPokemon;
    }

    public void executeMoveGUI(Pokemon attacker, Pokemon defender, Moves move) {
        executeMove(attacker, defender, move);
    }
}