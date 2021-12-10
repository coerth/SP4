package Rooms;

import processing.core.PApplet;
import processing.core.PVector;

public class Bed {

    private PApplet pApplet;
    public Bed(PApplet pApplet) {
        this.pApplet = pApplet;
    }
    private boolean usable = true;
    private PVector pVector = new PVector(300,250);
    private int bedWidth = 200;
    private int bedHeight = 100;

    public void display(){
        pApplet.fill(255, 6, 6);
        pApplet.rect(pVector.x, pVector.y, bedWidth, bedHeight);;
    }

    public int replenishHp()
    {
        if(usable){
            usable = false;
            return 10;
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
