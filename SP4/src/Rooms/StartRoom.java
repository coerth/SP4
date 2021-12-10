package Rooms;

import processing.core.PApplet;
import static processing.core.PConstants.SHAPE;

public class StartRoom extends Room{

    public StartRoom(PApplet pApplet) {
        super(pApplet);
    }

    @Override
    public void display()
    {
       super.display();
       displayControls();
    }

    public void displayControls(){

        super.getpApplet().textMode(SHAPE);
        super.getpApplet().textSize(60);
        super.getpApplet().textAlign(super.getpApplet().CENTER);
        super.getpApplet().fill(87, 53, 3);
        super.getpApplet().text("WASD to move", super.getpApplet().width*0.5f, super.getpApplet().height*0.3f);
        super.getpApplet().text("Arrow keys to attack", super.getpApplet().width*0.5f, super.getpApplet().height*0.2f);
        super.getpApplet().text("E to interact", super.getpApplet().width*0.5f, super.getpApplet().height*0.4f);
    }
}
