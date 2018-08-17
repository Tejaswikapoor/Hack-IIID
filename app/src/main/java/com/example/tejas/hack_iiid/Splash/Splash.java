package com.example.tejas.hack_iiid.Splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tejas.hack_iiid.HOME.home_act;
import com.example.tejas.hack_iiid.MainActivity;
import com.example.tejas.hack_iiid.R;

public class Splash extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
<<<<<<< HEAD
                Intent i = new Intent(Splash.this, home_act.class);
                startActivity(i);
=======
                Intent intent = new Intent(Splash.this, MainActivity.class);
                startActivity(intent);
>>>>>>> 77df49f9dcf1124bf0e8b3f5cf0e71a7f3040395
                finish();
            }
        }, SPLASH_TIME_OUT);


    }

}
