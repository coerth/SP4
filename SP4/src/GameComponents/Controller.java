package GameComponents;

import Rooms.*;
import processing.core.PApplet;

public class Controller {
    private PApplet pApplet;
    private Dungeon dungeon;
    private UI ui;
    private CollisionDetector collisionDetector;
    private boolean startScreen = false;

    public Controller(PApplet pApplet) {

        this.pApplet = pApplet;
        this.ui = new UI(pApplet);
        this.dungeon = new Dungeon(pApplet);
        this.collisionDetector = new CollisionDetector(pApplet,dungeon);
    }

    public void game(){
        if(!startScreen)
        {
            startScreen = ui.startMenu();
        }
        else {
            runGame();
        }

    }

    public void runGame(){
       Room room = dungeon.getMap().getRoom(dungeon.getMap().currentLocation());
       pApplet.background(118, 72, 3); // prut farve
       ui.statsBar(dungeon.getPlayer());
        room.display();
        if(room instanceof BossRoom) {
            ((CombatRoom) room).processEnemies(getDungeon().getPlayer());

            if (((BossRoom) room).proceedWithDescend(getDungeon().getPlayer())) {
                dungeon.startNextFloor();
                //new map layout
            }
        }
            else if(room instanceof CombatRoom)
            {
            ((CombatRoom)room).processEnemies(getDungeon().getPlayer());
        }
            else if(room instanceof ShopRoom)
            {
            ((ShopRoom)room).exchangeItems(((ShopRoom) room).buyItem(dungeon.getPlayer(),dungeon.getPlayer().interact()), getDungeon().getPlayer());
        }
            else if(room instanceof RestRoom)
            {
            ((RestRoom)room).sleep(dungeon.getPlayer(), dungeon.getPlayer().interact());
        }

        collisionDetector.combatDetection(room);
        dungeon.getMap().showMiniMap();
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
