package Rooms;

import Entitys.Player;
import processing.core.PApplet;

import static processing.core.PConstants.SHAPE;

public class RestRoom extends Room{
    private Bed bed;

    @Override
    public void display()
    {
        super.display();
        super.getpApplet().textMode(SHAPE);
        super.getpApplet().textSize(60);
        super.getpApplet().textAlign(super.getpApplet().CENTER);
        super.getpApplet().fill(87, 53, 3);
        super.getpApplet().text("RestRoom", super.getpApplet().width*0.5f, super.getpApplet().height*0.3f);
        bed.display();
    }

    public RestRoom(Bed bed, PApplet pApplet) {
        super(pApplet);
        this.bed = bed;
    }

    public void sleep(Player player, boolean interaction){

        if(player.getpVector().x <= bed.getpVector().x+bed.getBedWidth() && player.getpVector().x >= bed.getpVector().x && player.getpVector().y <= bed.getpVector().y+ bed.getBedHeight() && player.getpVector().y >= bed.getpVector().y && interaction )
        {
            player.setHP(player.getHP() + bed.replenishHp());
        }
    }
}
