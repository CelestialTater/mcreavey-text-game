package com.coled;

public class Battle {
    int enemyHp;
    int enemyDamage;
    int maxPlayerHp;
    int maxEnemyHp;
    String enemyName;
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
    }

    public void battleInit(){
        Main.clearConsole();
        System.out.println(enemyName + " appears!");
        Main.printArrayString(getHealthBar(maxPlayerHp, Player.health, false));
        System.out.print("     ");
        Main.printArrayString(getHealthBar(maxEnemyHp, enemyHp, true));
        Player.printInventory(true);
    }

    public void battleEnd(){
        //Return to map, delete enemy from map
        System.out.println("\nYou win!");
        Main.mode = PlayerMode.MAP;
    }

    /**
     * Attack the enemy
     * @param weapon weapon used to attack the enemy
     */
    public void attack(Item weapon) {
        if(weapon.getHpc() >= Math.random()) {
            enemyHp -= weapon.getDamage();
            Main.clearConsole();
            Main.printArrayString(getHealthBar(maxPlayerHp, Player.health, false));
            System.out.print("     ");
            Main.printArrayString(getHealthBar(maxEnemyHp, enemyHp, true));
            Player.printInventory(true);
            if(enemyHp <= 0){
                battleEnd();
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
                Main.clearConsole();
                Main.printArrayString(getHealthBar(maxPlayerHp, Player.health, false));
                System.out.print("     ");
                Main.printArrayString(getHealthBar(maxEnemyHp, enemyHp, true));
                Player.printInventory(true);
                if(Player.health <= 0){
                    battleEnd();
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
        Main.clearConsole();
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
