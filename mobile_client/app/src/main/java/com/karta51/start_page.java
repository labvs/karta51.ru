package com.karta51;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;



public class start_page extends Activity {

    // Время в милесекундах, в течение которого будет отображаться Splash Screen
    private final int SPLASH_DISPLAY_LENGTH = 2000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(start_page.this, MainMenuActivity.class);
                start_page.this.startActivity(mainIntent);
                start_page.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}