package com.example.tejas.hack_iiid.Splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.tejas.hack_iiid.HOME.home_act;
import com.example.tejas.hack_iiid.MainActivity;
import com.example.tejas.hack_iiid.MealRegister.MealRegister;
import com.example.tejas.hack_iiid.R;

public class Splash extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Splash.this, home_act.class);
                startActivity(i);
                Intent intent = new Intent(Splash.this, home_act.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);


    }

}
