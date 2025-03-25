package fr.ynov.pokemon.domain;

import java.util.HashMap;
import java.util.Map;

public class Type {
    private String name;
    private Map<String, Double> effectiveness;
    private Map<String, Type> types;

    public Type(String name) {
        this.name = name;
        this.effectiveness = new HashMap<>();
    }

    public Type() {
        this.types = new HashMap<>();
        initializedTypes();
    }

    private void setTypeEffectiveness(String attackType, String defendType, double value) {
        types.get(attackType).addEffectiveness(defendType, value);
    }

    private void setTypesEffectivenessFor(String type , String[][] types) {
        for (String[] typeEffectiveness : types) {
            setTypeEffectiveness(type, typeEffectiveness[0], Double.parseDouble(typeEffectiveness[1]));
        }
    }


    private void createTypes() {

        String[] typeNames = {"Fire", "Water", "Flying", "Normal",
                "Ground", "Ice", "Electric", "Grass", "Poison", "Fighting",
                "Psychic", "Bug", "Rock", "Ghost", "Dragon"
        };

        for (String typeName : typeNames) {
            types.put(typeName, new Type(typeName));
        }
    }
    private void initializedTypes() {
        types = new HashMap<>();
        createTypes();
        initializeEffectiveness();

    }

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


    public void addEffectiveness(String type, double value) {
        this.effectiveness.put(type, value);
    }

    public double getEffectiveness(String type) {
        return this.effectiveness.getOrDefault(type, 1.0);
    }

    public String getName() {
        return this.name;
    }

    public Map<String, Type> getTypes() {
        return types;
    }

}
