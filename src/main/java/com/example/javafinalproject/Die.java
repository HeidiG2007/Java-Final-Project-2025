package com.example.javafinalproject;

public class Die {

    //  constructor
    public Die() {
        // sides uninitialized here; they will be set in rollDie()
    }

    // method
    public int rollDie(int sides, int numTimes) {

        // set the total to 0

        int total = 0;

        // for every time the die should be rolled,
        for (int i = 0; i < numTimes; i++) {
            // generate a random number from 1 to the number of sides on the die, then add to total
            total += (int)((Math.random() * sides) + 1);
        }
        // return the total
        return total;
    }
}