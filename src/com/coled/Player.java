package com.coled;

import com.coled.Tile;

public class Player implements Tile {
    private static int[] position = {0,0};
    private static String sprite = "A";
    private static boolean[] movementOptions = {false, false, false, false};

    /**
     * Updates player position
     * @param direction cardinal direction
     */
    public static void updatePosition(String direction){
        switch(direction.toLowerCase()){
            case "n":
                if(movementOptions[0]){
                    position[1] -= 1;
                }
                break;
            case "s":
                if(movementOptions[1]){
                position[1] += 1;}
                break;
            case "e":
                if(movementOptions[2]){
                position[0] += 1;}
                break;
            case "w":
                if(movementOptions[3]){
                position[0] -=1;}
                break;
        }

        evalPlayerMovement();
    }

    /**
     * Updates which moves are currently valid for the player
     */
    public static void evalPlayerMovement(){
        movementOptions = new boolean[] {true, true, true, true};

        //Top
        if(position[1] == 0){
            movementOptions[0] = false;
        }
        //bottom
        if(position[1]+1 == Map.mapDimensions[1]){
            movementOptions[1] = false;
        }
        //right
        if(position[0]+1 == Map.mapDimensions[0]){
            movementOptions[2] = false;
        }
        //left
        if(position[0] == 0){
            movementOptions[3] = false;
        }

        if(movementOptions[0]){
            movementOptions[0] = Map.currentMap.get((position[1]-1)*Map.mapDimensions[0]+position[0]).isPassable();
        }
        if(movementOptions[1]){
            movementOptions[1] = Map.currentMap.get((position[1]+1)*Map.mapDimensions[0]+position[0]).isPassable();
        }
        if(movementOptions[2]){
            movementOptions[2] = Map.currentMap.get(position[1]*Map.mapDimensions[0]+position[0]+1).isPassable();
        }
        if(movementOptions[3]){
            movementOptions[3] = Map.currentMap.get(position[1]*Map.mapDimensions[0]+position[0]-1).isPassable();
        }
    }

    /**
     * Sets player position. Position is always -1 in both directions to adjust for indexing
     * @param xPos x position
     * @param yPos y position
     */
    public static void setPosition(int xPos, int yPos){
        position = new int[]{xPos-1, yPos-1};
        updatePosition("");
    }

    public static int[] getPosition(){
        return position;
    }

    //Tile methods
    @Override
    public boolean isPassable() {
        return true;
    }

    @Override
    public boolean isEvent() {
        return false;
    }

    @Override
    public String getEvent() {
        return null;
    }

    @Override
    public String getSprite() {
        return sprite;
    }
}
