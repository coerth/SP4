package Entitys;

import processing.core.PApplet;
import processing.core.PVector;

public abstract class Entity {

    private PApplet pApplet;
    private int HP;
    private int attack;
    private int defense;
    private int scale = 32;
    private PVector currentPvector = new PVector();
    private PVector lastPvector = new PVector();
    private boolean dmgTaken = false;

    public Entity(PApplet pApplet, int HP, int attack, PVector pVector) {
        this.pApplet = pApplet;
        this.HP = HP;
        this.attack = attack;
        this.currentPvector.x = pVector.x * scale;
        this.currentPvector.y = pVector.y * scale;
        this.lastPvector.x = pVector.x * scale;
        this.lastPvector.y = pVector.y * scale;
        //lastPvector = currentPvector;
    }

    public void changeCurrentPvector(int i)
    {

            lastPvector.x = currentPvector.x;
            lastPvector.y = currentPvector.y;

            if (i == 1) {
                currentPvector.y -= scale; //gå op
            } else if (i == 2) {
                currentPvector.y += scale; // gå ned
            } else if (i == 3) {
                currentPvector.x -= scale; // gå til venstre
            } else if (i == 4) {
                currentPvector.x += scale; // gå til højre
            }

    }

    public void revertCurrentPvector()
    {


        currentPvector.x = lastPvector.x;
        currentPvector.y = lastPvector.y;
    }

    public PApplet getpApplet() {
        return pApplet;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }


    public int getScale() {
        return scale;
    }

    public PVector getCurrentPvector() {

        return currentPvector;
    }

/*    public void setCurrentPvector(PVector currentPvector) {
        this.currentPvector = currentPvector;
    }*/

    public void setpApplet(PApplet pApplet) {
        this.pApplet = pApplet;
    }

    public boolean isDmgTaken() {
        return dmgTaken;
    }

    public void setDmgTaken(boolean dmgTaken) {
        this.dmgTaken = dmgTaken;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }
}

