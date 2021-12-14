package Rooms;

import Entitys.Demon;
import Entitys.Player;
import processing.core.PApplet;
import processing.core.PVector;

public class BossRoom extends CombatRoom {

    private EscapeHatch escapeHatch = new EscapeHatch(getpApplet());

    public BossRoom(PApplet pApplet, int difficulty) {
        super(pApplet, difficulty);
        super.setRoomName("BossRoom");
    }

    @Override
    public void processEnemies(Player player){
        super.processEnemies(player);
        unlockDescend();

    }

    @Override
    public void display()
    {
        super.display();
        escapeHatch.display();
    }

    @Override
    public void spawnEnemies(){
        super.getList().add(new Demon(getpApplet(),getDifficulty(), new PVector(12,10)));
    }


    public void unlockDescend(){
        if(super.getList().size() == 0)
        {
            escapeHatch.setOpen(true);
        }
    }

    public boolean proceedWithDescend(Player player){
        if (escapeHatch.isOpen() && player.interact() && escapeHatch.getpVector().x == player.getCurrentPvector().x && escapeHatch.getpVector().y == player.getCurrentPvector().y){
            return true;
        }

        return false;
    }

}
