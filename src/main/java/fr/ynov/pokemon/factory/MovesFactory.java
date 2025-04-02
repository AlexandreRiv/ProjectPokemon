package fr.ynov.pokemon.factory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.ynov.pokemon.domain.Moves;
import fr.ynov.pokemon.domain.Type;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MovesFactory {

    private final Type typeManager;
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // Constructor

    public MovesFactory() {

        this.typeManager = new Type();

    }


    // Take all moves from the Json and put them in a list .
    private List<Moves> loadMovesFromJson() {

        try {
            // InputStream get the path to the files here moves json
            InputStream inputStream = getClass().getResourceAsStream("/moves.json");
            // Mapper convert the files Json in an object Java
            JsonNode root = MAPPER.readTree(inputStream);
            // Take all the moves from Json
            JsonNode movesArray = root.get("moves");

            List<Moves> movesList = new ArrayList<>();


            // Create a moveNode that will take a list of moves,
            // and this loop will transform the moveArray from a JSON object into moves
            for (JsonNode moveNode : movesArray) {

                // transform each parameter of move

                int power = moveNode.get("power").asInt();
                String name = moveNode.get("name").asText();
                int accuracy = moveNode.get("accuracy").asInt();
                int pp = moveNode.get("pp").asInt();
                int maxPp = moveNode.get("maxPp").asInt();
                Type type = typeManager.getTypes().get(moveNode.get("type").asText().toLowerCase());
                String description = moveNode.get("description").asText();

                movesList.add(new Moves(name, power, accuracy, pp, maxPp, type, description));
               
            }
            return movesList;
        } catch (IOException e) {
            throw new RuntimeException("Error loading movements: " + e.getMessage());
        }
    }


    public List<Moves> getMoves() {
        return loadMovesFromJson();
    }

}
