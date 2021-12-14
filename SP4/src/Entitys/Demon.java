package Entitys;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.util.Random;

public class Demon extends BossEnemy {

    private PImage[] demonImages = new PImage[12];

    public Demon(PApplet pApplet, int difficulty, PVector pVector)
    {
        super(pApplet, difficulty, pVector);
        for (int i = 0; i < demonImages.length; i++) {
            demonImages[i] = pApplet.loadImage("src/Sprites/EnemySprites/BossSprites/tile" + pApplet.nf(i, 3) + ".png");
        }
        setEnemyImages(demonImages);
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
                    list.add(new Fireball(super.getpApplet(), new PVector(super.getCurrentPvector().x+ getOriginalScale(), super.getCurrentPvector().y + getOriginalScale()), i, getOriginalScale())); // midt
                    list.add(new Fireball(super.getpApplet(), new PVector(super.getCurrentPvector().x, super.getCurrentPvector().y+(2*getOriginalScale())), i, getOriginalScale())); //venstre
                    list.add(new Fireball(super.getpApplet(), new PVector(super.getCurrentPvector().x+(2*getOriginalScale()), super.getCurrentPvector().y+(2*getOriginalScale())), i, getOriginalScale())); //højre
                    setCooldown(80);
                }
                else if(i == 2)
                {
                    list.add(new Fireball(super.getpApplet(), new PVector(super.getCurrentPvector().x + getOriginalScale(), super.getCurrentPvector().y +getOriginalScale()), i, getOriginalScale())); //midt
                    list.add(new Fireball(super.getpApplet(), new PVector(super.getCurrentPvector().x, super.getCurrentPvector().y), i, getOriginalScale())); //venstre
                    list.add(new Fireball(super.getpApplet(), new PVector(super.getCurrentPvector().x+(2*getOriginalScale()), super.getCurrentPvector().y), i, getOriginalScale())); //højre
                    setCooldown(80);
                }
                else if(i == 3)
                {
                    list.add(new Fireball(super.getpApplet(), new PVector(super.getCurrentPvector().x+ getOriginalScale() , super.getCurrentPvector().y + getOriginalScale()), i, getOriginalScale())); //midt
                    list.add(new Fireball(super.getpApplet(), new PVector(super.getCurrentPvector().x+(2*getOriginalScale()), super.getCurrentPvector().y), i, getOriginalScale())); //øverst
                    list.add(new Fireball(super.getpApplet(), new PVector(super.getCurrentPvector().x+(2*getOriginalScale()), super.getCurrentPvector().y+(2*getOriginalScale())), i, getOriginalScale())); //nederst
                    setCooldown(80);
                }
                else
                {
                    list.add(new Fireball(super.getpApplet(), new PVector(super.getCurrentPvector().x+ getOriginalScale(), super.getCurrentPvector().y+ getOriginalScale()), i, getOriginalScale())); //midt
                    list.add(new Fireball(super.getpApplet(), new PVector(super.getCurrentPvector().x, super.getCurrentPvector().y), i, getOriginalScale())); //øverst
                    list.add(new Fireball(super.getpApplet(), new PVector(super.getCurrentPvector().x, super.getCurrentPvector().y+(2*getOriginalScale())), i, getOriginalScale())); //nederst
                    setCooldown(80);
                }
            }
        }
    }
}
