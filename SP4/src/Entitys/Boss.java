package Entitys;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.Random;

public class Boss extends RangedEnemy{

    public Boss(PApplet pApplet,int difficulty, PVector pVector) {
        super(pApplet, 40, difficulty+5, pVector);

    }

    @Override
    public void shootProjectile(int i)
    {
        Random rand = new Random();
        int j = rand.nextInt(500);

        if(getCooldown() > 0) //hvis cooldown for sidste skud ikke er klaret så sker der ikke noget
        {
            return;
        }
        else
        {
            if(j < 10) { //hvis det tilfældige tal er under 10 så skyd
                if(i == 1)
                {
                    list.add(new Projectile(super.getpApplet(), new PVector(super.getCurrentPvector().x, super.getCurrentPvector().y), i, super.getScale()));
                    list.add(new Projectile(super.getpApplet(), new PVector(super.getCurrentPvector().x-getScale(), super.getCurrentPvector().y-getScale()), i, super.getScale()));
                    list.add(new Projectile(super.getpApplet(), new PVector(super.getCurrentPvector().x+getScale(), super.getCurrentPvector().y-getScale()), i, super.getScale()));
                    setCooldown(80);
                }
               else if(i == 2)
               {
                    list.add(new Projectile(super.getpApplet(), new PVector(super.getCurrentPvector().x, super.getCurrentPvector().y), i, super.getScale()));
                    list.add(new Projectile(super.getpApplet(), new PVector(super.getCurrentPvector().x-getScale(), super.getCurrentPvector().y+getScale()), i, super.getScale()));
                    list.add(new Projectile(super.getpApplet(), new PVector(super.getCurrentPvector().x+getScale(), super.getCurrentPvector().y+getScale()), i, super.getScale()));
                    setCooldown(80);
                }
               else if(i == 3)
               {
                    list.add(new Projectile(super.getpApplet(), new PVector(super.getCurrentPvector().x, super.getCurrentPvector().y), i, super.getScale()));
                    list.add(new Projectile(super.getpApplet(), new PVector(super.getCurrentPvector().x+getScale(), super.getCurrentPvector().y-getScale()), i, super.getScale()));
                    list.add(new Projectile(super.getpApplet(), new PVector(super.getCurrentPvector().x+getScale(), super.getCurrentPvector().y+getScale()), i, super.getScale()));
                    setCooldown(80);
                }
                else
                {
                    list.add(new Projectile(super.getpApplet(), new PVector(super.getCurrentPvector().x, super.getCurrentPvector().y), i, super.getScale()));
                    list.add(new Projectile(super.getpApplet(), new PVector(super.getCurrentPvector().x-getScale(), super.getCurrentPvector().y-getScale()), i, super.getScale()));
                    list.add(new Projectile(super.getpApplet(), new PVector(super.getCurrentPvector().x-getScale(), super.getCurrentPvector().y+getScale()), i, super.getScale()));
                    setCooldown(80);
                }
            }
        }
    }

    @Override
    public void display()
    {
        //super.display();
        getpApplet().fill(0,255,0);
        getpApplet().rect(getCurrentPvector().x, getCurrentPvector().y, getScale(),getScale());
    }
}
