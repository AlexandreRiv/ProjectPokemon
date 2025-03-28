package fr.ynov.pokemon.domain;

import java.util.ArrayList;
import java.util.List;

public class Pokemon {

    private String name;
    private int id;
    private int hp;
    private int maxHp;
    private int attack;
    private int defense;
    private int speed;
    private int level;
    private Type type;
    private Type type2;
    private List<Moves> moves;

    // Constructor

    public Pokemon(String name, int i, int i1,int i2 , int i3, int i4, int i5, Type type3, Type type4) {

        this.name = name;
        this.id = i;
        this.hp = i1;
        this.maxHp = i2;
        this.attack = i3;
        this.defense = i4;
        this.speed = i5;
        this.level = 50;
        this.type = type3;
        this.type2 = type4;
        this.moves = new ArrayList<Moves>();

    }

    // Constructor end

    // Methods

    public boolean isFainted() {
        return hp <= 0;
    }


    /**
     * * @param move
     *
     * @return double of the effectiveness of the move depending on the type of the Pokemon
     */
    public double getTypeEffectiveness(Type moveType) {

        double effectiveness = moveType.getEffectiveness(type.getName());

        if (type2 != null) {
            effectiveness *= moveType.getEffectiveness(type2.getName());
        }

        return effectiveness;

    }

    public void addMoves(Moves move) {
            moves.add(move);
    }

    // Methods end


    // Getter & Setter

    public String getName() {
        return name ;
    }

    public List<Moves> getMoves() {
        return moves;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int i) {
        this.hp = i;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getLevel() {
        return level;
    }
}
