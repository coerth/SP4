package Entitys;

import Interfaces.EnemiesI;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;
import processing.core.PImage;

import java.util.Random;


public abstract class Enemies extends Entity implements EnemiesI {

    private final int scale = 32;
    private PVector pVector;
    private PImage[] enemyImages = new PImage[12];
    private int currentFrame, loopFrames, offSet;

    public Enemies(PApplet pApplet, int HP, int attack, PVector pVector) {
        super(pApplet, HP, attack);
        super.setDefense(0); // setting defense to zero from the start
        this.pVector = pVector;
        this.currentFrame = 0;
        this.loopFrames = 3;
        this.offSet = 0;
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
        super.setHP(super.getHP() - dmg);
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
    public void display()
    {
            super.getpApplet().fill(255,0,0,0);
            super.getpApplet().ellipseMode(PConstants.CORNER);
            super.getpApplet().ellipse(pVector.x, pVector.y, scale, scale);
           super.getpApplet().image(enemyImages[currentFrame + offSet], pVector.x, pVector.y);

    }

    @Override
    public void movement() {

        //Sætter vores enemies til at gå et felt i random retninger
        Random random = new Random();
        int i = random.nextInt(200) + 1;


        switch (i) {
            case 1:
                pVector.x = pVector.x + 1 * scale;
                this.offSet = 6;
                this.currentFrame = (this.currentFrame + 1) % loopFrames;
                break;
            case 2:
                pVector.y = pVector.y + 1 * scale;
                this.offSet = 0;
                this.currentFrame = (this.currentFrame + 1) % loopFrames;
                break;
            case 3:
                pVector.x = pVector.x - 1 * scale;
                this.offSet = 3;
                this.currentFrame = (this.currentFrame + 1) % loopFrames;
                break;
            case 4:
                pVector.y = pVector.y - 1 * scale;
                this.offSet = 9;
                this.currentFrame = (this.currentFrame + 1) % loopFrames;
                break;
        }


    }
    public void enemyBoundaries(){
        //Holder fjernerne indenfor spillets vægge
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

    public PVector getpVector() {
        return this.pVector;
    }

    public void setpVector(PVector pVector) {
        this.pVector = pVector;
    }

    public int getScale() {
        return this.scale;
    }

    public PImage[] getEnemyImages() {
        return enemyImages;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public int getOffSet() {
        return offSet;
    }

    public void setEnemyImages(PImage[] enemyImages) {
        this.enemyImages = enemyImages;
    }
}
