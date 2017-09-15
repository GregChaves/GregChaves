package com.gregchaves.jumper.com.gregchaves.jumper.engine;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;

import com.gregchaves.jumper.R;

public class Sound {

    private SoundPool soundPool;
    public static int JUMP;
    public static int POINTS;
    public static int COLLISION;

    public Sound(Context context){
        SoundPool.Builder builder = new SoundPool.Builder();
        builder.setMaxStreams(3);
        soundPool = builder.build();

        JUMP = this.soundPool.load(context, R.raw.pulo, 1);
        POINTS = this.soundPool.load(context, R.raw.pontos, 1);
        COLLISION = this.soundPool.load(context, R.raw.colisao, 1);
    }

    /* o método play , que recebe seis argumentos:
        O id do som;
        O volume esquerdo;
        O volume direito;
        A prioridade do som;
        O loop ( 1 para loop infinito, 0 para tocar uma
        única vez);
        A velocidade do som (lembre-se: 1 é a velocidade
        normal).
        */
    public void play(int sound){
        this.soundPool.play(sound, 1, 1, 1, 0, 1);
    }

}
