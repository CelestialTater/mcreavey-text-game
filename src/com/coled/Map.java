package com.coled;

import java.util.LinkedList;
import java.util.OptionalInt;
import java.util.Random;

public class Map {
    /*Ideas for map generation
    Basic: Test ground for initial game --Done
    Plains: Mostly open field, some enemies --Done
    Forest: Start with an open area, th3en populate with trees --Partially Done?
    Dungeon: create multiple rooms and connect them --Might be impossible
    EX Mountain: Map with cliffs and possibly extend to volcano?
    EX Town: peaceful map that houses traders ect. to use Gold?
     */

    /*Possible parameters:
    Dimensions(xDir yDir) -> specify how large the map is --Done
    Map type -> See above --Done
    Seed -> Provides randomness
    Map level -> intrinsic value to determine difficulty of enemies + rewards --???
    Enemy list -> list of enemies to use for the given map --???
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
    public static void createNewMap(String mapType, int xDir, int yDir, OptionalInt seed){
        //set up for new map
        currentMap = new LinkedList<Tile>();
        mapDimensions = new int[] {xDir, yDir};
        currentEnemies = new LinkedList<Enemy>();
        if(seed.isPresent()){
            rand = new Random(seed.getAsInt());
        }else{
            rand = new Random();
        }

        //Based on mapType, generate that map
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

        //Set player position randomly
        LinkedList<Integer[]> valid = MapGeneration.validPlayerPositions();
        Integer[] tilePicked = valid.get(rand.nextInt(valid.size()));
        Player.setPosition(tilePicked[0]+1, tilePicked[1]+1);
        /*
        while(true){
            int xPos = rand.nextInt(Map.mapDimensions[0]);
            int yPos = rand.nextInt(Map.mapDimensions[1]);
            Tile check = Map.getTile(xPos, yPos, true);
            if(!check.isEvent() && check.isPassable()){
                Player.setPosition(xPos+1, yPos+1);
                break;
            }
        }*/
    }

    /**
     * Gets a string representation of the map
     * @return the String representation of the map
     */
    public static String getMapString(){
        //Output variable
        String v = "";

        //Copy all of the tiles in the original map into a placeholder
        LinkedList<Tile> tilesToCopy = new LinkedList<>(currentMap);

        //Add enemies to placeholder
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

        //return the string
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
        //If trying to access a tile not in the map, return an obstacle
        if(xPos < 0 || xPos >= mapDimensions[0] || yPos < 0 || yPos >= mapDimensions[1]){
            return new Obstacle();
        }
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

        //Place random exit
        placeTileRandomly(new Exit());
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
        int treeCount = Map.rand.nextInt(Map.mapDimensions[0]*Map.mapDimensions[1]/5) + 1;
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
            LinkedList<String> directions = new LinkedList<>();
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
        //Place random exit
        placeTileRandomly(new Exit());
    }

    /**
     * Places a specified tile in a random spot.
     * Used for exits currently
     * @param t Tile to place
     */
    public static int[] placeTileRandomly(Tile t){
        while(true){
            int xPos = Map.rand.nextInt(Map.mapDimensions[0]);
            int yPos = Map.rand.nextInt(Map.mapDimensions[1]);
            Tile check = Map.getTile(xPos, yPos, false);
            if(check.isPassable() && !check.isEvent()){
                Map.setTile(xPos, yPos, t);
                return new int[] {xPos, yPos};
            }
        }
    }

    /**
     * A function that returns all positions that a map is possible to complete from.
     * @return
     */
    public static LinkedList<Integer[]> validPlayerPositions(){
        LinkedList<Integer[]> validPositions = new LinkedList<>();
        LinkedList<Integer[]> positions = new LinkedList<>();

        for(Tile v : Map.currentMap){
            if(v instanceof Exit){
                int pos = Map.currentMap.indexOf(v);
                int yPos = pos/Map.mapDimensions[0];
                int xPos = pos%Map.mapDimensions[0];
                positions.add(new Integer[]{xPos, yPos});
            }
        }

        boolean[] checkedPositions = new boolean[Map.mapDimensions[0]*Map.mapDimensions[1]];
        boolean a = true;

        while(a){
            a = false;
            LinkedList<Integer[]> buff = new LinkedList<Integer[]>();
            for(Integer[] i : positions){

                //if Position already checked, skip it
                if(checkedPositions[Map.mapDimensions[0]*i[1]+i[0]]){
                    continue;
                }
                Tile t = Map.getTile(i[0], i[1], false);

                //If the current tile is passable, then add the tiles surrounding it
                if(t.isPassable()){
                    buff.add(new Integer[]{i[0], i[1]+1});
                    buff.add(new Integer[]{i[0], i[1]-1});
                    buff.add(new Integer[]{i[0]+1, i[1]});
                    buff.add(new Integer[]{i[0]-1, i[1]});
                    //Tile must also be valid for player placement
                    validPositions.add(i);
                }

                //Add it to checked positions
                checkedPositions[Map.mapDimensions[0]*i[1]+i[0]] = true;
            }
            if(buff.size() > 0){
                a = true;
            }
            positions = new LinkedList<>();
            for(Integer[] i : buff){
                if(i[0] == -1 || i[1] == -1 || i[0] >= Map.mapDimensions[0]-1 || i[1] >= Map.mapDimensions[1]
                        || i.equals(checkedPositions[Map.mapDimensions[0]*i[1]+i[0]])){
                    continue;
                }
                positions.add(new Integer[]{i[0], i[1]});
            }
        }

        return validPositions;
    }
}
