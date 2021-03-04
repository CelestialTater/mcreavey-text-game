package com.coled;

public class Item {
    String name;
    int damage;
    int hpc;
    int heal;
    String type;

    /**
     * Constructor for weapon
     * @param name name of item
     * @param damage damage of weapon
     * @param hpc hit percentage of weapon
     */
    public Item(String name, int damage, int hpc){
        this.name = name;
        this.damage = damage;
        this.hpc = hpc;
        type = "w";
    }

    /**
     * Constructor for healing/food items
     * @param name name of item
     * @param heal amount of health recovered
     */
    public Item(String name, int heal){
        this.name = name;
        this.heal = heal;
        type = "h";
    }

    /**
     * get the name of an item
     * @return name of item
     */
    public String getName(){
        return name;
    }

    /**
     * Get the damage value of a weapon
     * @return damage of weapon
     * @throws IllegalCallerException if item does not have a damage value
     */
    public int getDamage() {
        if(type.equals("w")) {
            return damage;
        }else{
            throw new IllegalCallerException("Illegal item attribute call");
        }
    }

    /**
     * Get the heal value of a weapon, only works if item is a healing item
     * @return amount of heal
     * @throws IllegalCallerException if item does not have a healing value
     */
    public int getHeal() {
        if(type.equals("h")) {
            return heal;
        }else{
            throw new IllegalCallerException("Illegal item attribute call");
        }
    }

    /**
     * Get the hit percent value of a weapon, only works if item is a weapon
     * @return hpc of weapon
     * @throws IllegalCallerException if item does not have a hpc value
     */
    public int getHpc() {
        if(type.equals("w")) {
            return hpc;
        }else{
            throw new IllegalCallerException("Illegal item attribute call");
        }
    }
}