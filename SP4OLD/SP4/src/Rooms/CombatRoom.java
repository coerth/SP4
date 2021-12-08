package Rooms;
import Entitys.Enemies;
import Entitys.Skeleton;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.Random;

public class CombatRoom extends Room{

    ArrayList<Enemies> list = new ArrayList<>();

    public CombatRoom(PApplet pApplet)
    {
        super(pApplet);
        spawnEnemies();
        list.add(new Skeleton(pApplet,new PVector(12*getScale(),9*getScale())));
    }

    public void displayEnemies()
    {
        for(Enemies e : list){
            e.display();
        }
    }

    private void spawnEnemies()
    {
        int enemies = amountOfEnemies();

        for(int i = 0; i < enemies; i++){
           list.add(spawnLocation());
        }
    }

    private int amountOfEnemies()
    {
        Random rand = new Random();

        return rand.nextInt(1,5);
    }

    private Enemies spawnLocation()
    {
        Random rand = new Random();
       int i = rand.nextInt(1,5);
       Enemies enemy;

       //rummet er inddelt i 4 firkanter, og alt efter hvad i ruller så skal der et fjende ind i en af dem
       switch (i)
       {
           case 1:
               enemy = new Skeleton(getpApplet(), new PVector(rand.nextInt(0,13)*super.getScale(),rand.nextInt(0,10)*super.getScale()));
               //hvis det er den første fjende så skal der ikke tjekkes efter andre
               if(list.size() == 0)
               {
                   return enemy;
               }

               //tjek om den nye fjende overlapper med en eksiterende fjende.
               for(int j = 0; j < list.size(); j++)
               {
                   if(enemy.getPvector().x == list.get(j).getPvector().x && enemy.getPvector().y == list.get(j).getPvector().y)
                   {
                       enemy = spawnLocation();
                   }
               }
                return enemy;

           case 2:
               enemy = new Skeleton(getpApplet(), new PVector(rand.nextInt(13,26)*super.getScale(),rand.nextInt(0,10)*super.getScale()));
               //hvis det er den første fjende så skal der ikke tjekkes efter andre
               if(list.size() == 0)
               {
                   return enemy;
               }

               //tjek om den nye fjende overlapper med en eksiterende fjende.
               for(int j = 0; j < list.size(); j++)
               {
                   if(enemy.getPvector().x == list.get(j).getPvector().x && enemy.getPvector().y == list.get(j).getPvector().y)
                   {
                       enemy = spawnLocation();
                   }
               }
               return enemy;


           case 3:
               enemy = new Skeleton(getpApplet(), new PVector(rand.nextInt(0,13)*super.getScale(),rand.nextInt(10,21)*super.getScale()));
               //hvis det er den første fjende så skal der ikke tjekkes efter andre
               if(list.size() == 0)
               {
                   return enemy;
               }

               //tjek om den nye fjende overlapper med en eksiterende fjende.
               for(int j = 0; j < list.size(); j++)
               {
                   if(enemy.getPvector().x == list.get(j).getPvector().x && enemy.getPvector().y == list.get(j).getPvector().y)
                   {
                       enemy = spawnLocation();
                   }
               }
               return enemy;


           case 4:
               enemy = new Skeleton(getpApplet(), new PVector(rand.nextInt(13,26)*super.getScale(),rand.nextInt(10,21)*super.getScale()));
               //hvis det er den første fjende så skal der ikke tjekkes efter andre
               if(list.size() == 0)
               {
                   return enemy;

               }

               //tjek om den nye fjende overlapper med en eksiterende fjende.
               for(int j = 0; j < list.size(); j++)
               {
                   if(enemy.getPvector().x == list.get(j).getPvector().x && enemy.getPvector().y == list.get(j).getPvector().y)
                   {
                       enemy = spawnLocation();
                   }
               }
               return enemy;

           //der skal være en default hvis der er nogle edgecases da vi skal returne noget.
           default : enemy = new Skeleton(getpApplet(), new PVector(12*super.getScale(),10*super.getScale()));
           return enemy;
       }
    }

    public ArrayList<Enemies> getList()
    {
        return list;
    }
}
