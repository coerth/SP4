package GameComponents;

import Rooms.*;
import processing.core.PApplet;

import java.util.Random;

public class Map {

    private PApplet pApplet;
    private final int maxRooms = 32;
    private final int minRooms = 5;
    private int[] playerRoomPosition = {((maxRooms/2)/2)-1, ((maxRooms/2)/2)-1};
    private final Random rand = new Random();
    private int numOfRooms = rand.nextInt(minRooms, (maxRooms + 1));
    private int shopsInMap = 1;
    private int restRoomsInMap = 2;
    private Room[][] rooms;
    private int restRoomLastPlace = 6;

    public Map(PApplet papplet) {
        this.pApplet = papplet;
        this.rooms = generateLayout();
    }

    public Room[][] generateLayout() {
        Room[][] rooms = new Room[maxRooms/2][maxRooms/2]; // instantiere vores rooms array til at have maxRooms felter
        rooms[(rooms.length / 2) - 1][(rooms[0].length / 2) - 1] = new StartRoom(pApplet); //startrummet vil altid være i midten af arrayet
        int[] currentPos = {(rooms.length / 2) - 1, (rooms[0].length / 2) - 1}; //sætter vores start position for vores generator af layout
        for (int i = 0; i < numOfRooms; i++) { //kører igennem nedenstående kode indtil vi har ramt antallet af rum på vores map
            int dir;
            boolean roomPlaced = false; // en boolean så vi kan tjekke på om vi har en retning at placere et rum
            while (!roomPlaced) { // mens vi ikke har en retning at placere rummet i
                dir = rand.nextInt(4) + 1; // find en tilfældig retning
                switch (dir) { //kigger på den givende retning
                    case 1:
                        if (currentPos[0] == 0) { // tjekker om currentPosition er i en af hjørnerne
                            break;
                        } else if (rooms[currentPos[0] - 1][currentPos[1]] != null) { // hvis der er et rum i den retning den kigger, så sæt currentposition til det rum og prøv igen
                            currentPos[0] = currentPos[0] - 1;
                        } else { // ellers sæt rummet i den retning til at være det rum den får fra GetRandomRoom() og sæt vores current position til at være det nye rum og break med boolean roomplaced
                            rooms[currentPos[0] - 1][currentPos[1]] = GetRandomRoom();
                            currentPos[0] = currentPos[0] - 1;
                            roomPlaced = true;
                        }
                        break;
                    case 2:
                        if (currentPos[1] == rooms[0].length - 1) {
                            break;
                        } else if (rooms[currentPos[0]][currentPos[1] + 1] != null) {
                            currentPos[1] = currentPos[1] + 1;
                        } else {
                            rooms[currentPos[0]][currentPos[1] + 1] = GetRandomRoom();
                            currentPos[1] = currentPos[1] + 1;
                            roomPlaced = true;
                        }
                        break;
                    case 3:
                        if (currentPos[0] == rooms.length - 1) {
                            break;
                        } else if (rooms[currentPos[0] + 1][currentPos[1]] != null) {
                            currentPos[0] = currentPos[0] + 1;
                        } else {
                            rooms[currentPos[0] + 1][currentPos[1]] = GetRandomRoom();
                            currentPos[0] = currentPos[0] + 1;
                            roomPlaced = true;
                        }
                        break;
                    case 4:
                        if (currentPos[1] == 0) {
                            break;
                        } else if (rooms[currentPos[0]][currentPos[1] - 1] != null) {
                            currentPos[1] = currentPos[1] - 1;
                        } else {
                            rooms[currentPos[0]][currentPos[1] - 1] = GetRandomRoom();
                            currentPos[1] = currentPos[1] - 1;
                            roomPlaced = true;
                        }
                        break;
                }
            }
        }

        //KUN FOR AT VISE MAPPET I CONSOLEN!
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] != null)
                    System.out.print(1);
                else
                    System.out.print(0);
            }
            System.out.print("\n");
        }
        //KUN FOR AT VISE MAPPET I CONSOLEN!

        //GenerateDoors method
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == null) {
                    continue;
                }
                if (i == 0 && j == 0) {
                    if (rooms[i][j + 1] != null) {
                        rooms[i][j].SetRoomDirection('e');
                    }
                    if (rooms[i + 1][j] != null) {
                        rooms[i][j].SetRoomDirection('s');
                    }
                }
                else if(i == rooms.length - 1 && j == 0){
                    if (rooms[i - 1][j] != null) {
                        rooms[i][j].SetRoomDirection('n');
                    }
                    if (rooms[i][j + 1] != null) {
                        rooms[i][j].SetRoomDirection('e');
                    }
                }
                else if(i == 0 && j == rooms[0].length - 1){
                    if (rooms[i][j - 1] != null) {
                        rooms[i][j].SetRoomDirection('w');
                    }
                    if (rooms[i + 1][j] != null) {
                        rooms[i][j].SetRoomDirection('s');
                    }
                }
                else if(i == rooms.length - 1 && j == rooms[0].length - 1){
                    if (rooms[i][j - 1] != null) {
                        rooms[i][j].SetRoomDirection('w');
                    }
                    if (rooms[i - 1][j] != null) {
                        rooms[i][j].SetRoomDirection('n');
                    }
                }
                else if (i == 0 && j != 0 || i == 0 && j != rooms[0].length - 1){
                    if (rooms[i + 1][j] != null) {
                        rooms[i][j].SetRoomDirection('s');
                    }
                    if (rooms[i][j - 1] != null) {
                        rooms[i][j].SetRoomDirection('w');
                    }
                    if (rooms[i][j + 1] != null) {
                        rooms[i][j].SetRoomDirection('e');
                    }
                }
                else if(i != 0 && j == rooms[0].length - 1 || i != rooms.length - 1 && j == rooms[0].length - 1){
                    if (rooms[i + 1][j] != null) {
                        rooms[i][j].SetRoomDirection('s');
                    }
                    if (rooms[i][j - 1] != null) {
                        rooms[i][j].SetRoomDirection('w');
                    }
                    if (rooms[i - 1][j] != null) {
                        rooms[i][j].SetRoomDirection('n');
                    }
                }
                else if(i == rooms.length - 1 && j != 0 || i == rooms.length - 1 && j != rooms[0].length - 1){
                    if (rooms[i][j - 1] != null) {
                        rooms[i][j].SetRoomDirection('w');
                    }
                    if (rooms[i - 1][j] != null) {
                        rooms[i][j].SetRoomDirection('n');
                    }
                    if (rooms[i][j + 1] != null) {
                        rooms[i][j].SetRoomDirection('e');
                    }
                }
                else if(i != 0  && j == 0 || i != rooms.length - 1 && j == 0) {
                    if (rooms[i - 1][j] != null) {
                        rooms[i][j].SetRoomDirection('n');
                    }
                    if (rooms[i][j + 1] != null) {
                        rooms[i][j].SetRoomDirection('e');
                    }
                    if (rooms[i + 1][j] != null) {
                        rooms[i][j].SetRoomDirection('s');
                    }
                }
                else{
                    if (rooms[i - 1][j] != null) {
                        rooms[i][j].SetRoomDirection('n');
                    }
                    if (rooms[i][j + 1] != null) {
                        rooms[i][j].SetRoomDirection('e');
                    }
                    if (rooms[i + 1][j] != null) {
                        rooms[i][j].SetRoomDirection('s');
                    }
                    if (rooms[i][j - 1] != null) {
                        rooms[i][j].SetRoomDirection('w');
                    }
                }
            }
        }
        //GenerateDoors method

        return rooms;
    }

    private Room GetRandomRoom() {
        Room roomToGenerate = null;
        boolean roomFound = false;
        int randomRoom;
        while (!roomFound) {
            randomRoom = rand.nextInt(3) + 1;
            switch (randomRoom) {
                case 1:
                    if (shopsInMap == 0) {
                        break;
                    } else {
                        roomToGenerate = new ShopRoom(pApplet);
                        shopsInMap--;
                        roomFound = true;
                    }
                    break;
                case 2:
                    if (restRoomsInMap == 0 || restRoomLastPlace < 5) {
                        restRoomLastPlace++;
                        break;
                    } else {
                        roomToGenerate = new RestRoom(new Bed(pApplet), pApplet);
                        restRoomsInMap--;
                        restRoomLastPlace = 0;
                        roomFound = true;
                    }
                    break;
                case 3:
                    roomToGenerate = new CombatRoom(pApplet);
                    roomFound = true;
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
        intArray[0] = playerRoomPosition[0];
        intArray[1] = playerRoomPosition[1];
        return intArray;
    }

    public Room getRoom(int[] currentRoom) {
        return rooms[currentRoom[0]][currentRoom[1]];
    }

    public void setPlayerRoomPosition(int y, int x) {
        this.playerRoomPosition[0] = y;
        this.playerRoomPosition[1] = x;
    }

    public Room[][] getRooms() {
        return rooms;
    }
}
