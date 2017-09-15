package com.chaves.angrycars;

import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.opengl.CCGLSurfaceView;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.chaves.angrycars.scenes.TitleScreen;
import com.swarmconnect.Swarm;
import com.swarmconnect.SwarmActivity;


public class MainActivity extends SwarmActivity {
	
	public static int SWARM_APP_ID = 15151;
	public static String SWARM_APP_KEY = "786482254f88837a03fbe1b719abf0a6";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Swarm.init(this, SWARM_APP_ID, SWARM_APP_KEY);
        
     // definindo orientação como landscape
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
     // configura a tela
        CCGLSurfaceView glSurfaceView = new CCGLSurfaceView(this);
        
        setContentView(glSurfaceView);
        
        CCDirector.sharedDirector().attachInView(glSurfaceView);
        
        CCDirector.sharedDirector().setScreenSize(320, 480);
        
     // abre tela principal
        CCScene scene = new TitleScreen().scene();
        
        CCDirector.sharedDirector().runWithScene(scene);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
