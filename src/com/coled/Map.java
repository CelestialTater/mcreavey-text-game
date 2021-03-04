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
    //TODO Add variables for player, enemies, and events present on current map.

    public static void createNewMap(String mapType, int xDir, int yDir){
        //set up for new map
        currentMap = new LinkedList<Tile>();
        mapDimensions = new int[] {xDir, yDir};

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


        for(int y = 0; y < Map.mapDimensions[1]; y++){
            for(int x = 0; x < Map.mapDimensions[0]; x++){
                v += Map.currentMap.get((y* mapDimensions[0])+x).getSprite();
            }
            if(!(y+1 == Map.mapDimensions[1])){
                v += "\n";
            }
        }

        //TODO implement enemies, player and event sprites

        return v;
    }
}

class MapGeneration{

    ///Basic: Debug map for testing
    public static void basic(){
        for(int y = 0; y < Map.mapDimensions[1]; y++){
            for(int x = 0; x < Map.mapDimensions[0];x++){
                Map.currentMap.add(new Grass());
            }
        }
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
    }
}