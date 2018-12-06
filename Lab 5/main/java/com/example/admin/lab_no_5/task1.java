package com.example.admin.lab_no_5;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;
import android.widget.Toast;

public class task1 extends AppCompatActivity {
    GestureDetectorCompat gd;
    ScaleGestureDetector sgd;
    float scale = 1f;

    ImageView imageView;
    int[] images = {R.drawable.image_1 , R.drawable.image_2, R.drawable.image_3 , R.drawable.image_4 , R.drawable.image_5, R.drawable.image_6 };
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task1);

        //Initializing Image View
        imageView = findViewById(R.id.imageView);
        imageView.setImageResource(images[0]);

        sgd = new ScaleGestureDetector(this, new ScaleGestureDetector.OnScaleGestureListener() {
            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                scale *= detector.getScaleFactor();
                scale = Math.max(0.1f, Math.min(scale, 5.0f));
                imageView.setScaleX(scale);
                imageView.setScaleY(scale);
                return true;
            }

            @Override
            public boolean onScaleBegin(ScaleGestureDetector detector) {
                return true;
            }

            @Override
            public void onScaleEnd(ScaleGestureDetector detector) {

            }
        });
        //For Fling Left And Right
        gd = new GestureDetectorCompat(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

                if(e1.getX() < e2.getX()){

                    if(count < 5){
                        count++;
                        imageView.setImageResource(images[count]);
                    }
                    else Toast.makeText(getBaseContext() , "No More Images " + count , Toast.LENGTH_SHORT).show();
                }
                else if(e1.getX() > e2.getX()){

                        if(count < 0)
                            Toast.makeText(getBaseContext() , "No More Images " + count  , Toast.LENGTH_SHORT).show();
                        else {

                            imageView.setImageResource(images[count]);
                            count--;
                        }
                }
                return false;
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gd.onTouchEvent(event);
        sgd.onTouchEvent(event);
        imageView.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
