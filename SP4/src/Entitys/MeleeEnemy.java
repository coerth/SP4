package Entitys;

import Interfaces.MeleeI;
import processing.core.PApplet;
import processing.core.PVector;

public abstract class MeleeEnemy extends Enemies implements MeleeI {

    public MeleeEnemy(PApplet pApplet, int HP, int attack, PVector pVector) {
        super(pApplet, HP, attack, pVector);
    }



}
