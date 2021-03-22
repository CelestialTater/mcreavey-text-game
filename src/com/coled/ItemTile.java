package com.coled;

public interface ItemTile extends Tile {
    Item getItem();
    int[] getPosition();
}
class Potion implements ItemTile{
    int xpos;
    int ypos;
    Item potion = new Item("Healing Potion", 5);
    public Potion(int xPos, int yPos) {
        xpos = xPos;
        ypos = yPos;
    }

    @Override
    public Item getItem() {
        return potion;
    }

    @Override
    public int[] getPosition() { return new int[] {xpos, ypos}; }

    @Override
    public boolean isPassable() { return true; }

    @Override
    public boolean isEvent() { return true; }

    @Override
    public String getEvent() { return "Item-Potion"; }

    @Override
    public String getSprite() { return "I"; }
}
