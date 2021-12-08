import Rooms.*;
import processing.core.PApplet;

import java.util.Random;

public class Map {

    private PApplet pApplet;
    private int maxRooms = 32;
    private Random rand = new Random();
    private int numOfRooms = rand.nextInt(maxRooms) + 1;
    private int shopsInMap = 1;
    private int restRoomsInMap = 2;
    private Room[][] rooms;

    public Map(PApplet papplet) {
        this.pApplet = papplet;
        this.rooms = generateLayout();
    }

    public Room[][] generateLayout() {
        Room[][] rooms = new Room[maxRooms][maxRooms]; // instantiere vores rooms array til at have maxRooms felter
        rooms[(rooms.length / 2) - 1][(rooms[0].length / 2) - 1] = new CombatRoom(pApplet); //startrummet vil altid være i midten af arrayet
        int[] currentPos = {(rooms.length / 2) - 1, (rooms[0].length / 2) - 1}; //sætter vores start position for vores generator af layout
        for (int i = 0; i < numOfRooms; i++) { //kører igennem nedenstående kode indtil vi har ramt antallet af rum på vores map
            int dir;
            boolean roomPlaced = false; // en boolean så vi kan tjekke på om vi har en retning at placere et rum
            while (!roomPlaced) { // mens vi ikke har en retning at placere rummet i
                dir = rand.nextInt(4) + 1; // find en tilfældig retning
                switch (dir) { //kigger på den givende retning
                    case 1:
                        if (currentPos[0] == 0 || rooms[currentPos[0] - 1][currentPos[1]] != null) { // tjekker om currentPosition er i en af hjørnerne eller der ligger et rum i den retning, hvis sand skal den break
                            break;
                        } else { // ellers sæt rummet i den retning til at være det rum den får fra GetRandomRoom() og sæt vores current position til at være det nye rum og break med boolean roomplaced
                            rooms[currentPos[0] - 1][currentPos[1]] = GetRandomRoom();
                            currentPos[0] = currentPos[0] - 1;
                            currentPos[1] = currentPos[1];
                            roomPlaced = true;
                        }
                        break;
                    case 2:
                        if (currentPos[1] == rooms[0].length - 1 || rooms[0][currentPos[1] + 1] != null) {
                            break;
                        } else {
                            rooms[currentPos[0]][currentPos[1] + 1] = GetRandomRoom();
                            currentPos[0] = currentPos[0];
                            currentPos[1] = currentPos[1] + 1;
                            roomPlaced = true;
                        }
                        break;
                    case 3:
                        if (currentPos[0] == rooms.length - 1 || rooms[currentPos[0] + 1][currentPos[1]] != null) {
                            break;
                        } else {
                            rooms[currentPos[0] + 1][currentPos[1]] = GetRandomRoom();
                            currentPos[0] = currentPos[0] + 1;
                            currentPos[1] = currentPos[1];
                            roomPlaced = true;
                        }
                        break;
                    case 4:
                        if (currentPos[1] == 0 || rooms[currentPos[0]][currentPos[1] - 1] != null) {
                            break;
                        } else {
                            rooms[currentPos[0]][currentPos[1] - 1] = GetRandomRoom();
                            currentPos[0] = currentPos[0];
                            currentPos[1] = currentPos[1] - 1;
                            roomPlaced = true;
                        }
                        break;
                }
            }
        }
        return rooms;
    }

    private Room GetRandomRoom() {
        Room roomToGenerate = null;
        int randomRoom;
        while (roomToGenerate == null) {
            randomRoom = rand.nextInt(4) + 1;
            switch (randomRoom) {
                case 1:
                    if (shopsInMap == 0) {
                        break;
                    } else {
                        roomToGenerate = new ShopRoom(pApplet);
                        shopsInMap--;
                    }
                    break;
                case 2:
                    if (restRoomsInMap == 0) {
                        break;
                    } else {
                        roomToGenerate = new RestRoom(new Bed(pApplet), pApplet);
                        restRoomsInMap--;
                    }
                    break;
                case 3:
                    roomToGenerate = new CombatRoom(pApplet);
                    break;
                case 4:
                    roomToGenerate = new DefaultRoom(pApplet);
                    break;
            }
        }
        return roomToGenerate;
    }

    public int[] currentLocation() {
        int[] intArray = new int[2];
        /*for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == null)
                    continue;
                if (rooms[i][j] instanceof StartRoom) {
                    intArray[0] = i;
                    intArray[1] = j;
                }
            }
        }*/
        intArray[0] = (rooms.length / 2) - 1;
        intArray[1] = (rooms[0].length / 2) - 1;
        return intArray;
    }

    public Room getRoom(int[] currentRoom) {
        return rooms[currentRoom[0]][currentRoom[1]];
    }
}
