package com.coled;

import java.util.LinkedList;

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
    //TODO Add variables for player, enemies, and events present on current map.

    /**
     * Creates new map
     * @param mapType What type of map to generate
     * @param xDir Width of the map
     * @param yDir Length of the map
     */
    public static void createNewMap(String mapType, int xDir, int yDir){
        //set up for new map
        currentMap = new LinkedList<Tile>();
        mapDimensions = new int[] {xDir, yDir};
        currentEnemies = new LinkedList<Enemy>();

        switch (mapType.toLowerCase()){
            case "basic":
                MapGeneration.basic();
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

        for(Enemy i : currentEnemies){
            tilesToCopy.set((i.getPosition()[1]*Map.mapDimensions[0])+i.getPosition()[0], i);
        }

        for(int y = 0; y < mapDimensions[1]; y++){
            for(int x = 0; x < mapDimensions[0]; x++){
                v += tilesToCopy.get((y* mapDimensions[0])+x).getSprite();
            }
            if(!(y+1 == mapDimensions[1])){
                v += "\n";
            }
        }

        //TODO implement enemies, player and event sprites
        return v;
    }
}

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
                    Map.currentMap.set((y*Map.mapDimensions[0])+x, new Obstacle());
                }
            }
            else{
                Map.currentMap.set((y*Map.mapDimensions[0]), new Obstacle());
                Map.currentMap.set(((y+1)*Map.mapDimensions[0])-1, new Obstacle());
            }
        }

        //Place a sheep as close to the center as possible
        //Map.currentMap.add(new Sheep(Map.mapDimensions[0]/2, Map.mapDimensions[1]/2));
        Map.currentEnemies.add(new Sheep(Map.mapDimensions[0]/2, Map.mapDimensions[1]/2));
    }
}
