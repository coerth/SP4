package Inventory;

import java.util.ArrayList;

public class Inventory {

    private ArrayList<Item> inventoryList = new ArrayList<Item>();
    private int coins;

    public void AddLoot(Item loot){
        
        inventoryList.add(loot);
    }

    public void removeLoot(Item loot){
        
        inventoryList.remove(loot);
    }

    public void AddCoins(int amountOfCoins){

        coins += amountOfCoins;
    }

    public void RemoveCoins(int amountOfCoins){
        
        coins -= amountOfCoins;
    }

    public int getCoins() {
        return coins;
    }
}
