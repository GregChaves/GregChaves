package com.gregchaves.jumper.elements;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.gregchaves.jumper.com.gregchaves.jumper.engine.Colors;
import com.gregchaves.jumper.com.gregchaves.jumper.engine.GameDisplay;

public class Bird {

    public static final Paint RED = Colors.getBirdColor();
    public static final int X = 100;
    public static final int RADIUS = 50;

    private int height;

    private GameDisplay gameDisplay;

    public Bird(GameDisplay gameDisplay){
        this.gameDisplay = gameDisplay;
        this.height = 100;
    }

    public void fall(){
        boolean isOnTheGround = this.height + RADIUS > gameDisplay.getHeight();

        if (!isOnTheGround){
            this.height += 5;
        }
    }

    public void drawIn(Canvas canvas){
        canvas.drawCircle(X, this.height, RADIUS, RED);
    }

    public void jump(){
        if (this.height > RADIUS) {
            this.height -= 150;
        }
    }

    public int getHeight(){
        return this.height;
    }

}
