package com.lachongmedia.sol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 */
public class splash extends Activity {


    private boolean mIsBackButtonPressed;
    private static final int splash_duration = 2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();

        handler.postDelayed(new Runnable(){
            public  void run(){
                finish();

                if(!mIsBackButtonPressed){
                    Intent intent = new Intent(splash.this, reg.class);
                    splash.this.startActivity(intent);
                }
            }
        }, splash_duration);
    }

}
