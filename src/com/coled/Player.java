package com.coled;

import com.coled.Tile;

import java.util.LinkedList;

public class Player implements Tile {
    private static int[] position = {0,0};
    private static String sprite = "A";
    public static LinkedList<Item> inventory = new LinkedList<Item>();
    public static int health = 10;
    public static int maxHealth = 10;

    /**
     * Updates player position
     * @param direction cardinal direction
     */
    public static void updatePosition(String direction){
        try {
            switch (direction.toLowerCase()) {
                case "n":
                    if (Map.getTile(position[0], position[1]-1, true, true).isPassable()) {
                        position[1] -= 1;
                    }
                    break;
                case "s":
                    if (Map.getTile(position[0], position[1]+1, true, true).isPassable()) {
                        position[1] += 1;
                    }
                    break;
                case "e":
                    if (Map.getTile(position[0]+1, position[1], true, true).isPassable()) {
                        position[0] += 1;
                    }
                    break;
                case "w":
                    if (Map.getTile(position[0]-1, position[1], true, true).isPassable()) {
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

    /**
     * Prints the player inventory
     * @param battle whether the print occurs during a battle
     */
    public static void printInventory(boolean battle) {
        if(battle) {
            System.out.println();
        }
        System.out.println(Colors.CYAN + "Inventory: " + Colors.RESET);
        for (Item i:inventory) {
            System.out.print("[" + inventory.indexOf(i) + "] " + i.getName() + " ");
        }
        System.out.print("\n");
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
