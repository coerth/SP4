package Entitys;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.Random;

import static javax.swing.SwingConstants.CENTER;

public class Archer extends Enemies{

    ArrayList<Projectile> list = new ArrayList<>();

    public Archer(PApplet pApplet, PVector pVector) {
        super(pApplet, 6, 3, pVector);
    }

    public void shootArrow(){
        Random rand = new Random();
        int i = rand.nextInt(400);

        if(i < 10) {
            i = rand.nextInt(4) + 1;

            list.add(new Projectile(super.getpApplet(), new PVector(super.getPvector().x, super.getPvector().y), i, super.getScale()));
        }
    }

    public void processProjectiles(){
        shootArrow();
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
        super.getpApplet().fill(0);
        super.getpApplet().ellipseMode(PConstants.CENTER);
        super.getpApplet().ellipse(super.getPvector().x+ super.getScale()/2f, super.getPvector().y+ super.getScale()/2f, super.getScale()/2f, super.getScale()/2f);
    }

    public void projectileBoundary(){
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
