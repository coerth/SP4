import Entitys.Enemies;
import Entitys.Player;
import Entitys.Wizard;
import Rooms.CombatRoom;
import Rooms.Room;
import processing.core.PApplet;

import java.util.ArrayList;

import static java.awt.event.KeyEvent.*;

public class Controller {
    private PApplet pApplet;
    private Dungeon dungeon;
    private UI ui;
    private CollisionDetector collisionDetector;

    public Controller(PApplet pApplet) {

        this.pApplet = pApplet;
        this.ui = new UI(pApplet);
        this.dungeon = new Dungeon(pApplet);
        this.collisionDetector = new CollisionDetector(pApplet,dungeon);
    }

    public void runGame(){
       Room room = dungeon.getMap().getRoom(dungeon.getMap().currentLocation());
       pApplet.background(118, 72, 3); // prut farve
       ui.statsBar(dungeon.getPlayer());
        room.display();
        if(room instanceof CombatRoom)
        {
            ((CombatRoom)room).processEnemies(getDungeon().getPlayer());
        }
        collisionDetector.combatDetection(room);
        dungeon.getPlayer().processPlayer();
        collisionDetector.collisionRoomPlayer(room);
    }



    public CollisionDetector getCollisionDetector() {
        return collisionDetector;
    }

    public Dungeon getDungeon() {

        return dungeon;
    }

    public UI getUi() {
        return ui;
    }

}
