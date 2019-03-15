package com.eszter.lab11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.eszter.lab11.Room.Students;

public class Insert extends AppCompatActivity {
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        name = findViewById(R.id.studentName);
    }

    void save(View view){
        MainActivity.studentsViewModal.insert(new Students(name.getText().toString()));
        finish();
    }
}
