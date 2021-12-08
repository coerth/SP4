package Entitys;

import processing.core.PApplet;
import processing.core.PVector;

public class Projectile {

    PApplet pApplet;
    PVector pVector;
    final int  direction;
    int scale;

    public Projectile(PApplet pApplet,PVector pVector, int direction, int scale) {
        this.pVector = pVector;
        this.direction = direction;
        this.scale = scale;
        this.pApplet = pApplet;
    }

    public void projectileTrajectory(){
        switch (direction) {
            case 1 -> pVector.y -= scale; //pilen går op
            case 2 -> pVector.y += scale;//pilen går ned
            case 3 -> pVector.x -= scale; //pilen går venstre
            case 4 -> pVector.x += scale; //pilen går højre
        }
    }

    public void display(){
        pApplet.fill(0);
        pApplet.triangle(pVector.x + scale/2, pVector.y, pVector.x + scale, pVector.y + scale / 2f,pVector.x + scale/2f, pVector.y + scale );
        pApplet.line(pVector.x, pVector.y + scale /2f, pVector.x + scale, pVector.y + scale /2f);
    }


    public PVector getpVector() {
        return pVector;
    }
}
