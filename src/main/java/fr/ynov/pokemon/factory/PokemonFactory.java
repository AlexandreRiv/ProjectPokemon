package fr.ynov.pokemon.factory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.ynov.pokemon.domain.Moves;
import fr.ynov.pokemon.domain.Pokemon;
import fr.ynov.pokemon.domain.Type;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;


public class PokemonFactory {

    private final Type types;
    private Pokemon playerPokemon;
    private Pokemon opponentPokemon;
    private final MovesFactory movesFactory;
    private final List<Moves> allMoves;
    private final List<Pokemon> allPokemon;
    private final Random random;
    private static final ObjectMapper MAPPER = new ObjectMapper();


    public PokemonFactory() {
        types = new Type();
        movesFactory = new MovesFactory();
        allMoves = movesFactory.getMoves();
        this.allPokemon = loadPokemonFromJson();
        this.random = new Random();
        setRandomPokemon();
    }


    // Same as MovesFactory
    private List<Pokemon> loadPokemonFromJson() {

        try {

            InputStream inputStream = getClass().getResourceAsStream("/Pokemon.json");
            JsonNode root = MAPPER.readTree(inputStream);
            JsonNode pokemonArray = root.get("pokemon");

            List<Pokemon> pokemonList = new ArrayList<>();

            for (JsonNode pokemonNode : pokemonArray) {

                String name = pokemonNode.get("name").asText();
                int id = pokemonNode.get("id").asInt();
                int hp = pokemonNode.get("hp").asInt();
                int attack = pokemonNode.get("attack").asInt();
                int defense = pokemonNode.get("defense").asInt();
                int speed = pokemonNode.get("speed").asInt();
                int level = pokemonNode.get("level").asInt();

                Type type1 = types.getTypes().get(pokemonNode.get("type").asText().toLowerCase());
                Type type2 = null;
                if (!pokemonNode.get("type2").isNull()) {
                    type2 = types.getTypes().get(pokemonNode.get("type2").asText().toLowerCase());
                }

                Pokemon pokemon = new Pokemon(name, id, hp, hp, attack, defense, speed, level,type1, type2);
                pokemonList.add(pokemon);
            }
            return pokemonList;
        } catch (IOException e) {
            throw new RuntimeException("Pokémon loading error: " + e.getMessage());
        }
    }

    public void setRandomPokemon() {

        playerPokemon = allPokemon.get(random.nextInt(allPokemon.size()));
        opponentPokemon = allPokemon.get(random.nextInt(allPokemon.size()));

        assignMovesToPokemon(playerPokemon);
        assignMovesToPokemon(opponentPokemon);

    }


    /**
     * Randomly assigns up to 4 moves to a Pokemon.
     * The moves must be either of normal type or match one of the Pokemon's types.
     * The method tries to assign moves until the Pokemon has 4 moves or the
     * maximum number of attempts is reached.
     *
     * @param pokemon The Pokemon to assign moves to
     */
    private void assignMovesToPokemon(Pokemon pokemon) {

        int attempts = 0;

        while (pokemon.getMoves().size() < 4 && attempts < 50) {
            //generate a random moves
            Moves move = allMoves.get(random.nextInt(allMoves.size()));

            //verify if the moves Type match with the Pokemon's Type
            boolean isValidMove = Objects.equals(move.getType().getName(), "normal") ||
                    Objects.equals(pokemon.getType().getName(), move.getType().getName()) ||
                    (pokemon.getType2() != null && Objects.equals(pokemon.getType2().getName(), move.getType().getName()));

            // Verify if the moves is not already learned
            if (isValidMove && !pokemon.getMoves().contains(move)) {
                pokemon.addMoves(move);
            }

            attempts++;
        }
    }



    public Pokemon getPlayerPokemon() {
        return playerPokemon;
    }

    public Pokemon getOpponentPokemon() {
        return opponentPokemon;
    }


}
