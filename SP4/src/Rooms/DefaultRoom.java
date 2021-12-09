package Rooms;

import processing.core.PApplet;

import static processing.core.PConstants.SHAPE;

public class DefaultRoom extends Room{

    @Override
    public void display()
    {
        super.display();
        super.getpApplet().textMode(SHAPE);
        super.getpApplet().textSize(60);
        super.getpApplet().textAlign(super.getpApplet().CENTER);
        super.getpApplet().fill(87, 53, 3);
        super.getpApplet().text("DefaultRoom", super.getpApplet().width*0.5f, super.getpApplet().height*0.3f);
    }

    public DefaultRoom(PApplet pApplet) {
        super(pApplet);
    }
}
