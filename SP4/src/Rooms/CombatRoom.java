package Rooms;
import Entitys.Enemies;
import processing.core.PApplet;

import java.util.ArrayList;

public class CombatRoom extends Room{

    ArrayList<Enemies> list = new ArrayList<>();

    public CombatRoom(PApplet pApplet) {
        super(pApplet);
    }
}
