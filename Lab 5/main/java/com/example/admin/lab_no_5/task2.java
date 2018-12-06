package com.example.admin.lab_no_5;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class task2 extends AppCompatActivity implements SensorEventListener{
    SensorManager sm;
    Sensor s;
    ImageView imageView;

    float scale = 1f;
    float width;
    float height;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task2);

        imageView = findViewById(R.id.imageView2);
        imageView.setImageResource(R.drawable.image_1);

        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        s = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        width = imageView.getScaleX();
        height = imageView.getScaleY();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sm.registerListener( this , s , SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if(event.values[0] < s.getMaximumRange()){
            scale *= event.values[0];
            scale = Math.max(0.1f, Math.min(scale, 5.0f));
            imageView.setScaleX(scale);
            imageView.setScaleY(scale);
        }else{
            imageView.setScaleX(width);
            imageView.setScaleY(height);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
