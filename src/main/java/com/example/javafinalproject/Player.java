package com.example.javafinalproject;

public class Player {

    //Declare variables
    private double hitPoints;
    private final double strength;
    private final double dexterity;
    private final double intelligence;
    private int gold;

    //Constructor
    public Player() {
        //hitPoints always starts at 20
        hitPoints = 20;
        //Make a new die for use in Player
        Die die = new Die();
        //Roll for strength, dexterity, intelligence
        strength = die.rollDie(6, 3);
        dexterity = die.rollDie(6, 3);
        intelligence = die.rollDie(6, 3);
        //gold starts at 0
        gold = 0;
    }

    //Getters

    public double getHitPoints() {
        return hitPoints;
    }

    public double getStrength() {
        return strength;
    }

    public double getDexterity() {
        return dexterity;
    }

    public double getIntelligence() {
        return intelligence;
    }

    public int getTotalGold() {
        return gold;
    }

}
