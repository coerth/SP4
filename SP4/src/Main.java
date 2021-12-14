import GameComponents.Controller;
import processing.core.PApplet;
import processing.opengl.PJOGL;

public class Main extends PApplet {

    Controller controller;

    public static void main(String[] args)
    {

        PApplet.main(new String[]{Main.class.getName()});
    }

    @Override
    public void settings()

    {
        size(800, 640, P3D);
        PJOGL.setIcon("Sprites/PlayerSprites/tile000.png");
    }

    @Override
    public void setup()
    {

        surface.setTitle("Dungeon Crawler"); //sætter titlen på processing vinduet
        controller = new Controller(this);


    }

    @Override
    public void draw()
    {
        controller.game();
    }
}
