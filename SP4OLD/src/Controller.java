import Entitys.Enemies;
import Rooms.CombatRoom;
import Rooms.Room;
import processing.core.PApplet;
import static java.awt.event.KeyEvent.*;

public class Controller {
    private PApplet pApplet;
    private Dungeon dungeon;
    private UI ui;

    public Controller(PApplet pApplet) {

        this.pApplet = pApplet;
        this.ui = new UI(pApplet);
    }

    public void StartDungeon(){

        dungeon = new Dungeon(pApplet);
    }



    public Dungeon getDungeon() {

        return dungeon;
    }

    public UI getUi() {
        return ui;
    }
}
