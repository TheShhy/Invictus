package com.example.admin.lab_no_5;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class task3 extends AppCompatActivity {
    ImageView imageView;
    SensorManager sm;
    Sensor s;
    SensorEventListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task3);

        imageView = findViewById(R.id.imageView3);

        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        s = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

    }

    @Override
    protected void onResume() {
        super.onResume();
        listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float z = event.values[2];
                if(z>-0.5){

                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        sm.registerListener(listener , s , SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(listener);
    }
}
