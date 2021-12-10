package Inventory;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import static processing.core.PConstants.CENTER;
import static processing.core.PConstants.SHAPE;

public class Item {

    private PApplet pApplet;
    private String name;
    private int cost;
    private int toGive;
    private PImage icon;
    private PVector pVector;


    public Item(PApplet pApplet, PVector pVector, String name, int cost, int toGive, PImage icon) {
        this.pApplet = pApplet;
        this.pVector = pVector;
        this.name = name;
        this.cost = cost;
        this.toGive = toGive;
        this.icon = icon;
    }

    public void display(){
        pApplet.textMode(SHAPE);
        pApplet.textSize(16);
        pApplet.textAlign(CENTER);
        pApplet.fill(87, 53, 3);
        pApplet.text(name, pVector.x+16, pVector.y-5);
        pApplet.image(icon, pVector.x, pVector.y);
        pApplet.text("Cost: " + cost + " coins", pVector.x+16, pVector.y+45);
    }

    public String getName(){
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getToGive() {
        return toGive;
    }

    public PImage getIcon() {
        return icon;
    }

    public PVector getpVector() {
        return pVector;
    }
}
