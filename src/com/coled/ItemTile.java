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
    public String getSprite() { return "\uD83C\uDF7E"; }
}
class RedApple implements ItemTile {
    int xpos;
    int ypos;
    Item redApple = new Item("Red Apple", 2);
    public RedApple(int xPos, int yPos) {
        xpos = xPos;
        ypos = yPos;
    }

    @Override
    public Item getItem() {
        return redApple;
    }

    @Override
    public int[] getPosition() {
        return new int[] {xpos, ypos};
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
        return "Item-RedApple";
    }

    @Override
    public String getSprite() {
        return "\uD83C\uDF4E";
    }
}
class Burger implements ItemTile {
    int xpos;
    int ypos;
    Item burger = new Item("Burger", 6);
    public Burger(int xPos, int yPos) {
        xpos = xPos;
        ypos = yPos;
    }

    @Override
    public Item getItem() {
        return burger;
    }

    @Override
    public int[] getPosition() {
        return new int[] {xpos, ypos};
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
        return "Item-Burger";
    }

    @Override
    public String getSprite() {
        return "\uD83C\uDF54";
    }
}
class Katanas implements ItemTile {
    int xpos;
    int ypos;
    Item katana = new Item("Katana", 8, 0.8);
    public Katanas(int xPos, int yPos) {
        xpos = xPos;
        ypos = yPos;
    }

    @Override
    public Item getItem() {
        return katana;
    }

    @Override
    public int[] getPosition() {
        return new int[] {xpos, ypos};
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
        return "Item-Katanas";
    }

    @Override
    public String getSprite() {
        return "⚔";
    }
}
class Knife implements ItemTile {
    int xpos;
    int ypos;
    Item knife = new Item("Knife", 6, 1.0);
    public Knife(int xPos, int yPos) {
        xpos = xPos;
        ypos = yPos;
    }

    @Override
    public Item getItem() {
        return knife;
    }

    @Override
    public int[] getPosition() {
        return new int[] {xpos, ypos};
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
        return "Item-Knife";
    }

    @Override
    public String getSprite() {
        return "\uD83D\uDDE1";
    }
}
class Hammer implements ItemTile {
    int xpos;
    int ypos;
    Item hammer = new Item("Hammer", 10,0.35);
    public Hammer(int xPos, int yPos) {
        xpos = xPos;
        ypos = yPos;
    }

    @Override
    public Item getItem() {
        return hammer;
    }

    @Override
    public int[] getPosition() {
        return new int[] {xpos, ypos};
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
        return "Item-Hammer";
    }

    @Override
    public String getSprite() {
        return "⚒";
    }
}
class HammerSickle implements ItemTile {
    int xpos;
    int ypos;
    Item hammerSickle = new Item("Hammer and Sickle", 3,0.7);
    public HammerSickle(int xPos, int yPos) {
        xpos = xPos;
        ypos = yPos;
    }

    @Override
    public Item getItem() {
        return hammerSickle;
    }

    @Override
    public int[] getPosition() {
        return new int[] {xpos, ypos};
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
        return "Item-HammerSickle";
    }

    @Override
    public String getSprite() {
        return "☭";
    }
}
class Pickaxe implements ItemTile {
    int xpos;
    int ypos;
    Item pickaxe = new Item("Pickaxe", 12,0.5);
    public Pickaxe(int xPos, int yPos) {
        xpos = xPos;
        ypos = yPos;
    }

    @Override
    public Item getItem() {
        return pickaxe;
    }

    @Override
    public int[] getPosition() {
        return new int[] {xpos, ypos};
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
        return "Item-Pickaxe";
    }

    @Override
    public String getSprite() {
        return "⛏";
    }
}
class Umbrella implements ItemTile {
    int xpos;
    int ypos;
    Item umbrella = new Item("Umbrella", 2,0.65);
    public Umbrella(int xPos, int yPos) {
        xpos = xPos;
        ypos = yPos;
    }

    @Override
    public Item getItem() {
        return umbrella;
    }

    @Override
    public int[] getPosition() {
        return new int[] {xpos, ypos};
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
        return "Item-Umbrella";
    }

    @Override
    public String getSprite() {
        return "☂";
    }
}