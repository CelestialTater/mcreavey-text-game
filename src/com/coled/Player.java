package com.coled;

import com.coled.Tile;

public class Player implements Tile {
    private static int[] position = {0,0};
    private static String sprite = "A";

    /**
     * Updates player position
     * @param direction cardinal direction
     */
    public static void updatePosition(String direction){
        try {
            switch (direction.toLowerCase()) {
                case "n":
                    if (Map.getTile(position[0], position[1]-1, true).isPassable()) {
                        position[1] -= 1;
                    }
                    break;
                case "s":
                    if (Map.getTile(position[0], position[1]+1, true).isPassable()) {
                        position[1] += 1;
                    }
                    break;
                case "e":
                    if (Map.getTile(position[0]+1, position[1], true).isPassable()) {
                        position[0] += 1;
                    }
                    break;
                case "w":
                    if (Map.getTile(position[0]-1, position[1], true).isPassable()) {
                        position[0] -= 1;
                    }
                    break;
            }
        } catch(IndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Sets player position. Position is always -1 in both directions to adjust for indexing
     * @param xPos x position
     * @param yPos y position
     */
    public static void setPosition(int xPos, int yPos){
        position = new int[]{xPos-1, yPos-1};
    }

    /**
     * Returns position
     * @return position(x,y)
     */
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
