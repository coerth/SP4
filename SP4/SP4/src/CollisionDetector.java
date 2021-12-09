import Entitys.Enemies;
import Entitys.Player;
import Rooms.CombatRoom;
import Rooms.Room;
import processing.core.PApplet;

import java.util.ArrayList;

import static java.awt.event.KeyEvent.*;
import static java.awt.event.KeyEvent.VK_L;

public class CollisionDetector {
    private Dungeon dungeon;
    private PApplet pApplet;

    public CollisionDetector(PApplet pApplet, Dungeon dungeon)
    {
        this.pApplet = pApplet;
        this.dungeon = dungeon;
    }

    public void collisionRoomPlayer(Room room) {
        Player player = dungeon.getPlayer();
        if (player.getpVector().x < 0) {

            if (player.getpVector().y > 250 && player.getpVector().y < 350 && room.hasWestRoom()) {
                ChangeRoom('w');
                player.getpVector().x = pApplet.width - player.getScale();
            } else {
                player.getpVector().x = 0;
            }
        }

        if (player.getpVector().y < 0) {

            if (player.getpVector().x > 350 && player.getpVector().x < 450 && room.hasNorthRoom()) {
                ChangeRoom('n');
                player.getpVector().y = pApplet.height - player.getScale();
            } else {
                player.getpVector().y = 0;
            }
        }

        if (player.getpVector().x > pApplet.width - player.getScale()) {

            if (player.getpVector().y > 250 && player.getpVector().y < 350 && room.hasEastRoom()) {
                ChangeRoom('e');
                player.getpVector().x = 0;
            } else {
                player.getpVector().x = pApplet.width - player.getScale();
            }
        }

        if (player.getpVector().y > pApplet.height - player.getScale()) {

            if (player.getpVector().x > 350 && player.getpVector().x < 450 && room.hasSouthRoom()) {
                ChangeRoom('s');
                player.getpVector().y = 0;
            } else {
                player.getpVector().y = pApplet.height - player.getScale();
            }
        }
    }

        public void ChangeRoom ( char dir){
            switch (dir) {
                case 'n':
                    dungeon.getMap().setPlayerRoomPosition(dungeon.getMap().currentLocation()[0] - 1, dungeon.getMap().currentLocation()[1]);
                    break;
                case 'e':
                    dungeon.getMap().setPlayerRoomPosition(dungeon.getMap().currentLocation()[0], dungeon.getMap().currentLocation()[1] + 1);
                    break;
                case 's':
                    dungeon.getMap().setPlayerRoomPosition(dungeon.getMap().currentLocation()[0] + 1, dungeon.getMap().currentLocation()[1]);
                    break;
                case 'w':
                    dungeon.getMap().setPlayerRoomPosition(dungeon.getMap().currentLocation()[0], dungeon.getMap().currentLocation()[1] - 1);
                    break;
            }
        }
    public void combatDetection(Room room) {
        Player player = dungeon.getPlayer();

        if (pApplet.keyCode == VK_I) //tjek om piletasten er trykket
        {
            combatDetectionDirection(room, 0, -1); //tjek og afvikl attacks hvis det er nødvendigt
            player.displayAttack("up"); //visuel del af players angreb
        } else if (pApplet.keyCode == VK_K) //tjek om piletasten er trykket
        {
            combatDetectionDirection(room, 0, 1); //tjek og afvikl attacks hvis det er nødvendigt
            player.displayAttack("down"); //visuel del af players angreb

        } else if (pApplet.keyCode == VK_J) //tjek om piletasten er trykket
        {
            combatDetectionDirection(room,-1,0); //tjek og afvikl attacks hvis det er nødvendigt
            player.displayAttack("left"); //visuel del af players angreb

        } else if (pApplet.keyCode == VK_L) //tjek om piletasten er trykket
        {
            combatDetectionDirection(room,1,0); //tjek og afvikl attacks hvis det er nødvendigt
            player.displayAttack("right"); //visuel del af players angreb
        }
    }

    public void combatDetectionDirection(Room room, int xValue, int yValue )
    {
        if(xValue < -1 || xValue > 1)
        {
            throw  new ArithmeticException("xValue has to be either -1, 0 or 1.");
        }
        else if (yValue < -1 || yValue > 1)
        {
            throw  new ArithmeticException("yValue has to be either -1, 0 or 1.");
        }

        if (room instanceof CombatRoom) //tjek om det pågældende rum er et combatrum
        {
            ArrayList<Enemies> list = ((CombatRoom) room).getList();
            Player player = dungeon.getPlayer();
            Enemies enemy = null;

            for (int i = 0; i < list.size(); i++) // tag fat i listen af fjender
            {
                //hvis fjendens placering er rigtigt sættes enemy til den fjende
                if (list.get(i).getPvector().x == player.getpVector().x + xValue * player.getScale()  && (list.get(i).getPvector().y == player.getpVector().y + yValue * player.getScale()))
                {
                    enemy = list.get(i);
                    break;
                }
            }
            if (enemy != null) //hvis der blev fundet en fjende så skal den fjende tage skade og evt droppe mønter
            {
                enemy.takeDMG(player.attack());
                if(yValue == -1 && xValue == 0)
                { //knockback i de forskellige retninger
                    enemy.getPvector().y -= 1 * player.getScale();
                }
                else if(yValue == 0 && xValue == -1)
                {
                    enemy.getPvector().x -= 1 * player.getScale();
                }
                else if(yValue == 1 && xValue == 0)
                {
                    enemy.getPvector().y += 1 * player.getScale();
                }
                else if(yValue == 0 && xValue == 1)
                {
                    enemy.getPvector().x += 1 * player.getScale();
                }
                enemy.enemyBoundaries();
                if (enemy.getHP() <= 0) //hvis fjendens liv er 0 eller under så skal den fjernes.
                {
                    player.getInventory().AddCoins(enemy.DropCoins());
                    list.remove(enemy);
                }
            }
        }
    }

    }



