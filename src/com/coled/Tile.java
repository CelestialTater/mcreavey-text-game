package com.coled;

public interface Tile {

    //The Tile interface is designed to allow for both map tiles and enemy tiles.
    boolean isPassable();
    boolean isEvent();
    String getEvent();
    String getSprite();
}

class Grass implements Tile{
    String sprite = "#";

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