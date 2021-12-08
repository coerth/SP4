package Rooms;

import Inventory.Loot;
import processing.core.PApplet;

import java.util.ArrayList;

public class ShopRoom extends Room{
    private ArrayList<Loot> shopItems = new ArrayList<>();

    public ShopRoom(PApplet pApplet) {
        super(pApplet);
    }

    public Loot BuyItem(){
        return null;
    }

    public void DisplayItems(){

    }
}
