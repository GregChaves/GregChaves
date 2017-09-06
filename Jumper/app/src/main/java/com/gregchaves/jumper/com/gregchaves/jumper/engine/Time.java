package com.gregchaves.jumper.com.gregchaves.jumper.engine;

import android.content.Context;
import android.media.SoundPool;

import com.gregchaves.jumper.R;

public class Time {

    private double current;

    public double current(){
        return current;
    }

    public void pass(){
        current += 0.01;
    }

    public void restart(){
        current = 0.1;
    }

}
