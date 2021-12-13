package Entitys;

import Interfaces.MeleeI;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.Random;

public abstract class MeleeEnemy extends Enemies implements MeleeI {

    public MeleeEnemy(PApplet pApplet, int HP, int attack,int difficulty, PVector pVector) {

        super(pApplet, HP, attack,difficulty, pVector);
    }


    public void processEnemy(int i){ //overloadet funktion så int i kan være i signaturen
        chasePlayer(i);
        super.processEnemy();

    }

    @Override
    public void chasePlayer(int i)
    {
        Random rand = new Random();
        int j = rand.nextInt(400);

        if(j < 10)
        {

            switch (i)
            {
                case 1:
                    super.changeCurrentPvector(i); //gå op
                    super.setOffSet(9);
                    super.setCurrentFrame((getCurrentFrame() + 1) % getLoopFrames());
                    break;

                case 2:
                    super.changeCurrentPvector(i); //gå ned
                    super.setOffSet(0);
                    super.setCurrentFrame((getCurrentFrame() + 1) % getLoopFrames());
                    break;

                case 3:
                    super.changeCurrentPvector(i); // gå til venstre
                    super.setOffSet(3);
                    super.setCurrentFrame((getCurrentFrame() + 1) % getLoopFrames());
                    break;

                case 4:
                    super.changeCurrentPvector(i); // gå til højre
                    super.setOffSet(6);
                    super.setCurrentFrame((getCurrentFrame() + 1) % getLoopFrames());
                    break;
            }
        }
    }

    @Override
    public void movement() //movement nulstillet da random movement plus chasePlayer bliver underligt
    {
    }

}
