import Rooms.Room;
import Rooms.StartRoom;
import processing.core.PApplet;

public class Map {

    private PApplet pApplet;
    private Room[][] rooms;

    public Map(PApplet papplet) {
        this.pApplet = papplet;
        this.rooms = generateLayout();
    }

    public Room[][] generateLayout(){
    Room[][] rooms = new Room[8][8];
    rooms[(rooms.length/2)-1][(rooms[0].length/2)-1] = new StartRoom(pApplet); //startrummet vil altid være i midten af arrayet
        return rooms;
    }

    public int[] currentLocation(){
    int [] intArray = new int[2];

    intArray[0] = 3;
    intArray[1] = 3;

    return intArray;
    }

    public Room getRoom(int[] currentRoom) {

        return rooms[currentRoom[0]][currentRoom[1]];
    }
}
