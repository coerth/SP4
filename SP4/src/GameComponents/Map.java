package GameComponents;

import Rooms.*;
import processing.core.PApplet;

import java.awt.*;
import java.util.Random;

import static java.awt.event.KeyEvent.VK_M;

public class Map {

    private PApplet pApplet;
    private final int maxRooms = 32;
    private final int minRooms = 15;
    private int[] playerRoomPosition = {((maxRooms/2)/2)-1, ((maxRooms/2)/2)-1};
    private final Random rand = new Random();
    private int numOfRooms = rand.nextInt(minRooms, (maxRooms + 1));
    private int shopsInMap = 1;
    private int restRoomsInMap = 2;
    private Color[] roomColors = new Color[4];
    private Room[][] rooms;
    private int restRoomLastPlace = 6;
    private int[][][] minimap;
    private int difficulty;
    private boolean displayMinimap = false;

    public Map(PApplet papplet, int difficulty) {
        this.pApplet = papplet;
        this.difficulty = difficulty;
        this.rooms = generateLayout();
        this.roomColors[0] = new Color(255, 128, 0);
        this.roomColors[1] = new Color(204, 0, 0);
        this.roomColors[2] = new Color(0, 0, 204);
        this.roomColors[3] = new Color(204, 0, 0);
        generateMiniMap();
    }

    public Room[][] generateLayout() {
        rooms = new Room[maxRooms / 2][maxRooms / 2]; // instantiere vores rooms array til at have maxRooms felter
        //rooms[(rooms.length / 2) - 1][(rooms[0].length / 2) - 1] = new StartRoom(pApplet); //startrummet vil altid være i midten af arrayet
        rooms[(rooms.length / 2) - 1][(rooms[0].length / 2) - 1] = new BossRoom(pApplet, difficulty); //startrummet vil altid være i midten af arrayet
        int[] currentPos = {(rooms.length / 2) - 1, (rooms[0].length / 2) - 1}; //sætter vores start position for vores generator af layout
        for (int i = 0; i < numOfRooms; i++) { //kører igennem nedenstående kode indtil vi har ramt antallet af rum på vores map
            int dir;
            boolean roomPlaced = false; // en boolean så vi kan tjekke på om vi har en retning at placere et rum
            while (!roomPlaced) { // mens vi ikke har en retning at placere rummet i
                Room roomToPlace;
                if(i == numOfRooms - 1) // hvis det er den sidste itteration/sidste rum, placer bossrum
                {
                    roomToPlace = new BossRoom(pApplet, difficulty);
                }
                else // ellers placer et random rum
                {
                    roomToPlace = GetRandomRoom();
                }
                dir = rand.nextInt(4) + 1; // find en tilfældig retning
                switch (dir) { //kigger på den givende retning
                    case 1: // kigger op
                        if (currentPos[0] == 0) { // tjekker om currentPosition er i en af hjørnerne
                            break;
                        } else if (rooms[currentPos[0] - 1][currentPos[1]] != null) { // hvis der er et rum i den retning den kigger, så sæt currentposition til det rum og prøv igen
                            currentPos[0] = currentPos[0] - 1;
                        } else { // ellers sæt rummet i den retning til at være det rum den får fra GetRandomRoom() og sæt vores current position til at være det nye rum og break med boolean roomplaced
                            rooms[currentPos[0] - 1][currentPos[1]] = roomToPlace;
                            currentPos[0] = currentPos[0] - 1;
                            roomPlaced = true;
                        }
                        break;
                    case 2: // kigger til højre
                        if (currentPos[1] == rooms[0].length - 1) {
                            break;
                        } else if (rooms[currentPos[0]][currentPos[1] + 1] != null) {
                            currentPos[1] = currentPos[1] + 1;
                        } else {
                            rooms[currentPos[0]][currentPos[1] + 1] = roomToPlace;
                            currentPos[1] = currentPos[1] + 1;
                            roomPlaced = true;
                        }
                        break;
                    case 3: // kigger til ned
                        if (currentPos[0] == rooms.length - 1) {
                            break;
                        } else if (rooms[currentPos[0] + 1][currentPos[1]] != null) {
                            currentPos[0] = currentPos[0] + 1;
                        } else {
                            rooms[currentPos[0] + 1][currentPos[1]] = roomToPlace;
                            currentPos[0] = currentPos[0] + 1;
                            roomPlaced = true;
                        }
                        break;
                    case 4: // kigger til venstre
                        if (currentPos[1] == 0) {
                            break;
                        } else if (rooms[currentPos[0]][currentPos[1] - 1] != null) {
                            currentPos[1] = currentPos[1] - 1;
                        } else {
                            rooms[currentPos[0]][currentPos[1] - 1] = roomToPlace;
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
        generateDoors();
        //GenerateDoors method

        return rooms;
    }

    private void generateDoors() // kigger i givende retninger alt efter om den er i en af kanterne af array eller i midten og placere en dør i givende retning, hvis der er et rum
    {
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
                } else if (i == rooms.length - 1 && j == 0) {
                    if (rooms[i - 1][j] != null) {
                        rooms[i][j].SetRoomDirection('n');
                    }
                    if (rooms[i][j + 1] != null) {
                        rooms[i][j].SetRoomDirection('e');
                    }
                } else if (i == 0 && j == rooms[0].length - 1) {
                    if (rooms[i][j - 1] != null) {
                        rooms[i][j].SetRoomDirection('w');
                    }
                    if (rooms[i + 1][j] != null) {
                        rooms[i][j].SetRoomDirection('s');
                    }
                } else if (i == rooms.length - 1 && j == rooms[0].length - 1) {
                    if (rooms[i][j - 1] != null) {
                        rooms[i][j].SetRoomDirection('w');
                    }
                    if (rooms[i - 1][j] != null) {
                        rooms[i][j].SetRoomDirection('n');
                    }
                } else if (i == 0 && j != 0 || i == 0 && j != rooms[0].length - 1) {
                    if (rooms[i + 1][j] != null) {
                        rooms[i][j].SetRoomDirection('s');
                    }
                    if (rooms[i][j - 1] != null) {
                        rooms[i][j].SetRoomDirection('w');
                    }
                    if (rooms[i][j + 1] != null) {
                        rooms[i][j].SetRoomDirection('e');
                    }
                } else if (i != 0 && j == rooms[0].length - 1 || i != rooms.length - 1 && j == rooms[0].length - 1) {
                    if (rooms[i + 1][j] != null) {
                        rooms[i][j].SetRoomDirection('s');
                    }
                    if (rooms[i][j - 1] != null) {
                        rooms[i][j].SetRoomDirection('w');
                    }
                    if (rooms[i - 1][j] != null) {
                        rooms[i][j].SetRoomDirection('n');
                    }
                } else if (i == rooms.length - 1 && j != 0 || i == rooms.length - 1 && j != rooms[0].length - 1) {
                    if (rooms[i][j - 1] != null) {
                        rooms[i][j].SetRoomDirection('w');
                    }
                    if (rooms[i - 1][j] != null) {
                        rooms[i][j].SetRoomDirection('n');
                    }
                    if (rooms[i][j + 1] != null) {
                        rooms[i][j].SetRoomDirection('e');
                    }
                } else if (i != 0 && j == 0 || i != rooms.length - 1 && j == 0) {
                    if (rooms[i - 1][j] != null) {
                        rooms[i][j].SetRoomDirection('n');
                    }
                    if (rooms[i][j + 1] != null) {
                        rooms[i][j].SetRoomDirection('e');
                    }
                    if (rooms[i + 1][j] != null) {
                        rooms[i][j].SetRoomDirection('s');
                    }
                } else {
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
    }

    private Room GetRandomRoom() // returnere et rum alt efter hvilket random tal den får.
    {
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
                    roomToGenerate = new CombatRoom(pApplet, difficulty);
                    roomFound = true;
                    break;
            }
        }
        return roomToGenerate;
    }

    public int[] currentLocation() // returnere en array af din position i arrayet, så vi kan få vist det rum vi er i.
    {
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
        minimap[playerRoomPosition[0]][playerRoomPosition[1]][1] = 1;
        return intArray;
    }

    public Room getRoom(int[] currentRoom) {
        return rooms[currentRoom[0]][currentRoom[1]];
    }

    public void setPlayerRoomPosition(int y, int x) {
        this.playerRoomPosition[0] = y;
        this.playerRoomPosition[1] = x;
    }

    public void generateMiniMap()  // generere et minimap, via et 2Darray med et array i, der fortæller hvilket rum det er, og om du har været i det eller ej
    {
        minimap = new int[rooms.length][rooms[0].length][2];
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == null) {
                    minimap[i][j][0] = 0;
                    minimap[i][j][1] = 0;
                } else {
                    if (rooms[i][j] instanceof StartRoom) {
                        minimap[i][j][0] = 1;
                        minimap[i][j][1] = 0;
                    } else if (rooms[i][j] instanceof ShopRoom) {
                        minimap[i][j][0] = 2;
                        minimap[i][j][1] = 0;
                    } else if (rooms[i][j] instanceof BossRoom) {
                        minimap[i][j][0] = 3;
                        minimap[i][j][1] = 0;
                    } else if (rooms[i][j] instanceof RestRoom) {
                        minimap[i][j][0] = 4;
                        minimap[i][j][1] = 0;
                    } else if(rooms[i][j] instanceof CombatRoom){
                        minimap[i][j][0] = 5;
                        minimap[i][j][1] = 0;
                    }
                }
            }
        }
    }

    public void showMiniMap() // viser minimapet når du trykker m, og går derefter igennem vores minimap array og tjekker hvilket rum det er og om det er besøgt, og tegner en firkant ud fra det
    {
        if (pApplet.keyCode == VK_M) {
            displayMinimap = !displayMinimap;
        }

        if (displayMinimap) {
            for (int i = 0; i < rooms.length; i++) {
                for (int j = 0; j < rooms[0].length; j++) {
                    if (minimap[i][j][1] == 0) {
                        pApplet.fill(0);
                        pApplet.rect((j * 20)+4, (i * 20)+4, 50, 50);
                    } else if(currentLocation()[0] == i && currentLocation()[1] == j){
                        pApplet.fill(0, 255, 0);
                        pApplet.rect((j * 20)+4, (i * 20)+4, 50, 50);
                    }else{
                        if (minimap[i][j][0] == 1) {
                            pApplet.fill(roomColors[1].getRGB());
                            pApplet.rect((j * 20)+4, (i * 20)+4, 50, 50);
                        } else if (minimap[i][j][0] == 2) {
                            pApplet.fill(roomColors[2].getRGB());
                            pApplet.rect((j * 20)+4, (i * 20)+4, 50, 50);
                        } else if (minimap[i][j][0] == 3) {
                            pApplet.fill(roomColors[3].getRGB());
                            pApplet.rect((j * 20)+4, (i * 20)+4, 50, 50);
                        } else if (minimap[i][j][0] == 4) {
                            pApplet.fill(roomColors[0].getRGB());
                            pApplet.rect((j * 20)+4, (i * 20)+4, 50, 50);
                        } else if (minimap[i][j][0] == 5) {
                            pApplet.fill(255);
                            pApplet.rect((j * 20)+4, (i * 20)+4, 50, 50);
                        }
                    }
                }
            }
        }
    }

    public Room[][] getRooms() {
        return rooms;
    }
}
