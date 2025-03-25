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

    public boolean isFainted() {
        return hp <= 0;
    }

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

    public String getName() {
        return name ;
    }

    public int getSpeed() {
        return speed;
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

    public Type getType1() {
        return type;
    }

    public Type getType2() {
        if (type2 == null) {
            return null;
        }
        return type2;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getLevel() {
        return level;
    }
}
