package GameComponents;

import Entitys.Player;
import processing.core.PApplet;

public class Dungeon {

    private PApplet pApplet;
    private Map map;
    private Player player;
    private int difficulty = 0;

    public Dungeon(PApplet pApplet) {
        this.pApplet = pApplet;
        this.map = new Map(pApplet, difficulty);
        this.player = new Player(pApplet, 10, 4);
    }

    public Map getMap() {
        return map;
    }

    public Player getPlayer() {
        return player;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void startNextFloor(){
        difficulty++;
        map = new Map(pApplet,difficulty);
    }
}
