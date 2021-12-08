import Rooms.CombatRoom;
import Rooms.Room;
import Rooms.StartRoom;
import processing.core.PApplet;
import processing.core.PImage;

import static java.awt.event.KeyEvent.*;

public class Main extends PApplet {

    Room room;
    Controller controller;

    public static void main(String[] args)
    {

        PApplet.main(new String[]{Main.class.getName()});
    }

    @Override
    public void settings()
    {
        size(800, 640, P3D);
    }

    @Override
    public void setup()
    {
       //PImage icon = loadImage("src/icon.jpg");
       //surface.setIcon(icon); // sætter ikonet på processing vinduet
        surface.setTitle("Dungeon Crawler"); //sætter titlen på processing vinduet
        controller = new Controller(this);
        controller.StartDungeon();
        room = controller.getDungeon().getMap().getRoom(controller.getDungeon().getMap().currentLocation());
    }

    @Override
    public void draw()
    {
//         controller.getUi().gameStory(); //<|-- star wars tekst

        background(118, 72, 3); // prut farve
        ((CombatRoom)room).displayEnemies();
        room.display();
        controller.getDungeon().getPlayer().display();
        controller.combatDetection(room);
        controller.getDungeon().getPlayer().movement();

    }
}
