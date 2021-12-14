package GameComponents;

import processing.core.PApplet;

import static java.awt.event.KeyEvent.*;

import Entitys.*;
import processing.core.PFont;

public class UI {

    private PApplet pApplet;
    private float textY = 0;
    private int startGameOption = 0;
    private int endGameOption = 0;
    private boolean playCredits = false;
    private boolean playGameStory = false;
    private PFont hacked = new PFont();


    public UI(PApplet papplet) {
        this.pApplet = papplet;
        this.hacked = pApplet.createFont("Hacked.ttf", 80);
    }

    //
    private void displayText(String s, float verticalHeight, int size) {
        pApplet.textSize(size);
        pApplet.textAlign(pApplet.CENTER);
        pApplet.text(s, pApplet.width * 0.5f, pApplet.height * verticalHeight);

    }

    public boolean startMenu() // samlet funktion for startmenuen
    {
        if(!playCredits && !playGameStory)
        {
            startGameSelector();

            if(startGameOption == 0 && pApplet.keyCode == VK_E)
            {
                return true;
             }

            else if(startGameOption == 1 && pApplet.keyCode == VK_E)
            {
                playGameStory = true;
            }

            else if(startGameOption == 2 && pApplet.keyCode == VK_E)
            {
                playCredits = true;
            }
        }

        else if(playCredits)
        {
            rollingText("Hold B2. \n\nCasper \"Fluff\"\n Long \"2\" \nMia \"Schnackminister\" \nMorten \"Mr If\"", -900 );
        }
        else if(playGameStory)
        {
            rollingText("You have been banished to the darkest depths of CPHs basement by Lord Mark \n\nYou must prove your worth by slaying former students. \n\nAnd show to the world that you belong amongst the hallowed 22.", -1300);
        }




        return false;
    }

    private void startGameSelector()
    {
        pApplet.fill(0);
        pApplet.rect(0,0,pApplet.width,pApplet.height);
        pApplet.fill(255);
        pApplet.textFont(hacked,80);
        displayText("The CPH Basement", 0.2f, 80);
        pApplet.textFont(hacked,20);
        displayText("W/S = UP/Down\n E to Select", 0.9f,20);

        if(startGameOption == 0) // hvis den er 0 så er det start game der er highlightet
        {
            pApplet.fill(255, 225, 0);
            displayText("Start Game", 0.6f, 40);
            pApplet.fill(255);
            displayText("The Story so far", 0.7f, 40);
            pApplet.fill(255);
            displayText("Credits", 0.8f, 40);
            pApplet.fill(255);

        }
        else if(startGameOption == 1) // hvis den er 1 så er det the story so far der er highlightet
        {
            pApplet.fill(255);
            displayText("Start Game", 0.6f, 40);
            pApplet.fill(255, 225, 0);
            displayText("The Story so far", 0.7f, 40);
            pApplet.fill(255);
            displayText("Credits", 0.8f, 40);
            pApplet.fill(255);

        }

        else // ellers er det credits der er highlightet
        {
            pApplet.fill(255);
            displayText("Start Game", 0.6f, 40);
            pApplet.fill(255);
            displayText("The Story so far", 0.7f, 40);
            pApplet.fill(255, 225, 0);
            displayText("Credits", 0.8f, 40);
            pApplet.fill(255);
        }

        if(pApplet.keyCode == VK_W && startGameOption == 0) //man kan gå op og ned uanset hvor man er
        {
            startGameOption = 2;
            pApplet.keyCode = VK_BACK_SLASH;
        }

        else if(pApplet.keyCode == VK_W && startGameOption == 1)
        {
            startGameOption = 0;
            pApplet.keyCode = VK_BACK_SLASH;
        }

        else if(pApplet.keyCode == VK_W && startGameOption == 2)
        {
            startGameOption = 1;
            pApplet.keyCode = VK_BACK_SLASH;
        }

        else if(pApplet.keyCode == VK_S && startGameOption == 0)
        {
            startGameOption = 1;
            pApplet.keyCode = VK_BACK_SLASH;
        }
        else if(pApplet.keyCode == VK_S && startGameOption == 1)
        {
            startGameOption = 2;
            pApplet.keyCode = VK_BACK_SLASH;
        }
        else if(pApplet.keyCode == VK_S && startGameOption == 2)
        {
            startGameOption = 0;
            pApplet.keyCode = VK_BACK_SLASH;
        }
    }


    public boolean gameOverMenu()
    {
        endGameSelector();

        if(endGameOption == 0 && pApplet.keyCode == VK_E)
        {
            return true;
        }
        else if(endGameOption == 1 && pApplet.keyCode == VK_E)
        {
            return true;
        }

        return false;
    }

    private void endGameSelector()
    {
        pApplet.fill(0);
        pApplet.rect(0,0,pApplet.width,pApplet.height);
        pApplet.fill(255);
        pApplet.textFont(hacked,80);
        displayText("Game Over!", 0.2f, 80);
        pApplet.textFont(hacked,20);
        displayText("W/S = UP/Down\n E to Select", 0.9f,20);

        if(endGameOption == 0) // hvis den er 0 så er det retry der er highlightet
        {
            pApplet.fill(255, 225, 0);
            displayText("Retry?", 0.6f, 40);
            pApplet.fill(255);
            displayText("Return To Menu", 0.7f, 40);
            pApplet.fill(255);

        }
        else // hvis den er 1 så er det return to menu der er highlightet
        {
            pApplet.fill(255);
            displayText("Retry?", 0.6f, 40);
            pApplet.fill(255, 225, 0);
            displayText("Return To Menu", 0.7f, 40);

        }


        if(pApplet.keyCode == VK_W && endGameOption == 0) //man kan gå op og ned uanset hvor man er
        {
            endGameOption = 1;
            pApplet.keyCode = VK_BACK_SLASH;
        }

        if(pApplet.keyCode == VK_W && endGameOption == 1)
        {
            endGameOption = 0;
            pApplet.keyCode = VK_BACK_SLASH;
        }

        else if(pApplet.keyCode == VK_S && endGameOption == 0)
        {
            endGameOption = 1;
            pApplet.keyCode = VK_BACK_SLASH;
        }
        else if(pApplet.keyCode == VK_S && endGameOption == 1)
        {
            endGameOption = 0;
            pApplet.keyCode = VK_BACK_SLASH;
        }
    }



    public void rollingText(String crawl, int stopValue)  // op til debat
    {
        pApplet.background(0);
        pApplet.fill(255, 255, 0);
        pApplet.translate(pApplet.width / 2f - 550, pApplet.height); //sætter x og y koordinaternes nulpunkter til det man skriver
        pApplet.rotateX(PApplet.PI / 3f); //gør at teksten vinkles ind af i takt med at teksten afvikles
        pApplet.textSize(75);
        pApplet.text(crawl, 0, textY, 1100, 3600); //x er width/2f-400 translated, y er height translated, x2 og y2 er størrelsen på textboksen
        textY -= 1;

        if (textY <= stopValue) {
            textY = 0;
            playCredits = false;
            playGameStory = false;
            pApplet.keyCode = VK_BACK_SLASH;
        }


    }

    public void statsBar(Player player, Dungeon dungeon) {
        //liv
        pApplet.textSize(16);
        pApplet.fill(255);
        String s = "Health: " + player.getHP() + "";
        pApplet.text(s, 10, 10, (pApplet.textWidth(s)+10), 35);

        //forsvar
        pApplet.textSize(16);
        pApplet.fill(255);
        String s1 = "Defense: " + player.getDefense() + "";
        pApplet.text(s1, 10, 35, (pApplet.textWidth(s1)+10), 60);

        //angrib
        pApplet.textSize(16);
        pApplet.fill(255);
        String s2 = "Attack: " + player.attack() + "";
        pApplet.text(s2, 10, 60, (pApplet.textWidth(s2)+10), 85);

        //penge
        pApplet.textSize(16);
        pApplet.fill(255);
        String s3 = "Coins: " + player.getInventory().getCoins() + "";
        pApplet.text(s3, 10, 85, (pApplet.textWidth(s3)+10), 105);

        //level
        pApplet.textSize(16);
        pApplet.fill(255);
        String s4 = "Level: " + (dungeon.getDifficulty()+1) + "";
        pApplet.text(s4, pApplet.width-(pApplet.textWidth(s4)), pApplet.height*0.035f);

        //våben
        pApplet.textSize(16);
        pApplet.fill(255);
        String s5 = "Weapon: Syntax Errors";
        pApplet.text(s5, (pApplet.textWidth(s)+10)+10, 10, (pApplet.textWidth(s5)+20), 25);

    }

    public PFont getHacked() {
        return hacked;
    }

    public int getEndGameOption() {
        return endGameOption;
    }
}
