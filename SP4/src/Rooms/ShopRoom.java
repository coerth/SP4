package Rooms;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import Inventory.Item;

import static processing.core.PConstants.SHAPE;

public class ShopRoom extends Room{

    private PImage[] shopItems = new PImage[3];
    private PVector[] itemPos = new PVector[3];
    private Item[] items = new Item[3];

    public ShopRoom(PApplet pApplet) {
        super(pApplet);
        populateShop();
    }

    @Override
    public void display()
    {
        super.display();
        super.getpApplet().textMode(SHAPE);
        super.getpApplet().textSize(60);
        super.getpApplet().textAlign(super.getpApplet().CENTER);
        super.getpApplet().fill(87, 53, 3);
        super.getpApplet().text("ShopRoom", super.getpApplet().width*0.5f, super.getpApplet().height*0.3f);
        DisplayItems();
    }

    private void populateShop(){
        /*for (int i = 0; i < shopItems.length; i++) {
            shopItems[i] = super.getpApplet().loadImage("Sprites/ItemSprites/Item" + super.getpApplet().nf(i+1, 3) + ".png");
            if(count  == 0) {
                itemPos[i] = new PVector(10*getScale() + i * getScale(), 10*getScale());
                count++;
            }
            else{
                itemPos[i] = new PVector(10*getScale() + (count +i)*getScale(), 10*getScale());
                count++;
            }
        }*/
        items[0] = new Item(super.getpApplet(), new PVector(10*getScale(), 10*getScale()), "Attack", 2, 1, super.getpApplet().loadImage("Sprites/ItemSprites/Item001.png"));
        items[1] = new Item(super.getpApplet(), new PVector(10*getScale() + getScale(), 10*getScale()), "Shield", 2, 1, super.getpApplet().loadImage("Sprites/ItemSprites/Item002.png"));
        items[2] = new Item(super.getpApplet(), new PVector(10*getScale() + 2 * getScale(), 10*getScale()), "Hp", 2, 1, super.getpApplet().loadImage("Sprites/ItemSprites/Item003.png"));
    }

    public int BuyItem(){

        return 0;
    }

    public void DisplayItems(){
        for (int i = 0; i < items.length; i++) {
            items[i].display();
        }
    }
}
