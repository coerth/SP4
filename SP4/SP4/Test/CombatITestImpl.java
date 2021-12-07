import Entitys.Player;
import Interfaces.CombatI;
import processing.core.PApplet;

public class CombatITestImpl implements CombatITest {


    @Override
    public CombatI createNewCombat() {

        return new Player(new PApplet(),10,4);
    }


}
