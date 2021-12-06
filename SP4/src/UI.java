import processing.core.PApplet;
import processing.core.PFont;

public class UI {

    private PApplet pApplet;

    public UI(PApplet papplet) {

        this.pApplet = papplet;
    }

    //
    private void displayText(String s, float verticalHeight, int size){
        pApplet.fill(255);
        pApplet.textSize(size);
        pApplet.textAlign(pApplet.CENTER);
        pApplet.text(s, pApplet.width * 0.5f, pApplet.height * verticalHeight);

    }

    public void StartMenu (){

    }

    public void GameOverMenu(){

    }

}
