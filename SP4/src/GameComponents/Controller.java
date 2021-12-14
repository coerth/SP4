package GameComponents;

import Entitys.Player;
import Rooms.*;
import processing.core.PApplet;

import static java.awt.event.KeyEvent.VK_B;
import static java.awt.event.KeyEvent.VK_BACK_SLASH;

public class Controller {
    private PApplet pApplet;
    private Dungeon dungeon;
    private UI ui;
    private CollisionDetector collisionDetector;
    private boolean startScreen = false;
    private boolean endGameScreen = false;
    private boolean endGameRetry = false;

    public Controller(PApplet pApplet) {

        this.pApplet = pApplet;
        this.ui = new UI(pApplet);
        this.dungeon = new Dungeon(pApplet);
        this.collisionDetector = new CollisionDetector(pApplet,dungeon);
    }

    public void game()
    {
       if(!startScreen) //start skærm indtil den bliver sat til false
        {
            startScreen = ui.startMenu();
        }
        else
        {
            if(!endGameScreen)
            {
                runGame(); //spillet kører indtil endGameScreen bliver sat til true
            }

            else
            {
                endGameRetry = ui.gameOverMenu();

                if(endGameRetry && ui.getEndGameOption() == 0) //hvis der bliver trykket E og retry var selected så skal spillet startes forfra
                {
                    endGameScreen = !endGameScreen;
                    restartGame();
                }
                else if(endGameRetry && ui.getEndGameOption() == 1) //hvis der bliver trykket E og return to menu var selected så skal vi tilbage til start menuen og spillet forbederes til start
                {
                    restartGame();
                    startScreen = !startScreen;
                    endGameScreen = !endGameScreen;
                    pApplet.keyCode = VK_BACK_SLASH;
                }
            }
        }

    }

    public void runGame()
    {
       Room room = dungeon.getMap().getRoom(dungeon.getMap().currentLocation());
       pApplet.background(118, 72, 3); // prut farve
       ui.statsBar(dungeon.getPlayer(), dungeon);
        room.display();
        if(room instanceof BossRoom)
        {
            ((CombatRoom) room).processEnemies(getDungeon().getPlayer());

            if (((BossRoom) room).proceedWithDescend(getDungeon().getPlayer()))
            {
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
        if(dungeon.getPlayer().getHP() <= 0)
        {
            endGameScreen = !endGameScreen;
        }
        collisionDetector.collisionRoomPlayer(room);
        resetKeyCode();
    }

    public void restartGame() //laver ny spiller og map
    {
        dungeon.setMap(new Map(pApplet, 0));
        dungeon.setPlayer(new Player(pApplet));
    }

    public void resetKeyCode(){
        pApplet.keyCode = VK_BACK_SLASH;
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
