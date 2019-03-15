package com.eszter.lab10;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    ProgressBar pb;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pb = findViewById(R.id.progressBar2);
        b = findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new Downloder().execute();
            }
        });
    }

    class Downloder extends AsyncTask<Void,Integer,Integer> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pb.setMax(100);
            Toast.makeText(getApplicationContext(),"updating",Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pb.setProgress(values[0]);

        }

        @Override
        protected Integer doInBackground(Void... voids) {
            for(int i=0;i<100;i++){
                publishProgress(i);
                try {
                    Thread.sleep(65);
                }catch (InterruptedException ie){
                    ie.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            Toast.makeText(getApplicationContext(),"Downloaded",Toast.LENGTH_LONG).show();
        }
    }
}
