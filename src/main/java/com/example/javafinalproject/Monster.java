package com.example.javafinalproject;

public class Monster {

    // assign Monster variables
    private double hitPoints;
    private final double strength;
    private final double dexterity;
    private final double intelligence;

    // Constructor
    public Monster() {
        // make a new die for use in Monster
        Die die = new Die();

        // Roll for health, strength, dexterity, and intelligence
        hitPoints = die.rollDie(6, 1);
        strength = 2 * die.rollDie(6, 1);
        dexterity = 2 * die.rollDie(6, 1);
        intelligence = 2 * die.rollDie(6, 1);
    }

    // Getters
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

    // method to allow user to subtract hit points
    public void subtractHitPoints(double amount) {
        hitPoints -= amount;
        if (hitPoints < 0) {
            hitPoints = 0;
        }
    }
}