package Rooms;

import Entitys.Player;
import Entitys.Wizard;
import Entitys.Enemies;
import Entitys.Gargoyle;
import Interfaces.RangedI;
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

    public void processEnemies(Player player) { //den tager spilleren ind for at finde ud af distance mellem fjende og player
       for(Enemies e : list){
           if(e instanceof RangedI){
               ((Wizard)e).processEnemy(calculateDistance(e,player));
           }
           else {
               e.processEnemy();
           }
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

    public int calculateDistance(Enemies enemy, Player player) //funktion til at udregne hvilken retning der skal skydes i
    {
        int x = (int) (player.getpVector().x - enemy.getpVector().x); //gem forskellen på fjendes xpos og spilleren xpos
        int y = (int) (player.getpVector().y - enemy.getpVector().y); //gem forskellen på fjendes ypos og spilleren ypos

        int xDifference = x;
        int yDifference = y;
        int counterX = 0;
        int counterY = 0;

        if(xDifference < 0) //hvis x er negativ skal der plusses
        {
            while (xDifference != 0) //udregn hvor langt fra 0 xpos er
            {
                xDifference += 1;
                counterX++;
            }
        }
        else
        {
            while (xDifference != 0) //udregn hvor langt fra 0 xpos er
            {
                xDifference -= 1;
                counterX++;
            }
        }
        if(yDifference < 0)//hvis y er negativ skal der plusses
        {
            while (yDifference != 0) //udregn hvor langt fra 0 ypos er
            {
                yDifference += 1;
                counterY++;
            }
        }
        else
        {
            while (yDifference != 0) //udregn hvor langt fra 0 ypos er
            {
                yDifference -= 1;
                counterY++;
            }
        }

        if(counterX < counterY && y < 0 ) // hvis x forskellen er mindst og y er negativ skal der skydes op
        {
            return 1;
        }

        else if(counterX < counterY && y > 0) //hvis x forskellen er mindst og y er positiv skal der skydes ned
        {
            return 2;
        }

        else if(counterY < counterX && x < 0) //hvis y forskellen er mindst og x er negativ skal der skydes til venstre
        {
            return 3;
        }
        else //ellers hvis y forskellen er mindst og x er positiv skal der skydes til højre
        {
            return 4;
        }

    }

    public ArrayList<Enemies> getList() {
        return list;
    }
}
