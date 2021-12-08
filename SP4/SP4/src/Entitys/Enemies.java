package Entitys;

import Interfaces.EnemiesI;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

import java.util.Random;


public abstract class Enemies extends Entity implements EnemiesI {

    private final int scale = 32;
    private PVector pVector;
    private boolean dmgtaken = false;

    public Enemies(PApplet pApplet, int HP, int attack, PVector pVector) {
        super(pApplet, HP, attack);
        super.setDefense(0); // setting defense to zero from the start
        this.pVector = pVector;
    }

    @Override
    public int attack() {

        return super.getAttack();
    }

    @Override
    public void takeDMG(int dmg) {
        //damage has to be a positive number
        if (dmg < 0) {
            throw new ArithmeticException("Damage has to be higher than 0.");
        }
        System.out.println(super.getHP() + " - " + dmg);
        super.setHP(super.getHP() - dmg);
        dmgtaken = true;
    }

    public int DropCoins() {

        return 2;
    }
    public void processEnemy(){
        movement();
        enemyBoundaries();
        display();
    }


    @Override
    public void display() {
        if(dmgtaken)
        {
            super.getpApplet().fill(255,0,0);
            super.getpApplet().ellipseMode(PConstants.CORNER);
            super.getpApplet().ellipse(pVector.x, pVector.y, scale, scale);
            dmgtaken = false;
        }
        else
        {
            super.getpApplet().fill(255);
            super.getpApplet().ellipseMode(PConstants.CORNER);
            super.getpApplet().ellipse(pVector.x, pVector.y, scale, scale);
        }

    }

    @Override
    public void movement() {

        Random random = new Random();
        int i = random.nextInt(200) + 1;


        switch (i) {
            case 1:
                pVector.x = pVector.x + 1 * scale;
                break;
            case 2:
                pVector.y = pVector.y + 1 * scale;
                break;
            case 3:
                pVector.x = pVector.x - 1 * scale;
                break;
            case 4:
                pVector.y = pVector.y - 1 * scale;
                break;
        }


    }
    public void enemyBoundaries(){
        if (pVector.x < 0 ) {
            pVector.x=0;

        }if (pVector.y <0) {
            pVector.y=0;

        }if( pVector.x > getpApplet().width-scale){
            pVector.x= getpApplet().width -scale;

        }if( pVector.y > getpApplet().height-scale){
            pVector.y = getpApplet().height -scale;
        }
    }

    public PVector getPvector() {
        return this.pVector;
    }

    public void setpVector(PVector pVector) {
        this.pVector = pVector;
    }

    public int getScale() {
        return this.scale;
    }

}
