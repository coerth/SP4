package Rooms;

import processing.core.PApplet;
import processing.core.PVector;

public class EscapeHatch {

    private PApplet pApplet;
    private final int scale = 32;
    private PVector pVector = new PVector(12*scale, 10*scale);
    private boolean isOpen = false;

    public EscapeHatch(PApplet pApplet) {
        this.pApplet = pApplet;
    }

    public void display(){

        if(!isOpen){
            pApplet.fill(38, 16, 0);
            pApplet.rect(pVector.x,pVector.y, scale,scale);
        }
        else {
            pApplet.fill(255);
            pApplet.rect(pVector.x,pVector.y, scale,scale);
        }
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public PVector getpVector() {
        return pVector;
    }
}
