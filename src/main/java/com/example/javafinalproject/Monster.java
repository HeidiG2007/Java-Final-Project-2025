package com.example.javafinalproject;

public class Monster {

    //Initialize Monster variables
    private double hitPoints;
    private final double strength;
    private final double dexterity;
    private final double intelligence;

    public Monster() {
        //Make an instance of die to use in Monster
        Die die = new Die();

        //Roll for health, strength, dexterity, and intelligence
        hitPoints = die.rollDie(6, 1);
        strength = 2 * die.rollDie(6,1);
        dexterity = 2 * die.rollDie(6, 1);
        intelligence = 2 * die.rollDie(6, 1);
    }

    //getters

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

}
