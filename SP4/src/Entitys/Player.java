package Entitys;

import Interfaces.*;
import Inventory.Inventory;
import processing.core.PApplet;
import processing.core.PVector;
import processing.core.PImage;

import java.util.ArrayList;

import static java.awt.event.KeyEvent.*;

public class Player extends Entity implements PlayerI{

    private Inventory inventory = new Inventory();
    ArrayList<Projectile> list = new ArrayList<>();
    private PImage[] playerImages = new PImage[12];
    private int currentFrame, loopFrames, offSet;
    private int cooldown = 0;

    public Player(PApplet pApplet) {
        super(pApplet, 50, 4, new PVector(12,10));
        super.setDefense(0);
        for (int i = 0; i < playerImages.length; i++) {
            playerImages[i] = pApplet.loadImage("Sprites/PlayerSprites/tile" + PApplet.nf(i, 3) + ".png");
        }
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
        dmg -= super.getDefense();

        if(dmg < 0){
            //throw new ArithmeticException("Damage has to be higher than 0.");
            dmg = 1;
        }
            super.setHP(super.getHP() - dmg);
            super.setDmgTaken(true);

    }

    public void processPlayer(){ //samlet funktion for alle spillerens funktioner
        cooldownRecovery();
        attackDirection();
        movement();
        display();
        processProjectiles();

    }

    @Override
    public void display() {

        if(isDmgTaken())
        {
            super.getpApplet().tint(255,0,0); //farves rød
            super.getpApplet().image(playerImages[currentFrame + offSet], getCurrentPvector().x, getCurrentPvector().y);
            setDmgTaken(!isDmgTaken());
        }
        else
        {
            super.getpApplet().noTint();
            super.getpApplet().image(playerImages[currentFrame + offSet], getCurrentPvector().x, getCurrentPvector().y);
        }


    }

    @Override
    public boolean interact() { //bruges til shop og rest room
         boolean interaction = false;
        if(super.getpApplet().keyCode == VK_E){

            return interaction = true;
        }
        return interaction;
    }

    @Override
    public void movement()
    {
        if(super.getpApplet().keyCode == VK_W){ //gå op
            super.changeCurrentPvector(1);
            this.offSet = 9;
            this.currentFrame = (this.currentFrame + 1) % loopFrames;

        }else if(super.getpApplet().keyCode == VK_S){ //gå ned
            super.changeCurrentPvector(2);
            this.offSet = 0;
            this.currentFrame = (this.currentFrame + 1) % loopFrames;

        }else if(super.getpApplet().keyCode == VK_A){ //gå til venstre
            super.changeCurrentPvector(3);
            this.offSet = 3;
            this.currentFrame = (this.currentFrame + 1) % loopFrames;

        }else if(super.getpApplet().keyCode == VK_D){ //gå til højre
            super.changeCurrentPvector(4);
            this.offSet = 6;
            this.currentFrame = (this.currentFrame + 1) % loopFrames;
        }

        super.getpApplet().keyCode = VK_BACK_SLASH; //keycode skal "cleares",
        //ellers vil spilleren fortsætte i en retning.
    }

    public void processProjectiles(){ //samling af projectile funktioner

        if(list.size() > 0){
            for(Projectile p : list){
                p.projectileTrajectory();
                p.display();
            }
            projectileBoundary();
        }
    }

    private void attackDirection() { //skyd i en given retning
        int i;
        if (cooldown > 0) { //hvis cooldown for sidste skud ikke er klaret så sker der ikke noget
            return;

        } else {
            if (getpApplet().keyCode == VK_UP) { //skyd op
                i = 1;
                shootProjectile(i);
                cooldown = 50;

            } else if (getpApplet().keyCode == VK_DOWN) { //skyd ned
                i = 2;
                shootProjectile(i);
                cooldown = 50;

            } else if (getpApplet().keyCode == VK_LEFT) { //skyd venstre
                i = 3;
                shootProjectile(i);
                cooldown = 50;

            } else if (getpApplet().keyCode == VK_RIGHT) { //skyd højre
                i = 4;
                shootProjectile(i);
                cooldown = 50;
            }
        }
    }


    public void shootProjectile(int i) //skyd i den retning i indikerer
    {
            list.add(new Projectile(super.getpApplet(), new PVector(getCurrentPvector().x, getCurrentPvector().y), i, getScale()));
    }

        private void cooldownRecovery() //nedtælling til der kan skydes igen
        {
            if(cooldown > 0)
            {
                cooldown--;
            }
        }


    public void projectileBoundary(){ //når projectiles er uden for rammerne så skal de slettes
        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).getpVector().x < 0) {
                list.remove(i);
            }
            else if (list.get(i).getpVector().y < 0) {
                list.get(i).getpVector().y = 0;
            }
            else if (list.get(i).getpVector().x > getpApplet().width - getScale()) {
                list.remove(i);
            }
            else if (list.get(i).getpVector().y > getpApplet().height - getScale()) {
                list.remove(i);
            }
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

    public ArrayList<Projectile> getList() {
        return list;
    }
}
