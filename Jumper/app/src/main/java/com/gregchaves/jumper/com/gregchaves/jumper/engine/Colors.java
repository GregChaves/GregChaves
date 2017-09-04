package com.gregchaves.jumper.com.gregchaves.jumper.engine;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

public class Colors {

   public static Paint getBirdColor(){

       Paint red = new Paint();
       red.setColor(0xFFFF0000);
       return red;
   }

    public static Paint getTubeColor(){
        Paint green = new Paint();
        green.setColor(0xFF00FF00);
        return green;
    }

    public static Paint getScoreColor(){
        Paint white = new Paint();
        white.setColor(0xFFFFFFFF);
        white.setTextSize(80);
        white.setTypeface(Typeface.DEFAULT_BOLD);

        //sombra -- params: borda, direita, baixo e cor
        white.setShadowLayer(3, 5, 5, 0xFF000000);

        return white;
    }

}
