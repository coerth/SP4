package Entitys;

import Interfaces.*;
import Inventory.Inventory;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

import static java.awt.event.KeyEvent.*;

public class Player extends Entity implements PlayerI{

    private Inventory inventory = new Inventory();
    ArrayList<Projectile> list = new ArrayList<>();
    private int scale = 32;
    private PVector pVector;

    public Player(PApplet pApplet, int HP, int attack) {
        super(pApplet, HP, attack);
        this.pVector = new PVector(12*scale,10*scale);
        super.setDefense(0);
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
            super.setHP(super.getHP() - dmg);

    }

    public void processPlayer(){
        display();
        shootArrow();
        movement();
        processProjectiles();

    }

    @Override
    public void display() {
        super.getpApplet().fill(255, 0, 0);
        super.getpApplet().rect(pVector.x, pVector.y, scale, scale);
    }

    public void displayAttack(String direction)
    {
        String s = direction.toLowerCase(Locale.ROOT);
        super.getpApplet().fill(255, 0, 0);
        switch (s)
        {
            case "up":
                super.getpApplet().triangle(pVector.x, pVector.y, pVector.x + scale / 2f, pVector.y - scale, pVector.x + scale, pVector.y);
                break;

            case "down":
                super.getpApplet().triangle(pVector.x, pVector.y + scale, pVector.x + scale / 2f, pVector.y + 2* scale, pVector.x + scale, pVector.y + scale);
                break;

            case "left":
                super.getpApplet().triangle(pVector.x, pVector.y, pVector.x - scale, pVector.y + scale / 2f, pVector.x, pVector.y + scale);
                break;

            case "right":
            super.getpApplet().triangle(pVector.x + scale, pVector.y,pVector.x + 2* scale, pVector.y + scale / 2f, pVector.x+ scale, pVector.y + scale );
            break;
        }
    }



    @Override
    public void interact() {

    }

    @Override
    public void movement()
    {
        if(super.getpApplet().keyCode == VK_W){
            pVector = new PVector(pVector.x, pVector.y-1*scale); //gå et felt op af
        }else if(super.getpApplet().keyCode == VK_S){
            pVector = new PVector(pVector.x, pVector.y+1*scale);  //gå et felt ned af
        }else if(super.getpApplet().keyCode == VK_A){
            pVector = new PVector(pVector.x - 1*scale, pVector.y);  //gå et felt til venstre
        }else if(super.getpApplet().keyCode == VK_D){
            pVector = new PVector(pVector.x + 1*scale, pVector.y); //gå et felt til højre
        }

        super.getpApplet().keyCode = VK_BACK_SLASH; //keycode skal "cleares",
        //ellers vil spilleren fortsætte i en retning.
    }

    public void processProjectiles(){
        if(list.size() > 0){
            for(Projectile p : list){
                p.projectileTrajectory();
                p.display();
            }
            projectileBoundary();
        }
    }

    public void shootArrow(){
        int i;
        if(getpApplet().keyCode == VK_I)
        {
            i = 1;
            list.add(new Projectile(super.getpApplet(), new PVector(pVector.x, pVector.y), i, scale));
        }
        else if(getpApplet().keyCode == VK_K)
        {
            i = 2;
            list.add(new Projectile(super.getpApplet(), new PVector(pVector.x, pVector.y), i, scale));
        }
        else if(getpApplet().keyCode == VK_J)
        {
            i = 3;
            list.add(new Projectile(super.getpApplet(), new PVector(pVector.x, pVector.y), i, scale));
        }
        else if(getpApplet().keyCode == VK_L)
        {
            i = 4;
            list.add(new Projectile(super.getpApplet(), new PVector(pVector.x, pVector.y), i, scale));
        }

        }


    public void projectileBoundary(){
        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).getpVector().x < 0) {
                list.remove(i);
            }
            else if (list.get(i).getpVector().y < 0) {
                list.get(i).getpVector().y = 0;
            }
            else if (list.get(i).getpVector().x > getpApplet().width - scale) {
                list.remove(i);
            }
            else if (list.get(i).getpVector().y > getpApplet().height - scale) {
                list.remove(i);
            }
        }
    }

    public int getScale() {
        return scale;
    }

    public PVector getpVector() {
        return pVector;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setpVector(PVector pVector) {
        this.pVector = pVector;
    }

    public ArrayList<Projectile> getList() {
        return list;
    }
}
