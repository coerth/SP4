import Entitys.Enemies;
import Rooms.CombatRoom;
import Rooms.Room;
import processing.core.PApplet;
import static java.awt.event.KeyEvent.*;

public class Controller {
    private PApplet pApplet;
    private Dungeon dungeon;
    private UI ui;

    public Controller(PApplet pApplet) {

        this.pApplet = pApplet;
        this.ui = new UI(pApplet);
    }

    public void StartDungeon(){

        dungeon = new Dungeon(pApplet);
    }

    public void combatDetection(Room room)
    {
        if(pApplet.keyCode == VK_UP) //tjek om piletasten er trykket
        {
            if(room instanceof CombatRoom) //tjek om det pågældende rum er et combatrum
            {

                for(int i = 0; i < ((CombatRoom) room).getList().size(); i++) // tag fat i listen af fjender
                {
                    //hvis fjenden er rigtigt ved siden af så skal den tage skade
                    if(((CombatRoom) room).getList().get(i).getPvector().x == getDungeon().getPlayer().getpVector().x && ((CombatRoom) room).getList().get(i).getPvector().y == getDungeon().getPlayer().getpVector().y - 1 * getDungeon().getPlayer().getScale())
                    {
                        ((CombatRoom) room).getList().get(i).takeDMG(getDungeon().getPlayer().attack());
                    }
                }
            }
        }
        if(pApplet.keyCode == VK_DOWN) //tjek om piletasten er trykket
        {
            if(room instanceof CombatRoom) //tjek om det pågældende rum er et combatrum
            {
                for(int i = 0; i < ((CombatRoom) room).getList().size(); i++) // tag fat i listen af fjender
                {
                    //hvis fjenden er rigtigt ved siden af så skal den tage skade
                    if(((CombatRoom) room).getList().get(i).getPvector().x == getDungeon().getPlayer().getpVector().x && ((CombatRoom) room).getList().get(i).getPvector() .y == getDungeon().getPlayer().getpVector().y + 1 * getDungeon().getPlayer().getScale())
                    {
                        ((CombatRoom) room).getList().get(i).takeDMG(getDungeon().getPlayer().attack());
                    }
                }
            }
        }
        if(pApplet.keyCode == VK_LEFT) //tjek om piletasten er trykket
        {
            if(room instanceof CombatRoom) //tjek om det pågældende rum er et combatrum
            {
                for(int i = 0; i < ((CombatRoom) room).getList().size(); i++) // tag fat i listen af fjender
                {
                    //hvis fjenden er rigtigt ved siden af så skal den tage skade
                    if(((CombatRoom) room).getList().get(i).getPvector().x == getDungeon().getPlayer().getpVector().x - 1 * getDungeon().getPlayer().getScale() && ((CombatRoom) room).getList().get(i).getPvector().y == getDungeon().getPlayer().getpVector().y)
                    {
                        ((CombatRoom) room).getList().get(i).takeDMG(getDungeon().getPlayer().attack());
                    }
                }
            }
        }
        if(pApplet.keyCode == VK_RIGHT) //tjek om piletasten er trykket
        {
            if(room instanceof CombatRoom) //tjek om det pågældende rum er et combatrum
            {
                for(int i = 0; i < ((CombatRoom) room).getList().size(); i++) // tag fat i listen af fjender
                {
                    //hvis fjenden er rigtigt ved siden af så skal den tage skade
                    if(((CombatRoom) room).getList().get(i).getPvector().x == getDungeon().getPlayer().getpVector().x + 1 * getDungeon().getPlayer().getScale() && ((CombatRoom) room).getList().get(i).getPvector().y == getDungeon().getPlayer().getpVector().y)
                    {
                        ((CombatRoom) room).getList().get(i).takeDMG(getDungeon().getPlayer().attack());
                    }
                }
            }
        }

    }

    public Dungeon getDungeon() {

        return dungeon;
    }

    public UI getUi() {
        return ui;
    }
}
