import Entitys.Player;
import processing.core.PApplet;

public class Dungeon {

    private Map map;
    private Player player;

    public Dungeon(PApplet pApplet) {
        this.map = new Map(pApplet);
        this.player = new Player(pApplet, 10, 4);
    }

    public Map getMap() {
        return map;
    }

    public Player getPlayer() {
        return player;
    }
}
