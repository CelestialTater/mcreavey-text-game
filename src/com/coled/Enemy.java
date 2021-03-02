package com.coled;

public interface Enemy {
    //Abstract class to house values for enemies
    boolean isHostile();
    String getSprite();
    String getName();
}

class Sheep implements Enemy {

    String sprite = "";
    String name = "Sheep";

    @Override
    public boolean isHostile() {
        return false;
    }

    @Override
    public String getSprite() {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }
}