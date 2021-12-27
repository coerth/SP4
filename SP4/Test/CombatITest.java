import Entitys.Entity;
import Interfaces.CombatI;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public interface CombatITest {
    CombatI createNewCombat();


    @Test
    default void attackTest()
    {
        CombatI c  = createNewCombat();
        assertEquals(4,c.attack());
    }

    @Test
    default void takeDMGTest()
    {
        CombatI c = createNewCombat();
        c.takeDMG(4);
        assertEquals(6, ((Entity)c).getHP());
    }


    /*@Test
    default void throwsArithmicException(){
        CombatI c = createNewCombat();
        assertThrows(ArithmeticException.class , () -> c.takeDMG(-1));
    }*/
}
