package Entitys;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.util.Random;

public abstract class BossEnemy extends RangedEnemy{


    private int originalScale = 32;

    public BossEnemy(PApplet pApplet, int difficulty, PVector pVector) {
        super(pApplet, (difficulty*5)+40, (difficulty*2)+5, difficulty, pVector);
        //setOffSet(0);
        setScale(96); //bosserne er større så de bevæger med med store skala
    }

    @Override
    public void projectileBoundary(){ //når projectiles er uden for rammerne så skal de slettes
        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).getpVector().x < 0) {
                list.remove(i);
            }
            else if (list.get(i).getpVector().y < 0) {
                list.remove(i);
            }
            else if (list.get(i).getpVector().x > getpApplet().width - originalScale) {
                list.remove(i);
            }
            else if (list.get(i).getpVector().y > getpApplet().height - originalScale) {
                list.remove(i);
            }
        }
    }

    public int getOriginalScale() {
        return originalScale;
    }
}
