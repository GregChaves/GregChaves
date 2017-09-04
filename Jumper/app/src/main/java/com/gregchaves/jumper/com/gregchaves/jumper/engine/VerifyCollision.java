package com.gregchaves.jumper.com.gregchaves.jumper.engine;

import android.graphics.Paint;
import android.graphics.Typeface;

import com.gregchaves.jumper.elements.Bird;
import com.gregchaves.jumper.elements.Tubes;

public class VerifyCollision {

    private final Bird bird;

    private final Tubes tubes;

    public VerifyCollision(Bird bird, Tubes tubes){
        this.bird = bird;
        this.tubes = tubes;
    }

    public boolean hasCollision(){
        return tubes.hasCollisionWith(this.bird);
    }




}
