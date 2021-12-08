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

    public void AddCoins(){
        coins++;
    }

    public void RemoveCoins(){
        
        coins--;
    }

}
