package fr.ynov.pokemon.domain;

public class Moves {
    private String name;
    private int power;
    private int accuracy;
    private int pp;
    private int maxPp;
    private Type type;
    private String description;

    public Moves(String name, int power, int accuracy, int pp, int maxPp,Type type2 , String description) {
        this.name = name;
        this.power = power;
        this.accuracy = accuracy;
        this.pp = pp;
        this.maxPp = maxPp;
        this.type = type2;
        this.description = description;
    }

    public boolean isUsable() {
        if (pp > 0) {
            pp--;
            return true;
        }
        return false;
    }


    public String getName() {
        return name;
    }

    public int getPp() {
        return pp;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public Type getType() {
        return type;
    }

    public int getPower() {
        return power;
    }
}
