package Entitys;

import processing.core.PApplet;

public abstract class Entity {

    private PApplet pApplet;
    private int HP;
    private int attack;
    private int defense;


    public Entity(PApplet pApplet, int HP, int attack) {
        this.pApplet = pApplet;
        this.HP = HP;
        this.attack = attack;
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
}
