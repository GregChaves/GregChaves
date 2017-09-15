package com.gregchaves.jumper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.gregchaves.jumper.com.gregchaves.jumper.engine.Colors;
import com.gregchaves.jumper.com.gregchaves.jumper.engine.GameDisplay;
import com.gregchaves.jumper.com.gregchaves.jumper.engine.VerifyCollision;
import com.gregchaves.jumper.elements.Bird;
import com.gregchaves.jumper.elements.Score;
import com.gregchaves.jumper.elements.Tube;
import com.gregchaves.jumper.elements.Tubes;

public class GameOver {

    private static final Paint RED = Colors.getGameOverColor();

    private final GameDisplay gameDisplay;

    public GameOver(GameDisplay gameDisplay){
        this.gameDisplay = gameDisplay;
    }

    public void drawnIn(Canvas canvas){
        String gameOver = "Game Over";
        int centerTxt = centerText(gameOver);
        canvas.drawText(gameOver, centerTxt, this.gameDisplay.getHeight() /2, RED);
    }

    private int centerText(String text){
        Rect textLimit = new Rect();
        RED.getTextBounds(text, 0, text.length(), textLimit);
        int hCenter = this.gameDisplay.getWidth() / 2 - (textLimit.right - textLimit.left) /2;
        return hCenter;
    }

}
