package Rooms;

import Interfaces.RoomI;
import processing.core.PApplet;

import java.awt.*;

public abstract class Room implements RoomI {

    private PApplet pApplet;
    private boolean northRoom = false;
    private boolean southRoom = false;
    private boolean westRoom = false;
    private boolean eastRoom = false;
    private int[][] grid;

    public Room(PApplet pApplet) {
        this.pApplet = pApplet;
        grid = new int[pApplet.height/32][pApplet.width/32];
    }

    public void SetRoomDirection(char direction){
        if(direction == 'w'){
            westRoom = true;
        }else if(direction == 'e'){
            eastRoom = true;
        }else if(direction == 's'){
            southRoom = true;
        }else if(direction == 'n'){
            northRoom = true;
        }
    }

    @Override
    public void display(){
        for (int i = 0; i < 800 / 32; i++) {
            pApplet.line(i*32, 0, i*32, 640);
            pApplet.line(0, i*32, 800, i*32);
        }
        pApplet.strokeWeight(10);
        pApplet.line(0,0, pApplet.width, 0);
        pApplet.line(0,0,0, pApplet.width);
        pApplet.line(0, pApplet.height, pApplet.width, pApplet.height);
        pApplet.line(pApplet.width, 0, pApplet.width, pApplet.height);

        pApplet.strokeWeight(0);
    }

    public PApplet getpApplet() {
        return pApplet;
    }
}
