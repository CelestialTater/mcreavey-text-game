package com.coled;

/**
 * Tile interface for all tiles
 * Tiles should have:
 * -A sprite
 */
public interface Tile {

    //The Tile interface is designed to allow for both map tiles and enemy tiles.
    boolean isPassable();
    boolean isEvent();
    String getEvent();
    String getSprite();
}

/**
 * Implementation of a tile
 * This tile isPassable, and has no special events
 */
class Grass implements Tile{
    String sprite = Colors.GREEN + Colors.BGGREEN + "□" + Colors.RESET;

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

/**
 * Implementation of a tile
 * This Tile is impassable
 */
class Obstacle implements Tile{
    String sprite = Colors.RED + "\uD83C\uDF32" + Colors.RESET;

    @Override
    public boolean isPassable() {
        return false;
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

/**
 * Exit tile, should be present on every map
 */
class Exit implements  Tile {
    String sprite = ".";

    @Override
    public boolean isPassable() {
        return true;
    }

    @Override
    public boolean isEvent() {
        return true;
    }

    @Override
    public String getEvent() {
        return "Exit";
    }

    @Override
    public String getSprite() {
        return sprite;
    }
}