package GameComponents;

import Entitys.*;
import Interfaces.MeleeI;
import Interfaces.RangedI;
import Rooms.CombatRoom;
import Rooms.Room;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Random;

public class CollisionDetector {
    private Dungeon dungeon;
    private PApplet pApplet;

    public CollisionDetector(PApplet pApplet, Dungeon dungeon)
    {
        this.pApplet = pApplet;
        this.dungeon = dungeon;
    }


    private void entityCollision(Entity e1, Entity e2)
    {
        if(e1.getCurrentPvector().x == e2.getCurrentPvector().x && e1.getCurrentPvector().y == e2.getCurrentPvector().y )
        {
            e1.revertCurrentPvector(); //hvis e1 har samme x og y pos som e2 så skal e1 sendes tilbage til sin tidligere position
        }
    }

    private void projectileDetection(Entity shooter, Entity target)
    {
        if(shooter instanceof Player) //tjek om Entity'en er en player
        {
            ArrayList<Projectile> list = ((Player)shooter).getList(); //for hvert projektil som den entity har tjek følgende
            for(int i = 0; i < list.size(); i++)
            {

                if (list.get(i).getpVector().x == target.getCurrentPvector().x && list.get(i).getpVector().y == target.getCurrentPvector().y) //hvis projektil koordinaterne og target er det samme gør følgende
                {
                    ((Enemies) target).takeDMG(((Player) shooter).attack()); //giv skade
                    dmgKnockback(target, list.get(i).getDirection()); //skub target tilbage
                    list.remove(i); //fjern projektil
                    ((Enemies) target).enemyBoundaries();
                }
            }
        }
        else if(shooter instanceof RangedI) //tjek om Entity'en er en ranged fjende
        {
            ArrayList<Projectile> list = ((RangedEnemy)shooter).getList(); //for hvert projektil som den entity har tjek følgende
            for(int i = 0; i < list.size(); i++)
            {
                if (list.get(i).getpVector().x == target.getCurrentPvector().x && list.get(i).getpVector().y == target.getCurrentPvector().y) //hvis projektil koordinaterne og target er det samme gør følgende
                {
                    ((Player) target).takeDMG(((Enemies) shooter).attack()); //giv skade
                    dmgKnockback(target, list.get(i).getDirection()); //skub target tilbage
                    list.remove(i); //fjern projektil

                }
            }
        }
    }

    private void dmgKnockback(Entity target, int direction)
    {
            switch (direction)
            {
                case 1 -> target.changeCurrentPvector(1); //bliver knocket op af

                case 2 -> target.changeCurrentPvector(2); //bliver knocket ned af

                case 3 -> target.changeCurrentPvector(3); //bliver knocket til venstre

                case 4 -> target.changeCurrentPvector(4); //bliver knocket til højre
            }
    }


    public void collisionRoomPlayer(Room room) {
        Player player = dungeon.getPlayer();
        if (player.getCurrentPvector().x < 0) {

            if (player.getCurrentPvector().y > 250 && player.getCurrentPvector().y < 350 && room.hasWestRoom()) { //tjek om spilleren ville gå gennem en dør hvis den er der
                ChangeRoom('w');
                player.getCurrentPvector().x = pApplet.width - player.getScale(); //spillerens position bliver til at være gået igennem dør eller rumskifte
            } else {
                player.revertCurrentPvector(); //hvis der ikke er en dør så er det en væg og spilleren holdes indenfor rammerne
            }
        }

        if (player.getCurrentPvector().y < 0) {

            if (player.getCurrentPvector().x > 350 && player.getCurrentPvector().x < 450 && room.hasNorthRoom()) { //tjek om spilleren ville gå gennem en dør hvis den er der
                ChangeRoom('n');
                player.getCurrentPvector().y = pApplet.height - player.getScale(); //spillerens position bliver til at være gået igennem dør eller rumskifte
            } else {
                player.revertCurrentPvector(); //hvis der ikke er en dør så er det en væg og spilleren holdes indenfor rammerne
            }
        }

        if (player.getCurrentPvector().x > pApplet.width - player.getScale()) {

            if (player.getCurrentPvector().y > 250 && player.getCurrentPvector().y < 350 && room.hasEastRoom()) { //tjek om spilleren ville gå gennem en dør hvis den er der
                ChangeRoom('e');
                player.getCurrentPvector().x = 0; //spillerens position bliver til at være gået igennem dør eller rumskifte
            } else {
                player.revertCurrentPvector(); //hvis der ikke er en dør så er det en væg og spilleren holdes indenfor rammerne
            }
        }

        if (player.getCurrentPvector().y > pApplet.height - player.getScale()) {

            if (player.getCurrentPvector().x > 350 && player.getCurrentPvector().x < 450 && room.hasSouthRoom()) { //tjek om spilleren ville gå gennem en dør hvis den er der
                ChangeRoom('s');
                player.getCurrentPvector().y = 0; //spillerens position bliver til at være gået igennem dør eller rumskifte
            } else {
                player.revertCurrentPvector(); //hvis der ikke er en dør så er det en væg og spilleren holdes indenfor rammerne
            }
        }
    }

        public void ChangeRoom ( char dir){
            switch (dir) {
                case 'n':
                    dungeon.getMap().setPlayerRoomPosition(dungeon.getMap().currentLocation()[0] - 1, dungeon.getMap().currentLocation()[1]); //gå et rum op
                    break;
                case 'e':
                    dungeon.getMap().setPlayerRoomPosition(dungeon.getMap().currentLocation()[0], dungeon.getMap().currentLocation()[1] + 1); //gå et rum til højre
                    break;
                case 's':
                    dungeon.getMap().setPlayerRoomPosition(dungeon.getMap().currentLocation()[0] + 1, dungeon.getMap().currentLocation()[1]);  //gå et rum ned
                    break;
                case 'w':
                    dungeon.getMap().setPlayerRoomPosition(dungeon.getMap().currentLocation()[0], dungeon.getMap().currentLocation()[1] - 1);  //gå et rum venstre
                    break;
            }
        }
    public void combatDetection(Room room) //funktion for at samle del metoderne
    {
        Player player = dungeon.getPlayer();

        if(room instanceof CombatRoom)
        {
            ArrayList<Enemies> list = ((CombatRoom) room).getList();

            for(int i = 0; i < list.size(); i++)
            {
                projectileDetection(player, list.get(i)); //tjek spillerens projectiles
                projectileDetection(list.get(i), player); //tjek fjendernes projectiles
                meleeCombatDetection(list.get(i)); //tjek om fjenderne kan slå spilleren
                entityCollision(player,list.get(i)); //tjek om spilleren går ind i samme felt som en fjende
                if(i > 0)
                {
                    entityCollision(list.get(i),list.get(i-1)); //tjek om en fjende går ind i samme felt som en anden fjende
                }

                if(list.get(i).getHP() <= 0){
                    player.getInventory().AddCoins(list.get(i).DropCoins());
                    list.remove(i);
                }
            }
        }
    }

    private void meleeCombatDetection(Entity attacker)
    {
        if(attacker instanceof MeleeI)
        {
            meleeCombatDetectionDirection(attacker, 0, -1); //tjek og afvikl attacks hvis det er nødvendigt
            meleeCombatDetectionDirection(attacker, 0, 1); //tjek og afvikl attacks hvis det er nødvendigt
            meleeCombatDetectionDirection(attacker,-1,0); //tjek og afvikl attacks hvis det er nødvendigt
            meleeCombatDetectionDirection(attacker,1,0); //tjek og afvikl attacks hvis det er nødvendigt
        }
    }

    private void meleeCombatDetectionDirection(Entity attacker, int xValue, int yValue )
    {
        Random rand = new Random();
        int i = rand.nextInt(400); //tilfældig chance for at slå så man rent faktisk kan stå i feltet ved siden af
        Player player = dungeon.getPlayer();
        if(xValue < -1 || xValue > 1)
        {
            throw  new ArithmeticException("xValue has to be either -1, 0 or 1.");
        }
        else if (yValue < -1 || yValue > 1)
        {
            throw  new ArithmeticException("yValue has to be either -1, 0 or 1.");
        }

                //hvis fjendens placering er rigtigt og chanced er rigtigt så bliver playeren slået
                if (attacker.getCurrentPvector().x == player.getCurrentPvector().x + xValue * player.getScale()  && attacker.getCurrentPvector().y == player.getCurrentPvector().y + yValue * player.getScale() && i < 10)
                {
                    player.takeDMG(((Enemies) attacker).attack());

                    if(yValue == -1 && xValue == 0)
                    { //knockback i de forskellige retninger
                        dmgKnockback(player,2);
                    }
                    else if(yValue == 1 && xValue == 0)
                    {
                        dmgKnockback(player,1);
                    }
                    else if(yValue == 0 && xValue == -1)
                    {
                        dmgKnockback(player,4);
                    }
                    else if(yValue == 0 && xValue == 1)
                    {
                        dmgKnockback(player,3);
                    }
                }

            {

            }

    }

}



