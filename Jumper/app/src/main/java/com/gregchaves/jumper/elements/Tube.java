package com.gregchaves.jumper.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.gregchaves.jumper.R;
import com.gregchaves.jumper.com.gregchaves.jumper.engine.Colors;
import com.gregchaves.jumper.com.gregchaves.jumper.engine.GameDisplay;

public class Tube {

    private GameDisplay gameDisplay;

    private static final int TUBE_SIZE = 250;
    private static final int TUBE_WIDTH = 100;
    private int lowerTubeHeight;
    private int position;
    private int upperTubeHeight;
    private Bitmap lowerTube;
    private Bitmap upperTube;

    private static final Paint GREEN = Colors.getTubeColor();

    private int randomValue() {
        return (int) (Math.random() * 450);
    }

    public Tube(Context context, GameDisplay gameDisplay, int position){

        this.gameDisplay = gameDisplay;
        this.position = position;
        this.lowerTubeHeight = gameDisplay.getHeight() - TUBE_SIZE - randomValue();
        this.upperTubeHeight = 0 + TUBE_SIZE + randomValue();

        Bitmap bp = BitmapFactory.decodeResource(context.getResources(), R.drawable.cano);
        this.lowerTube = Bitmap.createScaledBitmap(bp, TUBE_WIDTH, this.lowerTubeHeight, false);
        this.upperTube = Bitmap.createScaledBitmap(bp, TUBE_WIDTH, this.upperTubeHeight, false);
    }

    /* Verifica se o cano saiu da tela verificando se a sua lateral direita saiu*/
    public boolean tubeOutOfScreen() {
        return this.position + TUBE_WIDTH < 0;
    }


    /* desenha os canos */
    public void drawIn(Canvas canvas){
        drawLowerTube(canvas);
        drawUpperTube(canvas);
    }

    /* os dois primeiros argumentos representam o
    canto superior esquerdo do retângulo, e os outros dois são o seu
    canto inferior direito.*/

    public void drawLowerTube(Canvas canvas){
        //canvas.drawRect(this.position, this.lowerTubeHeight, this.position + TUBE_WIDTH, this.gameDisplay.getHeight(), GREEN);
        canvas.drawBitmap(this.lowerTube, this.position -2, this.lowerTubeHeight, null);
    }

    public void drawUpperTube(Canvas canvas){
        //canvas.drawRect(this.position, 0, this.position + TUBE_WIDTH, this.upperTubeHeight, GREEN);
        canvas.drawBitmap(this.upperTube, this.position -2, 0, null);
    }

    public void move(){
        this.position -= 1;
    }

    public int getPosition() {
        return this.position;
    }

    public boolean touchVerticallyWith(Bird bird){
        return bird.getHeight() - bird.RADIUS < this.upperTubeHeight ||
                bird.getHeight() + bird.RADIUS > this.lowerTubeHeight;
    }

    public boolean touchHorizontallyWith(){
        return this.position - Bird.X < Bird.RADIUS;
    }

}
