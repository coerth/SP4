import Entitys.Enemies;
import Rooms.CombatRoom;
import Rooms.Room;
import processing.core.PApplet;

import java.util.ArrayList;

import static java.awt.event.KeyEvent.*;

public class Controller {
    private PApplet pApplet;
    private Dungeon dungeon;
    private UI ui;

    public Controller(PApplet pApplet) {

        this.pApplet = pApplet;
        this.ui = new UI(pApplet);
    }

    public void StartDungeon() {

        dungeon = new Dungeon(pApplet);
    }


    public void combatDetection(Room room) {
        if (room instanceof CombatRoom) //tjek om det pågældende rum er et combatrum
        {
            ArrayList<Enemies> list = ((CombatRoom) room).getList();
            //boolean makeAttack = false;
            Enemies enemy = null;

            if (pApplet.keyCode == VK_I) //tjek om piletasten er trykket
            {
                for (int i = 0; i < list.size(); i++) // tag fat i listen af fjender
                {
                    //hvis fjenden er rigtigt ved siden af så skal den tage skade
                    if (list.get(i).getPvector().x == getDungeon().getPlayer().getpVector().x && (list.get(i).getPvector().y == getDungeon().getPlayer().getpVector().y - 1 * getDungeon().getPlayer().getScale())) {
                        enemy = list.get(i);
                        break;
                    }
                }
                if (enemy != null) {
                    enemy.takeDMG(getDungeon().getPlayer().attack());
                    //list.get(i).getPvector().y -= 1 * getDungeon().getPlayer().getScale();
                    if (enemy.getHP() <= 0) //hvis fjendens liv er 0 eller under så skal den fjernes.
                    {
                        list.remove(enemy);
                        return;
                    }

                }
                //pApplet.keyCode = VK_BACK_SLASH;
            } else if (pApplet.keyCode == VK_K) //tjek om piletasten er trykket

            {
                for (int i = 0; i < list.size(); i++) // tag fat i listen af fjender
                {
                    //hvis fjenden er rigtigt ved siden af så skal den tage skade
                    if (list.get(i).getPvector().x == getDungeon().getPlayer().getpVector().x && (list.get(i).getPvector().y == getDungeon().getPlayer().getpVector().y + 1 * getDungeon().getPlayer().getScale())) {
                        enemy = list.get(i);
                        break;
                    }
                }
                if (enemy != null) {
                    enemy.takeDMG(getDungeon().getPlayer().attack());
                    //list.get(i).getPvector().y -= 1 * getDungeon().getPlayer().getScale();
                    if (enemy.getHP() <= 0) //hvis fjendens liv er 0 eller under så skal den fjernes.
                    {
                        list.remove(enemy);
                        return;
                    }

                }
                //pApplet.keyCode = VK_BACK_SLASH;
            } else if (pApplet.keyCode == VK_J) //tjek om piletasten er trykket
            {
                for (int i = 0; i < list.size(); i++) // tag fat i listen af fjender
                {
                    //hvis fjenden er rigtigt ved siden af så skal den tage skade
                    if (list.get(i).getPvector().x == getDungeon().getPlayer().getpVector().x - 1 * getDungeon().getPlayer().getScale() && (list.get(i).getPvector().y == getDungeon().getPlayer().getpVector().y )) {
                        enemy = list.get(i);
                        break;
                    }
                }
                if (enemy != null) {
                    enemy.takeDMG(getDungeon().getPlayer().attack());
                    //list.get(i).getPvector().y -= 1 * getDungeon().getPlayer().getScale();
                    if (enemy.getHP() <= 0) //hvis fjendens liv er 0 eller under så skal den fjernes.
                    {
                        list.remove(enemy);
                        return;
                    }

                }
                //pApplet.keyCode = VK_BACK_SLASH;
            } else if (pApplet.keyCode == VK_L) //tjek om piletasten er trykket
            {
                for (int i = 0; i < list.size(); i++) // tag fat i listen af fjender
                {
                    //hvis fjenden er rigtigt ved siden af så skal den tage skade
                    if (list.get(i).getPvector().x == getDungeon().getPlayer().getpVector().x + 1 * getDungeon().getPlayer().getScale() && (list.get(i).getPvector().y == getDungeon().getPlayer().getpVector().y )) {
                        enemy = list.get(i);
                        break;
                    }
                }
                if (enemy != null) {
                    enemy.takeDMG(getDungeon().getPlayer().attack());
                    //list.get(i).getPvector().y -= 1 * getDungeon().getPlayer().getScale();
                    if (enemy.getHP() <= 0) //hvis fjendens liv er 0 eller under så skal den fjernes.
                    {
                        list.remove(enemy);
                        return;
                    }

                }
                //pApplet.keyCode = VK_BACK_SLASH;
            }
        }

    }

    public Dungeon getDungeon() {

        return dungeon;
    }

    public UI getUi() {
        return ui;
    }
}
