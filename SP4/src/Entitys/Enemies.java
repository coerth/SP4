package Entitys;

import Interfaces.EnemiesI;
import processing.core.PApplet;
import processing.core.PVector;
import processing.core.PImage;

import java.util.Random;


public abstract class Enemies extends Entity implements EnemiesI {

    private PImage[] enemyImages = new PImage[12];
    private int currentFrame, loopFrames, offSet;
    private int difficulty;

    public Enemies(PApplet pApplet, int HP, int attack, int difficulty , PVector pVector) {
        super(pApplet, HP, attack,pVector);
        this.difficulty = difficulty;
        super.setDefense(0); // setting defense to zero from the start
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
        setDmgTaken(true);

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
        if(isDmgTaken())
        {
            super.getpApplet().tint(255,0,0); //farves rød
            super.getpApplet().image(enemyImages[currentFrame + offSet], getCurrentPvector().x, getCurrentPvector().y);
            setDmgTaken(!isDmgTaken());
        }
        else
        {
            super.getpApplet().noTint();
            super.getpApplet().image(enemyImages[currentFrame + offSet], getCurrentPvector().x, getCurrentPvector().y);
        }


    }

    @Override
    public void movement() {

        //Sætter vores enemies til at gå et felt i random retninger
        Random random = new Random();
        int i = random.nextInt(200) + 1;



        switch (i) {
            case 1:
                super.changeCurrentPvector(i); //gå op
                this.offSet = 9;
                this.currentFrame = (this.currentFrame + 1) % loopFrames;
                break;

            case 2:
                super.changeCurrentPvector(i); //gå ned
                this.offSet = 0;
                this.currentFrame = (this.currentFrame + 1) % loopFrames;
                break;
            case 3:
                super.changeCurrentPvector(i);  //gå venstre
                this.offSet = 3;
                this.currentFrame = (this.currentFrame + 1) % loopFrames;
                break;
            case 4:
                super.changeCurrentPvector(i);  //gå højre
                this.offSet = 6;
                this.currentFrame = (this.currentFrame + 1) % loopFrames;
                break;
        }

//        switch (i) {
//            case 1:
//                getCurrentPvector().x = getCurrentPvector().x + 1 * getScale();
//                this.offSet = 6;
//                this.currentFrame = (this.currentFrame + 1) % loopFrames;
//                break;
//            case 2:
//                getCurrentPvector().y = getCurrentPvector().y + 1 * getScale();
//                this.offSet = 0;
//                this.currentFrame = (this.currentFrame + 1) % loopFrames;
//                break;
//            case 3:
//                getCurrentPvector().x = getCurrentPvector().x - 1 * getScale();
//                this.offSet = 3;
//                this.currentFrame = (this.currentFrame + 1) % loopFrames;
//                break;
//            case 4:
//                getCurrentPvector().y = getCurrentPvector().y - 1 * getScale();
//                this.offSet = 9;
//                this.currentFrame = (this.currentFrame + 1) % loopFrames;
//                break;
//        }

    }
    public void enemyBoundaries(){
        //Holder fjernerne indenfor spillets vægge
        if (getCurrentPvector().x < 0 ) {
            //getCurrentPvector().x=0;
            super.revertCurrentPvector();

        }if (getCurrentPvector().y <0) {
            //getCurrentPvector().y=0;
            super.revertCurrentPvector();

        }if( getCurrentPvector().x > getpApplet().width-getScale()){
            //getCurrentPvector().x= getpApplet().width -getScale();
            super.revertCurrentPvector();

        }if( getCurrentPvector().y > getpApplet().height-getScale()){
            //getCurrentPvector().y = getpApplet().height -getScale();
            super.revertCurrentPvector();
        }
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

    public void setOffSet(int offSet) {
        this.offSet = offSet;
    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    public int getLoopFrames() {
        return loopFrames;
    }

    public void setEnemyImages(PImage[] enemyImages) {
        this.enemyImages = enemyImages;
    }
}
