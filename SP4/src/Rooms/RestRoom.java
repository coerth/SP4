package Rooms;

import Entitys.Player;
import processing.core.PApplet;

import static processing.core.PConstants.SHAPE;

public class RestRoom extends Room{
    private Bed bed;

    public RestRoom(Bed bed, PApplet pApplet) {
        super(pApplet);
        setRoomName("RestRoom");
        this.bed = bed;
    }

    @Override
    public void display()
    {
        super.display();
        super.getpApplet().textMode(SHAPE);
        super.getpApplet().textSize(60);
        super.getpApplet().textAlign(super.getpApplet().CENTER);
        super.getpApplet().fill(87, 53, 3);
        super.getpApplet().text(getRoomName(), super.getpApplet().width*0.5f, super.getpApplet().height*0.3f);
        bed.display();
    }

    public void sleep(Player player, boolean interaction){

        if(player.getCurrentPvector().x <= bed.getpVector().x+bed.getBedWidth() && player.getCurrentPvector().x >= bed.getpVector().x && player.getCurrentPvector().y <= bed.getpVector().y+ bed.getBedHeight() && player.getCurrentPvector().y >= bed.getpVector().y && interaction )
        {
            player.setHP(player.getHP() + bed.replenishHp());
        }
    }
}
