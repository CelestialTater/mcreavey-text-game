package com.coled;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Main {

    public static LinkedList<Item> inventory;
    public static int playerHealth;
    public static boolean inInventory = false;

    /**
     * Prints an array to the console
     * @param arr array to print
     */
    public static void printArrayString(String[] arr) {
        for (String i : arr){System.out.print(i);}
    }


    /**
     * Prints a series of newlines to the console to "clear" it.
     */
    public static void clearConsole() {
        System.out.println(System.lineSeparator().repeat(50));
    }

    /**
     * Sleep function
     * @param millis millis to sleep for
     */
    public static void sleep(int millis) {
        try{
            Thread.sleep(millis);
        }catch(InterruptedException e){ }
    }
    //Use modes to determine what to do with player input
    public static PlayerMode mode = PlayerMode.MAP;

    //Static battle variable to be used for all battles.
    public static Battle battle;

    /**
     * Function to access keycodes from the key listener
     * Handles all input from the user
     * @param e key event
     */
    public static void accessKeyCode(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_DOWN) {
            if(mode == PlayerMode.MAP) {
                Player.updatePosition("s");
            }
        }else if (key == KeyEvent.VK_UP) {
            if(mode == PlayerMode.MAP) {
                Player.updatePosition("n");
            }
        }else if (key == KeyEvent.VK_LEFT) {
            if(mode == PlayerMode.MAP) {
                Player.updatePosition("w");
            }
        }else if (key == KeyEvent.VK_RIGHT) {
            if(mode == PlayerMode.MAP) {
                Player.updatePosition("e");
            }
        }else if (key == KeyEvent.VK_E){
            if(inInventory){
                inInventory = false;
                clearConsole();
                mode = PlayerMode.MAP;
            }else{
                inInventory = true;
                mode = PlayerMode.INVENTORY;
                clearConsole();
                System.out.println(Colors.RED + "Health: " + Colors.RESET + Player.health + "/" + Player.maxHealth);
                Player.printInventory(false);
            }

        }else if (key == KeyEvent.VK_0){
            handleNumInput(0);
        }else if (key == KeyEvent.VK_1){
            handleNumInput(1);
        }else if (key == KeyEvent.VK_2){
            handleNumInput(2);
        }else if (key == KeyEvent.VK_3){
            handleNumInput(3);
        }else if (key == KeyEvent.VK_4){
            handleNumInput(4);
        }else if (key == KeyEvent.VK_5){
            handleNumInput(5);
        }else if (key == KeyEvent.VK_6){
            handleNumInput(6);
        }else if (key == KeyEvent.VK_7){
            handleNumInput(7);
        }else if (key == KeyEvent.VK_8){
            handleNumInput(8);
        }else if (key == KeyEvent.VK_9){
            handleNumInput(9);
        }
        Tile standingTile = Map.getTile(Player.getPosition()[0], Player.getPosition()[1], true);
        if(standingTile.isEvent() && mode != PlayerMode.BATTLE){
            //Execute event...."
            if(standingTile.getEvent().equalsIgnoreCase("Exit")){
                //Generate next floor/end game
            }else if(standingTile.getEvent().contains("battle")){
                String enemyType = standingTile.getEvent().substring(7);
                Enemy enemy;
                switch(enemyType){
                    case "sheep":
                        enemy = new Sheep(0,0);
                        break;
                    default:
                        enemy = null;
                        System.out.println(Colors.RED + "error: invalid enemytype. code probably crashes soon" + Colors.RESET);
                }
                mode = PlayerMode.BATTLE;
                battle = new Battle(enemy);
                battle.battleInit();
                Map.currentEnemies.remove(Map.currentEnemies.indexOf(standingTile));

            }

        }else if(mode == PlayerMode.MAP){
            System.out.println(Map.getMapString());
        }
    }

    public static void handleNumInput(int num) {
        String type;
        switch(mode) {
            case BATTLE:
                //Use the item in battle
                try{
                    type = Player.inventory.get(num).getType();
                }catch(IndexOutOfBoundsException e){ break; }
                if(type == "w") {
                    battle.attack(Player.inventory.get(num));
                    sleep(1000);
                    battle.enemyAttack();
                }else if(type == "h") {
                    if(Player.health != Player.maxHealth) {
                        Player.health += Player.inventory.get(num).getHeal();
                        if (Player.health >= Player.maxHealth) {
                            Player.health = Player.maxHealth;
                        }
                        Player.inventory.remove(num);
                        battle.update();
                        System.out.println("\nHealed!!");
                        sleep(1000);
                        battle.enemyAttack();
                    }else{
                        battle.update();
                        System.out.println(Colors.RED + "Can't heal at max health!" + Colors.RESET);
                    }
                }
                break;
            case INVENTORY:
                //Use the item in inventory
                try{
                    type = Player.inventory.get(num).getType();
                }catch(IndexOutOfBoundsException e){ break; }
                if(type == "w") {
                    //Print info
                    Main.clearConsole();
                    System.out.println(Colors.RED + "Health: " + Colors.RESET + Player.health + "/" + Player.maxHealth);
                    Player.printInventory(false);
                    System.out.println("\n" + Colors.PURPLE + Player.inventory.get(num).getName() + ":" + Colors.RESET + "\n" + Colors.YELLOW + "Damage: " + Colors.RESET + Player.inventory.get(num).getDamage() +"\n" + Colors.YELLOW + "Accuracy: " + Colors.RESET + Player.inventory.get(num).getHpc());
                }else if(type == "h") {
                    if(Player.health != Player.maxHealth) {
                        int heal = Player.inventory.get(num).getHeal();
                        if ((Player.health + heal) >= Player.maxHealth) {
                            heal = Player.maxHealth - Player.health;
                        }
                        Player.health += heal;
                        Main.clearConsole();

                        System.out.println("You used the " + Colors.PURPLE + Player.inventory.get(num).getName() + "!" + Colors.RESET);
                        System.out.println("You healed " + Colors.GREEN + heal + " health!" + Colors.RESET);
                        Player.inventory.remove(num);
                        System.out.println(Colors.RED + "Health: " + Colors.RESET + Player.health + "/" + Player.maxHealth);
                        Player.printInventory(false);
                    }else{
                        clearConsole();
                        System.out.println(Colors.RED + "Health: " + Colors.RESET + Player.health + "/" + Player.maxHealth);
                        Player.printInventory(false);
                        System.out.println(Colors.RED + "Can't heal at max health!" + Colors.RESET);
                    }
                }
                break;
            case MAP:
                //Do nothing
                break;
            default:
                System.out.println("Invalid mode!");
                break;
        }
    }

    /**
     * Prints a file line by line
     * @param path file path
     * @tag tag indicating portion of file to print
     * @color color of text
     */
    public static void printFromFile(String path, String tag, String color){
        boolean inTag = false;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(line.equals(tag)) {
                    inTag = true;
                    line = br.readLine();
                }else if(line.equals(("!" + tag))){
                    inTag = false;
                }
                if(inTag){
                    switch (color) {
                        case "Red":
                            System.out.println(Colors.RED + line + Colors.RESET);
                            break;
                        case "Blue":
                            System.out.println(Colors.BLUE + line + Colors.RESET);
                            break;
                        case "Green":
                            System.out.println(Colors.GREEN + line + Colors.RESET);
                            break;
                        case "Purple":
                            System.out.println(Colors.PURPLE + line + Colors.RESET);
                            break;
                        case "Yellow":
                            System.out.println(Colors.YELLOW + line + Colors.RESET);
                            break;
                        case "Cyan":
                            System.out.println(Colors.CYAN + line + Colors.RESET);
                            break;
                        case "White":
                            System.out.println(Colors.WHITE + line + Colors.RESET);
                            break;
                        default:
                            break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        printFromFile("src/com/coled/art.txt", "intro", "Red");
        new KeyListenerTester("Key Listener");

        //Below is a example for how to generate a map
        Map.createNewMap("Basic", 10,10, 0);
        //To get the current frame of the map for printing, call this function
        System.out.println(Map.getMapString());
        Item testItem = new Item("Sword",1,0.9);
        Item testItem2 = new Item("Apple", 5);
        Player.inventory.add(testItem2);
        Player.inventory.add(testItem);
        inInventory = false;
    }
}
