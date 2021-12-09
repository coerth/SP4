import processing.core.PApplet;
import processing.core.PFont;

import static java.awt.event.KeyEvent.VK_ENTER;
import static java.lang.Math.PI;

import Entitys.*;

public class UI {

    private PApplet pApplet;
    float textY = 0;


    public UI(PApplet papplet) {

        this.pApplet = papplet;
    }

    //
    private void displayText(String s, float verticalHeight, int size) {
        pApplet.fill(255);
        pApplet.textSize(size);
        pApplet.textAlign(pApplet.CENTER);
        pApplet.text(s, pApplet.width * 0.5f, pApplet.height * verticalHeight);

    }

    public void StartMenu() {

    }

    public void GameOverMenu() {

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
}
