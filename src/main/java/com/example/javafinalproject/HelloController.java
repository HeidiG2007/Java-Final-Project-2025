package com.example.javafinalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class HelloController {

    //Set up variables
    Room[][] roomsArray = new Room[10][10];
    Room currentRoom;
    boolean playerIsRunning = false;
    Player gamePlayer = new Player();
    Monster currentMonster = new Monster();
    int playerXLocation;
    int playerYLocation;
    Die die = new Die();

    //Set up buttons
    @FXML
    private TextArea textArea;
    @FXML
    private Button leftButton;
    @FXML
    private Button upButton;
    @FXML
    private Button downButton;
    @FXML
    private Button rightButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button attackButton;
    @FXML
    private Button sleepButton;
    @FXML
    private Button runButton;
    @FXML
    private Label playerStatLabel;
    @FXML
    private Label monsterStatLabel;
    @FXML
    private TextArea mapText;

    //When we initialize, we want all our buttons to be clickable
    //We also don't want the text area to be editable
    public void initialize() {
        upButton.setDisable(false);
        downButton.setDisable(false);
        leftButton.setDisable(false);
        rightButton.setDisable(false);
        attackButton.setDisable(false);
        runButton.setDisable(false);
        searchButton.setDisable(false);
        sleepButton.setDisable(false);

        textArea.setEditable(false);
        mapText.setEditable(false);

        //We put a room in every spot in the roomsArray
        for (int i = 0; i < roomsArray.length; i++) {
            for (int j = 0; j < roomsArray[i].length; j++) {
                roomsArray[i][j] = new Room(i, j);
            }
        }

        //We set our current room to be the upper left one
        currentRoom = roomsArray[0][0];
        //We update the player and monster information provided to the user
        updatePlayerStatLabels();
    }

    @FXML
    public void buttonClicked(ActionEvent actionEvent) {

        //If they click the up button
        if (actionEvent.getSource() == upButton) {

            //Get the current coordinates
            playerXLocation = currentRoom.getLocation()[0];
            playerYLocation = currentRoom.getLocation()[1];

            //Check to see if there's a monster in the current room.
            if (currentRoom.hasMonster() && !playerIsRunning) {
                //If they aren't running, tell the user that they can't leave until they fight or run from the monster
                textArea.appendText("You can either run or attack the monster.\n");

                //Otherwise, if the room area isn't available, tell them the room's blocked
            } else if (playerYLocation == 0 || roomsArray[playerXLocation][playerYLocation - 1].isBlocked()) {
                textArea.appendText("The area ahead is inaccessible.\n");

                //Otherwise move them to the room above and tell them they've moved up.
            } else {
                currentRoom = roomsArray[playerXLocation][playerYLocation - 1];
                textArea.appendText("You moved up.\n");
                //Remove the running status from the player if they were running
                playerIsRunning = false;
            }
            //Update the information about the player and whatever monster's in the room now
            updatePlayerStatLabels();

            //Otherwise, if they click the down button
        } else if (actionEvent.getSource() == downButton) {

            //Get the player's current location
            playerXLocation = currentRoom.getLocation()[0];
            playerYLocation = currentRoom.getLocation()[1];

            //Check to see if there's a monster in the current room
            if (currentRoom.hasMonster() && !playerIsRunning) {
                //If they aren't running, tell the user that they can't leave until they fight or run from the monster
                textArea.appendText("You can either run or attack the monster.\n");

                //Otherwise, if the room is blocked, tell them they can't go to that room
            } else if (playerYLocation >= 9 || roomsArray[playerXLocation][playerYLocation + 1].isBlocked()) {
                textArea.appendText("The area behind you is inaccessible.\n");

                //Otherwise move them down, tell them they've moved down, and set their running status to false if they just ran.
            } else {
                currentRoom = roomsArray[playerXLocation][playerYLocation + 1];
                textArea.appendText("You moved down.\n");
                playerIsRunning = false;
            }

            //Update teh info about the player and possibly a monster in the next room which is now our current room
            updatePlayerStatLabels();

            //Otherwise if they went left...
        } else if (actionEvent.getSource() == leftButton) {
            //Get their current position
            playerXLocation = currentRoom.getLocation()[0];
            playerYLocation = currentRoom.getLocation()[1];

            //Check if there's a monster in the room
            if (currentRoom.hasMonster() && !playerIsRunning) {
                //If they aren't running, tell the user that they can't leave until they fight or run from the monster
                textArea.appendText("You can either run or attack the monster.\n");

                //Otherwise block the room if blocked
            } else if (playerXLocation == 0 || roomsArray[playerXLocation - 1][playerYLocation].isBlocked()) {
                textArea.appendText("The area to the left is inaccessible.\n");

                //Otherwise move the player and tell them they moved and remove their running status
            } else {
                currentRoom = roomsArray[playerXLocation - 1][playerYLocation];
                textArea.appendText("You moved left.\n");
                playerIsRunning = false;
            }
            //Update label info
            updatePlayerStatLabels();


        } else if (actionEvent.getSource() == rightButton) {
            //Get current coordinates
            playerXLocation = currentRoom.getLocation()[0];
            playerYLocation = currentRoom.getLocation()[1];

            //Make sure room doesn't have monster in it
            if (currentRoom.hasMonster() && !playerIsRunning) {
                //If they aren't running, tell the user that they can't leave until they fight or run from the monster
                textArea.appendText("You can either run or attack the monster.\n");

                //Make sure room isn't blocked
            } else if (playerXLocation == 9 || roomsArray[playerXLocation + 1][playerYLocation].isBlocked()) {
                textArea.appendText("The area to the right is inaccessible.\n");

                //Move player, tell player they moved, remove running status
            } else {
                currentRoom = roomsArray[playerXLocation + 1][playerYLocation];
                textArea.appendText("You moved right.\n");
                playerIsRunning = false;
            }
            //Update label info
            updatePlayerStatLabels();

        } else if (actionEvent.getSource() == searchButton) {
            //Roll for quality of hiding spot
            int hidingSpot = die.rollDie(20, 1);

            //If the player finds the gold and there's no monster in the room
            if (hidingSpot < gamePlayer.getIntelligence() && !currentRoom.hasMonster()) {
                //Then we tell them they got the gold
                textArea.appendText("You found " + currentRoom.getGold() + " Gold!\n");
                //And we give them the gold
                gamePlayer.addGold(currentRoom.getGold());
                //Which means the room has no more gold to give
                currentRoom.setGold(0);
                //And we update their inventory info
                updatePlayerStatLabels();

                //If there's a monster, we tell them to defeat the monster before collecting
            } else if (currentRoom.hasMonster()) {
                textArea.appendText("Defeat monster before searching for gold!\n");

                //If they player doesn't find anything, tell them they found nothing.
            } else textArea.appendText("You found no gold, sorry!\n");
        } else if (actionEvent.getSource() == attackButton){
            if (currentRoom.hasMonster()){
                // The initial attack of the player, based on the player's intelligence
                boolean canSeeMonster = gamePlayer.rollDie(20,1) < gamePlayer.getIntelligence();
                // First strike by player
                if (canSeeMonster){
                    double damageDealt = gamePlayer.getStrength()/3;
                    currentMonster.subtractHitPoints(damageDealt);
                    updatePlayerStatLabels();
                }

                boolean monsterTurn = true;
                // The fight between the monster and player
                // Depending on the turn, each opponent attacks the other with their strength divided by 3
                // Before allowing the other opponent to attack
                while (currentMonster.getHitPoints() > 0 && gamePlayer.getHitPoints() > 0){
                    if (monsterTurn){
                        double damageDealt = currentMonster.getStrength()/3;
                        if (damageDealt < 1){
                            damageDealt = 1;
                        }
                        gamePlayer.subtractHitPoints(damageDealt);
                        updatePlayerStatLabels();
                        monsterTurn = false;
                    } else {
                        double damageDealt = gamePlayer.getStrength() / 3;
                        currentMonster.subtractHitPoints(damageDealt);
                        updatePlayerStatLabels();
                        monsterTurn = true;
                    }
                }
                // If the monster dies, remove the monster from that level, and update the labels
                // If the player dies, end the game
                if (currentMonster.getHitPoints() <= 0){
                    currentRoom.setMonster(false);
                    updatePlayerStatLabels();
                } else {
                    endGame();
                }
            } else {
                // If there is no monster in the room, alert the player
                textArea.appendText("You can't fight, there is no monster!\n");
            }
        } else if (actionEvent.getSource() == runButton){
            // If the room has a monster, allow the player to run
            if (currentRoom.hasMonster()){
                // Based on monster's intelligence decided if monster saw player or not
                // If they did see the player, cause them damage
                boolean seenByMonster = gamePlayer.rollDie(20,1) < currentMonster.getIntelligence();
                if (seenByMonster){
                    double damageDealt = currentMonster.getStrength()/3;
                    if (damageDealt < 1){
                        damageDealt = 1;
                    }
                    gamePlayer.subtractHitPoints(damageDealt);
                    if (gamePlayer.getHitPoints() <= 0) {
                        endGame();
                    }
                }
                // Inform the player they are running
                playerIsRunning = true;
                textArea.appendText("Your running! Choose a direction!\n");
            } else {
                // If room has no monster, tell player they can't run
                textArea.appendText("You can't run, there is no monster to run from!\n");
            }
        } else if (actionEvent.getSource() == sleepButton){
            // Only allowed to sleep in a room if not in a room with a monster
            // has one in six chance of spawning a monster as you sleep
            // resets the player's hitpoints as well
            if (!currentRoom.hasMonster()) {
                gamePlayer.resetHitPoints();
                boolean foundByMonster = gamePlayer.rollDie(6,1) == 1;
                if (foundByMonster){
                    currentRoom.setMonster(true);
                    currentMonster = new Monster();
                }
                updatePlayerStatLabels();

            } else {
                textArea.appendText("You can't sleep! There is a monster nearby!\n");
            }
        }

    }

    //Updates player stat labels
    private void updatePlayerStatLabels() {

        //If the room has a monster
        if (currentRoom.hasMonster()) {

            //Update the label with the monster's stats
            monsterStatLabel.setText("Monster Stats:" + "\nHit points: " + currentMonster.getHitPoints() + "\nStrength: " + currentMonster.getStrength() + "\nDexterity: " + currentMonster.getDexterity() + "\nIntelligence: " + currentMonster.getIntelligence());

            //Otherwise
        } else {
            //Tell the user there's no monster in the room
            monsterStatLabel.setText("Monster Stats: \nNo Monster.");
        }

        //Update the player's stats
        playerStatLabel.setText("Player Stats:" + "\nHit points: " + gamePlayer.getHitPoints() + "\nStrength: " + gamePlayer.getStrength() + "\nDexterity: " + gamePlayer.getDexterity() + "\nIntelligence: " + gamePlayer.getIntelligence() + "\nTotal Gold: " + gamePlayer.getTotalGold());

        //Log the current room as visited
        currentRoom.setVisited(true);

        //Tell the user the map key
        StringBuilder visitedMap = new StringBuilder("""
                Hello! This is a map of the dungeon. Key:
                    B: Blocked.
                    X: You've been to this room before.
                    O: Your current location.
                    U: Unvisited
                """);

        //Traverse the roomsArray and add characters to the StringBuilder according to the state of the room
        for (int i = 0; i < roomsArray.length; i++) {
            for (int j = 0; j < roomsArray[i].length; j++) {

                //If it's our current location, put an O
                if (roomsArray[j][i] == currentRoom) {
                    visitedMap.append("O");
                    //If it's been visited, put an X
                } else if (roomsArray[j][i].isVisited() && !roomsArray[j][i].isBlocked()) {
                    visitedMap.append("X");
                    //If it's blocked, put a B
                } else if (roomsArray[j][i].isBlocked()) {
                    visitedMap.append("B");
                    //Otherwise, it's unvisited. Put a U
                } else {
                    visitedMap.append("U");
                }
            }
            //Add a new line so we start the next row
            visitedMap.append("\n");
        }
        //Print the stringBuilder to the mapText area
        mapText.setText(visitedMap.toString());

    }

    //When the game ends
    private void endGame() {
        //Disable buttons so the player can't move
        upButton.setDisable(true);
        downButton.setDisable(true);
        leftButton.setDisable(true);
        rightButton.setDisable(true);
        attackButton.setDisable(true);
        runButton.setDisable(true);
        searchButton.setDisable(true);
        sleepButton.setDisable(true);
        //Tell the user the game ended
        textArea.appendText("Your health hit 0. Game over!");

    }

}
