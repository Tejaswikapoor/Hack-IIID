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

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
<<<<<<< HEAD
            public void run() {
                Intent i = new Intent(Splash.this, home_act.class);
                startActivity(i);
                Intent intent = new Intent(Splash.this, home_act.class);
                startActivity(intent);
=======
            public void run()
            {
                Intent i = new Intent(Splash.this, home_act.class);
                startActivity(i);
>>>>>>> 8593788f2b78839b6507391989a3157f1fcc7895
                finish();
            }
        }, SPLASH_TIME_OUT);


    }

}
