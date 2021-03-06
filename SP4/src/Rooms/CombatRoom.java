package Rooms;

import Entitys.*;
import Interfaces.RangedI;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.Random;

import static processing.core.PConstants.SHAPE;

public class CombatRoom extends Room {

   private ArrayList<Enemy> list = new ArrayList<>();
    private int difficulty;

    public CombatRoom(PApplet pApplet, int difficulty)
    {
        super(pApplet);
        this.difficulty = difficulty;
        setRoomName("CombatRoom");
        spawnEnemies();
    }

    @Override
    public void display()
    {
        super.display();
        super.getpApplet().textMode(SHAPE);
        super.getpApplet().textSize(60);
        super.getpApplet().textAlign(super.getpApplet().CENTER);
        super.getpApplet().fill(87, 53, 3);
        super.getpApplet().text(getRoomName(), super.getpApplet().width*0.5f, super.getpApplet().height*0.3f);
    }

    public void processEnemies(Player player) //den tager spilleren ind for at finde ud af distance mellem fjende og player
    {
       for(Enemy e : list){
           if(e instanceof RangedI)
           {
               ((RangedEnemy)e).processEnemy(calculateDistance(e,player));
           }
           else
           {
               ((MeleeEnemy)e).processEnemy(calculateDistance(e,player));
           }
       }
    }




    public void spawnEnemies() {
        int enemies = amountOfEnemies();

        for (int i = 0; i < enemies; i++) {
            list.add(spawnLocation());
        }
    }

    private int amountOfEnemies() {
        Random rand = new Random();

        return rand.nextInt(3)+4;
    }

    private Enemy spawnLocation() {
        Random rand = new Random();
        int i = rand.nextInt(1, 5);
        int j = rand.nextInt(2);
        Enemy enemy;

        //rummet er inddelt i 4 firkanter, og alt efter hvad i ruller så skal der et fjende ind i en af dem
        switch (i) {
            case 1:
                if(j%2 == 0)
                {
                    enemy = new Wizard(getpApplet(),difficulty, new PVector(rand.nextInt(1, 13), rand.nextInt(1, 10)));
                }else{
                    enemy = new Skeleton(getpApplet(),difficulty, new PVector(rand.nextInt(1, 13), rand.nextInt(1, 10)));
                }

                //hvis det er den første fjende så skal der ikke tjekkes efter andre
                if (list.size() == 0) {
                    return enemy;
                }

                //tjek om den nye fjende overlapper med en eksiterende fjende.
                for (int k = 0; k < list.size(); k++) {
                    if (enemy.getCurrentPvector().x == list.get(k).getCurrentPvector().x && enemy.getCurrentPvector().y == list.get(k).getCurrentPvector().y) {
                        enemy = spawnLocation();
                    }
                }
                return enemy;

            case 2:
                if(j%2 == 0)
                {
                    enemy = new Wizard(getpApplet(),difficulty, new PVector(rand.nextInt(13, 24), rand.nextInt(1, 10)));
                }
                else
                {
                    enemy = new Skeleton(getpApplet(),difficulty, new PVector(rand.nextInt(13, 24), rand.nextInt(1, 10)));
                }

                //hvis det er den første fjende så skal der ikke tjekkes efter andre
                if (list.size() == 0) {
                    return enemy;
                }

                //tjek om den nye fjende overlapper med en eksisterende fjende.
                for (int k = 0; k < list.size(); k++) {
                    if (enemy.getCurrentPvector().x == list.get(k).getCurrentPvector().x && enemy.getCurrentPvector().y == list.get(k).getCurrentPvector().y) {
                        enemy = spawnLocation();
                    }
                }
                return enemy;


            case 3:
                if(j%2 == 0)
                {
                    enemy = new Wizard(getpApplet(),difficulty, new PVector(rand.nextInt(1, 13), rand.nextInt(10, 19)));
                }
                else
                {
                    enemy = new Skeleton(getpApplet(),difficulty, new PVector(rand.nextInt(1, 13), rand.nextInt(10, 19)));
                }
                //hvis det er den første fjende så skal der ikke tjekkes efter andre
                if (list.size() == 0) {
                    return enemy;
                }

                //tjek om den nye fjende overlapper med en eksiterende fjende.
                for (int k = 0; k < list.size(); k++) {
                    if (enemy.getCurrentPvector().x == list.get(k).getCurrentPvector().x && enemy.getCurrentPvector().y == list.get(k).getCurrentPvector().y) {
                        enemy = spawnLocation();
                    }
                }
                return enemy;


            case 4:
                if(j%2 == 0)
                {
                    enemy = new Wizard(getpApplet(),difficulty, new PVector(rand.nextInt(13, 24), rand.nextInt(10, 19)));
                }
                else
                {
                    enemy = new Skeleton(getpApplet(),difficulty, new PVector(rand.nextInt(13, 24), rand.nextInt(10, 19)));
                }
                //hvis det er den første fjende så skal der ikke tjekkes efter andre
                if (list.size() == 0) {
                    return enemy;

                }

                //tjek om den nye fjende overlapper med en eksisterende fjende.
                for (int k = 0; k < list.size(); k++) {
                    if (enemy.getCurrentPvector().x == list.get(k).getCurrentPvector().x && enemy.getCurrentPvector().y == list.get(k).getCurrentPvector().y) {
                        enemy = spawnLocation();
                    }
                }
                return enemy;

            //der skal være en default hvis der er nogle edgecases da vi skal returne noget.
            default:
                if(j%2 == 0)
                {
                    enemy = new Skeleton(getpApplet(),difficulty, new PVector(12, 10));
                }
                else
                {
                    enemy = new Wizard(getpApplet(),difficulty, new PVector(12, 10));
                }
                return enemy;
        }
    }

    public int calculateDistance(Enemy enemy, Player player) //funktion til at udregne hvilken retning der skal skydes i
    {
        int x = (int) (player.getCurrentPvector().x - enemy.getCurrentPvector().x); //gem forskellen på fjendes xpos og spilleren xpos
        int y = (int) (player.getCurrentPvector().y - enemy.getCurrentPvector().y); //gem forskellen på fjendes ypos og spilleren ypos

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

    public ArrayList<Enemy> getList() {
        return list;
    }

    public int getDifficulty() {
        return difficulty;
    }
}
