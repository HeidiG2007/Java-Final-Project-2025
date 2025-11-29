package com.example.javafinalproject;

public class Die {
    public Die() {
    }

    public int rollDie(int sides, int numTimes) {
        //Set total to 0
        int total = 0;

        //For every time the die should be rolled
        for (int i = 0; i < numTimes; i++) {
            //Generate a random number from 1 to the number of sides on the die, and add it to total
            total += (int) ((Math.random() * sides)+1);
        }
        //return the total
        return total;
    }
}
