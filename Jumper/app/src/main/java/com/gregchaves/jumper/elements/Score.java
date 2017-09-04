package com.gregchaves.jumper.elements;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.gregchaves.jumper.com.gregchaves.jumper.engine.Colors;
import com.gregchaves.jumper.com.gregchaves.jumper.engine.GameDisplay;

public class Score {

    private int points = 0;

    public static final Paint white = Colors.getScoreColor();

    private GameDisplay gameDisplay;

    public void up(){
        this.points++;
    }

    public void drawIn(Canvas canvas){
        canvas.drawText(String.valueOf(this.points), 100, 100, white);
    }

}
