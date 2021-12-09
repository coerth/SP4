package Entitys;

import processing.core.PApplet;
import processing.core.PVector;
import processing.core.PImage;

public class Projectile {

    private PApplet pApplet;
    private PVector pVector;
    private PImage[] projectileImages = new PImage[8];
    private final int  direction;
    private int currentFrameProjectile, loopFramesProjectile, offSetProjectile;

    private int scale;

    public Projectile(PApplet pApplet,PVector pVector, int direction, int scale) {
        this.pVector = pVector;
        this.direction = direction;
        this.scale = scale;
        this.pApplet = pApplet;
        for (int i = 0; i < projectileImages.length; i++) {
            projectileImages[i] = pApplet.loadImage("Sprites/ProjectileSprites/FireBallSprites/FB-" + (i+1) + ".png");
        }
        this.currentFrameProjectile = 0;
        this.loopFramesProjectile = 5;
        this.offSetProjectile = 0;
    }

    public void projectileTrajectory(){
        switch (direction) {
            case 1:
                pVector.y -= scale; //pilen går op
                this.offSetProjectile = 0;
                this.currentFrameProjectile = (this.currentFrameProjectile + 1)%this.loopFramesProjectile;
                break;
            case 2:
                pVector.y += scale;//pilen går ned
                this.offSetProjectile = 0;
                this.currentFrameProjectile = (this.currentFrameProjectile + 1)%this.loopFramesProjectile;
                break;
            case 3:
                pVector.x -= scale; //pilen går venstre
                this.offSetProjectile = 0;
                this.currentFrameProjectile = (this.currentFrameProjectile + 1)%this.loopFramesProjectile;
                break;
            case 4:
                pVector.x += scale; //pilen går højre
                this.offSetProjectile = 0;
                this.currentFrameProjectile = (this.currentFrameProjectile + 1)%this.loopFramesProjectile;
                break;
        }
    }

    public void display(){

       pApplet.image(projectileImages[currentFrameProjectile + offSetProjectile], pVector.x, pVector.y);
    }


    public PVector getpVector() {
        return pVector;
    }

    public int getDirection() {
        return direction;
    }
}
