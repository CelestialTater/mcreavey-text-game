package com.coled;

import java.util.LinkedList;

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
    LinkedList<Object[]> attacks;
    /**
     * Initialize a battle
     * @param enemy enemy object
     */
    public Battle(Enemy enemy) {
        maxPlayerHp = Player.maxHealth;
        enemyHp = enemy.getHealth();
        maxEnemyHp = enemy.getHealth();
        enemyDamage = enemy.getDamage();
        enemyName = enemy.getName();
        enemyColor = enemy.getColor();
        enemyDrops = enemy.getDrops();
        dropRate = enemy.getDropRate();
        attacks = enemy.getAttacks();
    }

    /**
     * Initializes a battle by printing the interface.
     */
    public void battleInit(){
        clearConsole();
        Main.printFromFile("src/com/coled/art.txt", enemyName, enemyColor);
        System.out.println(enemyName + " appears!");
        Main.printArrayString(getHealthBar(maxPlayerHp, Player.health, false));
        System.out.print("     ");
        Main.printArrayString(getHealthBar(maxEnemyHp, enemyHp, true));
        Player.printInventory(true);
    }

    /**
     * Ends the battle by either returning to map or ending the game
     * @param won whether the battle was won or not.
     */
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
            //If lost, end the game
            Main.mode = PlayerMode.GAMEOVER;
            Main.sleep(1000);
            clearConsole();
            Main.printFromFile("src/com/coled/art.txt", "GameOver", "Red");
        }

    }

    /**
     * Attack the enemy
     * @param weapon weapon used to attack the enemy
     */
    public void attack(Item weapon) {
        //Check if weapon hits
        if(weapon.getHpc() >= Math.random()) {
            //update enemyHP
            enemyHp -= weapon.getDamage();
            clearConsole();
            Main.printFromFile("src/com/coled/art.txt", enemyName, enemyColor);
            Main.printArrayString(getHealthBar(maxPlayerHp, Player.health, false));
            System.out.print("     ");
            Main.printArrayString(getHealthBar(maxEnemyHp, enemyHp, true));
            Player.printInventory(true);
            //End battle if enemy dies
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
        //Only attack if the enemy is alive
        if(enemyHp > 0) {
            //Pick a random attack
            int attackNum = (int) (Math.random() * attacks.size());
            //Check if the attack hits
            if(((double) attacks.get(attackNum)[2]) >= Math.random()){
                //Update health, update UI
                Player.health -= (int) attacks.get(attackNum)[1];
                clearConsole();
                Main.printFromFile("src/com/coled/art.txt", enemyName, enemyColor);
                Main.printArrayString(getHealthBar(maxPlayerHp, Player.health, false));
                System.out.print("     ");
                Main.printArrayString(getHealthBar(maxEnemyHp, enemyHp, true));
                System.out.println("\n" + Colors.PURPLE + enemyName + Colors.RESET + " used " + Colors.RED + attacks.get(attackNum)[0] + "!" + Colors.RESET);
                Player.printInventory(true);
                //End battle if player dies
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
