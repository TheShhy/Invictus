package com.example.bilzz.getmsg3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {

    EditText editText;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editText =findViewById(R.id.editText);
        btn =findViewById(R.id.button2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String namevalue = editText.getText().toString();
                Intent intent = new Intent(Main2Activity.this, MainActivity.class );
                intent.putExtra("Name", namevalue);
                startActivity(intent);
            }
        });

    }
}