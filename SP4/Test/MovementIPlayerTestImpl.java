import Entitys.Entity;
import Entitys.Player;
import Interfaces.MovementI;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;
import processing.core.PVector;

import static java.awt.event.KeyEvent.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovementIPlayerTestImpl implements MovementITest {

    @Override
    public MovementI createNewMovement()
    {
        return new Player(new PApplet(),10,4);
    }

    @Test
    void moveNorthPlayerTest()
    {
        MovementI m = createNewMovement();

        //skal downcastes til Player for at tilgå de variabler
        assertEquals(12*((Player)m).getScale(), ((Player)m).getpVector().x);
        assertEquals(10*((Player)m).getScale(), ((Player)m).getpVector().y);

        //skal downcastes til Entity for at tilgå de variabler
        ((Entity)m).getpApplet().keyCode = VK_W;
        m.movement();

        assertEquals(12*((Player)m).getScale(), ((Player)m).getpVector().x);
        assertEquals(9*((Player)m).getScale(), ((Player)m).getpVector().y);
    }

    @Test
    void moveSouthPlayerTest()
    {
        MovementI m = createNewMovement();

        assertEquals(12*((Player)m).getScale(), ((Player)m).getpVector().x);
        assertEquals(10*((Player)m).getScale(), ((Player)m).getpVector().y);

        ((Entity)m).getpApplet().keyCode = VK_S;
        m.movement();

        assertEquals(12*((Player)m).getScale(), ((Player)m).getpVector().x);
        assertEquals(11*((Player)m).getScale(), ((Player)m).getpVector().y);
    }

    @Test
    void moveWestPlayerTest()
    {
        MovementI m = createNewMovement();

        assertEquals(12*((Player)m).getScale(), ((Player)m).getpVector().x);
        assertEquals(10*((Player)m).getScale(), ((Player)m).getpVector().y);

        ((Entity)m).getpApplet().keyCode = VK_A;
        m.movement();

        assertEquals(11*((Player)m).getScale(), ((Player)m).getpVector().x);
        assertEquals(10*((Player)m).getScale(), ((Player)m).getpVector().y);
    }

    @Test
    void moveEastPlayerTest()
    {
        MovementI m = createNewMovement();

        assertEquals(12*((Player)m).getScale(), ((Player)m).getpVector().x);
        assertEquals(10*((Player)m).getScale(), ((Player)m).getpVector().y);

        ((Entity)m).getpApplet().keyCode = VK_D;
        m.movement();

        assertEquals(13*((Player)m).getScale(), ((Player)m).getpVector().x);
        assertEquals(10*((Player)m).getScale(), ((Player)m).getpVector().y);
    }

    @Test
    void northBoundaryTest()
    {
        MovementI m = createNewMovement();
        ((Player)m).setpVector(new PVector(1*((Player) m).getScale(),1*((Player) m).getScale()));

        assertEquals(1*((Player)m).getScale(), ((Player)m).getpVector().x);
        assertEquals(1*((Player)m).getScale(), ((Player)m).getpVector().y);

        ((Entity)m).getpApplet().keyCode = VK_W;
        m.movement();

        assertEquals(1*((Player)m).getScale(), ((Player)m).getpVector().x);
        assertEquals(1*((Player)m).getScale(), ((Player)m).getpVector().y);
    }

    @Test
    void westBoundaryTest()
    {
        MovementI m = createNewMovement();
        ((Player)m).setpVector(new PVector(1*((Player) m).getScale(),1*((Player) m).getScale()));

        assertEquals(1*((Player)m).getScale(), ((Player)m).getpVector().x);
        assertEquals(1*((Player)m).getScale(), ((Player)m).getpVector().y);

        ((Entity)m).getpApplet().keyCode = VK_A;
        m.movement();

        assertEquals(1*((Player)m).getScale(), ((Player)m).getpVector().x);
        assertEquals(1*((Player)m).getScale(), ((Player)m).getpVector().y);
    }

    @Test
    void southBoundaryTest()
    {
        MovementI m = createNewMovement();
        ((Player)m).setpVector(new PVector(25*((Player) m).getScale(),20*((Player) m).getScale()));

        assertEquals(25*((Player)m).getScale(), ((Player)m).getpVector().x);
        assertEquals(20*((Player)m).getScale(), ((Player)m).getpVector().y);

        ((Entity)m).getpApplet().keyCode = VK_S;
        m.movement();

        assertEquals(25*((Player)m).getScale(), ((Player)m).getpVector().x);
        assertEquals(20*((Player)m).getScale(), ((Player)m).getpVector().y);
    }

    @Test
    void eastBoundaryTest()
    {
        MovementI m = createNewMovement();
        ((Player)m).setpVector(new PVector(25*((Player) m).getScale(),20*((Player) m).getScale()));

        assertEquals(25*((Player)m).getScale(), ((Player)m).getpVector().x);
        assertEquals(20*((Player)m).getScale(), ((Player)m).getpVector().y);

        ((Entity)m).getpApplet().keyCode = VK_D;
        m.movement();

        assertEquals(25*((Player)m).getScale(), ((Player)m).getpVector().x);
        assertEquals(20*((Player)m).getScale(), ((Player)m).getpVector().y);
    }


}
