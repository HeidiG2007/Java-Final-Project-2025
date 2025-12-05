package com.example.javafinalproject;

public class Player {

    // Variables
    private double hitPoints;
    private final double strength;
    private final double dexterity;
    private final double intelligence;
    private int totalGold;

    // Constructor
    public Player() {
        // hitPoints always starts at 20
        hitPoints = 20;
        // create a new die to use for player
        Die die = new Die();

        // Roll die for strength, dexterity, and intelligence using rollDie method
        strength = die.rollDie(6, 3);
        dexterity = die.rollDie(6, 3);
        intelligence = die.rollDie(6, 3);

        // gold starts at 0
        totalGold = 0;
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

    public int getTotalGold() {
        return totalGold;
    }

    // methods that allow you to subtract the hit points and add gold
    public void subtractHitPoints(double amount) {
        hitPoints -= amount;
        if (hitPoints < 0) {
            hitPoints = 0;
        }
    }

    public void addGold(int goldToAdd) {
        if (goldToAdd > 0) {
            totalGold += goldToAdd;
        }
    }

    // allows you to reset the hit points and roll the die again

    public void resetHitPoints() {
        hitPoints = 20; // Since initial HP is always 20
    }

    public int rollDie(int sides, int numTimes) {
        int total = 0;
        for (int i = 0; i < numTimes; i++) {
            total += (int)(Math.random() * sides) + 1;
        }
        return total;
    }
}
