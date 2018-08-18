package com.example.tejas.hack_iiid.HOME;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.tejas.hack_iiid.Daily.DailyFigures;
import com.example.tejas.hack_iiid.Fitness_Tracker;
import com.example.tejas.hack_iiid.History.History;
import com.example.tejas.hack_iiid.MainActivity;
import com.example.tejas.hack_iiid.MealRegister.MealRegister;
import com.example.tejas.hack_iiid.R;

public class home_act extends AppCompatActivity {

    CardView cardmeal,cardfitness,carddaily,cardhistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_act);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cardmeal = (CardView)findViewById(R.id.card_meal_analysis);
        cardfitness = (CardView)findViewById(R.id.card_fitness_tracker);
        carddaily = (CardView)findViewById(R.id.card_daily_report);
        cardhistory = (CardView)findViewById(R.id.card_history);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Health Bot API", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        cardmeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentMeal = new Intent(home_act.this, MealRegister.class);
                startActivity(intentMeal);
            }
        });

        cardfitness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentFitness = new Intent(home_act.this, Fitness_Tracker.class);
                startActivity(intentFitness);
            }
        });

        carddaily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentFitness = new Intent(home_act.this, DailyFigures.class);
                startActivity(intentFitness);
            }
        });

        cardhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentHistory = new Intent(home_act.this, History.class);
                startActivity(intentHistory);
            }
        });
    }

}
