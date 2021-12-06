import processing.core.PApplet;

public class Controller {
    private PApplet pApplet;
    private Dungeon dungeon;
    private UI ui;

    public Controller(PApplet pApplet) {
        this.pApplet = pApplet;
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
