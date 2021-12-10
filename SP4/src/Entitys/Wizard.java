package Entitys;

import Interfaces.RangedI;
import processing.core.PApplet;
import processing.core.PVector;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.Random;

public class Wizard extends RangedEnemy {

    private PImage[] wizardImages = new PImage[12];

    public Wizard(PApplet pApplet, PVector pVector) {
        super(pApplet, 6, 3, pVector);
        for (int i = 0; i < wizardImages.length; i++) {
            wizardImages[i] = pApplet.loadImage("Sprites/EnemySprites/ArcherSprites/tile" + PApplet.nf(i, 3) + ".png");
        }
        setEnemyImages(wizardImages);
    }
}
