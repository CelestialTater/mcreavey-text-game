package com.coled;


import java.util.LinkedList;

/**
 * Enemy interface
 * This interface defines common methods for all enemies and
 * extends the Tile interface.
 */
public interface Enemy extends Tile {
    //Abstract class to house values for enemies
    //TODO evaluate whether we need all these methods...
    Enemy getEnemy();
    boolean isHostile();
    String getSprite();
    String getName();
    String getColor();
    int getHealth();
    int getDamage();
    //Attacks *MUST* follow this format: [String name, int damage, double hitPercent, int weight]
    LinkedList<Object[]> getAttacks();
    int[] getPosition();
    Item[] getDrops();
    double getDropRate();
}

/**
 * Sheep enemy
 * Stats: 5 health, 1 attack
 * Attacks: None...
 */
class Sheep implements Enemy {

    private String sprite = "S";
    private String name = "Sheep";
    private String color = "Yellow";
    private int maxHealth = 5;
    int currentHealth = 5;
    private int dmg = 1;
    private LinkedList<Object[]> attacks = new LinkedList<>();
    private int xpos;
    private int ypos;
    private Item mutton = new Item("Mutton",3);
    private Item club = new Item("Club", 2, 0.7);
    private Item[] drops = {mutton, club};
    private double dropPc = 0.3;

    public Sheep(int xPos, int yPos){
        xpos = xPos;
        ypos = yPos;
        attacks.add(new Object[]{"Kick", 1, 0.8, 5});
        attacks.add(new Object[]{"Bite", 2, 0.3, 1});
    }


    @Override
    public Enemy getEnemy() {
        return this;
    }

    @Override
    public boolean isHostile() {
        return false;
    }

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
        return "battle-sheep";
    }

    @Override
    public String getSprite() {
        return sprite;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public int getHealth() {
        return currentHealth;
    }

    @Override
    public int getDamage() {
        return dmg;
    }

    @Override
    public LinkedList<Object[]> getAttacks() {
        return attacks;
    }

    @Override
    public int[] getPosition() {
        return new int[] {xpos, ypos};
    }

    @Override
    public Item[] getDrops() {
        return drops;
    }

    @Override
    public double getDropRate() {
        return dropPc;
    }

}