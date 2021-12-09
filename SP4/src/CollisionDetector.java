import Entitys.*;
import Interfaces.CombatI;
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



    private void projectileDetection(Entity shooter, Entity target)
    {
        if(shooter instanceof Player) //tjek om Entity'en er en player
        {
            ArrayList<Projectile> list = ((Player)shooter).getList(); //for hvert projektil som den entity har tjek følgende
            for(int i = 0; i < list.size(); i++)
            {

                if (list.get(i).getpVector().x == ((Enemies)target).getpVector().x && list.get(i).getpVector().y == ((Enemies)target).getpVector().y) //hvis projektil koordinaterne og target er det samme gør følgende
                {
                    ((Enemies) target).takeDMG(((Player) shooter).attack()); //giv skade
                    dmgKnockback(target, list.get(i).getDirection()); //skub target tilbage
                    list.remove(i); //fjern projektil
                }
            }
        }
        else if(shooter instanceof RangedI) //tjek om Entity'en er en ranged fjende
        {
            ArrayList<Projectile> list = ((Wizard)shooter).getList(); //for hvert projektil som den entity har tjek følgende
            for(int i = 0; i < list.size(); i++)
            {

                if (list.get(i).getpVector().x == ((Player)target).getpVector().x && list.get(i).getpVector().y == ((Player)target).getpVector().y) //hvis projektil koordinaterne og target er det samme gør følgende
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
        if(target instanceof Player)
        {
            switch (direction)
            {
                case 1 -> ((Player) target).getpVector().y -= ((Player) target).getScale(); //bliver knocket op af
                case 2 -> ((Player) target).getpVector().y += ((Player) target).getScale(); //bliver knocket ned af
                case 3 -> ((Player) target).getpVector().x -= ((Player) target).getScale(); //bliver knocket til venstre
                case 4 -> ((Player) target).getpVector().x += ((Player) target).getScale(); //bliver knocket til højre
            }
        }
        else if(target instanceof Enemies)
        {
            switch (direction)
            {
                case 1 -> ((Enemies) target).getpVector().y -= ((Enemies) target).getScale(); //bliver knocket op af
                case 2 -> ((Enemies) target).getpVector().y += ((Enemies) target).getScale(); //bliver knocket op af
                case 3 -> ((Enemies) target).getpVector().x -= ((Enemies) target).getScale(); //bliver knocket til venstre
                case 4 -> ((Enemies) target).getpVector().x += ((Enemies) target).getScale(); //bliver knocket til højre
            }
        }
    }


    public void collisionRoomPlayer(Room room) {
        Player player = dungeon.getPlayer();
        if (player.getpVector().x < 0) {

            if (player.getpVector().y > 250 && player.getpVector().y < 350 && room.hasWestRoom()) { //tjek om spilleren ville gå gennem en dør hvis den er der
                ChangeRoom('w');
                player.getpVector().x = pApplet.width - player.getScale(); //spillerens position bliver til at være gået igennem dør eller rumskifte
            } else {
                player.getpVector().x = 0;  //hvis der ikke er en dør så er det en væg og spilleren holdes indenfor rammerne
            }
        }

        if (player.getpVector().y < 0) {

            if (player.getpVector().x > 350 && player.getpVector().x < 450 && room.hasNorthRoom()) { //tjek om spilleren ville gå gennem en dør hvis den er der
                ChangeRoom('n');
                player.getpVector().y = pApplet.height - player.getScale(); //spillerens position bliver til at være gået igennem dør eller rumskifte
            } else {
                player.getpVector().y = 0;  //hvis der ikke er en dør så er det en væg og spilleren holdes indenfor rammerne
            }
        }

        if (player.getpVector().x > pApplet.width - player.getScale()) {

            if (player.getpVector().y > 250 && player.getpVector().y < 350 && room.hasEastRoom()) { //tjek om spilleren ville gå gennem en dør hvis den er der
                ChangeRoom('e');
                player.getpVector().x = 0; //spillerens position bliver til at være gået igennem dør eller rumskifte
            } else {
                player.getpVector().x = pApplet.width - player.getScale();  //hvis der ikke er en dør så er det en væg og spilleren holdes indenfor rammerne
            }
        }

        if (player.getpVector().y > pApplet.height - player.getScale()) {

            if (player.getpVector().x > 350 && player.getpVector().x < 450 && room.hasSouthRoom()) { //tjek om spilleren ville gå gennem en dør hvis den er der
                ChangeRoom('s');
                player.getpVector().y = 0; //spillerens position bliver til at være gået igennem dør eller rumskifte
            } else {
                player.getpVector().y = pApplet.height - player.getScale();  //hvis der ikke er en dør så er det en væg og spilleren holdes indenfor rammerne
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
    public void combatDetection(Room room)
    {
        Player player = dungeon.getPlayer();

        if(room instanceof CombatRoom)
        {
            ArrayList<Enemies> list = ((CombatRoom) room).getList();

            for(int i = 0; i < list.size(); i++)
            {
                projectileDetection(player, list.get(i));
                projectileDetection(list.get(i), player);
                meleeCombatDetection(list.get(i));

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
        int i = rand.nextInt(400);
        Player player = dungeon.getPlayer();
        if(xValue < -1 || xValue > 1)
        {
            throw  new ArithmeticException("xValue has to be either -1, 0 or 1.");
        }
        else if (yValue < -1 || yValue > 1)
        {
            throw  new ArithmeticException("yValue has to be either -1, 0 or 1.");
        }
        
                //hvis fjendens placering er rigtigt sættes enemy til den fjende
                if (((Enemies) attacker).getpVector().x == player.getpVector().x + xValue * player.getScale()  && ((Enemies) attacker).getpVector().y == player.getpVector().y + yValue * player.getScale() && i < 10)
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



