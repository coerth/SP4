package Entitys;

import Interfaces.RangedI;
import processing.core.PApplet;
import processing.core.PVector;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.Random;

public class Wizard extends Enemies implements RangedI {

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

    public void shootProjectile(int i){
        Random rand = new Random();
        int j = rand.nextInt(400);

        if(cooldown > 0) //hvis cooldown for sidste skud ikke er klaret så sker der ikke noget
        {
            return;
        }
        else{
            if(j < 10) { //hvis det tilfældige tal er under 10 så skyd
                list.add(new Projectile(super.getpApplet(), new PVector(super.getpVector().x, super.getpVector().y), i, super.getScale()));
                cooldown = 25;
            }
        }
    }



    private void cooldownRecovery() //nedtælling til der kan skydes igen
    {
        if(cooldown > 0)
        {
            cooldown--;
        }
    }

    private void processProjectiles(int i){ //samling af projectile funktioner
        shootProjectile(i);
        cooldownRecovery();
        if(list.size() > 0){
            for(Projectile p : list){
                p.projectileTrajectory();
                p.display();
            }
        }
        projectileBoundary();
    }

    public void processEnemy(int i){ //overloadet funktion så int i kan være i signaturen
        super.processEnemy();
        processProjectiles(i);
    }

//    @Override
//    public void display(){
//        super.display();
//
//    }

    public void projectileBoundary(){ //når projectiles er uden for rammerne så skal de slettes
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
