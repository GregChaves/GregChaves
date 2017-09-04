package com.gregchaves.jumper;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class MainActivity extends Activity {

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout container = (FrameLayout) findViewById(R.id.container);
        this.game = new Game(this);
        container.addView(this.game);
    }

    protected void onPause(){
        super.onPause();
        this.game.cancel();
    }

    protected void onResume(){
        super.onResume();
        this.game.init();
        new Thread(this.game).start();
    }
}
