package com.example.tejas.hack_iiid.Splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

<<<<<<< HEAD
import com.airbnb.lottie.LottieAnimationView;
=======
import com.example.tejas.hack_iiid.HOME.home_act;
>>>>>>> 0a1b11f533726ab557d414ee089cba18dafa5087
import com.example.tejas.hack_iiid.MainActivity;
import com.example.tejas.hack_iiid.R;

public class Splash extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        LottieAnimationView splashAnim=findViewById(R.id.splashAnimView);
        splashAnim.playAnimation();

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
