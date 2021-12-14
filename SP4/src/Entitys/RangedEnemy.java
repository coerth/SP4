package Entitys;

import Interfaces.RangedI;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.Random;

public  abstract class RangedEnemy extends Enemy implements RangedI {

    ArrayList<Projectile> list = new ArrayList<>();
    private int cooldown = 0;

    public RangedEnemy(PApplet pApplet, int HP, int attack,int difficulty, PVector pVector)
    {
        super(pApplet, HP, attack,difficulty, pVector);
    }

    public void processEnemy(int i){ //overloadet funktion så int i kan være i signaturen
        super.processEnemy();
        shootProjectile(i);
        processProjectiles();
    }

    @Override
    public void shootProjectile(int i){
        Random rand = new Random();
        int j = rand.nextInt(400);

        if(cooldown > 0) //hvis cooldown for sidste skud ikke er klaret så sker der ikke noget
        {
            return;
        }
        else{
            if(j < 10) { //hvis det tilfældige tal er under 10 så skyd
                list.add(new Fireball(super.getpApplet(), new PVector(super.getCurrentPvector().x, super.getCurrentPvector().y), i, super.getScale()));
                cooldown = 40;
            }
        }
    }

    @Override
    public void processProjectiles(){ //samling af projectile funktioner
        cooldownRecovery();
        if(list.size() > 0){
            for(Projectile p : list){
                p.projectileTrajectory();
                p.display();
            }
        }
        projectileBoundary();
    }

    private void cooldownRecovery() //nedtælling til der kan skydes igen
    {
        if(cooldown > 0)
        {
            cooldown--;
        }
    }

    @Override
    public void projectileBoundary(){ //når projectiles er uden for rammerne så skal de slettes
        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).getpVector().x < 0) {
                list.remove(i);
            }
            else if (list.get(i).getpVector().y < 0) {
                list.remove(i);
            }
            else if (list.get(i).getpVector().x > getpApplet().width - super.getScale()) {
                list.remove(i);
            }
            else if (list.get(i).getpVector().y > getpApplet().height - super.getScale()) {
                list.remove(i);
            }
        }
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public ArrayList<Projectile> getList() {
        return list;
    }
}
