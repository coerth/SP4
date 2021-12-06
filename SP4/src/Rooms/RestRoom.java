package Rooms;

import processing.core.PApplet;

public class RestRoom extends Room{
    private Bed bed;

    public RestRoom(Bed bed, PApplet pApplet) {
        super(pApplet);
        this.bed = bed;
    }
}
