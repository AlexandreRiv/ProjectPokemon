package fr.ynov.pokemon.domain;

import java.util.HashMap;
import java.util.Map;

public class Type {

    // Enum for type names
    private enum TypeName {
        FIRE("Fire"),
        WATER("Water"),
        FLYING("Flying"),
        NORMAL("Normal"),
        GROUND("Ground"),
        ICE("Ice"),
        ELECTRIC("Electric"),
        GRASS("Grass"),
        POISON("Poison"),
        FIGHTING("Fighting"),
        PSYCHIC("Psychic"),
        BUG("Bug"),
        ROCK("Rock"),
        GHOST("Ghost"),
        DRAGON("Dragon");

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
        setTypesEffectivenessFor("Normal", new String[][]{
                {"Rock" , "0.5"},
                {"Ghost" , "0.0"}
        });

        // Fire type effectiveness
        setTypesEffectivenessFor("Fire" , new String[][]{
                {"Fire" , "0.5"},
                {"Water" , "0.5"},
                {"Grass" , "2.0"},
                {"Ice" , "2.0"},
                {"Bug" , "2.0"},
                {"Rock" , "0.5"},
                {"Dragon" , "0.5"}
        });


        // Water type effectiveness
        setTypesEffectivenessFor("Water", new String[][]{
                {"Fire","2.0"},
                {"Water","0.5"},
                {"Grass","0.5"},
                {"Ground","2.0"},
                {"Rock","2.0"},
                {"Dragon","0.5"}
        });

        // Grass type effectiveness
        setTypesEffectivenessFor("Grass", new String[][]{
                {"Fire","0.5"},
                {"Water","2.0"},
                {"Grass","0.5"},
                {"Poison","0.5"},
                {"Ground","2.0"},
                {"Flying","0.5"},
                {"Bug","0.5"},
                {"Rock","2.0"},
                {"Dragon","0.5"}
        });

        // Flying type effectiveness
        setTypesEffectivenessFor("Flying", new String[][]{
                {"Electric","0.5"},
                {"Grass","2.0"},
                {"Fighting","2.0"},
                {"Bug","2.0"},
                {"Rock","0.5"}
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
