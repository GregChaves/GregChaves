package com.gregchaves.jumper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

import com.gregchaves.jumper.com.gregchaves.jumper.engine.GameDisplay;
import com.gregchaves.jumper.com.gregchaves.jumper.engine.Sound;
import com.gregchaves.jumper.com.gregchaves.jumper.engine.Time;
import com.gregchaves.jumper.com.gregchaves.jumper.engine.VerifyCollision;
import com.gregchaves.jumper.elements.Bird;
import com.gregchaves.jumper.elements.Score;
import com.gregchaves.jumper.elements.Tube;
import com.gregchaves.jumper.elements.Tubes;

public class Game extends SurfaceView implements Runnable, View.OnTouchListener{

    private boolean isRunning = true;

    private Bird bird;

    private Tube tube;

    private Tubes tubes;

    private Bitmap background;

    private GameDisplay gameDisplay;

    private Score score;

    private VerifyCollision verifyCollision;

    private Context context;

    private Sound sound;

    private Time time;

    private final SurfaceHolder holder = getHolder();

    public Game(Context context) {
        super(context);
        this.context = context;
        this.sound = new Sound(context);
        this.gameDisplay = new GameDisplay(context);
        initElements();
        setOnTouchListener(this);
    }

    private void initElements(){
        this.time = new Time();
        Bitmap back = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        this.background = Bitmap.createScaledBitmap(back, back.getWidth(), gameDisplay.getHeight(), false);
        this.bird = new Bird(this.gameDisplay, this.context, this.sound, this.time);
        this.score = new Score(this.sound);
        this.tubes = new Tubes(this.context, this.gameDisplay, score);

        this.verifyCollision = new VerifyCollision(bird, tubes);
    }

    @Override
    public void run() {
        while(this.isRunning){

     //verifica se o holder eh valido antes de desenhar qualquer coisa na tela
            if(!this.holder.getSurface().isValid()) continue;

            //obtem o canvas
            Canvas canvas = this.holder.lockCanvas();

            // desenha o fundo
            canvas.drawBitmap(this.background, 0, 0, null);

            //desenha o passaro
            this.bird.drawIn(canvas);
            this.bird.fly();

            //desenha canos
            this.tubes.drawIn(canvas);

            //desenha a pontuacao
            this.score.drawIn(canvas);

            //solta o tempo
            this.time.pass();

            // move os canos
            this.tubes.move();

            if (this.verifyCollision.hasCollision()){
                this.sound.play(Sound.COLLISION);
                new GameOver(this.gameDisplay).drawnIn(canvas);
                cancel();
            }

            this.holder.unlockCanvasAndPost(canvas);
        }
    }

    public void cancel(){
        this.isRunning = false;
    }

    public void init(){
        this.isRunning = true;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        this.bird.jump();
        return false;
    }

}
