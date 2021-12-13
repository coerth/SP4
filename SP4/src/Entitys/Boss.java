package Entitys;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.util.Random;

public class Boss extends RangedEnemy{

    private PImage[] bossImages = new PImage[12];
    private int originalScale = 32;

    public Boss(PApplet pApplet,int difficulty, PVector pVector) {
        super(pApplet, (difficulty*5)+40, (difficulty*2)+5, difficulty, pVector);
        for (int i = 0; i < bossImages.length; i++) {
            bossImages[i] = pApplet.loadImage("src/Sprites/EnemySprites/BossSprites/tile" + pApplet.nf(i, 3) + ".png");

        }
        setOffSet(0);
        setEnemyImages(bossImages);
        setScale(96);
    }

    @Override
    public void projectileBoundary(){ //når projectiles er uden for rammerne så skal de slettes
        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).getpVector().x < 0) {
                list.remove(i);
            }
            else if (list.get(i).getpVector().y < 0) {
                list.get(i).getpVector().y = 0;
            }
            else if (list.get(i).getpVector().x > getpApplet().width - originalScale) {
                list.remove(i);
            }
            else if (list.get(i).getpVector().y > getpApplet().height - originalScale) {
                list.remove(i);
            }
        }
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
                    list.add(new Projectile(super.getpApplet(), new PVector(super.getCurrentPvector().x+ originalScale, super.getCurrentPvector().y + originalScale), i, originalScale)); // midt
                    list.add(new Projectile(super.getpApplet(), new PVector(super.getCurrentPvector().x, super.getCurrentPvector().y+(2*originalScale)), i, originalScale)); //venstre
                    list.add(new Projectile(super.getpApplet(), new PVector(super.getCurrentPvector().x+(2*originalScale), super.getCurrentPvector().y+(2*originalScale)), i, originalScale)); //højre
                    setCooldown(80);
                }
               else if(i == 2)
               {
                    list.add(new Projectile(super.getpApplet(), new PVector(super.getCurrentPvector().x + originalScale, super.getCurrentPvector().y +originalScale), i, originalScale)); //midt
                    list.add(new Projectile(super.getpApplet(), new PVector(super.getCurrentPvector().x, super.getCurrentPvector().y), i, originalScale)); //venstre
                    list.add(new Projectile(super.getpApplet(), new PVector(super.getCurrentPvector().x+(2*originalScale), super.getCurrentPvector().y), i, originalScale)); //højre
                    setCooldown(80);
                }
               else if(i == 3)
               {
                    list.add(new Projectile(super.getpApplet(), new PVector(super.getCurrentPvector().x+ originalScale , super.getCurrentPvector().y + originalScale), i, originalScale)); //midt
                    list.add(new Projectile(super.getpApplet(), new PVector(super.getCurrentPvector().x+(2*originalScale), super.getCurrentPvector().y), i, originalScale)); //øverst
                    list.add(new Projectile(super.getpApplet(), new PVector(super.getCurrentPvector().x+(2*originalScale), super.getCurrentPvector().y+(2*originalScale)), i, originalScale)); //nederst
                    setCooldown(80);
                }
                else
                {
                    list.add(new Projectile(super.getpApplet(), new PVector(super.getCurrentPvector().x+ originalScale, super.getCurrentPvector().y+ originalScale), i, originalScale)); //midt
                    list.add(new Projectile(super.getpApplet(), new PVector(super.getCurrentPvector().x, super.getCurrentPvector().y), i, originalScale)); //øverst
                    list.add(new Projectile(super.getpApplet(), new PVector(super.getCurrentPvector().x, super.getCurrentPvector().y+(2*originalScale)), i, originalScale)); //nederst
                    setCooldown(80);
                }
            }
        }
    }
}
