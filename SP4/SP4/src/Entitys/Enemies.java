package Entitys;

import Interfaces.EnemiesI;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;


public abstract class Enemies extends Entity implements EnemiesI {

    private final int scale = 32;
    private PVector pVector;

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
        super.getpApplet().fill(255);
        super.getpApplet().ellipseMode(PConstants.CORNER);
        super.getpApplet().ellipse(pVector.x, pVector.y, scale, scale);

    }

    @Override
    public void movement() {

    }

    public PVector getPvector()
    {
        return this.pVector;
    }

    public void setpVector(PVector pVector)
    {
        this.pVector = pVector;
    }

    public int getScale(){
        return this.scale;
    }

}
