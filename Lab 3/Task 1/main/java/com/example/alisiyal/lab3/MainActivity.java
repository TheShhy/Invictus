package com.example.alisiyal.lab3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Declaring variables
    Button signUp;
    EditText userName;
    EditText password;
    EditText email;
    EditText dateOfBirth;
    RadioButton male;
    RadioButton female;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        signUp = findViewById(R.id.signupButton);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = findViewById(R.id.usernameInput);
                password = findViewById(R.id.passwordInput);
                email = findViewById(R.id.emailInput);
                dateOfBirth = findViewById(R.id.dateofbirthInput);
                male = findViewById(R.id.maleRB);
                female = findViewById(R.id.femaleRB);

                //Creating the intent
                Intent in = new Intent(MainActivity.this, secondActivity.class);
                //Setting the data
                in.putExtra("username", userName.getText().toString());
                in.putExtra("password", password.getText().toString());
                in.putExtra("dateOfBirth", dateOfBirth.getText().toString());
                in.putExtra("email", email.getText().toString());

                if(male.isSelected()){
                    in.putExtra("gender", "male");
                }
                else if (female.isSelected()){
                    in.putExtra("gender", "female");
                }
                //Starting the activity
                Toast t = Toast.makeText(getApplicationContext(),"Registered",Toast.LENGTH_SHORT);
                t.show();

                //Starting the Activity
                startActivity(in);
            }
        });

    }
}
