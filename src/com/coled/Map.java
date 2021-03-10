package com.coled;

import java.util.LinkedList;
import java.util.Random;

public class Map {
    /*Ideas for map generation
    Basic: Test ground for initial game
    Plains: Mostly open field, some enemies
    Forest: Start with an open area, th3en populate with trees
    Dungeon: create multiple rooms and connect them
    EX Mountain: Map with cliffs and possibly extend to volcano?
    EX Town: peaceful map that houses traders ect. to use Gold?
     */

    /*Possible parameters:
    Dimensions(xDir yDir) -> specify how large the map is
    Map type -> See above
    Map level -> intrinsic value to determine difficulty of enemies + rewards
    Enemy list -> list of enemies to use for the given map
     */


    //These variables store details about the CURRENT MAP. For this iteration of the game
    //there is no plans for remembering previous maps for backtracking.
    public static LinkedList<Tile> currentMap;
    public static int[] mapDimensions;
    public static LinkedList<Enemy> currentEnemies;
    public static Random rand;
    //TODO Add variables for player, enemies, and events present on current map.

    /**
     * Creates new map
     * @param mapType What type of map to generate
     * @param xDir Width of the map
     * @param yDir Length of the map
     * @param seed Seed to start with
     */
    public static void createNewMap(String mapType, int xDir, int yDir, int seed){
        //set up for new map
        currentMap = new LinkedList<Tile>();
        mapDimensions = new int[] {xDir, yDir};
        currentEnemies = new LinkedList<Enemy>();
        rand = new Random(seed);

        switch (mapType.toLowerCase()){
            case "basic":
                MapGeneration.basic();
                break;
            case "plains":
                MapGeneration.plains();
                break;
            case "forest":
                MapGeneration.forest();
                break;
            default:
                System.out.println("Abort!, this is not a valid map type: " + mapType);
        }

    }

    public static String getMapString(){
        String v = "";
        LinkedList<Tile> tilesToCopy = new LinkedList<Tile>();
        for(Tile i : currentMap){
            tilesToCopy.add(i);
        }

        //Add enemies
        for(Enemy i : currentEnemies){
            tilesToCopy.set((i.getPosition()[1]*Map.mapDimensions[0])+i.getPosition()[0], i);
        }

        //Add player
        //TODO Clean up player Tile
        tilesToCopy.set((Player.getPosition()[1])*mapDimensions[0]+Player.getPosition()[0], new Player());

        //TODO implement event sprites

        //Convert to string
        for(int y = 0; y < mapDimensions[1]; y++){
            for(int x = 0; x < mapDimensions[0]; x++){
                v += tilesToCopy.get((y* mapDimensions[0])+x).getSprite();
            }
            if(!(y+1 == mapDimensions[1])){
                v += "\n";
            }
        }



        return v;
    }

    /**
     * Function to get a tile using two ints. Does not account for index counting
     * @param xPos X co-ordinate + 1
     * @param yPos Y co-ordinate + 1
     * @param checkEnemies include enemy tiles
     * @return the tile at the given position
     */
    public static Tile getTile(int xPos, int yPos, boolean checkEnemies){
        if(checkEnemies){
            for(Enemy i: currentEnemies){
                if(i.getPosition()[0] == xPos && i.getPosition()[1] == yPos){
                    return i;
                }
            }
        }
        return currentMap.get(yPos*mapDimensions[0]+xPos);
    }

    /**
     * Function to set a tile easily. Does not account for index counting
     * @param xPos X co-ordinate
     * @param yPos Y co-ordinate
     * @param tile Tile to set
     */
    public static void setTile(int xPos, int yPos, Tile tile){
        Map.currentMap.set((yPos*Map.mapDimensions[0])+xPos, tile);
    }
}

//TODO Change to an interface?
class MapGeneration{

    /**
     * Basic map for debugging
     */
    public static void basic(){
        //Setup grass
        for(int y = 0; y < Map.mapDimensions[1]; y++){
            for(int x = 0; x < Map.mapDimensions[0];x++){
                Map.currentMap.add(new Grass());
            }
        }

        //Setup borders
        for(int y = 0; y < Map.mapDimensions[1]; y++){
            if(y == 0 || y == Map.mapDimensions[1]-1){
                for(int x = 0; x < Map.mapDimensions[0]; x++){
                    Map.setTile(x, y, new Obstacle());
                }
            }
            else{
                Map.setTile(0, y, new Obstacle());
                Map.setTile(Map.mapDimensions[0]-1, y, new Obstacle());
            }
        }

        //Place a sheep as close to the center as possible
        //Map.currentMap.add(new Sheep(Map.mapDimensions[0]/2, Map.mapDimensions[1]/2));
        Map.currentEnemies.add(new Sheep(Map.mapDimensions[0]/2-1, Map.mapDimensions[1]/2-1));

        //Set player in the bottom left corner
        Player.setPosition(2, 2);
    }

    /**
     * Plains generation
     * Creates an open map, the populates with a few rocks/boulders.
     */
    public static void plains(){
        //Setup grass
        for(int y = 0; y < Map.mapDimensions[1]; y++){
            for(int x = 0; x < Map.mapDimensions[0];x++){
                Map.currentMap.add(new Grass());
            }
        }

        //Setup random amounts of rocks
        int rockCount = Map.rand.nextInt(Map.mapDimensions[0]*Map.mapDimensions[1]/5);
        for(int i = 0; i < rockCount; i++){
            int xPos = Map.rand.nextInt(Map.mapDimensions[0]);
            int yPos = Map.rand.nextInt(Map.mapDimensions[1]);
            Map.setTile(xPos, yPos, new Obstacle());
        }

        //Place an exit
        int exitXPos = Map.rand.nextInt(Map.mapDimensions[0]);
        int exitYPos = Map.rand.nextInt(Map.mapDimensions[1]);
        Map.setTile(exitXPos, exitYPos, new Exit());

        //TODO set player
    }

    /**
     * Forest generation.
     * Creates an open map, then populates with trees
     */
    public static void forest(){
        //Setup grass
        for(int y = 0; y < Map.mapDimensions[1]; y++){
            for(int x = 0; x < Map.mapDimensions[0];x++){
                Map.currentMap.add(new Grass());
            }
        }


        //Create "seed trees"
        int treeCount = Map.rand.nextInt(Map.mapDimensions[0]*Map.mapDimensions[1]/5 + 1);
        int[][] treeLocations = new int[treeCount][2];
        for(int i = 0; i < treeCount; i++){
            int xPos = Map.rand.nextInt(Map.mapDimensions[0]);
            int yPos = Map.rand.nextInt(Map.mapDimensions[1]);
            Map.setTile(xPos, yPos, new Obstacle());
            treeLocations[i] = new int[]{xPos, yPos};
        }

        //Add additional trees up to the max close by other trees
        int treeMax = Map.mapDimensions[0]*Map.mapDimensions[1]/3+2;
        for(int i = 0; i < treeMax; i++){
            int chosenTree = Map.rand.nextInt(treeCount);
            int[] position = treeLocations[chosenTree];
            int treeAmount = Map.rand.nextInt(treeMax-i);
            LinkedList<String> directions = new LinkedList<String>();
            directions.add("n");
            directions.add("s");
            directions.add("e");
            directions.add("w");
            for(int x = 0; x < treeAmount; x++){
                if(directions.size() == 0){
                    break;
                }
                int directionToPick = Map.rand.nextInt(directions.size());
                switch(directions.get(directionToPick)){
                    case "n":
                        position[1] -= 1;
                        break;
                    case "s":
                        position[1] += 1;
                        break;
                    case "e":
                        position[0] += 1;
                        break;
                    case "w":
                        position[0] -= 1;
                        break;
                }
                try{
                    if(Map.getTile(position[0], position[1], false).isPassable()){
                        Map.setTile(position[0], position[1], new Obstacle());
                        i++;
                        break;
                    }
                }catch(IndexOutOfBoundsException e){
                    //do nothing...
                }
                directions.remove(directionToPick);
            }
        }
        //TODO add player, enemies
    }

    //TODO create common player setup

}
