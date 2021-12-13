package Entitys;

import Interfaces.MeleeI;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Skeleton extends MeleeEnemy {

    private PImage[] gargoyleImages = new PImage[12];

    public Skeleton(PApplet pApplet, int difficulty, PVector pVector) {
        super(pApplet, (difficulty*2)+6, difficulty+2,difficulty, pVector);
        for (int i = 0; i < super.getEnemyImages().length; i++) {
            gargoyleImages[i] = pApplet.loadImage("Sprites/EnemySprites/Enemy1/tile" + PApplet.nf(i, 3) + ".png");
        }
        setEnemyImages(gargoyleImages);
    }

//    @Override
//    public void display(){
//        super.getpApplet().image(getEnemyImages()[getCurrentFrame() + getOffSet()], getpVector().x, getpVector().y);
//    }




}
