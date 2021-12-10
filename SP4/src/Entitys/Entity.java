package Entitys;

import processing.core.PApplet;
import processing.core.PVector;

public abstract class Entity {

    private PApplet pApplet;
    private int HP;
    private int attack;
    private int defense;
    private final int scale = 32;
    private PVector pVector = new PVector();

    public Entity(PApplet pApplet, int HP, int attack, PVector pVector) {
        this.pApplet = pApplet;
        this.HP = HP;
        this.attack = attack;
        this.pVector.x = pVector.x * scale;
        this.pVector.y = pVector.y * scale;
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

    public PVector getpVector() {
        return pVector;
    }

    public void setpVector(PVector pVector) {
        this.pVector = pVector;
    }
}
