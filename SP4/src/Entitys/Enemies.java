package Entitys;

import Interfaces.EnemiesI;
import processing.core.PApplet;


public abstract class Enemies extends Entity implements EnemiesI {

    public Enemies( PApplet pApplet, int HP, int attack) {
        super(pApplet, HP ,attack);
        super.setDefense(0); // setting defense to zero from the start
    }

    @Override
    public int attack() {

        return super.getAttack();
    }

    @Override
    public void takeDMG(int dmg) {
        //damage has to be a positive number
        if(dmg < 0){
            throw new ArithmeticException("Damage has to be higher than 0.");
        }
        super.setHP(super.getHP() - dmg);
    }

    public int DropCoins(){

        return 0;
    }

    @Override
    public void display() {

    }

    @Override
    public void movement() {

    }

}
