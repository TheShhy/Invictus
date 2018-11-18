package com.example.alisiyal.lab3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;

public class thirdActivity extends AppCompatActivity {
    EditText userField;
    EditText emailField;
    RadioButton male;
    RadioButton female;

    String username;
    String email;
    String gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Intent in = getIntent();


        //Getting data From Intent
        username = in.getStringExtra("username");
        email = in.getStringExtra("email");
        gender = in.getStringExtra("gender");
        /*
        male = findViewById(R.id.maleRB);
        female = findViewById(R.id.femaleRB);

        if(gender.equals("male")){
            male.setSelected(true);
        }else if(gender.equals("female")){
            female.setSelected(true);
        }
        */
        //Finding Views By Id
        userField = findViewById(R.id.username2);
        emailField = findViewById(R.id.email);

        //Setting Text to Text Fields
        userField.setText(username);
        emailField.setText(email);
    }
}
