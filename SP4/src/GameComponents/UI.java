package GameComponents;

import processing.core.PApplet;

import static java.awt.event.KeyEvent.*;

import Entitys.*;

public class UI {

    private PApplet pApplet;
    private float textY = 0;
    private int startGameOption = 0;
    private int endGameOption = 0;
    private boolean playCredits = false;


    public UI(PApplet papplet) {

        this.pApplet = papplet;
    }

    //
    private void displayText(String s, float verticalHeight, int size) {
        //pApplet.fill(255);
        pApplet.textSize(size);
        pApplet.textAlign(pApplet.CENTER);
        pApplet.text(s, pApplet.width * 0.5f, pApplet.height * verticalHeight);

    }

    public boolean startMenu()
    {
        if(!playCredits)
        {
            startGameSelector();

            if(startGameOption == 1 && pApplet.keyCode == VK_E)
            {
                playCredits = true;
            }

            else if(startGameOption == 0 && pApplet.keyCode == VK_E)
            {
                return true;
            }
        }

        else if(playCredits)
        {
            credits();
        }



        return false;
    }

    private void startGameSelector()
    {
        pApplet.fill(0);
        pApplet.rect(0,0,pApplet.width,pApplet.height);
        pApplet.fill(255);
        displayText("Untitled Panda Game", 0.2f, 80);
        displayText("W/S = Up/Down\n E to Select", 0.9f,20);

        if(startGameOption == 0) // hvis den er 0 så er det start game der er highlightet
        {
            pApplet.fill(255, 225, 0);
            displayText("Start Game", 0.6f, 40);
            pApplet.fill(255);
            displayText("Credits", 0.7f, 40);
            pApplet.fill(255);

        }
        else // hvis den er 1 så er det credits der er highlightet
        {
            pApplet.fill(255);
            displayText("Start Game", 0.6f, 40);
            pApplet.fill(255, 225, 0);
            displayText("Credits", 0.7f, 40);

        }

        if(pApplet.keyCode == VK_W && startGameOption == 0) //man kan gå op og ned uanset hvor man er
        {
            startGameOption = 1;
            pApplet.keyCode = VK_BACK_SLASH;
        }

        if(pApplet.keyCode == VK_W && startGameOption == 1)
        {
            startGameOption = 0;
            pApplet.keyCode = VK_BACK_SLASH;
        }

        else if(pApplet.keyCode == VK_S && startGameOption == 0)
        {
            startGameOption = 1;
            pApplet.keyCode = VK_BACK_SLASH;
        }
        else if(pApplet.keyCode == VK_S && startGameOption == 1)
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
        displayText("Game Over!", 0.2f, 80);
        displayText("W/S = Up/Down\n E to Select", 0.9f,20);

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

    public void credits() { // op til debat
        String crawl = "Hold B2. \n\nCasper \"Fluff\"\n Long \"2\" \nMia \"Schnackminister\" \nMorten \"Mr If\"";

        pApplet.background(0);
        pApplet.fill(255, 255, 0);
        pApplet.translate(pApplet.width / 2f - 550, pApplet.height); //sætter x og y koordinaternes nulpunkter til det man skriver
        pApplet.rotateX(PApplet.PI / 3f); //gør at teksten vinkles ind af i takt med at teksten afvikles
        pApplet.textSize(75);
        pApplet.text(crawl, 0, textY, 1100, 3600); //x er width/2f-400 translated, y er height translated, x2 og y2 er størrelsen på textboksen
        textY -= 1;

        if (textY <= -900) {
            textY = 0;
            playCredits = !playCredits;
            pApplet.keyCode = VK_BACK_SLASH;
        }


    }

    public void gameStory() {
        String crawl = "The year is 2042. \n\nThe way of training new programmers has changed. \nA.I. does all the coding and automation has taken over production. \n\nWhich means new candidates need to be tested on different qualities. \nTheir ability to both problem solve and survive in a competitive environment. \n\nThe last exam for the student involves navigating the basement of CPH Lyngby. \nSolving riddles and defeating the remnants of previous failed students. \n\nWho got what it takes to become a programmer?";

        pApplet.background(0);
        pApplet.fill(255, 255, 0);
        pApplet.translate(pApplet.width / 2f - 400, pApplet.height); //sætter x og y koordinaternes nulpunkter til det man skriver
        pApplet.rotateX(PApplet.PI / 3f); //gør at teksten vinkles ind af i takt med at teksten afvikles
        pApplet.textSize(75);
        pApplet.text(crawl, 0, textY, 1100, 3600); //x er width/2f-400 translated, y er height translated, x2 og y2 er størrelsen på textboksen
        textY -= 1;

        if (textY <= -3600) {
            textY = 0;
        }


    }



    public void statsBar(Player player) {
        //liv
        pApplet.textSize(20);
        pApplet.fill(255);
        String s = "Health: " + player.getHP();
        pApplet.text(s, -20, 25, 180, 100);

        //forsvar
        pApplet.textSize(20);
        pApplet.fill(255);
        String s1 = "Defense: " + player.getDefense();
        pApplet.text(s1, 90, 25, 180, 100);

        //angrib
        pApplet.textSize(20);
        pApplet.fill(255);
        String s2 = "Attack: " + player.attack();
        pApplet.text(s2, 200, 25, 180, 100);

        //penge
        pApplet.textSize(20);
        pApplet.fill(255);
        String s3 = "Coins: " + player.getInventory().getCoins();
        pApplet.text(s3, 280, 25, 180, 100);

        //våben
        pApplet.textSize(20);
        pApplet.fill(255);
        String s4 = "Weapon: Boomerang";
        pApplet.text(s4, 450, 25, 180, 100);

    }

    public int getEndGameOption() {
        return endGameOption;
    }
}
