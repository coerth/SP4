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
    private final int scale = 32;
    private String roomName = "";

    public int getScale() {
        return scale;
    }

    public Room(PApplet pApplet) {
        this.pApplet = pApplet;
        grid = new int[pApplet.height / 32][pApplet.width / 32];
    }

    public void SetRoomDirection(char direction) {
        switch (direction){
            case 'w':
                westRoom = true;
                break;
            case 'e':
                eastRoom = true;
                break;
            case 's':
                southRoom = true;
                break;
            case 'n':
                northRoom = true;
                break;
        }
    }

    @Override
    public void display() {
        /*for (int i = 0; i < 800 / 32; i++) {
            pApplet.line(i * 32, 0, i * 32, 640);
            pApplet.line(0, i * 32, 800, i * 32);
        }*/

        pApplet.strokeWeight(10);
        pApplet.line(0, 0, pApplet.width, 0);
        pApplet.line(0, 0, 0, pApplet.width);
        pApplet.line(0, pApplet.height, pApplet.width, pApplet.height);
        pApplet.line(pApplet.width, 0, pApplet.width, pApplet.height);

        if (eastRoom) {
            pApplet.stroke(87, 53, 3);
            pApplet.line(pApplet.width, 250, pApplet.width, 350);
            pApplet.stroke(0);
        }
        if (westRoom) {
            pApplet.stroke(87, 53, 3);
            pApplet.line(0, 250, 0, 350);
            pApplet.stroke(0);
        }
        if (northRoom) {
            pApplet.stroke(87, 53, 3);
            pApplet.line(350, 0, 450, 0);
            pApplet.stroke(0);
        }
        if(southRoom){
            pApplet.stroke(87, 53, 3);
            pApplet.line(350, pApplet.height, 450, pApplet.height);
            pApplet.stroke(0);
        }

        pApplet.strokeWeight(0);
    }

    public PApplet getpApplet() {
        return pApplet;
    }

    public boolean hasNorthRoom() {
        return northRoom;
    }

    public boolean hasSouthRoom() {
        return southRoom;
    }

    public boolean hasWestRoom() {
        return westRoom;
    }

    public boolean hasEastRoom() {
        return eastRoom;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomName() {
        return roomName;
    }
}
