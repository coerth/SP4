package Entitys;

import Interfaces.*;
import Inventory.Inventory;
import processing.core.PApplet;
import processing.core.PVector;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.Locale;

import static java.awt.event.KeyEvent.*;

public class Player extends Entity implements PlayerI, RangedI{

    private Inventory inventory = new Inventory();
    ArrayList<Projectile> list = new ArrayList<>();
    private PImage[] playerImages = new PImage[12];
    private int currentFrame, loopFrames, offSet;
    private int cooldown = 0;

    public Player(PApplet pApplet, int HP, int attack) {
        super(pApplet, HP, attack, new PVector(12,10));
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
        if(dmg < 0){
            throw new ArithmeticException("Damage has to be higher than 0.");
        }
            dmg -= super.getDefense();
        if(super.getDefense() > 0){ //hvis du har defense så taber du en når du tager skade
            super.setDefense(super.getDefense() -1) ;
        }
            super.setHP(super.getHP() - dmg);

    }

    public void processPlayer(){
        cooldownRecovery();
        display();
        attackDirection();
        movement();
        processProjectiles();

    }

    @Override
    public void display() {
        super.getpApplet().image(playerImages[currentFrame + offSet], getpVector().x, getpVector().y);
    }

    public void displayAttack(String direction)
    {
        String s = direction.toLowerCase(Locale.ROOT);
        super.getpApplet().fill(255, 0, 0);
        switch (s)
        {
            case "up":
                super.getpApplet().triangle(getpVector().x, getpVector().y, getpVector().x + getScale() / 2f, getpVector().y - getScale(), getpVector().x + getScale(), getpVector().y);
                break;

            case "down":
                super.getpApplet().triangle(getpVector().x, getpVector().y + getScale(), getpVector().x + getScale() / 2f, getpVector().y + 2* getScale(), getpVector().x + getScale(), getpVector().y + getScale());
                break;

            case "left":
                super.getpApplet().triangle(getpVector().x, getpVector().y, getpVector().x - getScale(), getpVector().y + getScale() / 2f, getpVector().x, getpVector().y + getScale());
                break;

            case "right":
            super.getpApplet().triangle(getpVector().x + getScale(), getpVector().y,getpVector().x + 2* getScale(), getpVector().y + getScale() / 2f, getpVector().x+ getScale(), getpVector().y + getScale() );
            break;
        }
    }



    @Override
    public boolean interact() {
         boolean interaction = false;
        if(super.getpApplet().keyCode == VK_E){

            return interaction = true;
        }
        return interaction;
    }

    @Override
    public void movement()
    {
        if(super.getpApplet().keyCode == VK_W){
            setpVector(new PVector(getpVector().x, getpVector().y-1*getScale())); //gå et felt op af
            this.offSet = 9;
            this.currentFrame = (this.currentFrame + 1) % loopFrames;
        }else if(super.getpApplet().keyCode == VK_S){
            setpVector(new PVector(getpVector().x, getpVector().y+1*getScale()));  //gå et felt ned af
            this.offSet = 0;
            this.currentFrame = (this.currentFrame + 1) % loopFrames;
        }else if(super.getpApplet().keyCode == VK_A){
            setpVector(new PVector(getpVector().x - 1*getScale(), getpVector().y));  //gå et felt til venstre
            this.offSet = 3;
            this.currentFrame = (this.currentFrame + 1) % loopFrames;
        }else if(super.getpApplet().keyCode == VK_D){
            setpVector(new PVector(getpVector().x + 1*getScale(), getpVector().y)); //gå et felt til højre
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
            list.add(new Projectile(super.getpApplet(), new PVector(getpVector().x, getpVector().y), i, getScale()));
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
