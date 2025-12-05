package com.example.javafinalproject;

public class Room {

    //Declare variables
    private final int[] location = new int[2];
    private final boolean blocked;
    private boolean monster;
    private int gold;
    private boolean visited;

    //Constructor takes x and y location of the room
    Room(int xLocation, int yLocation) {
        // Initialize coordinates to their respective values
        location[0] = xLocation;
        location[1] = yLocation;
        //Have a random chance of the room being blocked or containing a monster
        blocked = Math.random() < .1;
        monster = Math.random() < .5;
        //Set gold randomly from 0 to 5 pieces
        gold = (int)(Math.round(Math.random() * 5));
        visited = false;
    }


    //Getters

    public int[] getLocation() {
        return location;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public boolean hasMonster() {
        return monster;
    }

    public int getGold() {
        return gold;
    }

    public boolean isVisited() {
        return visited;
    }

    //Setters

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    // generate the setters based off of the UML

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setMonster(boolean monster) {
        this.monster = monster;
    }
}
