package com.gregchaves.jumper.elements;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.gregchaves.jumper.com.gregchaves.jumper.engine.Colors;
import com.gregchaves.jumper.com.gregchaves.jumper.engine.GameDisplay;
import com.gregchaves.jumper.com.gregchaves.jumper.engine.Sound;

public class Score {

    private int points = 0;
    private Sound sound;

    public static final Paint white = Colors.getScoreColor();

    private GameDisplay gameDisplay;

    public Score(Sound sound){
        this.sound = sound;
    }

    public void up(){
        this.sound.play(sound.POINTS);
        this.points++;
    }

    public void drawIn(Canvas canvas){
        canvas.drawText(String.valueOf(this.points), 100, 100, white);
    }

}
