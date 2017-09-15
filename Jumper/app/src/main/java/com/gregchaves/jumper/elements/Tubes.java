package com.gregchaves.jumper.elements;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.gregchaves.jumper.Game;
import com.gregchaves.jumper.com.gregchaves.jumper.engine.Colors;
import com.gregchaves.jumper.com.gregchaves.jumper.engine.GameDisplay;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Tubes {

    private static final int QTT_TUBES = 5;
    private static final int DISTANCE_BETWEEN_TUBES = 250;
    private GameDisplay gameDisplay;
    private Score score;
    private Context context;

    private List<Tube> tubes = new ArrayList<Tube>();

    public Tubes(Context context, GameDisplay gameDisplay, Score score){
        this.context = context;
        this.gameDisplay = gameDisplay;
        this.score = score;

        int position = 800;

        for(int i = 0; i < QTT_TUBES; i++) {
            position += DISTANCE_BETWEEN_TUBES;
            this.tubes.add(new Tube(context, gameDisplay, position));
        }
    }

    public void drawIn(Canvas canvas){
        for(Tube tube: this.tubes){
            tube.drawIn(canvas);
        }
    }

    public void move(){

        ListIterator<Tube> tubeListIterator = this.tubes.listIterator();

            while (tubeListIterator.hasNext()) {
                Tube tube = (Tube) tubeListIterator.next();
                tube.move();

                if (tube.tubeOutOfScreen()) {
                    this.score.up();
                    tubeListIterator.remove();
                    Tube anotherTube = new Tube(this.context, this.gameDisplay, maxPosition() + DISTANCE_BETWEEN_TUBES);
                    tubeListIterator.add(anotherTube);
                }
            }
    }

    public int maxPosition(){
        int max = 0;

        for (Tube tube : this.tubes){
            max = Math.max(tube.getPosition(), max);
        }
        return max;
    }

    public boolean hasCollisionWith(Bird bird){

        for (Tube tube: this.tubes){
            if (tube.touchHorizontallyWith() && tube.touchVerticallyWith(bird)){
                return true;
            }
        }
        return false;
    }

}
