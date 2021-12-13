package Entitys;

import Interfaces.RangedI;
import processing.core.PApplet;
import processing.core.PVector;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.Random;

public class Wizard extends RangedEnemy {

    private PImage[] wizardImages = new PImage[12];

    public Wizard(PApplet pApplet,int difficulty, PVector pVector) {
        super(pApplet, (difficulty*2)+6, difficulty+3, difficulty, pVector);
        for (int i = 0; i < wizardImages.length; i++) {
            wizardImages[i] = pApplet.loadImage("Sprites/EnemySprites/Enemy3/tile" + PApplet.nf(i, 3) + ".png");
        }
        setEnemyImages(wizardImages);
    }
}
