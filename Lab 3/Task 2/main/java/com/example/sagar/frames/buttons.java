package com.example.sagar.frames;

import android.content.Intent;
import android.net.Uri;
import android.provider.CallLog;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class buttons extends AppCompatActivity {
    EditText text;
    Button b1;
    Button b2;
     Button b3;
     Button b4;
     Button b5;
     Button b6;
     Button b7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttons);
        text = findViewById(R.id.field);
        b1 = findViewById(R.id.call);
        b2 = findViewById(R.id.camera);
        b3 = findViewById(R.id.contact);
        b4 = findViewById(R.id.browser);
        b5 = findViewById(R.id.log);
        b6 = findViewById(R.id.gallery);
        b7 = findViewById(R.id.dail);
  /*
//open gallery
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("content://media/external/images/media/"));
        startActivity(i);
//perform CallLog open action
        Intent j = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("content://call_log/calls/1"));
        startActivity(j);
//perform browser
        Intent k = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("http://www.google.com/"));
        startActivity(k);
        */
//perform call
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Intent intent = new Intent(Intent.ACTION_CALL);
               intent.setData(Uri.parse("tel:"+ text.getText().toString()));
               startActivity(intent);
            }

        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
            }

        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("content://contacts/people/"));
                startActivity(i);

            }

        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http:"+text.getText().toString()));
                startActivity(i);

            }

        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent showCallLog = new Intent();
                showCallLog.setAction(Intent.ACTION_VIEW);
                showCallLog.setType(CallLog.Calls.CONTENT_TYPE);
                    startActivity(showCallLog);

            }

        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_VIEW);
               i.setData(Uri.parse("content://media/external/images/media/"));
                startActivity(i);

            }

        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:"+text.getText().toString()));
                startActivity(i);
            }

        });





    }
}

