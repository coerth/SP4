package Entitys;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Fireball extends Projectile{

    private PImage[] fireballImages = new PImage[8];

    public Fireball(PApplet pApplet, PVector pVector, int direction, int scale) {
        super(pApplet, pVector, direction, scale);
        for (int i = 0; i < fireballImages.length; i++) {
            fireballImages[i] = pApplet.loadImage("Sprites/ProjectileSprites/FireBallSprites/FB-" + (i+1) + ".png");
        }
        super.setProjectileImages(fireballImages);
    }
}
