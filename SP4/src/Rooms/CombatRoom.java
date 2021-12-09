package Rooms;

import Entitys.Wizard;
import Entitys.Enemies;
import Entitys.Gargoyle;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.Random;

import static processing.core.PConstants.SHAPE;

public class CombatRoom extends Room {

    ArrayList<Enemies> list = new ArrayList<>();

    @Override
    public void display()
    {
        super.display();
        super.getpApplet().textMode(SHAPE);
        super.getpApplet().textSize(60);
        super.getpApplet().textAlign(super.getpApplet().CENTER);
        super.getpApplet().fill(87, 53, 3);
        super.getpApplet().text("CombatRoom", super.getpApplet().width*0.5f, super.getpApplet().height*0.3f);
    }

    public CombatRoom(PApplet pApplet) {
        super(pApplet);
        spawnEnemies();
    }

    public void processEnemies() {
       for(Enemies e : list){
           e.processEnemy();
       }
    }


    private void spawnEnemies() {
        int enemies = amountOfEnemies();

        for (int i = 0; i < enemies; i++) {
            list.add(spawnLocation());
        }
    }

    private int amountOfEnemies() {
        Random rand = new Random();

        return rand.nextInt(3)+4;
    }

    private Enemies spawnLocation() {
        Random rand = new Random();
        int i = rand.nextInt(1, 5);
        int j = rand.nextInt(2);
        Enemies enemy;

        //rummet er inddelt i 4 firkanter, og alt efter hvad i ruller så skal der et fjende ind i en af dem
        switch (i) {
            case 1:
                if(j%2 == 0)
                {
                    enemy = new Wizard(getpApplet(), new PVector(rand.nextInt(0, 13) * super.getScale(), rand.nextInt(0, 10) * super.getScale()));
                }else{
                    enemy = new Gargoyle(getpApplet(), new PVector(rand.nextInt(0, 13) * super.getScale(), rand.nextInt(0, 10) * super.getScale()));
                }

                //hvis det er den første fjende så skal der ikke tjekkes efter andre
                if (list.size() == 0) {
                    return enemy;
                }

                //tjek om den nye fjende overlapper med en eksiterende fjende.
                for (int k = 0; k < list.size(); k++) {
                    if (enemy.getpVector().x == list.get(k).getpVector().x && enemy.getpVector().y == list.get(k).getpVector().y) {
                        enemy = spawnLocation();
                    }
                }
                return enemy;

            case 2:
                if(j%2 == 0)
                {
                    enemy = new Wizard(getpApplet(), new PVector(rand.nextInt(13, 26) * super.getScale(), rand.nextInt(0, 10) * super.getScale()));
                }
                else
                {
                    enemy = new Gargoyle(getpApplet(), new PVector(rand.nextInt(13, 26) * super.getScale(), rand.nextInt(0, 10) * super.getScale()));
                }

                //hvis det er den første fjende så skal der ikke tjekkes efter andre
                if (list.size() == 0) {
                    return enemy;
                }

                //tjek om den nye fjende overlapper med en eksisterende fjende.
                for (int k = 0; k < list.size(); k++) {
                    if (enemy.getpVector().x == list.get(k).getpVector().x && enemy.getpVector().y == list.get(k).getpVector().y) {
                        enemy = spawnLocation();
                    }
                }
                return enemy;


            case 3:
                if(j%2 == 0)
                {
                    enemy = new Wizard(getpApplet(), new PVector(rand.nextInt(0, 13) * super.getScale(), rand.nextInt(10, 21) * super.getScale()));
                }
                else
                {
                    enemy = new Gargoyle(getpApplet(), new PVector(rand.nextInt(0, 13) * super.getScale(), rand.nextInt(10, 21) * super.getScale()));
                }
                //hvis det er den første fjende så skal der ikke tjekkes efter andre
                if (list.size() == 0) {
                    return enemy;
                }

                //tjek om den nye fjende overlapper med en eksiterende fjende.
                for (int k = 0; k < list.size(); k++) {
                    if (enemy.getpVector().x == list.get(k).getpVector().x && enemy.getpVector().y == list.get(k).getpVector().y) {
                        enemy = spawnLocation();
                    }
                }
                return enemy;


            case 4:
                if(j%2 == 0)
                {
                    enemy = new Wizard(getpApplet(), new PVector(rand.nextInt(13, 26) * super.getScale(), rand.nextInt(10, 21) * super.getScale()));
                }
                else
                {
                    enemy = new Gargoyle(getpApplet(), new PVector(rand.nextInt(13, 26) * super.getScale(), rand.nextInt(10, 21) * super.getScale()));
                }
                //hvis det er den første fjende så skal der ikke tjekkes efter andre
                if (list.size() == 0) {
                    return enemy;

                }

                //tjek om den nye fjende overlapper med en eksisterende fjende.
                for (int k = 0; k < list.size(); k++) {
                    if (enemy.getpVector().x == list.get(k).getpVector().x && enemy.getpVector().y == list.get(k).getpVector().y) {
                        enemy = spawnLocation();
                    }
                }
                return enemy;

            //der skal være en default hvis der er nogle edgecases da vi skal returne noget.
            default:
                if(j%2 == 0)
                {
                    enemy = new Gargoyle(getpApplet(), new PVector(12 * super.getScale(), 10 * super.getScale()));
                }
                else
                {
                    enemy = new Wizard(getpApplet(), new PVector(12 * super.getScale(), 10 * super.getScale()));
                }
                return enemy;
        }
    }

    public ArrayList<Enemies> getList() {
        return list;
    }
}
