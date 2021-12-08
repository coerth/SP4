package Rooms;

import Inventory.Loot;
import processing.core.PApplet;

import java.util.ArrayList;

import static processing.core.PConstants.SHAPE;

public class ShopRoom extends Room{

    @Override
    public void display()
    {
        super.display();
        super.getpApplet().textMode(SHAPE);
        super.getpApplet().textSize(60);
        super.getpApplet().textAlign(super.getpApplet().CENTER);
        super.getpApplet().fill(87, 53, 3);
        super.getpApplet().text("ShopRoom", super.getpApplet().width*0.5f, super.getpApplet().height*0.3f);
    }

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
