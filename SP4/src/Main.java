import Entitys.Enemies;
import Rooms.CombatRoom;
import Rooms.Room;
import processing.core.PApplet;

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
        //controller.StartDungeon();


    }

    @Override
    public void draw()
    {
//         controller.getUi().gameStory(); //<|-- star wars tekst

        controller.runGame();

    }
}
