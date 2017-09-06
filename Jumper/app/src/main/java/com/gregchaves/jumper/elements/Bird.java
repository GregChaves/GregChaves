package com.gregchaves.jumper.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.gregchaves.jumper.R;
import com.gregchaves.jumper.com.gregchaves.jumper.engine.Colors;
import com.gregchaves.jumper.com.gregchaves.jumper.engine.GameDisplay;
import com.gregchaves.jumper.com.gregchaves.jumper.engine.Sound;
import com.gregchaves.jumper.com.gregchaves.jumper.engine.Time;

public class Bird {

    public static final Paint RED = Colors.getBirdColor();
    public static final int X = 100;
    public static final int RADIUS = 50;
    private static final int LEAP_DISPLACEMENT = 3;

    private int height;

    private GameDisplay gameDisplay;

    private final Bitmap bird;

    private Sound sound;

    private Time time;

    public Bird(GameDisplay gameDisplay, Context context, Sound sound, Time time){
        this.gameDisplay = gameDisplay;
        this.sound = sound;
        this.height = 100;

        /* Veja que passamos RAIO*2 para a altura e largura do novo
        bitmap. Como nosso círculo tinha o raio do tamanho RAIO ,
        sua largura e altura eram RAIO*2 . Agora, nossa imagem tem
        as mesmas dimensões que o círculo. */

        Bitmap bp = BitmapFactory.decodeResource(context.getResources(), R.drawable.passaro);
        this.bird = Bitmap.createScaledBitmap(bp, RADIUS*2, RADIUS*2, false);
        this.time = time;
    }

    /*

    uma das fórmulas do MUV é a que determina a altura em
    função do tempo, gravidade e velocidade inicial de queda:
    S = S0 + V0 * t + (g * (t * t)) / 2
    Onde:

    S = altura final;
    S0 = altura inicial;
    V0 = velocidade inicial;
    g = gravidade;
    t = tempo.

    Após um pulo, nosso pássaro começa a cair quando sua
    velocidade se torna zero. Aí ele fica sujeito à ação da gravidade,
    configurando uma queda-livre. Então, da nossa fórmula inicial,
    podemos simplificá-la ao deixar a velocidade inicial igual a zero:
    S = S0 + V0 * t + (g * (t * t)) / 2
    S = S0 + 0 * t + (g * (t * t)) / 2 //V0 = 0;
    S = S0 + 0 + (g * (t * t)) / 2
    S = S0 + (g * (t * t)) / 2

     */


    public void fly(){
        double time = this.time.current();
        double newHeight = -LEAP_DISPLACEMENT + ((10.0 * (time * time)) / 1.0);
        boolean isOnTheGround = this.height + RADIUS > gameDisplay.getHeight();

        if (!isOnTheGround){
            this.height += newHeight;
        }
    }

    public void drawIn(Canvas canvas){
        //canvas.drawCircle(X, this.height, RADIUS, RED);
        canvas.drawBitmap(this.bird, X - RADIUS, this.height - RADIUS, null);
    }

    public void jump(){
        if (this.height > RADIUS) {
            this.sound.play(sound.JUMP);
            this.time.restart();
        }
    }

    public int getHeight(){
        return this.height;
    }

}
