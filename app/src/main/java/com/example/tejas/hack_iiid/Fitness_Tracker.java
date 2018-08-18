package com.example.tejas.hack_iiid;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class Fitness_Tracker extends AppCompatActivity implements SensorEventListener {
    private long steps = 0;

    TextView stepTextView;
    public static SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness__tracker);
        stepTextView = findViewById(R.id.pedo_count_text);
        LottieAnimationView lottieAnimationView = findViewById(R.id.walkingAnim);
        lottieAnimationView.playAnimation();
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Log.d("bug",sensorManager.toString());
        if(sensorManager!=null) {
            Sensor sSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        try {
            Sensor sensor = event.sensor;
            float[] values = event.values;
            Log.d("bugA",sensor.toString()+"");
            Log.d("bugA",values.length+"");
            int value = -1;

            if (values.length > 0) {
                value = (int) values[0];
            }


            if (sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
                steps++;
            }

            Log.wtf("Steps", " Count" + steps);
            stepTextView.setText(steps + "");
        }catch (Exception e){
            Log.d("bug",e.toString());
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
