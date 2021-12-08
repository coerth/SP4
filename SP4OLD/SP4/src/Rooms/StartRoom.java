package Rooms;

import Entitys.Enemies;
import Entitys.Entity;
import Entitys.Skeleton;
import processing.core.PApplet;
import static processing.core.PConstants.SHAPE;

public class StartRoom extends Room{

    public StartRoom(PApplet pApplet) {
        super(pApplet);
    }

    public void displayControls(){

        super.getpApplet().textMode(SHAPE);
        super.getpApplet().textSize(60);
        super.getpApplet().textAlign(super.getpApplet().CENTER);
        super.getpApplet().fill(87, 53, 3);
        super.getpApplet().text("WASD to move", super.getpApplet().width*0.5f, super.getpApplet().height*0.3f);
    }
}