package com.gregchaves.jumper.com.gregchaves.jumper.engine;

import android.content.Context;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class GameDisplay {

    public DisplayMetrics metrics;

    public GameDisplay(Context context){
       //obtem altura da tela
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        this.metrics = new DisplayMetrics();
        display.getMetrics(this.metrics);
    }

    public int getHeight(){
        return this.metrics.heightPixels;
    }
}
