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
            boxDisplay();

        }
        else {
            pApplet.fill(255);
            boxDisplay();
        }
    }

    private void boxDisplay(){
        pApplet.rect(pVector.x,pVector.y, scale,scale);
        /*pApplet.fill(0);
        pApplet.strokeWeight(1);
        pApplet.line(pVector.x,pVector.y,pVector.x+scale,pVector.y);
        pApplet.line(pVector.x,pVector.y,pVector.x,pVector.y+scale);
        pApplet.line(pVector.x,pVector.y+scale,pVector.x+scale,pVector.y+scale);
        pApplet.line(pVector.x+scale,pVector.y,pVector.x+scale,pVector.y+scale);*/
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
