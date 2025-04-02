package fr.ynov.pokemon.domain;

import java.util.HashMap;
import java.util.Map;

public class Type {

    // Enum for type names
    private enum TypeName {
        FIRE("fire"),
        WATER("water"),
        FLYING("flying"),
        NORMAL("normal"),
        GROUND("ground"),
        ICE("ice"),
        ELECTRIC("electric"),
        GRASS("grass"),
        POISON("poison"),
        FIGHTING("fighting"),
        PSYCHIC("psychic"),
        BUG("bug"),
        ROCK("rock"),
        GHOST("ghost"),
        DRAGON("dragon");

        private final String value;

        // Constructor for enum
        TypeName(String value) {
            this.value = value;
        }

        // Getter for enum value
        public String getValue() {
            return value;
        }
    }

    private String name;
    private final Map<String, Double> effectiveness;
    private Map<String, Type> types;

    // Constructor

    public Type(String name) {
        this.name = name;
        this.effectiveness = new HashMap<>();
    }

    public Type() {
        this.effectiveness = new HashMap<>();
        initializedTypes();
    }

    //Constructor end


    // Method

    /**
     * Set the effectiveness of a type against another type
     *
     * @param attackType The attacking type
     * @param defendType The defending type
     * @param value      The effectiveness value
     */
    private void setTypeEffectiveness(String attackType, String defendType, double value) {
        types.get(attackType).addEffectiveness(defendType, value);
    }

    /**
     * Set the effectiveness of a type against multiple types
     *
     * @param type The attacking type
     * @param types The defending types and their effectiveness values
     */
    private void setTypesEffectivenessFor(String type , String[][] types) {
        for (String[] typeEffectiveness : types) {
            setTypeEffectiveness(type, typeEffectiveness[0], Double.parseDouble(typeEffectiveness[1]));
        }
    }


    /**
     * Create types and their effectiveness
     */
    private void createTypes() {
        for (TypeName type : TypeName.values()) {
            types.put(type.getValue(), new Type(type.getValue()));
        }
    }


    /**
     * Initialize types and their effectiveness
     */
    private void initializedTypes() {
        if (types == null){
            types = new HashMap<>();
            createTypes();
            initializeEffectiveness();
        }
    }

    /**
     * Initialize the effectiveness of types
     */
    private void initializeEffectiveness(){
        setTypesEffectivenessFor("normal", new String[][]{
                {"rock" , "0.5"},
                {"ghost" , "0.0"}
        });

        // Fire type effectiveness
        setTypesEffectivenessFor("fire" , new String[][]{
                {"fire" , "0.5"},
                {"water" , "0.5"},
                {"grass" , "2.0"},
                {"ice" , "2.0"},
                {"bug" , "2.0"},
                {"rock" , "0.5"},
                {"dragon" , "0.5"}
        });


        // Water type effectiveness
        setTypesEffectivenessFor("water", new String[][]{
                {"fire","2.0"},
                {"water","0.5"},
                {"grass","0.5"},
                {"ground","2.0"},
                {"rock","2.0"},
                {"dragon","0.5"}
        });

        // Grass type effectiveness
        setTypesEffectivenessFor("grass", new String[][]{
                {"fire","0.5"},
                {"water","2.0"},
                {"grass","0.5"},
                {"poison","0.5"},
                {"ground","2.0"},
                {"flying","0.5"},
                {"bug","0.5"},
                {"rock","2.0"},
                {"dragon","0.5"}
        });

        // Flying type effectiveness
        setTypesEffectivenessFor("flying", new String[][]{
                {"electric","0.5"},
                {"grass","2.0"},
                {"fighting","2.0"},
                {"bug","2.0"},
                {"rock","0.5"}
        });
    }

    /**
     * Add effectiveness for a type
     */
    public void addEffectiveness(String type, double value) {
        this.effectiveness.put(type, value);
    }
    // Method end


    /**
     * Get the effectiveness of a type against another type
     *
     * @param type The type to check effectiveness against
     * @return The effectiveness value
     */
    public double getEffectiveness(String type) {
        return this.effectiveness.getOrDefault(type, 1.0);
    }




    // Getter

    public String getName() {
        return this.name;
    }

    public Map<String, Type> getTypes() {
        if(types == null){
            new Type();
        }
        return types;
    }
}
