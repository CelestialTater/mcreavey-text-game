package com.coled;

public class Battle {
    int playerHp;
    int enemyHp;
    /**
     * Initialize a battle
     * @param playerHp health of the player
     * @param enemyHp health of the enemy
     */
    public Battle(int playerHp, int enemyHp) {
        this.playerHp = playerHp;
        this.enemyHp = enemyHp;
    }
    public void battleInit(){

    }
    public void battleEnd(){

    }

    /**
     * Attack the enemy
     * @param weapon weapon used to attack the enemy
     */
    public void attack(Item weapon) {
        if(weapon.getHpc() <= Math.random()) {
            enemyHp -= weapon.getDamage();
            if(enemyHp <= 0){
                battleEnd();
            }
        }

    }

}
