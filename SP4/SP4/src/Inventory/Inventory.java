package Inventory;

import java.util.ArrayList;

public class Inventory {

    private ArrayList<Loot> inventoryList = new ArrayList<Loot>();
    private int coins;

    public void AddLoot(Loot loot){
        
        inventoryList.add(loot);
    }

    public void removeLoot(Loot loot){
        
        inventoryList.remove(loot);
    }

    public void AddCoins(int amountOfCoins){

        coins += amountOfCoins;
        System.out.println(coins);
    }

    public void RemoveCoins(int amountOfCoins){
        
        coins =- amountOfCoins;
    }

}
