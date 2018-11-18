package com.example.alisiyal.lab3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class secondActivity extends AppCompatActivity {
    EditText userField;
    EditText passField;
    String username;
    String password;
    String email;
    String dateOfBirth;
    String gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent in = getIntent();
        username = in.getStringExtra("username");
        password = in.getStringExtra("password");
        email = in.getStringExtra("email");
        gender = in.getStringExtra("gender");
        dateOfBirth = in.getStringExtra("dateOfBirth");

        userField = findViewById(R.id.username2);
        passField = findViewById(R.id.password2);

        Button login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.equals(userField.getText().toString()) && password.equals(passField.getText().toString())){
                    //Creating the intent
                    Intent in = new Intent(secondActivity.this, thirdActivity.class);

                    //Setting the data
                    in.putExtra("username", username);
                    in.putExtra("password", password);
                    in.putExtra("dateOfBirth", dateOfBirth);
                    in.putExtra("email", email);
                    in.putExtra("gender", gender);

                    //Starting the Activity
                    startActivity(in);
                }
            }
        });

    }
}
