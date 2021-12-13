package Rooms;

import processing.core.PApplet;
import processing.core.PVector;

public class Bed {

    private PApplet pApplet;
    public Bed(PApplet pApplet) {
        this.pApplet = pApplet;
    }
    private boolean usable = true;
    private PVector pVector = new PVector(350,250);
    private int bedWidth = 70;
    private int bedHeight = 118;

    public void display(){
        pApplet.fill(255, 6, 6, 0);
        pApplet.rect(pVector.x, pVector.y, bedWidth, bedHeight);
        pApplet.image(pApplet.loadImage("src/Sprites/RoomSprites/BedSprites/Bed.png") , pVector.x, pVector.y);
    }

    public int replenishHp()
    {
        if(usable){
            usable = false;
            return 20;
        }
        else {
            return 0;
        }
    }

    public PVector getpVector() {
        return pVector;
    }

    public int getBedWidth() {
        return bedWidth;
    }

    public int getBedHeight() {
        return bedHeight;
    }

    public boolean isUsable() {
        return usable;
    }
}
