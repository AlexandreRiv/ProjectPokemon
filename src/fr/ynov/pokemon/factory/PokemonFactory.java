package fr.ynov.pokemon.factory;

import fr.ynov.pokemon.domain.Moves;
import fr.ynov.pokemon.domain.Pokemon;
import fr.ynov.pokemon.domain.Type;

public class PokemonFactory {

    private final Type types;
    private Pokemon playerPokemon;
    private Pokemon opponentPokemon;

    public PokemonFactory() {
        types = new Type();
        createPokemon();
    }

    /**
     * Creates the player's and opponent's Pokémon with their respective moves.
     */
    private void createPokemon() {

        // Create player's Pokémon - Charizard
        Type fireType = types.getTypes().get("Fire");
        Type flyingType = types.getTypes().get("Flying");
         playerPokemon = new Pokemon("Charizard", 3, 150, 150, 84, 78, 50, fireType, flyingType);

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
         opponentPokemon = new Pokemon("Balroise", 6, 150, 150, 83, 100, 85, waterType, null);

        // Add moves to Blastoise
        Moves hydroPump = new Moves("Hydro Pump", 80, 120, 5, 5, waterType , "");
        Moves surf = new Moves("Surf", 80, 95, 15, 15, waterType , "");
        Moves bite = new Moves("Bite", 60, 100, 25, 25, types.getTypes().get("Normal") , "");
        Moves iceBeam = new Moves("Ice Beam", 95, 100, 10, 10  , types.getTypes().get("Ice") , "");

        opponentPokemon.addMoves(hydroPump);
        opponentPokemon.addMoves(surf);
        opponentPokemon.addMoves(bite);
        opponentPokemon.addMoves(iceBeam);

    }

    public Pokemon getPlayerPokemon() {
        return playerPokemon;
    }

    public Pokemon getOpponentPokemon() {
        return opponentPokemon;
    }


}
