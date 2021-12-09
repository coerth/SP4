package Entitys;

import processing.core.PApplet;
import processing.core.PVector;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.Random;

public class Wizard extends Enemies{

    ArrayList<Projectile> list = new ArrayList<>();
    private int cooldown = 0;

    private PImage[] wizardImages = new PImage[12];

    public Wizard(PApplet pApplet, PVector pVector) {
        super(pApplet, 6, 3, pVector);
        for (int i = 0; i < wizardImages.length; i++) {
            wizardImages[i] = pApplet.loadImage("Sprites/EnemySprites/ArcherSprites/tile" + PApplet.nf(i, 3) + ".png");
        }
        setEnemyImages(wizardImages);
    }

    private void shootProjectile(){
        Random rand = new Random();
        int i = rand.nextInt(400);
//        float xPosDifference = player.getpVector().x - super.getpVector().x;
//        float yPosDifference = player.getpVector().y - super.getpVector().y;

        if(cooldown > 0)
        {
            return;
        }
        else{
            if(i < 10) {
                //i = rand.nextInt(4) + 1;

//                if(xPosDifference <= 0 && yPosDifference <= 0 || xPosDifference <= 0 && yPosDifference >= 0)
//                {
//                    list.add(new Projectile(super.getpApplet(), new PVector(super.getpVector().x, super.getpVector().y), 3, super.getScale()));
//                }
//                else if(xPosDifference >= 0 && yPosDifference <= 0 || xPosDifference >= 0 && yPosDifference >= 0)
//                {
//                    list.add(new Projectile(super.getpApplet(), new PVector(super.getpVector().x, super.getpVector().y), 4, super.getScale()));
//                }

                list.add(new Projectile(super.getpApplet(), new PVector(super.getpVector().x, super.getpVector().y), i, super.getScale()));
                cooldown = 25;
            }
        }

    }

    private void cooldownRecovery()
    {
        if(cooldown > 0)
        {
            cooldown--;
        }
    }

    private void processProjectiles(){
        shootProjectile();
        cooldownRecovery();
        if(list.size() > 0){
            for(Projectile p : list){
                p.projectileTrajectory();
                p.display();
            }
        }
        projectileBoundary();
    }

    @Override
    public void processEnemy(){
        super.processEnemy();
        processProjectiles();
    }

    @Override
    public void display(){
        super.display();

    }

    private void projectileBoundary(){
        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).getpVector().x < 0) {
                list.remove(i);
            }
            else if (list.get(i).getpVector().y < 0) {
                list.get(i).getpVector().y = 0;
            }
            else if (list.get(i).getpVector().x > getpApplet().width - super.getScale()) {
                list.remove(i);
            }
            else if (list.get(i).getpVector().y > getpApplet().height - super.getScale()) {
                list.remove(i);
            }
        }
    }

    public ArrayList<Projectile> getList() {
        return list;
    }
}
