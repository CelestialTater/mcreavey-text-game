package com.coled;

import static com.coled.Main.clearConsole;

public class Battle {
    int enemyHp;
    int enemyDamage;
    int maxPlayerHp;
    int maxEnemyHp;
    String enemyName;
    String enemyColor;
    Item[] enemyDrops;
    double dropRate;
    /**
     * Initialize a battle
     * @param enemy enemy object
     */
    public Battle(Enemy enemy) {
        maxPlayerHp = Player.maxHealth;
        enemyHp = enemy.getHealth();
        maxEnemyHp = enemy.getHealth();
        enemyDamage = enemy.getAttack();
        enemyName = enemy.getName();
        enemyColor = enemy.getColor();
        enemyDrops = enemy.getDrops();
        dropRate = enemy.getDropRate();
    }

    public void battleInit(){
        clearConsole();
        Main.printFromFile("src/com/coled/art.txt", enemyName, enemyColor);
        System.out.println(enemyName + " appears!");
        Main.printArrayString(getHealthBar(maxPlayerHp, Player.health, false));
        System.out.print("     ");
        Main.printArrayString(getHealthBar(maxEnemyHp, enemyHp, true));
        Player.printInventory(true);
    }

    public void battleEnd(boolean won){
        //Return to map, delete enemy from map
        if(won) {
            System.out.println("\nYou win!");
            Main.mode = PlayerMode.MAP;
            for(Item i : enemyDrops){
                if(dropRate >= Math.random()){
                    System.out.println(Colors.YELLOW + enemyName + Colors.RESET + " dropped: " + Colors.PURPLE + i.getName() + "!" + Colors.RESET);
                    Player.inventory.add(i);
                }
            }
            Main.sleep(1000);
        }else{
            //TODO: End game
        }

    }

    /**
     * Attack the enemy
     * @param weapon weapon used to attack the enemy
     */
    public void attack(Item weapon) {
        if(weapon.getHpc() >= Math.random()) {
            enemyHp -= weapon.getDamage();
            clearConsole();
            Main.printFromFile("src/com/coled/art.txt", enemyName, enemyColor);
            Main.printArrayString(getHealthBar(maxPlayerHp, Player.health, false));
            System.out.print("     ");
            Main.printArrayString(getHealthBar(maxEnemyHp, enemyHp, true));
            Player.printInventory(true);
            if(enemyHp <= 0){
                battleEnd(true);
            }
        }else{
            System.out.println("\nMiss!");
        }

    }

    /**
     * Attack the player. Enemy has a static hit change of 90%.
     */
    public void enemyAttack() {
        if(enemyHp > 0) {
            if(0.9 >= Math.random()){
                Player.health -= enemyDamage;
                clearConsole();
                Main.printFromFile("src/com/coled/art.txt", enemyName, enemyColor);
                Main.printArrayString(getHealthBar(maxPlayerHp, Player.health, false));
                System.out.print("     ");
                Main.printArrayString(getHealthBar(maxEnemyHp, enemyHp, true));
                Player.printInventory(true);
                if(Player.health <= 0){
                    battleEnd(false);
                }
            }else{
                System.out.println("\nEnemy Miss!");
            }
        }
    }

    /**
     * Updates the Battle Interface
     */
    public void update() {
        clearConsole();
        Main.printFromFile("src/com/coled/art.txt", enemyName, enemyColor);
        Main.printArrayString(getHealthBar(maxPlayerHp, Player.health, false));
        System.out.print("     ");
        Main.printArrayString(getHealthBar(maxEnemyHp, enemyHp, true));
        Player.printInventory(true);
    }

    /**
     * Get the health bar string for an enemy/the player
     * @param maxHp max health of the bar
     * @param hp current health of the bar
     * @param enemy boolean to determine color
     * @return array containing the health bar
     */
    public String[] getHealthBar(int maxHp, int hp, boolean enemy) {
        String[] bar = new String[maxHp+2];
        bar[0] = "<";
        int i;
        for(i = 1; i <= hp; i++){
            if(enemy){
                bar[i] = (Colors.BGRED + Colors.RED + " " + Colors.RESET);
            }else{
                bar[i] = (Colors.BGGREEN + Colors.GREEN + " " + Colors.RESET);
            }
        }
        for(i = i; i <= maxHp; i++) {
            bar[i] = "-";
        }
        bar[bar.length - 1] = ">";
        return bar;
    }

}
